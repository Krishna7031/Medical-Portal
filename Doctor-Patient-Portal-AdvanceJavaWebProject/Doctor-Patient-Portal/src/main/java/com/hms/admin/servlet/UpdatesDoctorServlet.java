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

@WebServlet("/updateDoctor")
public class UpdatesDoctorServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// Retrieve all doctor details from the form (doctor.jsp)
			String doctorFullName = request.getParameter("fullName");
			String doctorDateOfBirth = request.getParameter("dateOfBirth");
			String doctorQualification = request.getParameter("qualification");
			String doctorSpecialist = request.getParameter("specialist");
			String doctorEmail = request.getParameter("email");
			String doctorPhone = request.getParameter("phone");
			String doctorPassword = request.getParameter("password");

			// Retrieve the doctor's ID from the request to update the corresponding doctor's details
			int doctorId = Integer.parseInt(request.getParameter("id"));

			// Create a Doctor object with the provided data
			Doctor updatedDoctor = new Doctor(doctorId, doctorFullName, doctorDateOfBirth, doctorQualification, doctorSpecialist, doctorEmail, doctorPhone, doctorPassword);

			// Use DoctorDAO to interact with the database
			DoctorDAO doctorDAO = new DoctorDAO(DBConnection.getConn());

			// Call the update method from DoctorDAO and store the result
			boolean isUpdated = doctorDAO.updateDoctor(updatedDoctor);

			// Obtain the current session to store success or error messages
			HttpSession currentSession = request.getSession();

			// Check if the update was successful
			if (isUpdated) {
				// Set success message and redirect to view doctor page
				currentSession.setAttribute("successMessage", "Doctor updated Successfully");
				response.sendRedirect("admin/view_doctor.jsp");

			} else {
				// If update failed, set error message and redirect back to view doctor page
				currentSession.setAttribute("errorMessage", "Something went wrong on the server!");
				response.sendRedirect("admin/view_doctor.jsp");
			}

		} catch (Exception e) {
			// Print the stack trace if an exception occurs
			e.printStackTrace();
		}

	}

}
