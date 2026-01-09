package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.SignupRequest;
@WebServlet("/profile")
public class PatientDetailsServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("appliction/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession(false);
		SignupRequest user=(SignupRequest) session.getAttribute("user");
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson(user));
		
	}
	

}
