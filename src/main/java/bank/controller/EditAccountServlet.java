package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.dao.EditAccountDAO;
import bank.model.CreateAccountforUser;

@WebServlet("/EditAccountServlet")
public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditAccountServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        EditAccountDAO dao = new EditAccountDAO();
        CreateAccountforUser account = dao.getAccountDetails(accountNumber);

        if (account != null) {
            request.setAttribute("name", account.getName());
            request.setAttribute("address", account.getAddress());
            request.setAttribute("mobile", account.getMobile());
            request.setAttribute("email", account.getEmail());
            request.setAttribute("dob", account.getDob());
            request.setAttribute("balance", account.getBalance());
            request.setAttribute("accountNumber", account.getAccountNumber());
            request.setAttribute("accountType", account.getAccountType());
            request.setAttribute("accountStatus", account.getAccountStatus());
            request.getRequestDispatcher("EditAccountDetails.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
