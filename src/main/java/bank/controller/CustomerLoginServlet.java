package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.dao.CustomerLoginDAO;
import bank.model.LoggedInUsers;

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomerLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String inputPassword = request.getParameter("password"); // Retrieve the password input by the user
        String accountNumberStr = request.getParameter("accountNumber");

        long accountNumber = Long.parseLong(accountNumberStr);

        CustomerLoginDAO dao = new CustomerLoginDAO();

        if (dao.isAccountExists(accountNumber)) {
            String accountStatus = dao.getAccountStatus(accountNumber);

            if ("open".equals(accountStatus)) {
                boolean isValidUser = dao.validateUser(accountNumber, username, inputPassword);

                if (isValidUser) {
                    // If valid user, create a session
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("accountNumber", accountNumber);

                    // Add account number to logged-in users
                    LoggedInUsers.addAccount(accountNumber);

                    // Redirect to success dashboard
                    response.sendRedirect("CustomerSuccessDashboard.html");
                } else {
                    // Invalid credentials, redirect to failure page
                    response.sendRedirect("Failure.html");
                }
            } else if ("closed".equals(accountStatus)) {
                // Account is closed, redirect to closed account page
                response.sendRedirect("AccountClosed.html");
            }
        } else {
            // Account does not exist, redirect to failure page
            response.sendRedirect("Failure.html");
        }
    }
}
