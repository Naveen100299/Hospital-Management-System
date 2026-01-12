package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
@WebServlet("/logout")
public class LogoutServelt extends HttpServlet {
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            
			 throws IOException {
System.out.println("logout called");
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();   
	        }
	        String path=request.getContextPath();
	        Gson gson=new Gson();
	        response.getWriter().write(gson.toJson(new ApiResponse(true,"/HMS/html/signin.html" )));

	    }
}
