<%-- 
    Document   : comprobarAcceso.jsp
    Created on : Mar 3, 2020, 10:56:55 AM
    Author     : tux
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Comprobando</title>
  </head>
  <body>
    
    <%
      request.setCharacterEncoding("UTF-8");
      String usuario = request.getParameter("user");
      String contrasenia = request.getParameter("contrasenia");
      if ((usuario != null) && (contrasenia != null) && usuario.equals(contrasenia))
      {
        session.setAttribute("usuario",usuario);
        %>
        <h1>Acceso autorizado</h1>

        <%
      } else {
        %>
        <h1>Acceso denegado</h1>
        <%
      }
    %>
    <a href="index.jsp">Ir al ppo</a>
  </body>
</html>
