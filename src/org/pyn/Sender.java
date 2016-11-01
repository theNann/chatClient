package org.pyn;

import org.pyn.message.Request;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pyn on 2016/10/20.
 */
public class Sender implements Runnable{

    private Socket socket;
    private Proto proto = new Proto();
    private LinkedList<Request> queue = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public Sender(Socket socket) {
        this.socket = socket;
    }

    public void push(Request req) {
        lock.lock();
        queue.add(req);
        cond.signalAll();
        lock.unlock();
    }

    @Override
    public void run() {
        boolean ok = true;
        while(ok) {
            try {
                lock.lock();
                if(!queue.isEmpty()) {
                    Request req = queue.pop();
                    doSendMsg(req);
                } else {
                    try {
                        cond.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
                } catch (IOException e) {
                ok = false;
            }
        }
    }

    private void doSendMsg(Request req) throws IOException {
        OutputStream output = socket.getOutputStream();
        byte[] b = proto.encode(req);
        output.write(b);
    }
}
