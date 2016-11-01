package org.pyn.message;

/**
 * Created by pyn on 2016/11/1.
 */
public abstract class Request {
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract byte[] encode();
}
