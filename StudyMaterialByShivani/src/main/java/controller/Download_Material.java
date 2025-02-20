package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;

import java.io.*;
import java.io.InputStream;
import java.sql.*;

/**
 * Servlet implementation class Download_Material
 */
@WebServlet("/Download_Material")
public class Download_Material extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String fileId = request.getParameter("id");

	        try (Connection conn = DBConnection.getConnection()) {
	            PreparedStatement ps = conn.prepareStatement("SELECT name, file FROM materials WHERE id=?");
	            ps.setInt(1, Integer.parseInt(fileId));
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                String fileName = rs.getString("name");
	                Blob fileBlob = rs.getBlob("file");
	                InputStream fileContent = fileBlob.getBinaryStream();

	                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	                OutputStream out = response.getOutputStream();
	                byte[] buffer = new byte[1024];
	                int bytesRead;
	                while ((bytesRead = fileContent.read(buffer)) != -1) {
	                    out.write(buffer, 0, bytesRead);
	                }
	                out.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
