<%-- 
    Document   : clientes
    Created on : 3 mar. 2022, 17:24:43
    Author     : Progra
--%>

<%@page import="domain.Client"%>
<%@page import="businessLogic.ClientBL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Clientes</title>
        <jsp:include page="WEB-INF/pages/comunes/metaData.jsp"/>
    </head>
    <body>
        <jsp:include page="WEB-INF/pages/comunes/menu.jsp"/>
        <div class="container">
            <div clas="card-header">
                <h1>Listado de Clientes</h1>
            </div>
            <br>
            <% 
            if(request.getParameter("msg") != null){%>
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <%= request.getParameter("msg") %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" 
                            aria-label="Close"></button>
                </div>
            <%}
                request.setAttribute("msg", null);
            %>
            
            <br>
            <form class="container" action="clientes.jsp" method="post">
                <div class="input-group">
                    <input type="text" name="txtName" id="txtName" value=""
                           placeholder="Escriba un nombre para filtrar..."
                           class="form-control"/>
                    <input type="submit" value="Filtrar" id="btnFind" name="btnFind" class="btn btn-primary" />
                    
                </div>
            </form>
            <table class="table table striped">
                <thead class="thead-dark">
                     <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Teléfono</th>
                         <th>Dirección</th>
                         <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        String name = "";
                        String condition = "";
                        if(request.getParameter("txtName") != null)
                        {
                            name = request.getParameter("txtName").toString();
                            condition = "name like '%"+name+"%'";
                        }
                        ClientBL logic = new ClientBL();
                        List<Client> list = logic.listAll(condition);
                        for(Client client: list){%>
                           <tr>
                            <td><%= client.getId_client() %></td>
                            <td><%= client.getName() %></td>
                            <td><%= client.getPhone() %></td>
                             <td><%= client.getDirection() %></td>
                             <td>
                                 <a href="deleteClient?id=<%= client.getId_client() %>">Eliminar</a> |
                                  <a href="cliente.jsp?id=<%= client.getId_client() %>">Modificar</a>
                             </td>
                        </tr> 
                        <%}
                    %>
                    
                </tbody>
            </table>
            
                    <a href="index.jsp" class="btn btn-warning">Regresar</a>
                     <a href="cliente.jsp?id=-1" class="btn btn-primary">Agregar</a> 
            
            
        </div>   
        
       
            
    </body>
</html>
