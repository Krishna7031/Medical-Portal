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
import com.hms.entity.Doctor;

@WebServlet("/addDoctor")
public class DoctorServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// Retrieve all data coming from doctor.jsp related to the doctor's details
			String doctorFullName = request.getParameter("fullName");
			String doctorDateOfBirth = request.getParameter("dateOfBirth");
			String doctorQualification = request.getParameter("qualification");
			String doctorSpecialist = request.getParameter("specialist");
			String doctorEmail = request.getParameter("email");
			String doctorPhone = request.getParameter("phone");
			String doctorPassword = request.getParameter("password");

			// Create a Doctor object with the provided data
			Doctor newDoctor = new Doctor(doctorFullName, doctorDateOfBirth, doctorQualification, doctorSpecialist, doctorEmail, doctorPhone, doctorPassword);

			// Instantiate DoctorDAO to handle database operations
			DoctorDAO doctorDAO = new DoctorDAO(DBConnection.getConn());

			// Register the new doctor using the DAO method
			boolean isRegistered = doctorDAO.registerDoctor(newDoctor);

			// Get the current session to store success or error messages
			HttpSession currentSession = request.getSession();

			// Check if the doctor registration was successful
			if (isRegistered) {
				// Set success message and redirect to the doctor.jsp page
				currentSession.setAttribute("successMessage", "Doctor added Successfully");
				response.sendRedirect("admin/doctor.jsp");
			} else {
				// If registration failed, set error message and redirect to doctor.jsp
				currentSession.setAttribute("errorMessage", "Something went wrong on the server!");
				response.sendRedirect("admin/doctor.jsp");
			}

		} catch (Exception e) {
			// Print the stack trace in case of any exceptions
			e.printStackTrace();
		}
	}
}
