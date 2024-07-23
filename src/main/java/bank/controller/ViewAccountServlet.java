package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ViewAccountDAO;
import bank.model.CreateAccountforUser; // Correct import for CreateAccountforUser

@WebServlet("/ViewAccountServlet")
public class ViewAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewAccountServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        ViewAccountDAO dao = new ViewAccountDAO();
        CreateAccountforUser accountDetails = dao.getAccountDetails(accountNumber); // Adjusted to CreateAccountforUser

        if (accountDetails != null) {
            // Set attributes to display in JSP page
            request.setAttribute("accountNumber", accountNumber);
            request.setAttribute("name", accountDetails.getName());
            request.setAttribute("address", accountDetails.getAddress());
            request.setAttribute("mobile", accountDetails.getMobile());
            request.setAttribute("email", accountDetails.getEmail());
            request.setAttribute("dob", accountDetails.getDob());
            request.setAttribute("balance", accountDetails.getBalance());
            request.setAttribute("accountType", accountDetails.getAccountType());
            request.setAttribute("createdDate", accountDetails.getCreatedDate()); // Set createdDate

            // Forward to the AccountDetails.jsp page to display account details
            request.getRequestDispatcher("AccountDetails.jsp").forward(request, response);
        } else {
            // Account number not found
            request.setAttribute("error", "Account number not found.");
            request.getRequestDispatcher("ViewAccount.html").forward(request, response);
        }
    }
}
