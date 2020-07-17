<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/17
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:useBean id="loginBean" class="classes.data.Login" scope="session"></jsp:useBean>
    <%@ include file="WEB-INF/ch10/head"%>
</head>
<body background="image/bg.png"><center>
    <%
        if (loginBean == null){
            response.sendRedirect("login.jsp");
        }else {
            boolean b = loginBean.getLogname() == null ||
            loginBean.getLogname().length() == 0;
            if (b){
                response.sendRedirect("login.jsp");
            }
        }
        String numberID = request.getParameter("xijie");
        out.print("<th>产品号" +numberID);
        if (numberID == null){
            out.print("没有产品号，无法查看细节");
            return;
        }
        Connection con;
        Statement sql;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){}
        String uri = "jdbc:mysql://localhost:3306/shop?user=root&password=root&serverTimezone=UTC";
        try {
            con = DriverManager.getConnection(uri);
            sql = con.createStatement();
            String cdn = "SELECT * FROM cosmeticForm where cosmetic_number = '" + numberID + "'";
            rs = sql.executeQuery(cdn);
            out.print("<table border = 2>");
            out.print("<tr>");
            out.print("<th>产品号");
            out.print("<th>名称");
            out.print("<th>价格");
            out.print("<th><font color = blue>放入购物车</font>");
            out.print("</tr>");
            String picture = "welcome.jpg";
            String detailMess = "";
            while (rs.next()){
                String number = rs.getString(1);
                String name = rs.getString(2);
                String maker = rs.getString(3);
                String price = rs.getString(4);
                detailMess = rs.getString(5);
                picture = rs.getString(6);
                String goods = "(" + number + "," + name + "," + price + ")#" + price;
                goods = goods.replaceAll("\\p{Blank}","");
                String button = "<form action = 'putGoodsServlet' method = 'post'>" +
                        "<input type = 'hidden' name = 'java' value = " + goods + ">" +
                        "<input type = 'submit' value = '放入购物车'></form>";
                out.print("<tr>");
                out.print("<td>" + number + "</td>");
                out.print("<td>" + name + "</td>");
                out.print("<td>" + maker + "</td>");
                out.print("<td>" + price + "</td>");
                out.print("<td>" + button + "</td>");
                out.print("<tr>");
            }
            out.print("</table>");
            out.print("产品详情：<br>");
            out.println("<div align = center>"+ detailMess + "</div>");
            String pic = "<img src = 'image/" + picture + "'width = 260 height = 200></img>";
            out.print(pic);
            con.close();
        }catch (SQLException exp){}
    %>
</center>
</body>
</html>
