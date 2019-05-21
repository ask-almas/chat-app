package chat;

import java.io.*;
import java.net.*;

public class Client1 extends javax.swing.JFrame {

    public Client1() throws Exception{
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jTextField3 = new javax.swing.JTextField();
        exit = new javax.swing.JButton();
        sendMessage = new javax.swing.JButton();
        leave = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        messageSend = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        join = new javax.swing.JButton();
        Group_ID = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        members = new javax.swing.JList();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setMinimumSize(new java.awt.Dimension(630, 430));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField3.setEditable(false);
        jTextField3.setText("    Members");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 90, -1));

        exit.setText("EXIT");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitMouseReleased(evt);
            }
        });
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 100, 30));

        sendMessage.setText("Send");
        sendMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sendMessageMouseReleased(evt);
            }
        });
        getContentPane().add(sendMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 80, 30));

        leave.setText("Leave");
        leave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                leaveMouseReleased(evt);
            }
        });
        getContentPane().add(leave, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 70, -1));

        message.setEditable(false);
        message.setColumns(20);
        message.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        message.setRows(8);
        message.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                messageInputMethodTextChanged(evt);
            }
        });
        jScrollPane6.setViewportView(message);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 360, 290));

        messageSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageSendActionPerformed(evt);
            }
        });
        getContentPane().add(messageSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 260, 30));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 180, -1));

        join.setText("Join");
        join.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                joinMouseReleased(evt);
            }
        });
        getContentPane().add(join, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 70, -1));

        getContentPane().add(Group_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 80, -1));

        jScrollPane3.setViewportView(members);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 180, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void messageInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_messageInputMethodTextChanged
    
    }//GEN-LAST:event_messageInputMethodTextChanged

    private void exitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseReleased
        if(!connected){
            System.exit(0);
        }
    }//GEN-LAST:event_exitMouseReleased

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void messageSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageSendActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_messageSendActionPerformed

    
    
    private void sendMessageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMessageMouseReleased
        String msg = messageSend.getText();
        if (connected) {
            if (!msg.equals("")) {
                String t = "You sent: "+msg+"\n";
                message.append(t);
                
//                message.setText(message.getText()  + "You send: " + msg+ "\n");//111111111111111111111111111
                
                msg = msg.replace(" ", "|");
                msg = "MSG " + myName + "/" + host + "/" + msg;

                System.out.println("////////////////////////////////////////////////////////////");
                System.out.println(msg);
                System.out.println("////////////////////////////////////////////////////////////");
                
                DatagramPacket txt = new DatagramPacket(msg.getBytes(), msg.length(), group, mult_port);
                try {
                    mult_sock.send(txt);
                    System.out.println("MSG sent");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Cannot send MSG to the Chat");
                }
                messageSend.setText("");
            }
        }
    }//GEN-LAST:event_sendMessageMouseReleased

    private void leaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveMouseReleased
        if (connected) {
            try {
                mult_sock.leaveGroup(group);
                connected = !connected;
                name.setEditable(true);
                Group_ID.setVisible(true);
                request = "LEAVE " + gid + "/" + host + "/" + myName;
                System.out.println(request);
                message.setText("");
                try {
                    sos.write(request.getBytes());
                    System.out.println("Leave msg sent from client");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Cannot send LEAVE MSG from client");
                }

                members.setModel(new javax.swing.AbstractListModel() {String[] mm = {};
                    public int getSize() {return mm.length;}
                    public Object getElementAt(int i) {return mm[i];}
                });
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Leave error here");
            }
        }
    }//GEN-LAST:event_leaveMouseReleased

    private void joinMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_joinMouseReleased
        if(!connected){
            if(!name.getText().equals("")){
                gid = Group_ID.getSelectedItem().toString();
                myName=name.getText();
                name.setEditable(false);
                Group_ID.setVisible(false);
                myName=myName.replace(" ", "-");
                request="JOIN "+gid+"/"+myName+"/"+host;
                System.out.println(request);
                try {
                    sos.write(request.getBytes());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("CLIENT Cannot join to the group");
                }
            }
        }
    }//GEN-LAST:event_joinMouseReleased

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    static javax.swing.JComboBox Group_ID;
    javax.swing.JButton exit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    javax.swing.JTextField jTextField3;
    private javax.swing.JTextPane jTextPane1;
    javax.swing.JButton join;
    javax.swing.JButton leave;
    static javax.swing.JList members;
    static javax.swing.JTextArea message;
    javax.swing.JTextField messageSend;
    static javax.swing.JTextField name;
    javax.swing.JButton sendMessage;
    // End of variables declaration//GEN-END:variables
    
    static String host = "127.0.0.2";
    static int port = 0;
    String line;
    static BufferedReader br;
    static OutputStream sos;
    static InputStream sis;
    static Socket sock;
    static byte buf[];
    static int n;
    static String request;
    static String response;
    static MulticastSocket mult_sock;
    static InetAddress group;
    static String command;
    static int mult_port;
    static String mult_ip;
    static String gid;
    static String myName;
    static boolean connected=false;
    public static void main(String[] argv) {     
        try{
            new Client1().setVisible(true);
        }catch(Exception e){
            System.out.println("Cannot make visible");
        }
        switch (argv.length) {
            case 0:
                port = 8080;
                break;
            case 1:
                port = (new Integer(argv[0])).intValue();
                break;
            case 2:
                host = argv[0];
                port = (new Integer(argv[1])).intValue();
                break;
            default:
                System.out.println("Usage: client [host] port");
                System.exit(-1);
                break;
        }

        br = new BufferedReader(new InputStreamReader(System.in));
        buf = new byte[1024];

        try {
            sock = new Socket(InetAddress.getByName(host), port);
            sos = sock.getOutputStream();
            sis = sock.getInputStream();
        } catch (UnknownHostException uhe) {
            System.out.println("client: " + host + " cannot be resolved.");
            System.exit(-1);
        } catch (IOException ioe) {
            System.out.println("client: cannot initialize socket.");
            System.exit(-1);
        }
        for (;;) {
            try {
                n = sis.read(buf);
                if (n <= 0){
                    break;
                }
                response = new String(buf, 0, n);
                System.out.println("Server sent: " + response);
                final String[] lines = response.split(" ");
                command = lines[0];
                
                if(command.equals("GROUPS")){
                    System.out.println("Got GROUPS command");
                    String[] num = lines[1].split("/");
                    Group_ID.setModel(new javax.swing.DefaultComboBoxModel(num));
                    
                }else if(command.equals("GROUP")){
                    System.out.println("Got GROUP command");
                    mult_port = Integer.parseInt(lines[1].split("/")[0]);
                    mult_ip = lines[1].split("/")[1];
                    mult_sock = new MulticastSocket(mult_port);
                    group = InetAddress.getByName(mult_ip);
                    mult_sock.joinGroup(group);
                    System.out.println("joined");
                    connected=true;
                    
                    request="WHO "+gid;
                    System.out.println(request);
                    try {
                        sos.write(request.getBytes());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("Cannot send WHO msgS");
                    }
                    try {
                        Receive r = new Receive(message, mult_sock, sock, gid, host);
                        r.start();
                    } catch (Exception ex) {
                        System.out.println("Cannot start Receive Thread");
                        System.out.println(ex.getMessage());
                    }
                }else if(command.equals("MEMBERS")){
                    try{
                        System.out.println(lines[1]);
                        members.setModel(new javax.swing.AbstractListModel() 
                        { String[] list = lines[1].split("/") ; 
                        public int getSize() { return list.length; } 
                        public Object getElementAt(int i) { return list[i]; } 
                        });
                    }catch(Exception e){
                        
                    }
                }else{
                    
                }
            } catch (IOException rwe) {
                System.out.println("client: I/O error.");
                System.out.println(rwe.getMessage());
            }
        }
    }
}