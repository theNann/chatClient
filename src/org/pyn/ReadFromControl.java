package org.pyn;

import org.pyn.message.AddFriRequest;
import org.pyn.message.ChatRequest;
import org.pyn.message.FriendsRequest;
import org.pyn.message.Request;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by pyn on 2016/11/17.
 */
public class ReadFromControl implements Runnable{

    LinkedList<Request> queue = new LinkedList<>();
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择：");
        System.out.println("1 : 加好友");
        System.out.println("2 : 聊天");
        System.out.println("3 : 查看好友列表");
        while(sc.hasNext()) {
            int option = sc.nextInt();
            if(option == 1) {
                System.out.println("请输入好友昵称：");
                String name = sc.next();
                System.out.println(name);
                queue.add(new AddFriRequest(name));
            } else if(option == 2){
                System.out.println("请输入好友昵称及消息：");
                String to_name = sc.next();
                String content = sc.next();
                queue.add(new ChatRequest(to_name,content));
            } else if(option == 3) {
                System.out.println("好友列表如下：");
                queue.add(new FriendsRequest());
            }
        }
    }

    public Request getRequest() {
        if(queue.isEmpty()) {
            return null;
        } else {
            return queue.pop();
        }
    }
}
