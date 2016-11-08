package org.pyn;
import org.pyn.message.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pyn on 2016/10/20.
 */
public class Receiver implements Runnable{
    private Socket socket = null;
    LinkedList<Response> queue = new LinkedList<>();
    Proto proto = new Proto();
    private Lock lock = new ReentrantLock();

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    private void push(Response m) {
        lock.lock();
        queue.add(m);
        lock.unlock();
    }

    public Response pop() {
        Response resp = null;
        lock.lock();
        if(!queue.isEmpty()) {
            resp = queue.pop();
        }
        lock.unlock();
        return resp;
    }

    @Override
    public void run() {
        boolean ok = true;
        while(ok) {
            try {
                Response m = doReceiveMsg();
                push(m);
            } catch (IOException e) {
                e.printStackTrace();
                ok = false;
            }
        }
    }

    private Response doReceiveMsg() throws IOException {
        InputStream input = socket.getInputStream();

        byte[] in = new byte[65538];
        int len = 0;
        int curlen = 0;

        while(curlen < 2) {
            curlen += input.read(in, curlen, 2 - curlen);
        }
        len = byteToNum(in);
        curlen = 0;
        while(curlen < len) {
            curlen += input.read(in,curlen+2,len - curlen);
        }
        Response m = null;
        try {
            m = proto.decode(in, len);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return m;
    }

    private int byteToNum(byte[] b) {
        int len = 0;
        len = (int)b[1] << 8 | b[0];
        return len;
    }
}
