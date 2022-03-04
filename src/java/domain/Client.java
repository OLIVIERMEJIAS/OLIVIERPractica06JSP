/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Progra
 */
public class Client {
    private int id_client;
    private String name;
    private String direction;
    private String phone;
    private boolean exist;//No existe en la base de datos! Campo de verificación!
    
    public Client(){
        id_client=0;
        name="";
        direction  ="";
        phone = "";
        exist= false;
    }
    public Client(int id_client, String name, String direcction, String phone){
        this.id_client=id_client;
        this.name = name;
        this.direction = direcction;
        this.phone = phone;
        this.exist=true;
    }
    
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
    
    
}
