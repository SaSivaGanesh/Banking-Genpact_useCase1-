package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginRedirectServlet")
public class LoginRedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginRedirectServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("customerLogin".equals(action)) {
            response.sendRedirect("CustomerLogin.html");
        } else if ("adminLogin".equals(action)) {
            response.sendRedirect("AdminLogin.html");
        } else {
            response.sendRedirect("Failure.html"); // Redirect to an error page if action is not recognized
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
