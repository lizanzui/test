package classes.control;
import classes.data.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class HandleRegister extends HttpServlet {
   public void init(ServletConfig config) throws ServletException { 
      super.init(config);
      try {  Class.forName("com.mysql.cj.jdbc.Driver");
      }
      catch(Exception e){} 
   }
   public String handleString(String s)
   {   try{ byte bb[]=s.getBytes("utf8");
            s=new String(bb);
       }
       catch(Exception ee){} 
       return s;  
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
      String uri="jdbc:mysql://localhost:3306/shop?"+
                             "user=root&password=root&serverTimezone=UTC";
      Connection con; 
      PreparedStatement sql; 
      Register userBean=new Register();
      request.setAttribute("userBean",userBean);
      String logname=request.getParameter("logname").trim();
      String password=request.getParameter("password").trim();
      String again_password=request.getParameter("again_password").trim();
      String phone=request.getParameter("phone").trim();
      String address=request.getParameter("address").trim();
      String realname=request.getParameter("realname").trim();
      if(logname==null)
           logname="";
      if(password==null)
           password="";
      if(!password.equals(again_password)) { 
         userBean.setBackNews("密码不相同");
         RequestDispatcher dispatcher= 
         request.getRequestDispatcher("inputRegisterMess.jsp");
         dispatcher.forward(request, response);
         return;
      }
      boolean isLD=true;
      for(int i=0;i<logname.length();i++){
          char c=logname.charAt(i);
           if(!((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0'))) 
             isLD=false;
      } 
      boolean boo=logname.length()>0&&password.length()>0&&isLD;
      String backNews="";
      try{   con=DriverManager.getConnection(uri);
             String insertCondition="INSERT INTO user (logname,password,phone,address,realname)  VALUES (?,?,?,?,?)";
             sql=con.prepareStatement(insertCondition);
             if(boo)
             { sql.setString(1,handleString(logname));
               sql.setString(2,handleString(password));
               sql.setString(3,handleString(phone));
               sql.setString(4,handleString(address));
               sql.setString(5,handleString(realname));
               int m=sql.executeUpdate();
               if(m!=0){
                  backNews="注册成功";
                  userBean.setBackNews(backNews);
                  userBean.setLogname(logname);
                  userBean.setPhone(handleString(phone));
                  userBean.setAddress(handleString(address));
                  userBean.setRealname(handleString(realname));
               }
             }
             else {
                 backNews="信息填写不完整或名字中有非法字符";
                 userBean.setBackNews(backNews);  
             }
             con.close();
      }
      catch(SQLException exp){
             backNews="名字重复"+exp;
             userBean.setBackNews(backNews); 
      }
      RequestDispatcher dispatcher= 
      request.getRequestDispatcher("inputRegisterMess.jsp");
      dispatcher.forward(request, response);
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }
}