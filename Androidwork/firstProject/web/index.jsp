<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2016/9/5
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%
  try{Class.forName("com.mysql.jdbc.Driver");
    String a="1";
  String dburl="jdbc:mysql://localhost:3306/firstproject";
  Connection conn= DriverManager.getConnection(dburl,"root","1234");
  Statement stmt=conn.createStatement();
  String sql="SELECT * FROM firstproject.loginin WHERE phonenumber="+a;
  ResultSet rst=stmt.executeQuery(sql);
    while(rst.next()){
    out.println(rst.getString(1)+"     "+rst.getString(2)+"<br>");}
  stmt.close();
  conn.close();


//    String sql="insert into firstproject.circlehave (user,circle,introduce,builder) values (?,?,?,?)";
//    PreparedStatement ps=conn.prepareStatement(sql);
//    ps.setString(1,"1");
//    ps.setString(2,"游戏爱好圈");
//    ps.setString(3,"游戏者聚集群");
//    ps.setString(4,"1");
//    //需返回处理结果int
//    int i=ps.executeUpdate();
//    String s = String.valueOf(i);
//    char c = s.charAt(0);
//    response.getWriter().append(c);
//    ps.close();
//    conn.close();



  }
  catch (ClassNotFoundException e){
    out.println(e);
  } catch (SQLException e) {
    out.println(e);
  }%>
  </body>
</html>
