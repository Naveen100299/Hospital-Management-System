package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ViewAppoinmentDAO;
import models.AppoinmentRequest;
import models.SignupRequest;

@WebServlet("/history")
public class PatientHistory extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession(false);
		SignupRequest user=(SignupRequest) session.getAttribute("user");
		
		ViewAppoinmentDAO dao=new ViewAppoinmentDAO();
		List<AppoinmentRequest> list=dao.patientHistory(user.getUser_id());
		Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.getWriter().write(gson.toJson(list));
	}

}
