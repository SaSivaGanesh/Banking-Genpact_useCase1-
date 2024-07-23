package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.dao.EditAccountDAO;
import bank.model.CreateAccountforUser;

@WebServlet("/SaveEditedAccountServlet")
public class SaveEditedAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SaveEditedAccountServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String accountType = request.getParameter("accountType");
        String accountStatus = request.getParameter("accountStatus");

        CreateAccountforUser editedAccount = new CreateAccountforUser(name, address, mobile, email, dob, 0, "", accountNumber, accountType, accountStatus);

        EditAccountDAO dao = new EditAccountDAO();
        boolean success = dao.saveEditedAccountDetails(editedAccount);

        if (success) {
            response.sendRedirect("ViewAccount.html");
        } else {
            request.setAttribute("error", "Failed to update account details.");
            request.getRequestDispatcher("EditAccountDetails.jsp").forward(request, response);
        }
    }
}
