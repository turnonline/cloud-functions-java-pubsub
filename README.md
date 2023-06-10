# Google Cloud Functions Java Pub/Sub - Serverless Function Template

[cloudevents.io](https://cloudevents.io) based Google Cloud Functions - Java Pub/Sub skeleton

## Function Example

Cloud Pub/Sub (2nd Gen),
[more details here](https://cloud.google.com/functions/docs/samples/functions-cloudevent-pubsub).

```java
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
        final CloudEventData data = event.getData();
        if ( data == null )
        {
            LOGGER.warn( "Event data are missing" );
            return;
        }

        String rawBody = new String( data.toBytes(), StandardCharsets.UTF_8 );
        PubSubBody body = gson.fromJson( rawBody, PubSubBody.class );
        String payload = new String( Base64
                .getDecoder()
                .decode( body.getMessage().getData() ), StandardCharsets.UTF_8 );

        LOGGER.info( payload );
    }
}
```

