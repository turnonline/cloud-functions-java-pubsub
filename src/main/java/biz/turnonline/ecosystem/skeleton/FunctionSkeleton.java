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

import com.google.cloud.functions.CloudEventsFunction;
import com.google.gson.Gson;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Entry point that listens for Pub/Sub message.
 * See <a href="https://cloudevents.io/">A specification for describing event data in a common way</a>.
 */
public class FunctionSkeleton
        implements CloudEventsFunction
{
    private static final Logger LOGGER = LoggerFactory.getLogger( FunctionSkeleton.class );

    private final Gson gson;

    public FunctionSkeleton()
    {
        this.gson = new Gson();
    }

    @Override
    public void accept( CloudEvent event ) throws Exception
    {
        CloudEventData data = event.getData();
        if ( data == null )
        {
            LOGGER.warn( "Event's data is not provided" );
            return;
        }

        String rawBody = new String( data.toBytes(), StandardCharsets.UTF_8 );
        PubSubBody body = gson.fromJson( rawBody, PubSubBody.class );
        byte[] decodedData = Base64.getDecoder().decode( body.getMessage().getData() );
        String payload = new String( decodedData, StandardCharsets.UTF_8 );

        LOGGER.info( payload );
    }
}