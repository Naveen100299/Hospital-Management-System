package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.BookAppoinmentDAO;
import models.AppoinmentRequest;
import models.SignupRequest;
import models.SignupRequest.Role;
@WebServlet("/bookappoinment")
public class PatientBookAppoinmentServlet extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BufferedReader reader=request.getReader();
		Gson gson=new Gson();
		
		AppoinmentRequest appoint = gson.fromJson(reader,AppoinmentRequest.class );
	    HttpSession session=request.getSession();
	    SignupRequest patient= (SignupRequest) session.getAttribute("user");
	    
	    if(patient==null) {
	    	response.getWriter().write(
			        gson.toJson(new ApiResponse(false, "unauthorized")));
	    	return;
	    }
	    if(!patient.getRole().equals(Role.RECEPTIONIST)) {
		appoint.setUser_id(patient.getUser_id());
	    }
	    BookAppoinmentDAO book=new BookAppoinmentDAO();
	    
		try {
		    String name=book.getPatientNameById(appoint.getUser_id());

			if(name==null) {
			    response.getWriter().write(gson.toJson(new ApiResponse(false, "Invaild Patient Id,Failed to book appointment")));
			    return;
		    }
		book.BookAppoinment(appoint);
		response.getWriter().write(
		        gson.toJson(new ApiResponse(true, "Appoinment Booked")));
		}
		catch (Exception e) {
		    response.getWriter().write(gson.toJson(new ApiResponse(false, "Failed to book appointment")));
		}

	}

}
