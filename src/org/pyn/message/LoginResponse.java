package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/1.
 */
public class LoginResponse extends Response{
    private String result;

    public LoginResponse() {
        this.type = "LoginResponse";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "LoginResponse{" +
                "result='" + result + '\'' +
                "type="+ type + '}';
    }

    @Override
    public void decode(JSONObject jsonObject) {
        setResult((String)jsonObject.get("result"));
    }
}
