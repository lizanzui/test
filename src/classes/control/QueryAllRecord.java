package classes.control;
import classes.data.DataByPage;
import com.sun.rowset.CachedRowSetImpl;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class QueryAllRecord extends HttpServlet {
   CachedRowSetImpl rowSet=null;
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      try {  Class.forName("com.mysql.cj.jdbc.Driver");
      }
      catch(Exception e){} 
   }
   public void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
       request.setCharacterEncoding("utf8");
       String idNumber = request.getParameter("fenleiNumber");
       if (idNumber == null){
           idNumber = "0";
       }
       int id = Integer.parseInt(idNumber);
       HttpSession session = request.getSession(true);
       Connection con = null;
       DataByPage dataBean = null;
       try {
           dataBean = (DataByPage) session.getAttribute("dataBean");
           if (dataBean ==null){
               dataBean = new DataByPage();
               session.setAttribute("dataBean",dataBean);
           }
       }catch (Exception e){
           dataBean = new DataByPage();
           session.setAttribute("dataBean",dataBean);
       }
       String uri = "jdbc:mysql://localhost:3306/shop?user=root&password=root&serverTimezone=UTC";
       try {
           con = DriverManager.getConnection(uri);
           Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
           ResultSet rs;
           if (id == 1){
               rs = sql.executeQuery("SELECT * FROM cosmeticForm");
           }else {
               rs = sql.executeQuery("SELECT * FROM cosmeticForm where id = " + id);
           }
           rowSet = new CachedRowSetImpl();
           rowSet.populate(rs);
           dataBean.setRowSet(rowSet);
           con.close();
       } catch (SQLException e) {
       }
       response.sendRedirect("byPageShow.jsp");
   }
   public void doGet(HttpServletRequest request,
              HttpServletResponse response) 
                        throws ServletException,IOException{
       doPost(request,response);
   }
}

