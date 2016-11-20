package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/10/29.
 */
public class ChatResponse extends Response{
    private String fromName;
    private String content;
    private boolean success;
    public ChatResponse() {
        this.type = "ChatResponse";
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ChatResponse{" +
                "fromName='" + fromName + '\'' +
                ", content='" + content + '\'' +
                ", type-'" + type + '\'' +
                '}';
    }

    @Override
    public void decode(JSONObject jsonObject) {
        setSuccess((boolean)jsonObject.get("success"));
        setFromName((String)jsonObject.get("fromName"));
        setContent((String)jsonObject.get("content"));
    }
}
