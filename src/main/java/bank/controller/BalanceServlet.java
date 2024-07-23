package bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.CustomerLoginDAO;

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        // Create DAO instance and get the balance
        CustomerLoginDAO dao = new CustomerLoginDAO();
        double balance = dao.getBalance(accountNumber);

        // Prepare the response
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Balance Details</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Balance Details</h2>");
            out.println("<p>Account Number: " + accountNumber + "</p>");
            out.println("<p>Balance: " + (balance != 0 ? balance : "0.00") + "</p>");
            out.println("<a href=\"CustomerSuccessDashboard.html\">Back to Dashboard</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
