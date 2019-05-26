package formbean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.MasterEntity;
@ManagedBean		
@RequestScoped		
public class Search {

	private String id; 
	private String nameKanji; 
	private String nameKana; 
	private List<MasterEntity>items;
	EntityManager em ;
	
	@PostConstruct
	private void createEm() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("myUnitInPersistenceXML");
		em = factory.createEntityManager();
	}
	public String search() {
		//画面の入力チェックを行う
		if(!isVaildate()) {
			return "search.xhtml";
		}
		//DBから情報を取得する
		getMasterList();
		return "search.xhtml";
		
	}
	//画面の入力チェック
	private boolean isVaildate() {
		return true;
	}
	
	//マスターの一覧を取得する
	private void getMasterList() {
		//Querryの取得
		TypedQuery<MasterEntity>q = em.createQuery(createQuerry(), 
				MasterEntity.class);
		//bind変数の値をセットする
		setValue(Optional.ofNullable(null),
				Optional.ofNullable(s->q.setParameter(((String)s[0]),s[1])));
		
		items=q.getResultList();
	}
	
	//DBへのQueyy文字列の組み立て
	private String createQuerry() {
		String querry="Select e from Master e"
				+ "where "
				+ " %s ;";
		List<String>list = new ArrayList<String>();
		setValue(Optional.ofNullable(s->list.add(s)),Optional.ofNullable(null));
		return String.format(querry, String.join("AND ", list));
	}
	private void setValue(
			Optional<Consumer<String>> e,
			Optional<Consumer<Object[]>>q) {
		if(id!=null & !id.equals("")) {
			e.ifPresent(s->s.accept("id=:id"));
			q.ifPresent(s->s.accept(new Object[]{"id",id}));
		}
		
		if(nameKanji!=null & !nameKanji.equals("")) {
			e.ifPresent(s->s.accept("nameKanji like ='%:nameKanji%'"));
			q.ifPresent(s->s.accept(new Object[]{"nameKanji", nameKanji}));
		}
		
		if(nameKana!=null & !nameKana.equals("")) {
			e.ifPresent(s->s.accept("nameKana like ='%:nameKana%'"));
			q.ifPresent(s->s.accept(new Object[]{"nameKana", nameKana}));
		}
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
	public List<MasterEntity> getItems() {
		return items;
	}
	public void setItems(List<MasterEntity> items) {
		this.items = items;
	}

}
