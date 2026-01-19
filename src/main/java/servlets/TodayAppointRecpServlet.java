package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Exception.DataAccessException;
import dao.AppoinmentDAO;
import models.AppoinmentRequest;
import models.SignupRequest;
@WebServlet("/todayappointments")
public class TodayAppointRecpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
     
        AppoinmentDAO dao = new AppoinmentDAO();
        List<AppoinmentRequest> list = dao.viewTodayAppointments();

        Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        response.getWriter().write(gson.toJson(list));
    }
}
