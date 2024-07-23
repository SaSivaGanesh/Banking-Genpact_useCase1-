package bank.controller;

import bank.dao.AdminCheckTransactionsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminCheckTransactionServlet")
public class AdminCheckTransactionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enteredAccountNumberStr = request.getParameter("accountNumber");
        if (enteredAccountNumberStr == null || enteredAccountNumberStr.isEmpty()) {
            request.setAttribute("errorMessage", "Account number is missing or empty.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        long enteredAccountNumber;
        try {
            enteredAccountNumber = Long.parseLong(enteredAccountNumberStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid account number format.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        AdminCheckTransactionsDAO dao = new AdminCheckTransactionsDAO();
        if (!dao.isAccountExists(enteredAccountNumber)) {
            request.setAttribute("errorMessage", "Invalid account number.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        List<String> transactions = dao.getAllTransactions(enteredAccountNumber);
        if (transactions.isEmpty()) {
            request.setAttribute("errorMessage", "No transactions performed.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("ForAdminDisplayTransactions.jsp").forward(request, response);
        }
    }
}
