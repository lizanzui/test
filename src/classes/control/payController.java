package classes.control;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class payController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con;
        Statement sql;
        int id = Integer.parseInt(req.getParameter("id"));
        Long time = Long.valueOf(req.getParameter("time"));
        int state = Integer.parseInt(req.getParameter("state"));
        Long payTime = System.currentTimeMillis();
        String uri = "jdbc:mysql://localhost:3306/shop?user=root&password=root&serverTimezone=UTC";
        try{
            con=DriverManager.getConnection(uri);
            String condition="delete from orders where create_time = '" + time + "'";
            sql=con.createStatement();
            sql.executeUpdate(condition);
        }
        catch(SQLException exp){
        }
        if (state == 1){
            try{
                con=DriverManager.getConnection(uri);
                String condition1="update orderform set state = '"+ state + "',pay_time = '"+ payTime +"' where id = '"+ id +"'";
                sql=con.createStatement();
                sql.executeUpdate(condition1);
                con.close();
            }
            catch(SQLException exp){
            }
        }else {
            try{
                con=DriverManager.getConnection(uri);
                String condition1="update orderform set state = '"+ state + "'where id = '"+ id +"'";
                sql=con.createStatement();
                sql.executeUpdate(condition1);
                con.close();
            }
            catch(SQLException exp){
            }
        }
        speakSomeMess(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {  Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){}
    }

    public void speakSomeMess(HttpServletRequest request,
                              HttpServletResponse response) {
        response.setContentType("text/html;charset=utf8");
        try {
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("支付成功<br>");
            out.println("<a href = lookShoppingCar.jsp>查看购物车</a>");
            out.println("<a href = lookOrderForm.jsp>查看订单</a>");
            out.println("<br><a href =byPageShow.jsp>浏览商品</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
