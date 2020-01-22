/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edoardo
 */
public class ClientSocket implements Runnable{

    private Socket client;
    private int id;
    
    ClientSocket(String address, int port, int id) throws UnknownHostException, IOException{
        //create client socket
        client = new Socket(InetAddress.getByName(address), port);
        System.out.println("client port:" + client.getInetAddress());
        this.id = id;
    }
    
    @Override
    public void run() {
        
        OutputStream output = null;
        
        try {
            //get the output stream of connection
            output = client.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            //write to the server
            writer.println("Client" + id);
            
            //get the input stream of connection
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            //read message from server
            String line = reader.readLine();
            System.out.println(line);
            
            //close the connection
            writer.close();
            output.close();
            client.close();
        } catch (IOException ex) {
            System.out.println("errore");
            Logger.getLogger(Client_socket.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(Client_socket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
