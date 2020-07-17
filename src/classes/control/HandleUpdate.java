package classes.control;

import classes.data.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

public class HandleUpdate extends HttpServlet {
   public void init(ServletConfig config) throws ServletException { 
      super.init(config);
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
      request.setCharacterEncoding("utf8");
      String goods = request.getParameter("delete");
      Login loginBean=null;
      String number = request.getParameter("newnumber");
      HttpSession session=request.getSession(true);
      try{  loginBean=(Login)session.getAttribute("loginBean");
            boolean b =loginBean.getLogname()==null||
            loginBean.getLogname().length()==0;
            if(b)
              response.sendRedirect("login.jsp");
            LinkedList<String> car = loginBean.getCar();
            car.remove(goods);
          int index=goods.lastIndexOf("#");
          String newGoods = goods.substring(0,index);
          car.add(newGoods+"#"+number);
      }
      catch(Exception exp){
           response.sendRedirect("login.jsp");
      }
      RequestDispatcher dispatcher= 
      request.getRequestDispatcher("lookShoppingCar.jsp");
      dispatcher.forward(request, response);
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }
}
