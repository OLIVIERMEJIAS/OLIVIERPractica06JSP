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
public class saveClient extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Se verifica que el Id en el input tipo text del código esté vacío,
     * si lo está es insersión, no no es actualización
     * el id con número mayor a cero es asignado a una variable si el input
     * contiene algo, sino es cero
     * después esa variable id es evaluada y si es mayor a cero se actualiza 
     * - previo análisis de que exista todavía el cliente -
     * y sino se enserta un nuevo cliente, ambos casos usan un nuevo cliente,
     * para el cual se han asignado sus atributos con los datos de los inputs
     * de la página cliente.jps
     * Al final se redirecciona a la página de clientes.jps con un mensaje de salida
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            ClientBL logic = new ClientBL();
            Client client = new Client();
            String msg = "";
            int result = 0;
            int id = 0;
            if(!request.getParameter("txtCod").isEmpty())
            {
                client.setId_client(Integer.parseInt(request.getParameter("txtCod")));
            }else
            {
                client.setId_client(0);
            }
                
            client.setName(new String(request.getParameter("txtName").getBytes("ISO-8859-1"),
                "UTF-8"));
            client.setPhone(new String(request.getParameter("txtPhone").getBytes("ISO-8859-1"),
                    "UTF-8"));
            client.setDirection(new String(request.getParameter("txtDirection").getBytes("ISO-8859-1"),
                    "UTF-8"));
            if(client.getId_client() > 0){
                if(logic.getOne("id_client =" + client.getId_client()).isExist()){
                    result = logic.update(client);
                     msg = logic.getMessage();
                    
                }else{
                    msg = "No se logró modificar, porque el cliente ya no existe";
                }
            }
            else{
                result = logic.insert(client);
                 msg = logic.getMessage();
            }
            
           
            response.sendRedirect("clientes.jsp?msg="+msg);
            
            
        }catch(Exception e){
            out.print(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
