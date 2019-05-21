package chat;

import java.io.*;
import java.net.*;


public class Handler extends Thread{
    BufferedReader br;
    Socket s;
    int n;
    OutputStream sos = null;
    InputStream sis = null;
    byte buf[];
    String request;
    String response;
    String command;
    Queue<Groups> q;
    Groups temp;
    
    public Handler(Socket sock, Queue<Groups> q) throws IOException {
        s = sock;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        sos = s.getOutputStream();
        sis = s.getInputStream();
        
        this.q=q;
        
        response = "GROUPS ";
        int size = q.getSize();

        for (int i = 0; i < size; i++) {

            try {
                Groups tp = q.dequeue();
                response += tp.getID() + "/";
                q.enqueue(tp);
            } catch (Exception ex) {

            }
        }
        try {
            sos.write(response.getBytes());
        } catch (IOException ex) {
            System.out.println("Cannot send response GROUPS");
        }
        response = "";
    }
    
    public void run() {
        
        for (;;) {
            buf = new byte[1024];
            try {
                n = sis.read(buf);
                if (n <= 0){
                    break;
                }
                request = new String(buf, 0, n);
                System.out.println("Client sent: " + request);
                
                String[] lines = request.split(" ");
                command = lines[0];
                
                if(command.equals("JOIN")){
                    System.out.println("Got JOIN command");
                    String[] data = lines[1].split("/");
                    int gid = Integer.parseInt(data[0]);
                    System.out.println("Want to connect to Group "+gid);
                    int s = q.getSize();
                    
                    for(int i=0; i<s; i++){
                        temp = q.dequeue();
                        q.enqueue(temp);
                        if(gid==temp.getID()){
                            
                            int gport = temp.getPort();
                            String gip = temp.getIP();
                            response = "GROUP "+gport+"/"+gip;
                            System.out.println(response);
                            sos.write(response.getBytes());
                            System.out.println("msg sent");
                            
                            System.out.println(data[1]+" "+data[2]);
                            temp.addClient(data[2], data[1]);
                            System.out.println("client added");
                            System.out.println(temp.getClients());
                            
                            String msg = "JOIN "+data[1]+"/"+data[2];
                            System.out.println(msg);
                            DatagramPacket txt = new DatagramPacket(msg.getBytes(), msg.length(),
                                    temp.getInetAddr(), temp.getPort());
                            
                            try {
                                temp.getMs().send(txt);
                                System.out.println("Join message sent");
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Exception ");
                            }
                        }   
                    }
                }else if(command.equals("LEAVE")){
                    System.out.println("Got LEAVE command");
                    String[] d = lines[1].split("/");
                    int gid = Integer.parseInt(d[0]);
                    System.out.println(gid);
                    String ip = d[1];
                    System.out.println(ip);
                    String myName = d[2];
                    System.out.println(myName);
                    
                    int n = q.getSize();
                    System.out.println(n);
                    
                    for(int i=0; i<n; i++){
                        temp = q.dequeue();
                        q.enqueue(temp);
                        if(gid==temp.getID()){
                            System.out.println("Found");
                            temp.deleteCliet(ip);
                            System.out.println("Client deleted");
                            System.out.println(temp.getClients());
                            
                            String msg = "LEAVE "+myName+"/"+ip;
                            DatagramPacket txt = new DatagramPacket(msg.getBytes(), msg.length(), 
                                    temp.getInetAddr(), temp.getPort());
                            System.out.println(msg);
                            try {
                                temp.getMs().send(txt);
                                System.out.println("Leave message sent to the port: "+temp.getPort());
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Cannot send LEAVE message");
                            }
                        }
                    }
                }else if(command.equals("WHO")){
                    int gid = Integer.parseInt(lines[1]);
                    int n = q.getSize();
                    
                    for(int i=0; i<n; i++){
                        temp = q.dequeue();
                        q.enqueue(temp);
                        if(gid==temp.getID()){
                            System.out.println("Found Groups");
                            response = "MEMBERS "+temp.getClients();
                            System.out.println(response);
                            sos.write(response.getBytes());
                            System.out.println("MEMBERS msg sent");
                        }
                    }
                }
            } catch (IOException ex) {
//                System.out.println("UNKNOWN Exception 1");
//                System.out.println(ex.getMessage());
            } catch (Exception ex) {
//                System.out.println("UNKNOWN Exception 1");
//                System.out.println(ex.getMessage());
            }
        }
    }
}
