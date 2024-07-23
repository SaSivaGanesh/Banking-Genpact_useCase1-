package bank.controller;

import bank.dao.CustomerLoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerLoginDAO customerLoginDAO;

    public WithdrawServlet() {
        this.customerLoginDAO = new CustomerLoginDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the session
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

        // Retrieve withdrawal amount from request
        String withdrawAmountStr = request.getParameter("withdrawAmount");
        if (withdrawAmountStr == null || withdrawAmountStr.isEmpty()) {
            response.sendRedirect("Withdraw.html"); // Redirect to the withdraw page if the amount is not provided
            return;
        }

        double withdrawAmount = Double.parseDouble(withdrawAmountStr);

        // Get the current balance
        double currentBalance = customerLoginDAO.getBalance(accountNumber);
        if (currentBalance < withdrawAmount) {
            request.setAttribute("message", "Insufficient funds. Your current balance is " + currentBalance);
            request.getRequestDispatcher("Withdraw.jsp").forward(request, response);
            return;
        }

        // Update the balance
        boolean isUpdated = customerLoginDAO.updateBalance(accountNumber, -withdrawAmount);
        if (isUpdated) {
            // Update the balance again to get the new balance after withdrawal
            double newBalance = customerLoginDAO.getBalance(accountNumber);
            request.setAttribute("message", "Withdrawal successful. Your new balance is " + newBalance);
        } else {
            request.setAttribute("message", "Withdrawal failed. Please try again.");
        }
        request.getRequestDispatcher("Withdraw.jsp").forward(request, response);
    }
}
