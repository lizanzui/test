<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="loginBean" class="classes.data.Login" scope="session"/>
<%@ page import="java.sql.*" %>
<HTML><HEAD><%@ include file="WEB-INF/ch10/head" %></HEAD>
<BODY background="image/bg.png">
<div align="center">
<%  if(loginBean==null){
        response.sendRedirect("login.jsp");
    }
    else {
       boolean b =loginBean.getLogname()==null||
                  loginBean.getLogname().length()==0;
       if(b)
         response.sendRedirect("login.jsp");
    }
    Connection con;
    Statement sql; 
    ResultSet rs;
    try{  Class.forName("com.mysql.cj.jdbc.Driver");
    }
    catch(Exception e){}
    try { String uri= "jdbc:mysql://127.0.0.1/shop?user=root&password=root&serverTimezone=UTC";
          con=DriverManager.getConnection(uri);
          sql=con.createStatement();
          String cdn=
         "SELECT id,mess,sum,create_time  FROM orderform where logname= '"+loginBean.getLogname()+"' and state = '0' ";
          rs=sql.executeQuery(cdn);
          out.print("<table border=2>");
          out.print("<tr>");
            out.print("<th width=100>"+"订单号");
            out.print("<th width=100>"+"信息");
            out.print("<th width=100>"+"价格");
            out.print("<th width=100>"+"支付订单");
          out.print("</TR>");
          while(rs.next()){
            out.print("<tr>");
              out.print("<td >"+rs.getString(1)+"</td>"); 
              out.print("<td >"+rs.getString(2)+"</td>");
              out.print("<td >"+rs.getString(3)+"</td>");
              out.print("<td ><form action = 'paySelevt' method = 'post'>" +
                      "<input type = 'submit' value = '确认支付'>" +
                      "<input type = 'hidden' name = 'id' value = '" + rs.getString(1) + "'>" +
                      "<input type = 'hidden' name = 'time' value = '" + rs.getLong(4) + "'>" +
                      "<input type = 'hidden' name = 'state' value = '1'>" +
                      "</form></td>");
              out.print("<td ><form action = 'paySelevt' method = 'post'>" +
                      "<input type = 'submit' value = '取消'>" +
                      "<input type = 'hidden' name = 'id' value = '" + rs.getString(1) + "'>" +
                      "<input type = 'hidden' name = 'time' value = '" + rs.getLong(4) + "'>" +
                      "<input type = 'hidden' name = 'state' value = '-1'>" +
                      "</form></td>");
              out.print("</tr>") ;
          }
          out.print("</table>");
          con.close();
    }
    catch(SQLException e){ 
          out.print(e);
    }
 %>
</div">
</BODY></HTML>
