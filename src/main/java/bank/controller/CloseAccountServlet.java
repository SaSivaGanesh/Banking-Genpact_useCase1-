package bank.controller;

import bank.dao.CustomerLoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve session
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session
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

        CustomerLoginDAO dao = new CustomerLoginDAO();

        // Check if balance is 0.00 and update account status
        boolean closed = dao.closeAccount(accountNumber);
        if (closed) {
            response.sendRedirect("AccountClosedSuccess.html"); // Redirect to success page
        } else {
            response.sendRedirect("AccountCloseFailure.html"); // Redirect back to close account page with error message
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
