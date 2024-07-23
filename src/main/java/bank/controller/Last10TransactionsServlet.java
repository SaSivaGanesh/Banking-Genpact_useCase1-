package bank.controller;

import bank.dao.TransactionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Last10TransactionsServlet")
public class Last10TransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve session
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if one doesn't exist
        if (session == null) {
            response.sendRedirect("Login.html"); // Redirect to login or an appropriate page if session is not found
            return;
        }

        // Retrieve account number from session
        Long accountNumber = (Long) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            response.sendRedirect("Login.html"); // Redirect to login or an appropriate page if account number is not found in session
            return;
        }

        // Perform DAO operations
        TransactionDAO transactionDAO = new TransactionDAO();

        // Check if account number exists in both user_details and transactions tables
        if (transactionDAO.isAccountExists(accountNumber) && transactionDAO.hasTransactions(accountNumber)) {
            // Fetch last 10 transactions
            List<String> transactions = transactionDAO.getLast10Transactions(accountNumber);

            // Set attributes for JSP
            request.setAttribute("accountNumber", accountNumber);
            request.setAttribute("transactions", transactions);

            // Forward to JSP to display transactions
            request.getRequestDispatcher("Last10Transactions.jsp").forward(request, response);
        } else {
            // Account number not found or no transactions exist
            response.sendRedirect("InvalidAccount.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
