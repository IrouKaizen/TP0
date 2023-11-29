/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author _irk
 */
public class Server implements Runnable{
    
    List<Chat> listClients = new ArrayList<>();
    public static void main(String[] args) throws IOException {
         new Thread(new Server()).start(); 
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Démarrage du serveur");
            
            Socket s = ss.accept();
            //Lecture de données
                 

                 String pseudo = null;
                 Chat c = new Chat(s,pseudo);
                    c.start();
                    listClients.add(c);
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class Chat extends Thread{
         private Socket socket;
         private String pseudo;
        public Chat(Socket s, String pseudo) {
            this.socket=s;
            this.pseudo = pseudo;
        }

        public Socket getSocket() {
            return socket;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        public String getPseudo() {
            return pseudo;
        }

        public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
        }
        
        

        @Override
        public void run() {
            
             try {
                 //Lecture de données
                 
                 InputStream is = socket.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr);
                 
                 //Ecriture de données
                 
                 OutputStream os = socket.getOutputStream();
                 PrintWriter pw = new PrintWriter(os,true);
                 String IP = socket.getRemoteSocketAddress().toString();
                 
                 while (true) {
                    pw.println("Veuillez entrer un pseudo: ");
                    pseudo = br.readLine();
                    
                    boolean isExiste = false;
                   
                    while ("".equals(pseudo)) {
                       pw.println("Le Pseudo ne peut pas etre nul: ");
                       pseudo = br.readLine();
                    } 
                    pw.println("Bienvenue client " +pseudo);
                 }
                 
                 
             } catch (IOException ex) {
                 Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
             } 

        }
        
    }
    
}
