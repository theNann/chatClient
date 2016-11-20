package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/18.
 */
public class FriendsRequest extends Request{
    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "FriendsRequest");
        return jsonObject.toString().getBytes();
    }
}
