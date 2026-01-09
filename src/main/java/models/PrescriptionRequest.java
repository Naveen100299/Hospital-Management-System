package models;

public class PrescriptionRequest {
	private int appointment_id;
	private int user_id;
	private String diagnosis;
	private String medicines;
	private String doctor_advice;
	
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getMedicines() {
		return medicines;
	}
	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
	public String getDoctor_advice() {
		return doctor_advice;
	}
	public void setDoctor_advice(String doctor_advice) {
		this.doctor_advice = doctor_advice;
	}
	

}
