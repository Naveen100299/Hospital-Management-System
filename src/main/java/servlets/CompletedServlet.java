package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.CompletedDAO;
import models.AppoinmentRequest;
import models.SignupRequest;
@WebServlet("/completed")
public class CompletedServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession(false);
		SignupRequest signupRequest =(SignupRequest) session.getAttribute("user");
		
		 CompletedDAO completedDAO=new CompletedDAO();
		
		 List<AppoinmentRequest> list= completedDAO.completed(signupRequest.getDepartment().name());
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson(list));
		
	}

}
