package classes.control;
import classes.data.Login;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class PutGoodsToCar extends HttpServlet {
   public void init(ServletConfig config) throws ServletException { 
      super.init(config);
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
      request.setCharacterEncoding("utf8");
      String goods = request.getParameter("java");
      String text = request.getParameter("number");
      Login loginBean=null;
      HttpSession session=request.getSession(true);
      try{  loginBean=(Login)session.getAttribute("loginBean");
            boolean b =loginBean.getLogname()==null||
            loginBean.getLogname().length()==0;
            if(b)
              response.sendRedirect("login.jsp");
            LinkedList<String> car = loginBean.getCar();
            car.add(goods+"#"+text);
            speakSomeMess(request,response,goods,text);
      }
      catch(Exception exp){
           response.sendRedirect("login.jsp");
      }
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }
   public void speakSomeMess(HttpServletRequest request,
                     HttpServletResponse response,String goods,String text) {
       response.setContentType("text/html;charset=utf8");
        try {
         PrintWriter out=response.getWriter();
         out.println("<html><body>");
         out.println("<h2>"+goods+"#"+text+"件"+"放入购物车</h2>") ;
         out.println("查看购物车或返回浏览商品<br>");
         out.println("<a href = lookShoppingCar.jsp>查看购物车</a>");
         out.println("<br><a href =byPageShow.jsp>浏览商品</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){}
   }
}
