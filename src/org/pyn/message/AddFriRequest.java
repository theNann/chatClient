package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/3.
 */
public class AddFriRequest extends Request {
    private String name;
    public AddFriRequest() {
        this.type = "AddFriRequest";
    }
    public AddFriRequest(String name) {
        this.name = name;
        this.type = "AddFriRequest";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("name", name);
        return jsonObject.toString().getBytes();
    }
}
