package org.pyn.message;

/**
 * Created by pyn on 2016/11/3.
 */
public class AddFriResponse extends Response{
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AddFriResponse{" +
                "result='" + result + '\'' +
                '}';
    }
}
