package com.rccf.model.result;

import com.rccf.interfaces.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by greatland on 17/7/19.
 */
@XStreamAlias("xml")
public class WeixinMessage implements Serializable{

    /**
     * ToUserName
     */
    @XStreamAlias("ToUserName")
    @XStreamCDATA
    protected String toUserName;

    /**
     * FromUserName
     */
    @XStreamAlias("FromUserName")
    @XStreamCDATA
    protected String fromUserName;


    /**
     * CreateTime
     */
    @XStreamAlias("CreateTime")
    protected long createTime;

    /**
     * MsgType
     */
    @XStreamAlias("MsgType")
    @XStreamCDATA
    protected String msgType;


    @XStreamAlias("Event")
    @XStreamCDATA
    protected String event;


    @XStreamAlias("EventKey")
    @XStreamCDATA
    protected String eventKey;

    @XStreamAlias("Ticket")
    @XStreamCDATA
    protected String ticket;


    /**
     * Content
     */
    @XStreamAlias("Content")
    @XStreamCDATA
    protected String content;


    /**
     * MsgId
     */
    @XStreamAlias("MsgId")
    protected String MsgId;


    /**
     * PicUrl
     */
    @XStreamAlias("PicUrl")
    @XStreamCDATA
    protected String picurl;


    /**
     * MediaId
     */
    @XStreamAlias("MediaId")
    @XStreamCDATA
    protected String MediaId;



    /**
     *  Title
     */
    @XStreamAlias("Title")
    @XStreamCDATA
    protected String title;




    /**
     *  Description
     */
    @XStreamAlias("Description")
    @XStreamCDATA
    protected String description;

    /**
     *  Url
     */
    @XStreamAlias("Url")
    @XStreamCDATA
    protected String url;

    /**
     * Url
     */
    @XStreamAlias("MenuId")
    @XStreamCDATA
    protected String menu_id;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }


    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }


    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
