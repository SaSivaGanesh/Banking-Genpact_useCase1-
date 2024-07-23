package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.AdminLoginDAO;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminLoginDAO loginDAO;

    public void init() {
        loginDAO = new AdminLoginDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (loginDAO.validate(username, password)) {
            // Create or obtain the session
            HttpSession session = request.getSession(true);
            // Store the username in the session
            session.setAttribute("username", username);
            // Redirect to the success page
            response.sendRedirect("AdminSuccessDashboard.html");
        } else {
            // Redirect to the failure page
            response.sendRedirect("Failure.html");
        }
    }
}
