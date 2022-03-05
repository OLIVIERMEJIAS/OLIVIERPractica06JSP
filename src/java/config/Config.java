/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.SQLException;

/**
 *
 * @author Progra
 */
public final class Config {
    
    /**
     * Metodo que arma y retorna la cadena de conexion
     * @return String con la cadena de conexión a utilizar
     */
    public static String getConnectionString() throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return "jdbc:sqlserver://localhost:1433;databaseName=EXAMPLE_JAVA;"
                + "user=javaUser;password=javaPass;";
    }
}
