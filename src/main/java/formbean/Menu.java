package formbean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean		
@RequestScoped		
public class Menu {
	
	public String moveRegist() {
		return "regist.xhtml";
	}
	public String moveSearch() {
		return "search.xhtml";
	}

}
