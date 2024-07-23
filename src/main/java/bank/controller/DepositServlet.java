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

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
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

        // Retrieve deposit amount from request
        double amount = Double.parseDouble(request.getParameter("amount"));

        // Create DAO instance and update balance
        CustomerLoginDAO dao = new CustomerLoginDAO();
        boolean isUpdated = dao.updateBalance(accountNumber, amount);

        // Prepare the response
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            if (isUpdated) {
                out.println("<h2>Deposit Successful</h2>");
            } else {
                out.println("<h2>Deposit Failed</h2>");
            }
            out.println("<a href='CustomerSuccessDashboard.html'>Back to Dashboard</a>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
