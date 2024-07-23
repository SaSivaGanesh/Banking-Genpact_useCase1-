<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Created Successfully</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(to right, #6dd5ed, #2193b0);
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 90%;
            max-width: 500px;
            text-align: center;
        }
        .container h2 {
            margin-bottom: 20px;
            color: #333;
            font-size: 24px;
        }
        .container p {
            margin-bottom: 10px;
            color: #555;
            font-size: 16px;
        }
        .container a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }
        .container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Account Created Successfully!</h2>
        <p>Name: <%= request.getAttribute("name") %></p>
        <p>Address: <%= request.getAttribute("address") %></p>
        <p>Mobile: <%= request.getAttribute("mobile") %></p>
        <p>Email: <%= request.getAttribute("email") %></p>
        <p>Date of Birth: <%= request.getAttribute("dob") %></p>
        <p>Balance: <%= request.getAttribute("balance") %></p>
        <p>Account Number: <%= request.getAttribute("accountNumber") %></p>
        <p>Account Type: <%= request.getAttribute("accountType") %></p> <!-- New line for account type -->
        <p>Password: Not displayed for security reasons</p>
        <a href="AdminSuccessDashboard.html">Back to Dashboard</a>
    </div>
</body>
</html>
