package org.pyn;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Connect conn = new Connect();
        try {
            conn.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Client client = new Client(conn);
        client.start();
    }
}

