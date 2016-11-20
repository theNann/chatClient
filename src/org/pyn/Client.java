package org.pyn;
import org.pyn.message.*;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by pyn on 2016/10/20.
 */
public class Client {
    private Connect conn;
    public Client(Connect c) {
        conn = c;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean is_login = false;
        while(!is_login) {
            System.out.println("请输入您的昵称：");
            String name = sc.nextLine();
            LoginRequest loginRequest = new LoginRequest(name);
            conn.write(loginRequest);

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LoginResponse resp = (LoginResponse) conn.read();
                if(resp == null) {
                    continue;
                } else if (resp.getResult().compareTo("OK") == 0) {
                    is_login = true;
                    System.out.println("登录成功...");
                    break;
                } else {
                    System.out.println("登录失败...");
                    break;
                }
            }
        }

        ReadFromControl readFromControl = new ReadFromControl();
        Thread readThread = new Thread(readFromControl);
        readThread.start();

        while (true) {
            Request request = readFromControl.getRequest();
            if(request != null) {
               conn.write(request);
            }
            Response response = conn.read();
            if(response != null) {
                if(response.type.equals("AddFriResponse")) {
                    AddFriResponse addFriResponse = (AddFriResponse) response;
                    System.out.println(addFriResponse);
                    if(addFriResponse.getResult().equals("on-yes")) {
                        System.out.println("加好友消息提示：" + addFriResponse.getName() + "愿意加您为好友");
                    } else if(addFriResponse.getResult().equals("off")){
                        System.out.println("加好友消息提示：" + addFriResponse.getName() + "不在线");
                    } else if(addFriResponse.getResult().equals("on-no")) {
                        System.out.println("加好友消息提示：" + addFriResponse.getName() + "拒绝加您为好友");
                    }
                } else if (response.type.equals("ChatResponse")) {
                    ChatResponse chatResponse = (ChatResponse) response;
                    String fromName = chatResponse.getFromName();
                    String content = chatResponse.getContent();
                    System.out.println("新的消息：");
                    if(chatResponse.isSuccess()) {
                        System.out.println(fromName + ": " + content);
                    } else {
                        System.out.println(content);
                    }
                } else if(response.type.equals("FriendsResponse")) {
                    FriendsResponse friendsResponse = (FriendsResponse) response;
                    LinkedList<String> friendQueue = friendsResponse.getFriendQueue();
                    if(friendQueue.size() == 0) {
                        System.out.println("好友列表为空...");
                    } else {
                        for (int i = 0; i < friendQueue.size(); i++) {
                            System.out.println(friendQueue.get(i));
                        }
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // 主线程负责发送消息，服务器已成功将回应消息发送过来，接下来处理如何接受消息并显示出来

        }
    }
}
