package chat;

import java.net.*;
import java.util.*;

public class Groups {
    private String name;
    private int id;
    private int port;
    private String ip;
    private Map<String, String> map;
    private MulticastSocket ms;
    private InetAddress inetAddr;
    
    public Groups(int port, String ip, int id, MulticastSocket ms, InetAddress inetAddr){
        this.port=port;
        this.ip=ip;
        this.id=id;
        map = new HashMap<String, String>();
    }
    
    public int getID() {
        return id;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getIP() {
        return ip;
    }
    
    public void addClient(String ip, String nm){
        map.put(ip, nm);
        System.out.println("Successfully added");
    }
    
    public void deleteCliet(String ip){
        map.remove(ip);
        System.out.println("Successfully deleted");
    }
    
    public MulticastSocket getMs() {
        return ms;
    }

    public void setMs(MulticastSocket ms) {
        this.ms = ms;
    }

    public InetAddress getInetAddr() {
        return inetAddr;
    }

    public void setInetAddr(InetAddress inetAddr) {
        this.inetAddr = inetAddr;
    }
    
    public String getClients(){
        String res = map.toString();
        res = res.replace("{", "");
        res = res.replace("}", "");
        res = res.replace(" ", "");
        res = res.replace(",", "/");
        res = res.replace("=", "@");
        return res;
    }
}