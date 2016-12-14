import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;
import java.lang.*;
import javax.sql.DataSource;

/**
 * Created by dell on 2016/9/5.
 */
@WebServlet(name = "Main")
public class Main extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str1, str2,str3,key=null;
        String[] NameAndIntroduce;
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        str1 = request.getHeader("para1");
        str2 = request.getHeader("para2");
        str3 = request.getHeader("para3");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String dburl="jdbc:mysql://localhost:3306/firstproject";
            Connection conn=DriverManager.getConnection(dburl,"root","1234");
            Statement stmt=conn.createStatement();
            if("get_circle".equals(str3)){
                String sql="SELECT * FROM firstproject.circlehave where user="+str1;
                ResultSet rst=stmt.executeQuery(sql);
                int temp=0;

                while(rst.next()){
                    temp++;
                    response.getWriter().append(rst.getString(2)+" ");
                }
                if(temp==0){
                    response.getWriter().append(" ");
                }
                stmt.close();
                conn.close();
            }
            else if("login".equals(str3)){
                String sql="SELECT * FROM firstproject.loginin where phonenumber="+str1;
                ResultSet rst=stmt.executeQuery(sql);
                while(rst.next()){ key=rst.getString(2);break;}
                if(str2.equals(key)){
                    response.getWriter().append('1');
                }else{
                    response.getWriter().append('0');
                }
                stmt.close();
                conn.close();
            }
            else if("get-other-circle".equals(str3)){
                String sql="SELECT * FROM firstproject.circlehave where user<>"+str1;
                ResultSet rst=stmt.executeQuery(sql);
                int temp=0;

                while(rst.next()){
                    temp++;
                    response.getWriter().append(rst.getString(2)+" ");
                }
                if(temp==0){
                    response.getWriter().append(" ");
                }
                stmt.close();
                conn.close();
            }
            else if("build_circle".equals(str3)){
//                File debug = new File("C:\\Users\\dell\\Desktop\\debug.txt");
//                debug.createNewFile();
//                PrintWriter out = new PrintWriter(debug);
//                out.println("message in server: " + URLDecoder.decode(str2, "UTF-8"));
//                out.flush();
//                out.close();
                NameAndIntroduce=new String[2];
                NameAndIntroduce=URLDecoder.decode(str2, "UTF-8").split("  !-!  ");
                String sql="insert into firstproject.circlehave (user,circle,introduce,builder) values (?,?,?,?)";
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,str1);
                ps.setString(2,NameAndIntroduce[0]);
                ps.setString(3,NameAndIntroduce[1]);
                ps.setString(4,str1);
                //需返回处理结果int
                int i=ps.executeUpdate();
                String s = String.valueOf(i);
                char c = s.charAt(0);
                response.getWriter().append(c);
                ps.close();
                stmt.close();
                conn.close();

            }
            else if("get_circle_have_build".equals(str3)){
                String sql="SELECT * FROM firstproject.circlehave where builder="+str1;
                ResultSet rst=stmt.executeQuery(sql);
                int temp=0;
                while(rst.next()){
                    temp++;
                    response.getWriter().append(rst.getString(2)+" ");
                }
                if(temp==0){
                    response.getWriter().append(" ");
                }
                stmt.close();
                conn.close();
            }
            else if("circle_enjoy_introduce".equals(str3)){

//                String string="信息：";
//                String s="";
//                File debug = new File("C:\\Users\\dell\\Desktop\\debug.txt");
//                debug.createNewFile();
//                PrintWriter out = new PrintWriter(debug);
//                out.println("创建完成");

                str2=URLDecoder.decode(str2, "UTF-8");

//                out.println(str2);

                String sql="SELECT * FROM firstproject.circlehave where circle=\'"+str2+"\'";
                ResultSet rst=stmt.executeQuery(sql);
                while(rst.next()){
                    response.getWriter().append(rst.getString(3));
//                    s=rst.getString(3);
                    break;
                }

//                string=string+"str2="+str2+"返回值为："+s;
//                out.println(string);
//                out.flush();
//                out.close();

                stmt.close();
                conn.close();
            }

            else if("circle_delete_introduce".equals(str3)){
                str2=URLDecoder.decode(str2, "UTF-8");
                String sql="SELECT * FROM firstproject.circlehave where circle=\'"+str2+"\'";
                ResultSet rst=stmt.executeQuery(sql);
                while(rst.next()){
                    response.getWriter().append(rst.getString(3));
                    break;
                }
                stmt.close();
                conn.close();
            }

            else if("circle_enjoy_enjoy".equals(str3)){
                String builder,introduce;
                builder="";
                introduce="";
                str2=URLDecoder.decode(str2, "UTF-8");
                String sql1="SELECT * FROM firstproject.circlehave where circle=\'"+str2+"\'";
                ResultSet rst=stmt.executeQuery(sql1);
                while(rst.next()){
                    introduce=rst.getString(3);
                    builder=rst.getString(4);
                    break;
                }
                stmt.close();
                conn.close();

                Connection conn1=DriverManager.getConnection(dburl,"root","1234");
                String sql="insert into firstproject.circlehave (user,circle,introduce,builder) values (?,?,?,?)";
                PreparedStatement ps=conn1.prepareStatement(sql);
                ps.setString(1,str1);
                ps.setString(2,str2);
                ps.setString(3,introduce);
                ps.setString(4,builder);
                //需返回处理结果int
                int i=ps.executeUpdate();
                String s = String.valueOf(i);
                char c = s.charAt(0);
                response.getWriter().append(c);
                ps.close();
                conn1.close();
            }

            else if("circle_delete_sure".equals(str3)){
                str2=URLDecoder.decode(str2, "UTF-8");
                String sql1="DELETE FROM firstproject.circlehave where circle=\'"+str2+"\'";
                stmt.executeQuery(sql1);
                stmt.close();
                conn.close();
            }


        }catch (ClassNotFoundException e){
            System.out.println(e);
            response.getWriter().append('2');
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().append('3');
        }

//        response.getWriter().append(res.toString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
