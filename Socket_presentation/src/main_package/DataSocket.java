/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author the project
 */
public class DataSocket implements Runnable{

    private Socket socket;
    private Control control;
    protected int id;
    
    DataSocket(Control control, Socket socket, int id){
        this.socket = socket;
        this.control = control;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public void run() {
        try {
            control.accept("" + socket.getInetAddress(),
                    "" + socket.getPort(), id);
            
            //read what sent by the client
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine();    // reads a line of text
            control.read(line, id);
            
            //write back the string to the client
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(line);
            control.write(id);
            
            //close the stream and the socket
            output.close();
            writer.close();
            input.close();
            socket.close();
            control.closeDataSocket(id);
        } catch (IOException ex) {
            Logger.getLogger(DataSocket.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
