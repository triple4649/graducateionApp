package formbean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import logic.RegistLogic;
@ManagedBean		
@RequestScoped		
public class Regist {
	private String nameKanji;
	private String error;
	private String kana;
	private String birthdayYear;
	private String birthdayMonth;
	private String birthdayDay;
	private String sex;
	private String phoneNum;
	
	public String backMenu() {
		return "Menu.xhtml";
	}
	
	public String regist() {
		//画面チェックエラーがある場合は、自画面遷移する
		if(!isValidate()) {
			return "regist.xhtml";
		}
		//ロジッククラスを実行する
		try {
			new RegistLogic().doLogic(this);
		}catch(Exception e) {
			e.printStackTrace();
			//DB登録時にエラーが発生した場合は自画面遷移する
			error = "DB登録時にエラーが発生しました";
			return "regist.xhtml";
		}
		//エラーがない場合は、メニューに戻る
		return "menu.xhtml";
	}
	
	//画面入力チェック
	private boolean isValidate() {
		return true;
	}
	
	public String getNameKanji() {
		return nameKanji;
	}

	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getBirthdayYear() {
		return birthdayYear;
	}


	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}


	public String getBirthdayMonth() {
		return birthdayMonth;
	}
	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}


	public String getBirthdayDay() {
		return birthdayDay;
	}


	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	
}
