package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/3.
 */
public class AddFriResponse extends Response{
    private String result;
    private String name;

    public AddFriResponse() {
        this.type = "AddFriResponse";
    }

    public String getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AddFriResponse{" +
                "result='" + result + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public void decode(JSONObject jsonObject) {
        setName((String)jsonObject.get("name"));
        setResult((String)jsonObject.get("result"));
    }
}
