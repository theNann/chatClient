package org.pyn;

import org.pyn.message.Request;
import org.pyn.message.Response;

import java.io.*;
import java.net.Socket;

/**
 * Created by pyn on 2016/10/20.
 */

public class Connect{
    private Socket socket;
    private Sender sender = new Sender(socket);
    private Receiver receiver = new Receiver(socket);

    public void start() throws IOException {
        socket = new Socket("127.0.0.1",8090);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        Thread senderThread = new Thread(sender);
        senderThread.start();
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
    }

    public void write(Request req) {
        sender.push(req);
    }

    public Response read() {
        return receiver.pop();
    }
}
