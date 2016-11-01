package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/10/29.
 */
public class LoginRequest extends Request {
    private String name;

    public LoginRequest(String name) {
        this.type = "LoginRequest";
        this.name = name;
    }
    public LoginRequest() {

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
        jsonObject.put("name",name);
        return jsonObject.toString().getBytes();
    }
}
