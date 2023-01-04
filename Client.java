/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myClient;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author david
 */
public class Client {
    public static void main(String[]args)
    {
        Client myClient = new Client("localhost",8000);
        myClient.sendMessage("Hello there server !");
    }
    
    private InetSocketAddress address;
    
    public Client(String hostname, int port)
    {
        address = new InetSocketAddress(hostname, port);
    }
    
    public void sendMessage(String msg)
    {
        try{
            System.out.println("[Client] Verbindet zu Server.....");
            Socket socket = new Socket();
            socket.connect(address,5000);
            System.out.println("[Client] Verbindung hergestellt.....");
            
            System.out.println("[Client] Message senden.....");
            PrintWriter myWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            myWriter.println(msg);
            myWriter.flush();
            System.out.println("[Client] Message gesendet.....");
            //Verbindung schließen!
            myWriter.close();
            socket.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
