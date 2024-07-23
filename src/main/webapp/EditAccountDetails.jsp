<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Account Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            box-sizing: border-box;
        }
        h2 {
            margin-top: 0;
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        button {
            width: 100%;
            padding: 12px;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Account Details</h2>
        <form action="SaveEditedAccountServlet" method="post">
            <input type="hidden" name="accountNumber" value="${accountNumber}">
            
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${name}" required>
            
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${address}" required>
            
            <label for="mobile">Mobile:</label>
            <input type="text" id="mobile" name="mobile" value="${mobile}" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${email}" required>
            
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${dob}" required>
            
            <label for="accountType">Account Type:</label>
            <select id="accountType" name="accountType" required>
                <option value="Savings" ${accountType == 'Savings' ? 'selected' : ''}>Savings</option>
                <option value="Current" ${accountType == 'Current' ? 'selected' : ''}>Current</option>
            </select>
            
            <label for="accountStatus">Account Status:</label>
            <select id="accountStatus" name="accountStatus" required>
                <option value="Open" ${accountStatus == 'Open' ? 'selected' : ''}>Open</option>
                <option value="Closed" ${accountStatus == 'Closed' ? 'selected' : ''}>Closed</option>
            </select>
            
            <button type="submit">Update Account</button>
        </form>
    </div>
</body>
</html>
