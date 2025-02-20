<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <title>Admin - Upload Material</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; background-color: #f3f3f3; }
        .header { background-color: purple; color: white; padding: 15px; font-size: 24px; }
        .footer { background-color: purple; color: white; padding: 10px; position: fixed; bottom: 0; width: 100%; }
        .container { margin: 50px auto; width: 50%; background: white; padding: 20px; border-radius: 10px; }
    </style>
</head>

<body>
    <div class="header">Material JAVA by Shivani Gajjar</div>
    <div class="container">
        <h2>Upload Material</h2>
        <form action="upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" required>
            <button type="submit">Upload</button>
        </form>
    </div>
    <div class="footer">Contact: shivanigajjar98@gmail.com</div>
</body>
</html>