package com.zycus.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BillPayServlet
 */
@WebServlet("/bill-pay.do")
public class BillPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("bill-pay.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String billType = request.getParameter("billType");
		String amountString = request.getParameter("amount");
		
		if(billType == null) {
			out.println("<h4> Please! Select a bill type </h4>");
		}
		else if(billType.equals("electricbillPay")) {
			String board = request.getParameter("electricityBoard");
			
			if(board.equals("Select")) {
				out.println("<h4> Please! Select an electricity board </h4>");
			}
			else {
				
				if(amountString == null) {
					out.println("<h4> Please! Enter Amount </h4>");
				}
				else {
					
					try {
						int amount = Integer.parseInt(amountString);
						out.println("<h4> Your electricity bill for " + board + " of " + amount + " is paid successfully. </h4>");
					} catch(NumberFormatException e) {
						System.out.println(e.getMessage());
						out.println("<h4> Please! Enter Amount in number format </h4>");
					}
					
				}
			}
		}
		else {
			String network = request.getParameter("phoneNetwork");
			
			if(network.equals("Select")) {
				out.println("<h4> Please! Select a phone network </h4>");
			}
			else {

				if(amountString == null) {
					out.println("<h4> Please! Enter Amount </h4>");
				}
				else {
					
					try {
						int amount = Integer.parseInt(amountString);
						out.println("<h4> Your phone bill for " + network + " of " + amount + " is paid successfully. </h4>");
					}catch(NumberFormatException e) {
						System.out.println(e.getMessage());
						out.println("<h4> Please! Enter Amount in number format </h4>");
					}
				}
			}
		}
		out.close();
	}

}
