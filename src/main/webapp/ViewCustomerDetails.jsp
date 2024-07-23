<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            height: 100vh;
            align-items: center;
            justify-content: center;
        }
        .container {
            width: 80%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        .header {
            width: 100%;
            padding: 10px;
            display: flex;
            justify-content: flex-end;
            background-color: #4CAF50;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .header button {
            background-color: #f44336;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .header button:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
    <div class="header">
        <form action="CustomerDashboardRedirectionServlet" method="post">
            <input type="hidden" name="action" value="logout">
            <button type="submit">Logout</button>
        </form>
    </div>
    <div class="container">
        <h1>Customer Details</h1>
        <table>
            <tr>
                <th>Name</th>
                <td>${customer.name}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${customer.address}</td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td>${customer.dob}</td>
            </tr>
            <tr>
                <th>Mobile Number</th>
                <td>${customer.mobileNumber}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${customer.email}</td>
            </tr>
            <tr>
                <th>Account Number</th>
                <td>${customer.accountNumber}</td>
            </tr>
            <tr>
                <th>Account Type</th>
                <td>${customer.accountType}</td>
            </tr>
        </table>
    </div>
</body>
</html>
