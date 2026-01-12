package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BookAppoinmentDAO;
import dao.PatientImpl;

@WebServlet("/getPatientName")
public class GetPatientNameServlet extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String patidStr = request.getParameter("patid");
        Gson gson = new Gson();

        if (patidStr == null) {
            response.getWriter().write(
                gson.toJson(new ApiResponse(false, "Invalid Patient ID"))
            );
            return;
        }

        int patid = Integer.parseInt(patidStr);

        BookAppoinmentDAO patientDao = new BookAppoinmentDAO();

        try {
            String name = patientDao.getPatientNameById(patid);

            if (name != null) {
                response.getWriter().write(
                    gson.toJson(new ApiResponse(true, name))
                );
            } else {
                response.getWriter().write(
                    gson.toJson(new ApiResponse(false, "Not Found"))
                );
            }

        } catch (Exception e) {
            response.getWriter().write(
                gson.toJson(new ApiResponse(false, "Server Error"))
            );
        }
    }
}
