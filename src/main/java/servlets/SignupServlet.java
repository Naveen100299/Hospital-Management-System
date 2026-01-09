package servlets;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Exception.ValidationException;
import dao.Patient;
import dao.PatientImpl;
import models.SignupRequest;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
	 System.out.println("signup recep");
		response.setContentType("application/json");
	 response.setCharacterEncoding("UTF-8");
	BufferedReader reader= request.getReader();
	Gson gson=new Gson();
	SignupRequest signupRequest= gson.fromJson(reader,SignupRequest.class);
	Patient patient= new PatientImpl();
	try {
		HttpSession session=request.getSession(false);
		SignupRequest signup=(SignupRequest) session.getAttribute("user");
		String role=signup.getRole().name();
		if(role==null) {
			role="PATIENT";
		}
	int pId=	patient.Sigup(signupRequest,role);
	Map<String, Object> json = new HashMap<>();
	json.put("status", true);
	json.put("patientId", pId);
	json.put("message", "Sign Up Success");

	response.getWriter().write(new Gson().toJson(json));

		
	} catch (Exception e) {
		response.getWriter().write(gson.toJson(new ApiResponse(false,e.getMessage())));
	}
	
 }
}
