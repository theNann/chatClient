package org.pyn;

import org.json.JSONArray;
import org.json.JSONObject;
import org.pyn.message.*;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

/**
 * Created by pyn on 2016/10/20.
 */

public class Proto {
    public byte[] encode(Request req) {
        byte[] str_b = req.encode();
        int len = str_b.length;
        byte[] buffer = new byte[len+2];
        byte[] len_b = numToByte(len);
        buffer[0] = len_b[0];
        buffer[1] = len_b[1];

        for(int i = 0; i < len; i++) {
            buffer[i+2] = str_b[i];
        }
        return buffer;
    }

    public Response decode(byte[] b, int len) throws UnsupportedEncodingException {
        String s = new String(b, 2, len, "UTF-8");
        JSONObject jsonObject = new JSONObject(s);
        String type = (String)jsonObject.get("type");
        ResponseFactory factory = new ResponseFactory();
        return factory.decode(type,jsonObject);
    }

    private byte[] numToByte(int num) {
        byte[] b = new byte[2];
        b[0] = (byte) (num & 255);
        b[1] = (byte) (num >> 8 & 255);
        return b;
    }
}
