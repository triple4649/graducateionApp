package logic;

import javax.persistence.EntityManager;

import entity.KokyakuMaster;
import formbean.Regist;
import proxy.TransactionIntercepter;

public class RegistLogic implements Persist{
	private Regist regist; 
	EntityManager em;
	
	public void doLogic(Regist regist)throws Exception {
		this.regist=regist;
		doRegist();
	}
	//DB登録
	public void doRegist() throws Exception {
		Persist targetClass = TransactionIntercepter.getProxyInstance(this);
		targetClass.persist();
	}
	
	//DBのトランザクション境界
	public void persist(){
		KokyakuMaster entity = new KokyakuMaster();
		em.clear();
		//エンティティに画面の入力値をバインドする
		entity.setBirthday(regist.getBirthdayYear()
				+regist.getBirthdayMonth()
				+regist.getBirthdayDay());
		entity.setKana(regist.getKana());
		entity.setNameKanji(regist.getNameKanji());
		entity.setSex(regist.getSex());
		entity.setPhoneNum(regist.getPhoneNum());
		em.persist(entity);
	}
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

}
