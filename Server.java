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
import java.util.SplittableRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noudo
 */

public class Server implements Runnable {

    List<Chat> listClients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Thread(new Server()).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Démarrage du serveur");

            while (true) {
                Socket s = ss.accept();
                /*OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            String IP = s.getRemoteSocketAddress().toString();*/

                String pseudo = "";
                Chat c = new Chat(s, pseudo);

                //System.out.println("Bienvenue client " +IP);
                c.start();

                listClients.add(c);
                System.out.println("Taille " + listClients.size());
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private class Chat extends Thread {

        private Socket socket;
        private String pseudo;

        public Chat(Socket s, String pseudo) {
            this.socket = s;
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
                PrintWriter pw = new PrintWriter(os, true);
                String IP = socket.getRemoteSocketAddress().toString();
                

                while (true) {
                    

                            pw.println("Veuillez entrer un pseudo: ");
                            pseudo = br.readLine();
                        //} else if (pseudo.isEmpty()) {
                         //   pw.println("Le Pseudo ne peut pas etre nul: ");
                         //   pseudo = br.readLine();
                        //} else {
                            
                        //}
                    
                    
                    
                    //pseudo = br.readLine();
                    /*boolean isExiste = false;*/

                        /*while ("".equals(pseudo)) {
                       pw.println("Le Pseudo ne peut pas etre nul: ");
                       pseudo = br.readLine();
                    } */
                    pw.println("Bienvenue client " +pseudo);
                    String requete = null;
                    while ((requete = br.readLine()) != null) {
                        System.out.println("Le client a envoyé : " + requete);
                        pw.println("Le client a envoyé : " + requete);
                        broadCast(pseudo, requete);
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        
    public void broadCast(String pseudo, String requete) throws IOException {

        System.out.println("Taille ");
        System.out.println("Taille " + listClients.size());
        
        if(requete.contains(":")){
            String msg [] = requete.split(":",2);
            String req = msg[0].trim();
            String destinateur = msg[1].trim();
            for (Chat client : listClients) {
            if (client.getPseudo().equals(req)) {
                Socket sock = client.getSocket();
                OutputStream os = sock.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                System.out.println("Tu veux quoi? :");
                pw.println(pseudo+ " Diffuse :" +destinateur);
            return;}}
            
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println("Le pseudo " +req+ "est indisponible");
            
       }else {
            for(Chat client:listClients){
                if(!client.getPseudo().equals(pseudo)){
                    Socket sock = client.getSocket();
                    OutputStream os = sock.getOutputStream();
                    PrintWriter pw = new PrintWriter(os,true);
                    pw.println(pseudo+ "diffuse: " +requete);
                    if(client.pseudo == null?pseudo != null :! client.pseudo.equals(pseudo)){
                        pw.println(pseudo+ "Diffuse: " +requete);
                    }
                }
            }
        }
     
        }
    }

    }
