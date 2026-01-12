package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppoinmentRequest {
	private int appoint_id;
	private int user_id;
	private String name;
	private String reason;
	private Status status;
	private String department;
	private Timestamp appointmentDate;
	
	public enum Status {
		Completed,Pending
	}
	
	public int getAppoint_id() {
		return appoint_id;
	}
	public void setAppoint_id(int appoint_id) {
		this.appoint_id = appoint_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Timestamp getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(java.sql.Timestamp timestamp) {
		this.appointmentDate = timestamp;
	}
	
	

}
