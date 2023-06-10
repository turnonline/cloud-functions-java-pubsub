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

import com.google.cloud.functions.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Cloud Functions Pub/Sub message structure.
 */
public class PubSubMessage
{
    private String data;

    private Map<String, String> attributes;

    private String messageId;

    private String publishTime;

    /**
     * Returns the data payload published to the topic. It's stored as a base64-encoded string.
     *
     * @return the base64-encoded string
     */
    public String getData()
    {
        return data;
    }

    @SuppressWarnings( "unused" )
    public void setData( String data )
    {
        this.data = data;
    }

    /**
     * Returns attributes provided by the publisher while message was published.
     *
     * @return the map of the values
     */
    public Map<String, String> getAttributes()
    {
        return attributes == null ? new HashMap<>() : attributes;
    }

    @SuppressWarnings( "unused" )
    public void setAttributes( Map<String, String> attributes )
    {
        this.attributes = attributes;
    }

    /**
     * Use {@link Context#eventId()} instead.
     *
     * @return <code>null</code>
     */
    @SuppressWarnings( "unused" )
    public String getMessageId()
    {
        return messageId;
    }

    @SuppressWarnings( "unused" )
    public void setMessageId( String messageId )
    {
        this.messageId = messageId;
    }

    /**
     * Use {@link Context#timestamp()} instead.
     *
     * @return <code>null</code>
     */
    @SuppressWarnings( "unused" )
    public String getPublishTime()
    {
        return publishTime;
    }

    @SuppressWarnings( "unused" )
    public void setPublishTime( String publishTime )
    {
        this.publishTime = publishTime;
    }
}
