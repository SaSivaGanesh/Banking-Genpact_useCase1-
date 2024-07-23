package bank.controller;

import bank.dao.CustomerLoginDAO;
import bank.model.Customer; // Adjust import to use Customer class

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MyDetailsServlet")
public class MyDetailsServlet extends HttpServlet {
    private CustomerLoginDAO customerLoginDAO;

    public MyDetailsServlet() {
        this.customerLoginDAO = new CustomerLoginDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the account number from the session
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if one doesn't exist
        if (session != null) {
            Long accountNumber = (Long) session.getAttribute("accountNumber");
           // System.out.println(accountNumber);
            if (accountNumber != null) {
                // Use the account number to get user details
                Customer customer = customerLoginDAO.getUserDetails(accountNumber);

                if (customer != null) {
                    request.setAttribute("customer", customer);
                    request.getRequestDispatcher("MyDetails.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "User details not found. Please contact support.");
                    request.getRequestDispatcher("DetailsNotFound.jsp").forward(request, response);
                }
            } else {
                // Account number not found in session, redirect to login or error page
                response.sendRedirect("Login.html"); // or some other appropriate page
            }
        } else {
            // Session does not exist, redirect to login or error page
            response.sendRedirect("Login.html"); // or some other appropriate page
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Delegate POST requests to doGet
    }
}
