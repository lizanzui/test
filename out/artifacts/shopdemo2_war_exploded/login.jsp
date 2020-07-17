<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="loginBean" class="classes.data.Login" scope="session"/>
<HTML>
<HEAD>
    <%@ include file="WEB-INF/ch10/head" %>
</HEAD>
<BODY background="image/bg.png"><font size=2>
    <div align="center">
        <table border=2>
            <tr>
                <th>登录</th>
            </tr>
            <FORM action="loginServlet" Method="post">
                <tr>
                    <td>登录名称:<Input type=text name="logname"></td>
                </tr>
                <tr>
                    <td>输入密码:<Input type=password name="password"></td>
                </tr>
                <Input type=submit name="g" value="提交">
            </form>
        </table>
    </div>
</font>
</BODY>
</HTML>