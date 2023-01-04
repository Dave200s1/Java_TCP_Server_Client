/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tcp_netz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class Server {
    public static void main(String[]args)
    {
        //Instanz bilden !!!
        Server myServer = new Server(8000);
        myServer.startListening();
        
    }
    
    private int port;
    
    public Server(int port)
    {
        this.port = port;
    }
    
    //Thread erzeugen, wo der Server auf eine Verbindung wartet und startet.
    public void startListening()
    {
        new Thread(new Runnable(){
            @Override
            public void run()
            {
                while(true){
                try{
                    System.out.println("[Server] Server startet....");
                    ServerSocket serverSocket = new ServerSocket(port);
                    System.out.println("[Server] warten auf Verbindung....");
                    Socket remoteClient = serverSocket.accept(); //Das Programm läuft an der Stelle nicht weiter bis eine Verbindung kommt
                     System.out.println("[Server] Verbindung hergestellt...."+ remoteClient.getRemoteSocketAddress());
                    //kurzgesagt: Eine wait() function;
                    
                    Scanner input = new Scanner(new BufferedReader(new InputStreamReader(remoteClient.getInputStream())));
                    //Eine Zeile von Cleint auslesen.
                    if(input.hasNextLine())
                    {
                        System.out.println("[Server] Message from Client: "+ input.nextLine());
                    }
                    //Verbindung schließen
                    input.close();
                    remoteClient.close();
                    serverSocket.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                }
            }
        }).start();
       
    }
}

