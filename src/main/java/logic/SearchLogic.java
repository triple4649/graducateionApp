package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import code.SEX;
import entity.KokyakuMaster;
import formbean.Search;

public class SearchLogic {
	private Search search;
	public void doLogic(Search search)throws Exception {
		this.search=search;
		getMasterList();
	}
	//マスターの一覧を取得する
	private void getMasterList() {
		//Querryの取得
		TypedQuery<KokyakuMaster>q = search.getEm().createQuery(createQuerry(), 
				KokyakuMaster.class);
		//bind変数の値をセットする
		setValue(Optional.ofNullable(null),
				Optional.ofNullable(s->q.setParameter(((String)s[0]),s[1])));
		
		search.setItems(
			q.getResultList()
			.stream()
			.map(SearchLogic::convertEntity)
			.collect(Collectors.toList())
		);
	}
	
	//DBから取得した値を画面表示用に変換する
	private static KokyakuMaster convertEntity(KokyakuMaster m) {
		if(m.getSex().equals(SEX.MALE.getSexCode())) {
			m.setSex(SEX.MALE.getSexName());
		}else {
			m.setSex(SEX.FEMALE.getSexName());
		}
		m.setBirthday(String.format("%s年%s月%s日", 
				m.getBirthday().substring(0, 4),
				m.getBirthday().substring(4, 6),
				m.getBirthday().substring(6, 8)));
		return m;
	}
	
	//DBへのQueyy文字列の組み立て
	private String createQuerry() {
		String querry="Select e from KokyakuMaster e "
				+ "where "
				+ " %s ";
		List<String>list = new ArrayList<String>();
		setValue(Optional.ofNullable(s->list.add(s)),Optional.ofNullable(null));
		return String.format(querry, String.join("AND ", list));
	}
	//クエリの組み立てと、バインド値の設定を行う
	//引数 Optional<Consumer<String>> e クエリの条件を設定する処理
	//引数 Optional<Consumer<Object[]>>q バインド変数を設定する処理
	private void setValue(
			Optional<Consumer<String>> e,
			Optional<Consumer<Object[]>>q) {
		String id = search.getId();
		String nameKanji = search.getNameKanji();
		String nameKana = search.getNameKana();
		if(id!=null & !id.equals("")) {
			e.ifPresent(s->s.accept(" e.id=:id "));
			q.ifPresent(s->s.accept(new Object[]{"id",id}));
		}
		
		if(nameKanji!=null & !nameKanji.equals("")) {
			e.ifPresent(s->s.accept(" e.nameKanji like :nameKanji "));
			q.ifPresent(s->s.accept(new Object[]{"nameKanji", "%"+ nameKanji+"%"}));
		}
		
		if(nameKana!=null & !nameKana.equals("")) {
			e.ifPresent(s->s.accept(" e.kana like :nameKana "));
			q.ifPresent(s->s.accept(new Object[]{"nameKana", "%"+nameKana+"%"}));
		}
	}
	

}
