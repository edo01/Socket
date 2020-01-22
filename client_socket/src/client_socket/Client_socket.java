/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
public class Client_socket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        //create a 
        Socket client = new Socket(InetAddress.getByName("localhost"), 8080);
        System.out.println("client port:" + client.getInetAddress());
        OutputStream output = null;
        
        try {
            output = client.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("This is a message sent to the server");
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine();
            System.out.println(line);
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
