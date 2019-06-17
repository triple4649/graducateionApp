package formbean;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.KokyakuMaster;
import logic.SearchLogic;
@ManagedBean		
@RequestScoped		
public class Search {

	private String id; 
	private String nameKanji; 
	private String nameKana; 
	private List<KokyakuMaster>items;
	private String error;
	EntityManager em ;
	
	@PostConstruct
	private void createEm() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("myUnitInPersistenceXML");
		em = factory.createEntityManager();
	}
	public String backMenu() {
		return "Menu.xhtml";
	}
	public String search() {
		//画面の入力チェックを行う
		if(!isVaildate()) {
			return "search.xhtml";
		}
		//Logicクラスを実行する
		try {
			new SearchLogic().doLogic(this);
		}catch(Exception e) {
			//DB検索時にエラーが発生した場合はエラーメッセージを表示する
			this.error +="DB検索時にエラーが発生しました";
		}
		return "search.xhtml";
		
	}
	//画面の入力チェック
	private boolean isVaildate() {
		return true;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNameKanji() {
		return nameKanji;
	}
	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}
	public String getNameKana() {
		return nameKana;
	}
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}
	public List<KokyakuMaster> getItems() {
		return items;
	}
	public void setItems(List<KokyakuMaster> items) {
		this.items = items;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
