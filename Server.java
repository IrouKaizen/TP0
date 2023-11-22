/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ODG
 */
public class Server {
    
    public static void main(String[] iren) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        Socket s = ss.accept();
        
        //lecture de données
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        //Ecriture de données
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        
        String IP = s.getRemoteSocketAddress().toString();
        pw.println("Bienvenue client IP= " +IP);
        String chaine = br.readLine();
        int n = chaine.length();
        
        pw.println("la longueur de la chaine est :" +n);
        
    }
  
}
