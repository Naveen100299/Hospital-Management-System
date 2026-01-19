package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.Patient;
import dao.PatientImpl;
import models.SignupRequest;
@WebServlet("/receptionistSignup")
public class ReceptionistSignup extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();

        try (BufferedReader reader = request.getReader()) {

          
            SignupRequest signupRequest = gson.fromJson(reader, SignupRequest.class);


            PatientImpl patientDao = new PatientImpl();

           int patientId= patientDao.receptionistSignup(signupRequest);

            Map<String, Object> json = new HashMap<>();
            json.put("status", true);
            json.put("patientId", patientId);
            json.put("message", "Sign Up Success");

            response.getWriter().write(gson.toJson(json));

        } catch (Exception e) {

            Map<String, Object> error = new HashMap<>();
            error.put("status", false);
            error.put("message", e.getMessage());

            response.getWriter().write(gson.toJson(error));
        }
    }
	
	

}
