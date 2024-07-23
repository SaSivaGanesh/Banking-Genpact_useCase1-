package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.dao.CreateAccountDAO;
import bank.model.CreateAccountforUser;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateAccountServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        double balance = Double.parseDouble(request.getParameter("balance"));
        String password = request.getParameter("password");
        String accountType = request.getParameter("account_type"); // New field
        String createdDate = request.getParameter("created_date"); // Optional created_date

        // Create a User object
        CreateAccountforUser user = new CreateAccountforUser(name, address, mobile, email, dob, balance, password);
        user.setAccountType(accountType);

        // Call DAO to save user
        CreateAccountDAO dao = new CreateAccountDAO();
        String accountNumber = dao.addUser(user, createdDate); // Pass createdDate to DAO
        user.setAccountNumber(accountNumber);

        // Set attributes to pass to JSP
        request.setAttribute("name", user.getName());
        request.setAttribute("address", user.getAddress());
        request.setAttribute("mobile", user.getMobile());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("dob", user.getDob());
        request.setAttribute("balance", user.getBalance());
        request.setAttribute("accountNumber", user.getAccountNumber());
        request.setAttribute("accountType", user.getAccountType());

        // Forward to JSP page to display account details
        request.getRequestDispatcher("AccountCreationSuccessUserDetails.jsp").forward(request, response);
    }
}
