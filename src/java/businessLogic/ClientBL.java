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
     /**
     * Método que lista de forma ordenada a los clientes que se soliciten
     * @param condition - Condición en formato SQL para filtrar la consulta
     * @param order - Nombre del campo de la tabla, por el que se quiere ordenar
     * @return Resulset con todos los registros obtenidos en la consulata
     * @throws java.sql.SQLException 
     */
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
     /**
     * Método que lista y devuelve un List con los clientes y sus datos cada uno
     * @param condition - filtro para listar
     * @return lista con los clientes
     * @throws SQLException
     * @throws Exception 
     */
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
     /**
     * Obtene los datos de un sólo cliente
     * @param condition - condición donde se pasa el Id
     * @return un elemento tipo cliente
     * @throws SQLException 
     */
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
    /**
     * Inserta un nuevo cliente
     * @param client - objeto tipo cliente
     * @return un valor diferente a - 1 cuando se pudo realizar la consulta con
     * e inserto a un  nuevo cliente
     * @throws SQLException
     * @throws Exception 
     */
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
      /**
     * Actualiza el registro de un cliente existente
     * @param client - objeto tipo cliente
     * @return un int con un número diferente a -1 cuando logró realizar la consulta,
     * podría ser cero, si ya el cliente no existe, pero como
     * se valida esto, se espera sea mayor a cero, que significa
     * que se actualizó el cliente con éxito
     * @throws SQLException 
     */
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
      /**
     * Elimina el registro de un cliente existente
     * @param client - objeto tipo cliente
     * @return un int con un número diferente a -1 cuando logró realizar la consulta,
     * podría ser cero, si ya el cliente no existe, pero como
     * se valida esto, se espera sea mayor a cero, que significa
     * que se eliminó el cliente con éxito
     * @throws SQLException 
     */
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
