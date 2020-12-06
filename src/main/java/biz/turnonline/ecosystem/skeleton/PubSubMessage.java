package biz.turnonline.ecosystem.skeleton;

import java.util.Map;

/**
 * Cloud Functions uses GSON to populate this object.
 * Field types/names are specified by Cloud Functions
 * Changing them may break your code!
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class PubSubMessage
{
    private String data;

    private Map<String, String> attributes;

    private String messageId;

    private String publishTime;

    public String getData()
    {
        return data;
    }

    public void setData( String data )
    {
        this.data = data;
    }

    public Map<String, String> getAttributes()
    {
        return attributes;
    }

    public void setAttributes( Map<String, String> attributes )
    {
        this.attributes = attributes;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageId( String messageId )
    {
        this.messageId = messageId;
    }

    public String getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime( String publishTime )
    {
        this.publishTime = publishTime;
    }
}
