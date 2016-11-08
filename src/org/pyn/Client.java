package org.pyn;
import org.pyn.message.AddFriRequest;
import org.pyn.message.ChatRequest;
import org.pyn.message.LoginRequest;
import org.pyn.message.LoginResponse;

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


        System.out.println("请选择：");
        System.out.println("1 : 加好友");
        System.out.println("2 : 聊天");
        while (sc.hasNext()) {
            int option = sc.nextInt();
            if(option == 1) {
                System.out.println("请输入好友昵称：");
                String name = sc.nextLine();
                conn.write(new AddFriRequest(name));
            } else {
                System.out.println("请输入好友昵称及消息：");
                String to_name = sc.nextLine();
                String content = sc.nextLine();
                conn.write(new ChatRequest(to_name,content));
            }
        }

        // 主线程负责发送消息，服务器已成功将回应消息发送过来，接下来处理如何接受消息并显示出来
    }
}
