package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/10/29.
 */
public class ChatRequest extends Request {
    private String toName;
    private String content;

    public ChatRequest(String toName, String content) {
        this.type = "ChatRequset";
        this.toName = toName;
        this.content = content;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("toName",toName);
        jsonObject.put("content",content);
        return jsonObject.toString().getBytes();
    }
}
