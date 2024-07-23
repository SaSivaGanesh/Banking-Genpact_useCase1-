package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.CustomerLoginDAO;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Retrieve the session
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if one doesn't exist
        if (session == null) {
            response.sendRedirect("Login.html"); // or another appropriate page
            return;
        }

        // Retrieve account number from session
        Long accountNumber = (Long) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            response.sendRedirect("Login.html"); // or another appropriate page
            return;
        }

        // Retrieve username from session
        String username = (String) session.getAttribute("username");

        CustomerLoginDAO dao = new CustomerLoginDAO();

        // Validate account existence
        if (!dao.isAccountExists(accountNumber)) {
            response.sendRedirect("Failure.html");
            return;
        }

        // Validate the current password and username
        if (!dao.validateUser(accountNumber, username, currentPassword)) {
            response.sendRedirect("Failure.html");
            return;
        }

        // Validate new password and confirmation
        if (!newPassword.equals(confirmPassword)) {
            response.sendRedirect("Failure.html");
            return;
        }

        // Update password
        boolean passwordUpdated = dao.updatePassword(accountNumber, newPassword);

        if (passwordUpdated) {
            response.sendRedirect("PasswordResetSuccess.html");
        } else {
            response.sendRedirect("Failure.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for this endpoint.");
    }
}
