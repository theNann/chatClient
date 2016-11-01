package org.pyn;
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
        System.out.println("请输入您的昵称：");
        String name = sc.nextLine();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(name);
        conn.write(loginRequest);
        boolean is_login;
        while (true) {
            LoginResponse resp = (LoginResponse) conn.read();
            if(resp != null) {
                if(resp.getResult().compareTo("OK") == 0) {
                    is_login = true;
                } else {
                    is_login = false;
                }
                break;
            }
        }
        if(!is_login) {
            System.out.println("登录失败");
        } else {
            System.out.println("登录成功");
//            while (sc.hasNext()) {
//                String to_name = sc.nextLine();
//                String content = sc.nextLine();
//                conn.write(new ChatRequest(to_name,content));
//            }
        }
    }
}
