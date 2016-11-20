package org.pyn.message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by pyn on 2016/11/18.
 */
public class FriendsResponse extends Response{
    private LinkedList<String> friendQueue;

    public FriendsResponse() {
        this.type = "FriendsResponse";
    }

    public LinkedList<String> getFriendQueue() {
        return friendQueue;
    }

    public void setFriendQueue(LinkedList<String> friendQueue) {
        this.friendQueue = friendQueue;
    }

    @Override
    public String toString() {
        return "FriendsResponse{" +
                "friendQueue=" + friendQueue +
                '}';
    }

    @Override
    public void decode(JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray)jsonObject.get("friendQueue");
        LinkedList<String> friendQueue = new LinkedList<String>();
        for(int i = 0; i < jsonArray.length(); i++) {
            friendQueue.add((String)jsonArray.get(i));
        }
        setFriendQueue(friendQueue);
    }
}
