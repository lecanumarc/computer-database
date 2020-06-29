<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.excilys.formation.cdb.pojos.Computer"%> 
<%@page import="java.util.ArrayList"%> 
<!DOCTYPE html> 
<html> 
  <head> 
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
   <title>Student List</title> 
  </head> 
  <body> 
      <h1>Displaying computer List</h1> 
      <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Computer Name</b></th> 
          <th><b>Intro date</b></th> 
          <th><b>Disc dtae </b></th> 
          <th><b>Copany name</b></th> 
         </tr> 
        <%-- Fetching the attributes of the request object 
             which was previously set by the servlet  
              "StudentServlet.java" 
        --%>  
        <%ArrayList<Computer> list = (ArrayList<Computer>)request.getAttribute("computerList"); 
        for(Computer computer:list){%> 
        <%-- Arranging data in tabular form 
        --%> 
            <tr> 
                <td><%=computer.getName()%></td> 
                <td><%=computer.getintroduced()%></td> 
                <td><%=computer.getdiscontinued()%></td> 
                <td>Nom compagnie</td>
            </tr> 
            <%}%> 
        </table>  
        <hr/> 
    </body> 
</html> 