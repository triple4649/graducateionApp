package formbean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import entity.MasterEntity;
import logic.Persist;
import proxy.TransactionIntercepter;
@ManagedBean		
@RequestScoped		
public class Regist implements Persist{
	private String nameKanji;
	private String error;
	private String kana;
	private String birthday;
	private String sex;
	private String phoneNum;
	EntityManager em;
	

	public String regist() {
		//画面チェックエラーがある場合は、自画面遷移する
		if(!isValidate()) {
			return "regist.xhtml";
		}
		//加入者マスターに登録する
		try {
			doRegist();
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
	//DB登録
	public void doRegist() throws Exception {
		Persist targetClass = TransactionIntercepter.getProxyInstance(this);
		targetClass.persist();
	}
	
	//DBのトランザクション境界
	public void persist(){
		MasterEntity entity = new MasterEntity();
		em.clear();
		//エンティティに画面の入力値をバインドする
		entity.setBirthday(birthday);
		entity.setKana(kana);
		entity.setNameKanji(nameKanji);
		entity.setSex(sex);
		entity.setPhoneNum(phoneNum);
		em.persist(entity);
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
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
