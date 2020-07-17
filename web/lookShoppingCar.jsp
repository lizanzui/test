<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="classes.data.Login" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="loginBean" class="classes.data.Login" scope="session"/>
<HTML><HEAD><%@ include file="WEB-INF/ch10/head" %></HEAD>
<BODY background="image/bg.png"><font size=2>
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
    LinkedList car =loginBean.getCar();
    if(car==null)
      out.print("<h2> 购物车没有物品.</h2>");
    else {
       Iterator<String> iterator=car.iterator();
       StringBuffer buyGoods = new StringBuffer();
       double priceSum =0;
       int number = 0;
       out.print("购物车中的物品：<table border=2>");
       while(iterator.hasNext()) {
           String goods=iterator.next();
           String showGoods="";
           int index=goods.indexOf("#");
           int size = goods.lastIndexOf("#");
           number = Integer.parseInt(goods.substring(size+1));
           if(index!=-1){
              priceSum+=Double.parseDouble(goods.substring(index+1,size-1))*number;
              showGoods = goods.substring(0,index);
           }
           buyGoods.append(showGoods+"商品数量"+number);
           String num= "商品数量"+"<input type ='text' name='newnumber'  value=" + number + " >";
           String update="<form  action='updateServlet' method = 'post'>"+
                   "<input type ='hidden' name='delete' value= "+goods+">"+
                   num +
                   "<input type ='submit' value='修改' ></form>";
           String del="<form  action='deleteServlet' method = 'post'>"+
                     "<input type ='hidden' name='delete' value= "+goods+">"+
                     "<input type ='submit'  value='删除' ></form>";
           out.print("<tr><td>"+showGoods+"</td>");
           out.print("<td>"+update+"</td>");
           out.print("<td>"+del+"</td></tr>");
       }
       out.print("</table>");
       String orderForm = "<form action='buyServlet' method='post'>"+
              " <input type ='hidden' name='buy' value= "+buyGoods+" >"+ 
              " <input type ='hidden' name='price' value= "+priceSum+" >"+           
              " <input type ='hidden' name='number' value= "+number+" >"+
              "<input type ='submit'  value='生成订单'></form>";
       out.print(orderForm); 
    } 
%>
</div></font>
</BODY></HTML>
