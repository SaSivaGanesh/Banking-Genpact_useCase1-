<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .dashboard-actions {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px;
        }
        .dashboard-actions form {
            display: inline-block;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .message {
            color: red;
            font-size: 14px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Customer Dashboard</h2>
    <div class="dashboard-actions">
        <!-- Form to view customer details -->
        <form action="MyDetailsServlet" method="post">
            <input type="hidden" name="action" value="MyDetails">
            <button type="submit">View My Details</button>
        </form>
        <!-- Add more forms/actions as needed -->
    </div>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p class="message"><%= message %></p>
    <%
        }
    %>
</body>
</html>
