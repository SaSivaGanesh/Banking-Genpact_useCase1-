package bank.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.model.LoggedInUsers;

@WebServlet("/PrintLoggedInAccounts")
public class PrintLoggedInAccountsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PrintLoggedInAccountsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the list of logged-in account numbers
        Set<Long> loggedInAccounts = LoggedInUsers.getLoggedInAccounts();

        // Print the list of account numbers to the response
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Logged-In Accounts</h1>");
        response.getWriter().println("<ul>");
        for (Long accountNumber : loggedInAccounts) {
            response.getWriter().println("<li>Account Number: " + accountNumber + "</li>");
        }
        response.getWriter().println("</ul>");
        response.getWriter().println("</body></html>");
    }
}
