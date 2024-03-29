/*
 * Copyright (c) 2023 TurnOnline.biz s.r.o. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.skeleton;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Base64;
import java.util.stream.Collectors;

/**
 * {@link FunctionSkeleton} unit testing.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@ExtendWith( MockitoExtension.class )
public class FunctionSkeletonTest
{
    @InjectMocks
    private FunctionSkeleton tested;

    @Test
    public void validInputBody() throws Exception
    {
        String json = readString( "pubsub-payload.json" );
        String encodedJson = Base64.getEncoder().encodeToString( json.getBytes() );
        String encodedData = "{\"message\": { \"data\": \"" + encodedJson + "\"} }";

        CloudEvent event = CloudEventBuilder.v1()
                .withId( "1" )
                .withType( "pubsub.message" )
                .withSource( URI.create( "https://cloudevents.io" ) )
                .withData( encodedData.getBytes() )
                .build();
        tested.accept( event );
    }

    /**
     * Reads the content of the file in the same package as this test and converts it into a string.
     *
     * @param filename the file name to be read
     * @return the string content of the file
     */
    private String readString( String filename )
    {
        InputStream stream = FunctionSkeletonTest.class.getResourceAsStream( filename );
        if ( stream == null )
        {
            throw new IllegalArgumentException( filename + " not found" );
        }
        return new BufferedReader( new InputStreamReader( stream ) )
                .lines()
                .collect( Collectors.joining( System.lineSeparator() ) );
    }
}
