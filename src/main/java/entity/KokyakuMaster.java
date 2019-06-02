package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class KokyakuMaster {
	@Id
	@GeneratedValue
	@Column(name="BANGO",length=7)
	private long id;
	@Column(name="SHIMEI_KANJI",length=20)
	private String nameKanji;
	@Column(name="SHIMEI_KANA",length=20)
	private String kana;
	@Column(name="BIRTH_YMD",length=8)
	private String birthday;
	@Column(name="SEX",length=1)
	private String sex;
	@Column(name="TEL_NO",length=12)
	private String phoneNum;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	

}