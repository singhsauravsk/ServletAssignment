package com.zycus.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet will process the request for account creation.
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/new-account.do")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("new-account.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String title = request.getParameter("title");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Welcome</title>");
		out.println("</head>");
		out.println("<body>");
		
		if(title == null) {
			out.println("<h3> Your title is not valid </h3>");
			out.println("<a href = \"new-account.html\">");
			out.println("<button type = \"button\"> Try Again </button> </a>");
		}
		else if(firstName.length() < 3 || lastName.length() < 3) {
			
			out.println("<h3> Your name is not valid </h3>");
			out.println("<a href = \"new-account.html\">");
			out.println("<button type = \"button\"> Try Again </button> </a>");
		} else if(dob == null){
			out.println("<h3> Your date of birth is not valid </h3>");
			out.println("<a href = \"new-account.html\">");
			out.println("<button type = \"button\"> Try Again </button> </a>");
		}
		else {
			Integer age = findAge(dob);
			
			if(age < 18) {
				out.println("<h3> You are under age </h3>");
				out.println("<a href = \"new-account.html\">");
				out.println("<button type = \"button\"> Try Again </button> </a>");
			}
			else {
				out.println("<h1> Welcome to Virtual Bank </h1> </br>");
				out.println("<h3> Name : " + title + " " + firstName + " " + lastName + ". Your account creation is under processing.</h3>");
			}
			
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	private Integer findAge(String dob) {
		int month[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		Integer currentDate = Calendar.getInstance().get(Calendar.DATE);
		Integer dobYear = Integer.parseInt(dob.substring(0, 4));
		Integer dobMonth = Integer.parseInt(dob.substring(5, 7));
		Integer dobDate = Integer.parseInt(dob.substring(8));
		
		if (dobDate > currentDate) {
			currentDate = currentDate + month[dobMonth - 1];
			currentMonth = currentMonth - 1;
		}

		if (dobMonth > currentMonth) {
			currentYear = currentYear - 1;
			currentMonth = currentMonth + 12;
		}
		/*System.out.println(currentDate);
		System.out.println(currentMonth);
		System.out.println(currentYear);
		System.out.println(dobDate);
		System.out.println(dobMonth);
		System.out.println(dobYear);*/
		
		return currentYear - dobYear;
	}
} 
