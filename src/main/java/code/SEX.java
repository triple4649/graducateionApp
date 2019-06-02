package code;

public enum SEX {
	MALE("M","男性"),
	FEMALE("F","女性");
	
	private final String sexCode;
	private final String sexName;
	
	private SEX(String sexCode,String sexName) {
		this.sexCode = sexCode;
		this.sexName = sexName;
	}

	public String getSexCode() {
		return sexCode;
	}

	public String getSexName() {
		return sexName;
	}
	
	
}
