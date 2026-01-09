package servlets;

public class ApiResponse {
	private boolean status;
	private String  message;
	private String redirectUrl;
	public ApiResponse(boolean status,String message) {
		this.status=status;
		this.message=message;
	}
	public ApiResponse(boolean b, String string, String redirectUrl) {
		this.status=b;
		this.message=string;
		this.redirectUrl=redirectUrl;
	}
	
	
}
