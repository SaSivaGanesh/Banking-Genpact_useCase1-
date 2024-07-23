<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Last 10 Transactions</title>
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
        h1 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: 20px 0;
            width: 100%;
            max-width: 600px;
        }
        li {
            background: #fff;
            margin: 5px 0;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        a:hover {
            background-color: #45a049;
        }
        form {
            margin-top: 20px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Last 10 Transactions</h1>
    <ul>
        <% 
            List<String> transactions = (List<String>) request.getAttribute("transactions");
            if (transactions != null && !transactions.isEmpty()) {
                for (String transaction : transactions) {
                    out.println("<li>" + transaction + "</li>");
                }
            } else {
                out.println("<li>No transactions found.</li>");
            }
        %>
    </ul>
    <form action="DownloadTransactionsPDF" method="post">
        <input type="submit" value="Download Transactions as PDF" />
    </form>
    <a href="CustomerSuccessDashboard.html">Back to Home</a>
</body>
</html>
