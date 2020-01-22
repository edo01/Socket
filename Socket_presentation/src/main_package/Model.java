/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author the project
 */
public class Model{
    
    private ServerSocket connectionSocket;
    private Socket dataSocket;
    private String message;
    private Control control;
    private int port;
    private int number_of_connection = 1;
    
    public Model(int port, Control control){
        //control if the port is a Well-Known port
        if(port<1023){
            port=8080; //set default port 8080
        }
        this.port = port;
        try{
            //create the server socket at given port
            connectionSocket = new ServerSocket(this.port);
            control.creation(0);
            //bind ip with port
            control.bind("" + connectionSocket.getInetAddress(), "" + port, 0);
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
        this.control = control;
    }
    
    public void startServer(){
        try {
            while(true){
                control.listening(0);
                Thread new_connection = new Thread(
                        new DataSocket(control, connectionSocket.accept(),
                        number_of_connection));
                number_of_connection++;//increment the number of connection
                new_connection.start();//start the connection
            }
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public InetAddress getServerAddress(){
        return connectionSocket.getInetAddress();
    }
    
    public int getPort(){
        return port;
    }
    
    public void close() throws IOException{    
        dataSocket.close();
        connectionSocket.close();
        control.closeServerSocket(0);
    }
}
