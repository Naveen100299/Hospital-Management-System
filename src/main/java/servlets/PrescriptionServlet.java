package servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.PrescriptionDAO;
import models.PrescriptionRequest;
@WebServlet("/prescription")
public class PrescriptionServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {

		response.setContentType("application/json");
		
		response.setCharacterEncoding("UTF-8");
		
		
		Gson gson=new Gson();
		
		PrescriptionDAO dao=new PrescriptionDAO();
		PrescriptionRequest prescription =  dao.PatientPrescriptionDetails(Integer.valueOf(request.getParameter("user_id")));
		response.getWriter().write(gson.toJson(prescription));
	}
	
	
	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BufferedReader reader=request.getReader();
		Gson gson=new Gson();
		PrescriptionRequest prescriptionRequest= gson.fromJson(reader, PrescriptionRequest.class);
		PrescriptionDAO dao=new PrescriptionDAO();
		try {
		dao.PatientPrescription(prescriptionRequest);
		response.getWriter().write(gson.toJson(new ApiResponse(true, "update Success")));
		}
		catch (Exception e) {
			response.getWriter().write(gson.toJson(new ApiResponse(false, "update failed")));

		}
	}

}
