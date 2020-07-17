package classes.control;
import classes.data.Login;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class HandleBuyGoods extends HttpServlet {
   public void init(ServletConfig config) throws ServletException { 
      super.init(config);
      try{ 
           Class.forName("com.mysql.cj.jdbc.Driver");
      }
      catch(Exception e){} 
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
      request.setCharacterEncoding("utf8");
      Long time = System.currentTimeMillis();
      request.setAttribute("time",time);
      String buyGoodsMess = request.getParameter("buy");
      if(buyGoodsMess==null||buyGoodsMess.length()==0) {
         fail(request,response,"购物车没有订单，无法生成订单");
         return;
      }
      String price = request.getParameter("price");
      String number = request.getParameter("number");
      if(price==null||price.length()==0) {
         fail(request,response,"没有计算价格和，无法生成订单");
         return;
      }
      if(number==null||number.length()==0) {
         fail(request,response,"没有计算价格和，无法生成订单");
         return;
      }
      float sum = Float.parseFloat(price);
      Login loginBean=null;
      HttpSession session=request.getSession(true);
      try{  loginBean=(Login)session.getAttribute("loginBean");
            boolean b =loginBean.getLogname()==null||
            loginBean.getLogname().length()==0;
            if(b)
              response.sendRedirect("login.jsp");
      }
      catch(Exception exp){
           response.sendRedirect("login.jsp");
      }
      String uri="jdbc:mysql://127.0.0.1/shop?"+"user=root&password=root&serverTimezone=UTC";
      Connection con; 
      PreparedStatement sql;
      try{ con=DriverManager.getConnection(uri);
          String insertCondition= "INSERT INTO orderform(logname,number,mess,sum,user_id,create_time,pay_time,state)" +
                  " VALUES (?,?,?,?,?,?,?,?)";
          sql=con.prepareStatement(insertCondition);
          sql.setString(1,loginBean.getLogname());
          sql.setString(2,number);
          sql.setString(3,buyGoodsMess);
          sql.setFloat(4,sum);
          sql.setFloat(5,loginBean.getId());
          sql.setFloat(6,time);
          sql.setInt(7,0);
          sql.setInt(8,0);
          sql.executeUpdate();
          LinkedList car=loginBean.getCar();
          car.clear();
          success(request,response,"生产订单成功");
      }
      catch(SQLException exp){
          fail(request,response,"生成订单失败"+exp);
      }
       try{ con=DriverManager.getConnection(uri);
          String Condition = "insert into orders (order_id,goods_id,amount,price,create_time) values (?,?,?,?,?)";
          PreparedStatement sql1 = con.prepareStatement(Condition);
          sql1.setInt(1, loginBean.getId());
          sql1.setString(2, loginBean.getLogname());
          sql1.setInt(3, Integer.parseInt(number));
          sql1.setFloat(4, Float.parseFloat(price));
          sql1.setFloat(5, time);
          sql1.executeUpdate();
       }
       catch(SQLException exp){
       }
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }
   public void success(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
       response.setContentType("text/html;charset=utf8");
        try {
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+backNews+"</h2>") ;
            out.println("返回主页<br>");
            out.println("<br><a href =index.jsp>主页</a>");
            out.println("查看订单<br>");
            out.println("<br><a href =lookOrderForm.jsp>查看订单</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
   public void fail(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=utf8");
        try {
         PrintWriter out=response.getWriter();
         out.println("<html><body>");
         out.println("<h2>"+backNews+"</h2>") ;
         out.println("返回主页");
         out.println("<a href =index.jsp>主页</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
