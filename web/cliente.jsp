<%-- 
    Document   : cliente
    Created on : 4 mar. 2022, 14:50:44
    Author     : Progra
--%>

<%@page import="businessLogic.ClientBL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="domain.Client" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente</title>
        <jsp:include page="WEB-INF/pages/comunes/metaData.jsp" />
    </head>
    <body>
        <jsp:include page="WEB-INF/pages/comunes/menu.jsp" />
        
        <main>
            <div class="container">
                <div class="row justify-content-md-center">
                    <div class="col-md-6">
                        <div class="card-header">
                            <h4>Gestión Cliente</h4>
                        </div>
                        <%
                            String id = request.getParameter("id").toString();
                            int cod = Integer.parseInt(id);
                            
                            Client client;
                            ClientBL logic = new ClientBL();
                            if(cod > 0){
                                client = logic.getOne("id_client = " + id);
                                id=""+client.getId_client();
                            }
                            else{
                                client = new Client();
                                id="";
                            }
                            %>
                        <form action="saveClient" method="post">
                            <div class="form-group">
                                <label for="txtCod" class="control-label">
                                    Código:
                                </label>
                        
                                <input type="number" id="txtCod" name="txtCod"
                                   value="<%= id %>" readonly
                                   class="form-control"/>
                                    
                            </div>
                                   
                             <div class="form-group">
                                <label for="txtName" class="control-label">
                                    Nombre:
                                </label>
                        
                                <input type="text" id="txtName" name="txtName"
                                   value="<%= client.getName() %>" required
                                   class="form-control"/>
                            </div>
                                   
                             <div class="form-group">
                                <label for="txtPhone" class="control-label">
                                    Teléfono:
                                </label>
                        
                                <input type="text" id="txtPhone" name="txtPhone"
                                   value="<%= client.getPhone() %>" 
                                   class="form-control"/>
                            </div>
                                   
                             <div class="form-group">
                                <label for="txtDirection" class="control-label">
                                    Dirección:
                                </label>
                        
                                <input type="text" id="txtDirection" name="txtDirection"
                                   value="<%= client.getDirection() %>" 
                                   class="form-control"/>
                            </div>
                                   
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="submit" id="btnSave" value="Guardar" class="btn btn-primary m-2"/>
                                    <input type="reset" id="btnReset" value="Limpiar" class="btn btn-secondary m-2"/>
                                    <input type="button" id="btnBack" value="Regresar" class="btn btn-warning m-2"
                                           onclick="location.href='clientes.jsp?id='+id"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
