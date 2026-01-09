package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.AppoinmentDAO;
import models.AppoinmentRequest;
import models.SignupRequest;

@WebServlet("/appointments")
public class AppoinmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

       
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
       SignupRequest signupRequest= (SignupRequest) session.getAttribute("user");
      


        AppoinmentDAO appoinmentDAO = new AppoinmentDAO();

       
        List<AppoinmentRequest> list =
                appoinmentDAO.viewAppointment(signupRequest.getDepartment().name());

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(list));
    }
}
