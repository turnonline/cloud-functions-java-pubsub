package biz.turnonline.ecosystem.skeleton;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Entry point that listens for Pub/Sub message.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class FunctionSkeleton
        implements BackgroundFunction<PubSubMessage>
{
    private static final Logger LOGGER = LoggerFactory.getLogger( FunctionSkeleton.class.getName() );

    private final Gson gson;

    public FunctionSkeleton()
    {
        this.gson = new Gson();
    }

    @Override
    public void accept( PubSubMessage message, Context context )
    {
        LOGGER.info( context.attributes().toString() );
        LOGGER.info( context.eventId() );
        LOGGER.info( context.eventType() );
        LOGGER.info( context.resource() );
        LOGGER.info( context.timestamp() );

        String name;
        if ( message != null && message.getData() != null )
        {
            name = new String( Base64.getDecoder()
                    .decode( message
                            .getData()
                            .getBytes( StandardCharsets.UTF_8 ) ), StandardCharsets.UTF_8 );

            LOGGER.info( name );
        }
    }
}