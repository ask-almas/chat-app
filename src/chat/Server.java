package chat;

import java.io.*;
import java.net.*;

public class Server {

    int port = 0;
    String request;
    ServerSocket ss_post = null;
    Socket sock = null;
    InetAddress group1;
    MulticastSocket ms1;    
    InetAddress group2;
    MulticastSocket ms2;
    Groups gr1;
    Groups gr2;
    Queue<Groups> q;

    public static void main(String argv[]) {
        new Server(argv);
    }

    public Server(String argv[]) {
        switch (argv.length) {
            case 0:
                port = 8080;
                break;
            case 1:
                port = (new Integer(argv[0])).intValue();
                break;
            default:
                System.out.println("Usage: server port");
                System.exit(-1);
                break;
        }
        gr1 = new Groups(7474, "227.3.7.4", 1, null, null);
        gr2 = new Groups(7575, "227.3.7.5", 2, null, null);
        q = new LinkedListQueue();
        q.enqueue(gr1);
        q.enqueue(gr2);
        
        try {
            ss_post = new ServerSocket(port);
            
            ms1 = new MulticastSocket(gr1.getPort());
            group1 = InetAddress.getByName(gr1.getIP());
            ms1.joinGroup(group1);
            gr1.setInetAddr(group1);
            gr1.setMs(ms1);
            
            ms2 = new MulticastSocket(gr2.getPort());
            group2 = InetAddress.getByName(gr2.getIP());
            ms2.joinGroup(group2);
            gr2.setInetAddr(group2);
            gr2.setMs(ms2);
            
            
        } catch (IOException ioe) {
            System.out.println("server: cannot initialize socket.");
            System.exit(-1);
        }
        
        server_main_loop();
    }
    
    private void server_main_loop() {
        for (;;) {
            try {
                System.out.println("I am waiting for a client");
                sock = ss_post.accept();
                System.out.println("I got one");
                Handler handler = new Handler(sock, q);
                handler.start();
            } catch (IOException rwe) {
                
            }
        }
    }
}