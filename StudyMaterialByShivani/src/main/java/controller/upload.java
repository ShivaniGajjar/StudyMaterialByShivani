package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/upload")
@MultipartConfig // Ensures the servlet handles multipart form data
public class upload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieving the file part from the request
        if (filePart == null) {
            response.sendRedirect("admin.jsp?upload=failed&error=noFile");
            return;
        }

        String fileName = filePart.getSubmittedFileName(); // Extract the file name from the part
        InputStream fileContent = filePart.getInputStream(); // Get the input stream for the file content

        // Handle database interaction within a try-with-resources block to automatically close the connection
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO materials (name, file) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, fileName);  // Set the file name in the database
                ps.setBlob(2, fileContent);  // Set the file content (as a Blob) in the database
                ps.executeUpdate(); // Execute the update
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?upload=failed&error=databaseError");
            return;
        }

        // After successful upload, redirect to the admin page with a success message
        response.sendRedirect("admin.jsp?upload=success");
    }
}
