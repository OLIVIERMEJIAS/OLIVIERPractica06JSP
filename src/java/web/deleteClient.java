/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import businessLogic.ClientBL;
import domain.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Progra
 */
public class deleteClient extends HttpServlet {

 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Este método responde al llamado de request en Get, lo que realiza
     * es redirigir a la página de borrar cliente que es una confirmación de borrado
     * para este caso al redireccionar se le pasa el id del cliente, 
     * para cargar los datos en la confirmación
     * este id es procesado en este método doGet del servlet, el proviene 
     * de la página clientes.jso
     * cuando le dan click a eliminar
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String id = request.getParameter("id").toString();
            response.sendRedirect("borrarCliente.jsp?id="+id);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Este método elimina el cliente directamente, después de haberse confirmado
     * la eliminación, en sí el obtiene el id del cliente de input con el name txtCod
     * se valida que este input no este vacío, se valida que el cliente aún existe
     * y se procede a eliminar y redirijir a la página de clientes.jps con un mensaje
     * de salida que aparecerá en un alert
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Client client = new Client();
            ClientBL logic = new ClientBL();
            String msg = "";
            if(!request.getParameter("txtCod").isEmpty())
            
            {
                int id = Integer.parseInt(request.getParameter("txtCod"));
                client.setId_client(id);
                if(logic.getOne("id_client = "+id).isExist()){
                    logic.delete(client);
                    msg=logic.getMessage();
                }
            }
            response.sendRedirect("clientes.jsp?msg="+msg);
        }
        catch(Exception e){
            out.print(e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
