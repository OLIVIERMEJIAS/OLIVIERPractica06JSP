/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Client;
import config.Config;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Progra
 */
public class ClientDAO {
    
    private Connection _cnn=null;
    private String msg;
    
    public ClientDAO() throws SQLException, Exception{
        String url = Config.getConnectionString();
        try {
            _cnn = DriverManager.getConnection(url);
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw new Exception ("Error Genérico: " + e.getMessage());
        }
    }
    
    /**
     * Método que lista de forma ordenada a los clientes que se soliciten
     * @param condition - Condición en formato SQL para filtrar la consulta
     * @param order - Nombre del campo de la tabla, por el que se quiere ordenar
     * @return Resulset con todos los registros obtenidos en la consulata
     * @throws java.sql.SQLException 
     */
    public ResultSet listAll (String condition, String order) throws SQLException, Exception{
        ResultSet rs =null;
        String query = "Select id_client, name, phone, direction from Clients";
        if (!condition.equals("")) {
            query = String.format("%s Where %s", query, condition);
        }
        if (!order.equals("")) {
            query = String.format("%s Order By %s", query, order);
        }
        
        try {
            Statement statement = _cnn.createStatement();
            rs = statement.executeQuery(query);
            
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }finally{
            _cnn=null;
        }
        
        return rs;
    }
    /**
     * Método que lista y devuelve un List con los clientes y sus datos cada uno
     * @param condition - filtro para listar
     * @return lista con los clientes
     * @throws SQLException
     * @throws Exception 
     */
    public List<Client> listAll (String condition) throws SQLException, Exception{
        ResultSet rs =null;
        List<Client> list =  new ArrayList();
        String query = "Select id_client, name, phone, direction from Clients";
        if (!condition.equals("")) {
            query = String.format("%s Where %s", query, condition);
        }
        
        try {
            Statement statement = _cnn.createStatement();
            rs = statement.executeQuery(query);
            
        //Recorrer ResultSet para sacarle los datos!    
            while(rs!=null && rs.next()){
                Client client = new Client(rs.getInt("id_client"),rs.getString("name"),rs.getString("direction"),rs.getString("phone"));
                list.add(client);
            }//fin del while
        
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }finally{
            _cnn=null;
        }
        
        return list;
    }
    /**
     * Obtene los datos de un sólo cliente
     * @param condition - condición donde se pasa el Id
     * @return un elemento tipo cliente
     * @throws SQLException 
     */
    public Client getOne(String condition) throws SQLException{
        Client client=new Client();
        ResultSet rs =null;
        String query = "Select id_client, name, phone, direction from Clients";
        if (!condition.equals("")) {
            query = String.format("%s Where %s", query, condition);
        }
        try {
            Statement statement = _cnn.createStatement();
            rs = statement.executeQuery(query);
            
            if (rs!=null && rs.next()) {
                client.setId_client(rs.getInt(1));
                client.setName(rs.getString(2));
                //Revisar que no sea null!
                client.setPhone(rs.getString(3));
                client.setDirection(rs.getString(4));
                client.setExist(true);
            }
            
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }finally{
            _cnn=null;
        }
        
        return client;
    }
      /**
     * Inserta un nuevo cliente
     * @param client - objeto tipo cliente
     * @return un valor diferente a - 1 cuando se pudo realizar la consulta con
     * e inserto a un  nuevo cliente
     * @throws SQLException
     * @throws Exception 
     */
    public int insert(Client client) throws SQLException, Exception{
        int result = -1;
        //String query = "Insert into Clients (name, phone, direction) Values('%s','%s','%s')".formatted(client.getName(),client.getPhone(),client.getDirection());
        String query = "Insert into Clients (name, phone, direction) Values(?,?,?)";
        ResultSet rs=null;
        try {
            PreparedStatement ps = _cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());
            ps.setString(2, client.getPhone());
            ps.setString(3, client.getDirection());
            ps.execute();
            rs= ps.getGeneratedKeys();
            if (rs!=null && rs.next()) {
                result = rs.getInt(1);//Identity generado por SQL Server!
                msg = "Cliente Alamacenado con Éxito!!!";
            }
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }finally{
            _cnn=null;
        }
        return result;
    }
    /**
     * Actualiza el registro de un cliente existente
     * @param client - objeto tipo cliente
     * @return un int con un número diferente a -1 cuando logró realizar la consulta,
     * podría ser cero, si ya el cliente no existe, pero como
     * se valida esto, se espera sea mayor a cero, que significa
     * que se actualizó el cliente con éxito
     * @throws SQLException 
     */
    public int update(Client client) throws SQLException{
        int result = -1;
        String query = String.format("Update Clients set "
                + "name = '%s', phone='%s', direction='%s' "
                + "Where id_client = %d",
                client.getName(),client.getPhone(),client.getDirection(),client.getId_client());
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(query);
            result = ps.executeUpdate();
            
            if (result >0) {
                msg = "Cliente Actualizado con Éxito!!!";
            }
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }finally{
            _cnn=null;
        }
        return result;
    }
     /**
     * Elimina el registro de un cliente existente
     * @param client - objeto tipo cliente
     * @return un int con un número diferente a -1 cuando logró realizar la consulta,
     * podría ser cero, si ya el cliente no existe, pero como
     * se valida esto, se espera sea mayor a cero, que significa
     * que se eliminó el cliente con éxito
     * @throws SQLException 
     */
    public int delete(Client client) throws SQLException{
        int result = -1;
        String query = String.format("Delete From Clients Where id_client = %d",client.getId_client());
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(query);
            result = ps.executeUpdate();
            
            if (result >0) {
                msg = "Cliente Eliminado!!!";
            }
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }finally{
            _cnn=null;
        }
        return result;
    }
    
    public String getMessage(){
        return msg;
    }
}
