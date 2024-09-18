package com.hms.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.entity.User; // Ensure that User class exists and is properly imported

@WebServlet("/adminLogin")
public class LoginAdminServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// Retrieve login credentials from the request (email and password)
			String userEmail = request.getParameter("email");
			String userPassword = request.getParameter("password");

			// Create a session object to store user data across multiple requests
			HttpSession currentSession = request.getSession();

			// Check if the entered email and password match the hardcoded admin credentials
			if ("admin@gmail.com".equals(userEmail) && "admin".equals(userPassword)) {

				// Ensure User class exists, otherwise, this line will throw an error
				currentSession.setAttribute("adminUser", new User());

				// Redirect the admin to the admin dashboard page
				response.sendRedirect("admin/index.jsp");
			} else {
				// If the credentials don't match, set an error message in the session
				currentSession.setAttribute("errorMessage", "Invalid Username or Password.");

				// Redirect back to the admin login page
				response.sendRedirect("admin_login.jsp");
			}

		} catch (Exception e) {
			// Print detailed error information for better debugging
			System.err.println("Error during admin login: " + e.getMessage());
			e.printStackTrace();
		}
	}
}