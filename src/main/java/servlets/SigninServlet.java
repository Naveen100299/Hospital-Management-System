package servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Exception.DataAccessException;
import dao.SigninDAO;
import models.SigninRequest;
import models.SignupRequest;
import models.SignupRequest.Role;
  @WebServlet("/signin")
  public class SigninServlet extends HttpServlet {
  
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BufferedReader reader=request.getReader();
		Gson gson=new Gson();
		SigninRequest signinRequest= gson.fromJson(reader,SigninRequest.class);
		SigninDAO signin=new SigninDAO();
		try {
			SignupRequest signupRequest= signin.signin(signinRequest);
			if(signupRequest==null) {
				response.getWriter().write(gson.toJson(new ApiResponse(false,"Invalid Email or Password")));
				return;
			}
			HttpSession session=request.getSession();
			session.setAttribute("user",signupRequest);
			String redirectUrl="";
			String contextPath=request.getContextPath();
			if(signupRequest.getRole()==Role.PATIENT ) {
				redirectUrl=contextPath+"/html/patient/patientpanel.html";
			}
			else if(signupRequest.getRole()==Role.DOCTOR) {
				redirectUrl=contextPath+"/html/doctor/doctorpanel.html";
			}
			else if(signupRequest.getRole()==Role.RECEPTIONIST) {
				redirectUrl=contextPath+"/html/receptionist/receptionistpanel.html?type=receptionist";
			}
			response.getWriter().write(gson.toJson(new ApiResponse(true, "Login Success",redirectUrl)));

		} catch (Exception e) {
			
			response.getWriter().write(gson.toJson(new ApiResponse(false,e.getMessage())));

		}	
	}
}
