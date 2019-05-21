package chat;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Receive extends Thread{
    JTextArea message;
    MulticastSocket mult_sock;
    OutputStream sos;
    String gid;
    String host;
    Socket socket;
    
    public Receive(JTextArea message, MulticastSocket mult_sock, Socket sock, String gid, String host){
        socket=sock;
        this.message=message;
        this.mult_sock=mult_sock;
        try {
            sos=socket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gid=gid;
        this.host=host;
    }
    
    public void run(){
        for(;;){
                byte[] buffer = new byte[1024];
                DatagramPacket pkt = new DatagramPacket(buffer, buffer.length);
            try {
                mult_sock.receive(pkt);
            } catch (IOException ex) {
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            }
                String mes = new String(pkt.getData(), pkt.getOffset(), pkt.getLength());
                System.out.println(mes);
                String[] msg = mes.split(" ");
                String command = msg[0];
                System.out.println(command);
                
                if(command.equals("LEAVE")){
                    System.out.println("Got LEAVE command from another client");
                    String[] d = msg[1].split("/");
                    String name = d[0];
                    String ip = d[1];
                    
                    if(!ip.equals(host)){
                        String txt = "<----"+name+" has left the chat"+"---->";
                        System.out.println(txt);
                        String req = "WHO "+gid;
                        try {
                            sos.write(req.getBytes());
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                            System.out.println("Cannot send WHO msgS");
                        }

                        System.out.println(txt);
                        message.append(txt+"\n");
                    }
                     
                }else if(command.equals("JOIN")){
                    System.out.println("Got JOIN command");
                    String[] d = msg[1].split("/");
                    String name = d[0];
                    String ip = d[1];
                    String txt = "<++++"+name+" has entered the chat"+"++++>";
                    System.out.println(txt);
                    if(!ip.equals(host)){
                        String req = "WHO " + gid;
                        try {
                            sos.write(req.getBytes());
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                            System.out.println("Cannot send WHO msgS");
                    }
                    
                        System.out.println(txt);
                        message.append(txt+"\n");
                        
                    }else{
                        System.out.println("Client with IP: "+ip+" joined");
                    }

                    
                }else if(command.equals("MSG")){
                    System.out.println("Got MSG command");
                    String[] d = msg[1].split("/");
                    String name = d[0];
                    String ip = d[1];
                    String text = d[2];
                    text=text.replace("|", " ");
                    System.out.println(text);
                    if(!ip.equals(host)){
                        String txt = name+"@"+pkt.getAddress().getHostAddress()+": "+text;
                        System.out.println("////////////////////////////////////////////////////////////");
                        System.out.println(txt);
                        System.out.println("////////////////////////////////////////////////////////////");

                        message.append(txt+"\n");//4444444444444444444444444444444444444444444444444
                        System.out.println(txt);
                        }else{
                            System.out.println("Message from: "+ip);
                        }
                }else{
                    
                }

            }
        }
    }
