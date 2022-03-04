/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import domain.Client;
import dao.ClientDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Progra
 */
public class ClientBL {
    private String msg;
    private ClientDAO _dAClient;
    
    public ClientBL (){
        msg ="";
    }
    
    public ResultSet listAll (String condition, String order) throws SQLException, Exception{
        ResultSet rs=null;
        
        try {
            
            _dAClient = new ClientDAO();
            rs = _dAClient.listAll(condition, order);
            
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
        return rs;
    }
    
    public List<Client> listAll (String condition) throws SQLException, Exception{
        List<Client> list;
        
        try {
            
            _dAClient = new ClientDAO();
            list = _dAClient.listAll(condition);
            
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
    
    public Client getOne(String condition) throws SQLException, Exception{
        Client client = null;
        
        try {
            
            _dAClient = new ClientDAO();
            client = _dAClient.getOne(condition);
            
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
        return client;
    }
    
    public int insert(Client client) throws SQLException, Exception{
        int result=-1;
        
        try {
            _dAClient = new ClientDAO();
            result = _dAClient.insert(client);
            msg=_dAClient.getMessage();
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    
    public int update(Client client) throws SQLException, Exception{
        int result=-1;
        
        try {
            _dAClient = new ClientDAO();
            result = _dAClient.update(client);
            msg=_dAClient.getMessage();
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    
    public int delete(Client client) throws SQLException, Exception{
        int result=-1;
        
        try {
            _dAClient = new ClientDAO();
            result = _dAClient.delete(client);
            msg=_dAClient.getMessage();
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    
    public String getMessage(){
        return msg;
    }
}
