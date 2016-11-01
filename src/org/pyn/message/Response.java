package org.pyn.message;

/**
 * Created by pyn on 2016/11/1.
 */
public abstract class Response {
    private String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
