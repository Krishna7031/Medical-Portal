package com.hms.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.dao.SpecialistDAO;
import com.hms.db.DBConnection;

@WebServlet("/addSpecialist")
public class SpecialistDoctorServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Retrieve the specialist name from the form data
		String specialistName = request.getParameter("specialistName");

		// Create an instance of SpecialistDAO to interact with the database
		SpecialistDAO specialistDAO = new SpecialistDAO(DBConnection.getConn());

		// Call the method to add the specialist and store the result in a boolean flag
		boolean isSpecialistAdded = specialistDAO.addSpecialist(specialistName);

		// Obtain the current session to store success or error messages
		HttpSession currentSession = request.getSession();

		// Check if the specialist was successfully added
		if (isSpecialistAdded) {
			// Set success message and redirect to the admin dashboard
			currentSession.setAttribute("successMessage", "Specialist added Successfully.");
			response.sendRedirect("admin/index.jsp");
		} else {
			// If there was an error, set error message and redirect back to the admin dashboard
			currentSession.setAttribute("errorMessage", "Something went wrong on the server");
			response.sendRedirect("admin/index.jsp");
		}
	}
}
