/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_socket;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author the project
 */
public class Client_socket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        int id = 1;
        while(true){
            ClientSocket clientsocket = new ClientSocket("localhost", 8080, id);
            id++;
            Thread client = new Thread(clientsocket);
            client.start();
            Thread.sleep(1 + (long)(10000*Math.random()));
        }
    }
    
}
