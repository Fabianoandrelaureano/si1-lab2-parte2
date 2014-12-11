package controllers;

import java.util.List;

import models.Episodio;
import models.Serie;
import models.dao.GenericDAO;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

private static final GenericDAO dao = new GenericDAO();
private static Form<Serie> serieForm = Form.form(Serie.class);
	
	@Transactional
    public static Result index() {
    	List<Serie> series = dao.findAllByClassName(Serie.class.getName());    	
    	
    	return ok(index.render(series));
    }
	
	@Transactional
    public static Result acompanha(){
    	List<Serie> series = dao.findAllByClassName(Serie.class.getName());
    	return ok(acompanhar.render(series));
    }
	
	@Transactional
    public static Result acompanhar() {
    	DynamicForm requestData = Form.form().bindFromRequest();
		long id = Long.parseLong(requestData.get("opcoes"));
		
		List<Serie> series = dao.findAllByClassName(Serie.class.getName());
		
		for (int i = 0; i < series.size(); i++) {
			if (series.get(i).getId().equals(id)) {
				series.get(i).setStatus();
			}
		}
		
		return redirect(routes.Application.index());
		
    }
	
	@Transactional
	public static Result assistido() {
		Form<Serie> filledForm = serieForm.bindFromRequest();
		long id = Long.parseLong(filledForm.data().get("id"));
		Episodio episodio = dao.findByEntityId(Episodio.class, id);
			
		episodio.setStatus();
			
		dao.merge(episodio);
		dao.flush();
			
		return redirect(routes.Application.index());
		
	}

}
