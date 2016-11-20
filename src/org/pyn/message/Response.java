package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/1.
 */
public abstract class Response {
    public String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void decode(JSONObject jsonObject);
}
