package org.pyn.message;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pyn on 2016/11/20.
 */
public class ResponseFactory {
    private interface newResponse {
        Response getResponse();
    }
    private Map<String,newResponse> typeResponse = new HashMap<String,newResponse>();

    public ResponseFactory() {
        typeResponse.put("LoginResponse", new newResponse() {
            @Override
            public Response getResponse() {
                return new LoginResponse();
            }
        });
        typeResponse.put("ChatResponse", new newResponse() {
            @Override
            public Response getResponse() {
                return new ChatResponse();
            }
        });
        typeResponse.put("AddFriResponse", new newResponse() {
            @Override
            public Response getResponse() {
                return new AddFriResponse();
            }
        });
        typeResponse.put("FriendsResponse", new newResponse() {
            @Override
            public Response getResponse() {
                return new FriendsResponse();
            }
        });
    }

    public Response decode(String type, JSONObject jsonObject) {
        Response response = typeResponse.get(type).getResponse();
        response.decode(jsonObject);
        return response;
    }
}
