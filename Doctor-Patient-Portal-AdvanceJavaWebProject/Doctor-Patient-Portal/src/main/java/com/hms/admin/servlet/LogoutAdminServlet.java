package com.hms.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogout")
public class LogoutAdminServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the current session to manage admin logout
		HttpSession currentSession = request.getSession();

		// Remove the "adminUser" attribute from the session, effectively logging out the admin
		currentSession.removeAttribute("adminUser");

		// Set a success message to indicate the admin has logged out
		currentSession.setAttribute("successMessage", "Admin Logout Successfully");

		// Redirect the user back to the admin login page
		response.sendRedirect("admin_login.jsp");
	}
}