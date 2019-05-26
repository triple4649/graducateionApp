package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MasterEntity {
	@Id
	@GeneratedValue
	private long id;
	private String nameKanji;
	private String kana;
	private String birthday;
	private String sex;
	private String phoneNum;
	public long getId() {
		return id;
	}
	public String getNameKanji() {
		return nameKanji;
	}
	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	public void setId(long id) {
		this.id = id;
	}


}