package biz.turnonline.ecosystem.skeleton;

import com.google.cloud.functions.Context;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

/**
 * {@link FunctionSkeleton} unit testing.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class FunctionSkeletonTest
{
    private final String resourceAsJson = readString( "resource.json" );

    private AutoCloseable closeable;

    @InjectMocks
    private FunctionSkeleton tested;

    @Mock
    private Context context;

    @BeforeEach
    public void before()
    {
        closeable = MockitoAnnotations.openMocks( this );

        when( context.attributes() ).thenReturn( new HashMap<>() );
        when( context.eventId() ).thenReturn( "1646358556781447" );
        when( context.eventType() ).thenReturn( "google.pubsub.topic.publish" );
        when( context.resource() ).thenReturn( resourceAsJson );
        when( context.timestamp() ).thenReturn( "2020-12-06T07:00:22.236Z" );
    }

    @AfterEach
    public void releaseMocks() throws Exception
    {
        closeable.close();
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
