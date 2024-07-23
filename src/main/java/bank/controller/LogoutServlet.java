package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.model.LoggedInUsers;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Long accountNumber = (Long) session.getAttribute("accountNumber");
            if (accountNumber != null) {
                // Remove the account number from the logged-in users list
                LoggedInUsers.removeAccount(accountNumber);
            }
            session.invalidate();
        }
        response.sendRedirect("Logn.html");
    }
}
