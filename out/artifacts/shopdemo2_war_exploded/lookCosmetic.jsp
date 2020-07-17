<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<HTML><HEAD><%@ include file="WEB-INF/ch10/head" %></HEAD>
<BODY background="image/bg.png"><font size=2>
<div align="center">
<%   try {  Class.forName("com.mysql.cj.jdbc.Driver");
      }
      catch(Exception e){} 
      String uri="jdbc:mysql://127.0.0.1/shop?user=root&password=root&serverTimezone=UTC";
      Connection con; 
      Statement sql;
      ResultSet rs;
      try {
        con=DriverManager.getConnection(uri);
        sql=con.createStatement();
        rs=sql.executeQuery("SELECT * FROM classify");
        out.print("<form action='queryServlet' method ='post'>") ;
        out.print("<select name='fenleiNumber'>");
        while(rs.next()){
           int id = rs.getInt(1);
           String name = rs.getString(2);
           out.print("<option value ="+id+">"+name+"</option>");
        }  
        out.print("</select>");
        out.print("<input type ='submit' value ='提交'>");  
        out.print("</form>");
        con.close();
     }
     catch(SQLException e){ 
        out.print(e);
     }
%>
</div></font>
</BODY></HTML>