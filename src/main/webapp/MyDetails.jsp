<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bank.model.Customer" %> <!-- Make sure to replace with the actual package name of your Customer class -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h2 {
            color: #333;
        }
        dl {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }
        dt {
            font-weight: bold;
            color: #555;
            margin-bottom: 5px;
        }
        dd {
            margin: 0 0 10px 0;
            color: #333;
        }
        button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Customer Details</h2>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
        String message = (String) request.getAttribute("message");
    %>
    <% if (customer == null) { %>
        <p><%= message %></p>
    <% } else { %>
        <dl>
            <dt>Name:</dt>
            <dd><%= customer.getName() %></dd>
            
            <dt>Address:</dt>
            <dd><%= customer.getAddress() %></dd>
            
            <dt>Date of Birth:</dt>
            <dd><%= customer.getDob() %></dd>
            
            <dt>Mobile Number:</dt>
            <dd><%= customer.getMobileNumber() %></dd>
            
            <dt>Email:</dt>
            <dd><%= customer.getEmail() %></dd>
            
            <dt>Account Number:</dt>
            <dd><%= customer.getAccountNumber() %></dd>
            
            <dt>Account Type:</dt>
            <dd><%= customer.getAccountType() %></dd>
            
            <dt>Balance:</dt>
            <dd><%= customer.getBalance() %></dd>
            
            <dt>Account Status:</dt>
            <dd><%= customer.getAccountStatus() %></dd>
            
            <dt>Created Date:</dt>
            <dd><%= customer.getCreatedDate() %></dd>
        </dl>
    <% } %>
    <button onclick="window.location.href='CustomerSuccessDashboard.html'">Back to Dashboard</button>
</body>
</html>
