/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import java.awt.Color;

/**
 *
 * @author the project
 */
public class Control{
    private Model model;
    private View  view;
    private String log_message;
    
    public Control(View view){
        this.view = view;
        this.view.getjTextArea1().setWrapStyleWord(true);
        
    }
    
    public void startServer(int port, int id){
        model = new Model(port, this);
        model.startServer();
        updateLogs(id, "server started...");
        updateLogs(id, "Server ip:" + model.getServerAddress());
        updateLogs(id, "Server service port:" + model.getPort());
    }
    
    public void creation(int id){
        view.getLblCreation().setForeground(Color.RED);
        updateLogs(id, "create new Server socket" );
        
    }

    public void bind(String ip, String port, int id){
        view.getLblBind().setForeground(Color.RED);
        updateLogs(id, "binding:" + ip + ":" + port);
    }
    
    public void listening(int id){
        view.getLblListening().setForeground(Color.RED);
        updateLogs(id, "Server starts listen");
    }
    
    public void accept(String ip, String port, int id){
        view.getLblAccepted().setForeground(Color.RED);
        updateLogs(id, "new connection from client:" + ip + ":" + port);
        updateLogs(id, "Create new data socket");
    }
    
    public void read(String text, int id){
        view.getLblRead().setForeground(Color.RED);
        updateLogs(id, "Client is writing...");
        updateLogs(id, "New string from client:");
        updateLogs(id, text);
    }
    
    public void write(int id){
        view.getLblWrite().setForeground(Color.RED);
        updateLogs(id, "Send response to the client");
    }   
    
    public void closeDataSocket(int id){
        view.getLblCloseDatasocket().setForeground(Color.RED);
        updateLogs(id, "Close data socket");
        view.getLblRead().setForeground(Color.BLACK);
        view.getLblWrite().setForeground(Color.BLACK);
        view.getLblAccepted().setForeground(Color.BLACK);
        view.getLblCloseDatasocket().setForeground(Color.BLACK);

    }
    
    public void closeServerSocket(int id){
        view.getJlblClose().setForeground(Color.RED);
        updateLogs(id, "Close server socket...");
    }
    
    private void updateLogs(int id, String new_log){
        log_message += "/n" + "Connessione " + id + ":" + new_log;
        view.getjTextArea1().setText(log_message);
        System.out.println("Connessione " + id + ":" + new_log);
    }
}
