<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%> 
    <%@ page import="java.io.*"%> 
    <%@ page import="util.DBConnection" %> 

<!DOCTYPE html>
<html>
<head>
    <title>Material JAVA by Shivani Gajjar</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; background-color: #f3f3f3; }
        .header { background-color: purple; color: white; padding: 15px; font-size: 24px; }
        .footer { background-color: purple; color: white; padding: 10px; position: fixed; bottom: 0; width: 100%; }
        .container { margin: 50px auto; width: 70%; background: white; padding: 20px; border-radius: 10px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; border: 1px solid black; }
    </style>
</head>
<body>
    <div class="header">Material JAVA by Shivani Gajjar</div>
    <div class="container">
        <h2>Available Materials</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Download</th>
            </tr>
            <% 
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM materials");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) { 
            %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("name") %></td>
                <td><a href="Download_Material?id=<%= rs.getInt("id") %>">Download</a></td>
            </tr>
            <% } %>
        </table>
    </div>
    <div class="footer">Contact: shivanigajjar98@gmail.com</div>
</body>
</html>
