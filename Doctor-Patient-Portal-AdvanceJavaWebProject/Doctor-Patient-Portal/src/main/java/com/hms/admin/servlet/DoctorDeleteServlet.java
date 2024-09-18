package com.hms.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.dao.DoctorDAO;
import com.hms.db.DBConnection;

@WebServlet("/deleteDoctor")
public class DoctorDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Retrieve the doctor ID from the request parameters, which comes as a string, and convert it to an integer
		int doctorId = Integer.parseInt(request.getParameter("id"));

		// Create a DoctorDAO instance to interact with the database, using the DBConnection utility
		DoctorDAO doctorDAO = new DoctorDAO(DBConnection.getConn());

		// Obtain the current session to store success or error messages
		HttpSession currentSession = request.getSession();

		// Call the method to delete the doctor by ID and store the result in a boolean flag
		boolean isDeleted = doctorDAO.deleteDoctorById(doctorId);

		// Check if the doctor was successfully deleted
		if (isDeleted) {
			// If successful, set a success message in the session
			currentSession.setAttribute("successMessage", "Doctor Deleted Successfully.");
			// Redirect to the view doctor page
			response.sendRedirect("admin/view_doctor.jsp");
		} else {
			// If the deletion failed, set an error message in the session
			currentSession.setAttribute("errorMessage", "Something went wrong on the server!");
			// Redirect back to the view doctor page
			response.sendRedirect("admin/view_doctor.jsp");
		}
	}
}