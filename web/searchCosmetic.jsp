<%@ page contentType="text/html;charset=UTF-8" %>
<HTML><HEAD><%@ include file="WEB-INF/ch10/head" %></HEAD>
<BODY background="image/bg.png"><font size=2>
<div align="center">
<br>查询时可以输入游戏的版本号或游戏名称及价格。<br>
游戏名称支持模糊查询。
<br>输入价格是在2个值之间的价格，格式是：价格1-价格2<br>
例如 258-689 
<FORM action="searchByConditionServlet" Method="post" >
   <br>输入查询信息:<Input type=text name="searchMess"><br>
   <Input type =radio name="radio" value="cosmetic_number">游戏版本号
   <Input type =radio name="radio" value="cosmetic_name" checked="ok">游戏名称
   <Input type =radio name="radio" value="cosmetic_price">游戏价格
   <br><Input type=submit name="g" value="提交">
</Form>
</div>
</Font></BODY></HTML>