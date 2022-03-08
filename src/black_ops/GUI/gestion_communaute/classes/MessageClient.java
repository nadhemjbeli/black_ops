/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute.classes;

import black_ops.Entity.Client;
import black_ops.Entity.Messagee;

/**
 *
 * @author ASUS
 */
public class MessageClient {
    public Messagee message;
    public Client client;

    public MessageClient() {
        
    }

    public MessageClient(Messagee message, Client client) {
        this.message = message;
        this.client = client;
    }

    public Messagee getMessage() {
        return message;
    }

    public void setMessage(Messagee message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "MessageClient{" + "message=" + message + ", client=" + client + '}';
    }
    
    
    
    
}
