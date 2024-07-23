package bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import bank.dao.TransactionDAO;

@WebServlet("/DownloadTransactionsPDF")
public class DownloadTransactionsPDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Login.html");
            return;
        }

        Long accountNumber = (Long) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            response.sendRedirect("Login.html");
            return;
        }

        TransactionDAO transactionDAO = new TransactionDAO();
        List<String> transactions = transactionDAO.getLast10Transactions(accountNumber);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=transactions.pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Last 10 Transactions"));
            if (transactions != null && !transactions.isEmpty()) {
                for (String transaction : transactions) {
                    document.add(new Paragraph(transaction));
                }
            } else {
                document.add(new Paragraph("No transactions found."));
            }
        } catch (DocumentException e) {
            throw new IOException(e.getMessage(), e);
        } finally {
            document.close();
        }
    }
}
