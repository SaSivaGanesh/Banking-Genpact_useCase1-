<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .result-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }
        .result-container h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .result-container p {
            font-size: 16px;
            color: #dc3545; /* Red color for error messages */
            margin-bottom: 20px;
        }
        .result-container a {
            text-decoration: none;
            color: #4CAF50;
            font-size: 16px;
            font-weight: bold;
        }
        .result-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="result-container">
        <h2>Withdraw Result</h2>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
        <a href="CustomerSuccessDashboard.html">Go Back</a>
    </div>
</body>
</html>
