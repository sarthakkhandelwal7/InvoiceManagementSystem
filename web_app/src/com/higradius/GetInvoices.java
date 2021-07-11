package com.higradius;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higradius.web.dao.InvoicesDao;

/**
 * Servlet implementation class GetInvoices
 */
@SuppressWarnings("serial")
@WebServlet("/getInvoices")
public class GetInvoices extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		try {
			InvoicesDao invoice_list = new InvoicesDao();
			response.setContentType("application/json");
			response.getWriter().write(invoice_list.getInvoices(start, limit));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
