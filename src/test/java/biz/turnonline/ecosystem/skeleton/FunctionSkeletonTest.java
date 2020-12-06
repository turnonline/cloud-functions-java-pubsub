package biz.turnonline.ecosystem.skeleton;

import com.google.cloud.functions.Context;
import com.google.gson.Gson;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * {@link FunctionSkeleton} unit testing.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class FunctionSkeletonTest
{
    private final String resourceAsJson = readString( "resource.json" );

    @Tested
    private FunctionSkeleton tested;

    @Mocked
    private Context context;

    @BeforeEach
    public void before()
    {
        new Expectations()
        {
            {
                context.attributes();
                result = new HashMap<>();

                context.eventId();
                result = "1646358556781447";

                context.eventType();
                result = "google.pubsub.topic.publish";

                context.resource();
                result = resourceAsJson;

                context.timestamp();
                result = "2020-12-06T07:00:22.236Z";
            }
        };
    }

    @Test
    public void validInputBody()
    {
        String json = readString( "pubsub-message.json" );
        PubSubMessage message = new Gson().fromJson( json, PubSubMessage.class );

        tested.accept( message, context );
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
