package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerDashboardRedirectionServlet")
public class CustomerDashboardRedirectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomerDashboardRedirectionServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("POST request received with action: " + action);

        if (action == null) {
            response.sendRedirect("CustomerSuccessDashboard.html");
            return;
        }

        switch (action) {
            case "logout":
            	 HttpSession session = request.getSession(false);
                 if (session != null) {
                     session.invalidate(); // Invalidate the session
                 }
                 response.sendRedirect("Logn.html"); 
                break;
            case "MyDetails":
                response.sendRedirect("MyDetailsServlet");
                break;
            
            case "resetPassword":
                response.sendRedirect("ResetPassword.html");
                break;

            case "balance":
                response.sendRedirect("BalanceServlet");
                break;
            case "deposit":
                response.sendRedirect("Deposit.html");
                break;
            case "withdraw":
                response.sendRedirect("Withdraw.html");
                break;
            case "viewTransactions":
                response.sendRedirect("Last10TransactionsServlet");
                break;
            case "closeAccount":
                response.sendRedirect("CloseAccountServlet");
                break;
            default:
                response.sendRedirect("CustomerSuccessDashboard.html");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GET request received");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for this endpoint.");
    }
}
