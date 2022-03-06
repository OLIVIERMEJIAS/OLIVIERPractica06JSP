<%-- 
    Document   : borrarCliente
    Created on : 5 mar. 2022, 19:54:08
    Author     : HP
--%>

<%@page import="domain.Client"%>
<%@page import="businessLogic.ClientBL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <jsp:include page="WEB-INF/pages/comunes/metaData.jsp" />
         <title>Confirmación de Borrado</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/pages/comunes/menu.jsp" />
         <div class="container">
                <div class="row justify-content-md-center">
                    <div class="col-md-6">
                        
        
                        <form action="deleteClient" method="post">
                            <% ClientBL logic = new ClientBL();
                                int id = Integer.parseInt(request.getParameter("id"));
                                Client client;
                                String name = "";
                                String phone = "";
                                String direction = "";
                                if(id != -1){
                                    client = logic.getOne("id_client = "+id);
                                    if(client.isExist()){
                                }
                                    name = client.getName();
                                    phone = client.getPhone();
                                    direction = client.getDirection();
                                    id = client.getId_client();
                                }
                                %>
                                <br>
                                <div class="card-header text-center">
                                    <label>Por favor. Confirme el borrado!</label>
                                </div>
                                <br>
                                <label class="control-label">Código</label>
                                <br>
                                <input type="text" id="txtCod" name="txtCod"
                                value="<%= id %>" readonly
                                class="form-control"/>
                                <label class="control-label">Nombre</label>
                                <br>
                                <input type="text" id="txtName" name="txtName"
                                value="<%= name%>" readonly
                                class="form-control"/>
                                <label class="control-label">Teléfono</label>
                                <br>
                                <input type="text" id="txtPhone" name="txtPhone"
                                value="<%= phone%>" readonly
                                class="form-control"/>
                                <label class="control-label">Dirección</label>
                                <br>
                                <input type="text" id="txtDirection" name="txtDirection"
                                value="<%= direction %>" readonly
                                class="form-control"/>
                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="submit" id="btnDelete" value="Eliminar" class="btn btn-primary m-2"/>
                                        <input type="button" id="btnBack" value="Regresar" class="btn btn-warning m-2"
                                               onclick="location.href='clientes.jsp'"/>
                                    </div>
                                </div>
                        </form>
                    </div>
                </div>
         </div>
    </body>
</html>
