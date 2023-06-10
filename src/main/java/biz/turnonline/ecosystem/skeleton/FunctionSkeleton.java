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