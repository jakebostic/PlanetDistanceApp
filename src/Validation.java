
public class Validation {
	
	private String lineEnd;
	
	public Validation() {
		this.lineEnd = "\n";
		
	}
	
	public Validation(String lineEnd) {
		this.lineEnd = lineEnd;
	}
	
	public String isPresent(String value, String name) {
		String msg = "";
		if (value.isEmpty()) {
			msg = name + " is required." + lineEnd;
		}
		return msg;
	}
	
	public String isDouble(String name, String value) {
		String msg = "";
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			msg = name + " must be a valid number." + lineEnd;
		}
		return msg;
	}
	
	public String isInteger(String value, String name) {
		String msg = "";
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			msg = name + " must be a valid integer." + lineEnd;
		}
		return msg;
		
	}
	
	public String isDuplicate(double fromPlanet, double toPlanet) {
		String msg = "";
		if (fromPlanet==toPlanet) {
			msg = "'From Planet' cannot be the same as 'To Planet' . Try again." + lineEnd;	
		}
		return msg;
	}
	


}
