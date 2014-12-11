import play.*;
import models.dao.GenericDAO;
//import models.dao.GenericDAOImpl;
import play.db.jpa.JPA;
import models.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Global extends GlobalSettings {
	
	private static GenericDAO dao = new GenericDAO();;

	@Override
	public void onStart(Application app) {
		Logger.info("Aplicação inicializada...");
		
		JPA.withTransaction(new play.libs.F.Callback0() {
			
			@Override
			public void invoke() throws Throwable {
				
				povoarBD();
                dao.flush();   
				
			}
					    			    		
		});
		
	}
	
	public void povoarBD() throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader("series.cvs")).useDelimiter("\\n");
		
		List<Serie> series = new ArrayList<Serie>();
		
		List<String> all = new ArrayList<String>();
		
		while (scanner.hasNext()) {
			String linha = scanner.next();
			all.add(linha);
		}
		
		Serie s = new Serie(all.get(0).split(",")[0]);
		Temporada t = new Temporada(all.get(0).split(",")[1]);
		Episodio e = new Episodio(all.get(0).split(",")[2], all.get(0).split(",")[3]);
		
		t.addEpisodio(e);
		s.addTemporada(t);
		
		for (int i = 1; i < all.size(); i ++) {
			if (s.getNome().equals(all.get(i).split(",")[0])) {
				
				if (all.get(i).split(",").length >= 4) {
				
					if (s.getTemporadas().size() != 0 && s.getUltimaTemporada().getNome().equals(all.get(i).split(",")[1])) {
						e = new Episodio(all.get(i).split(",")[2], all.get(i).split(",")[3]);
						s.getUltimaTemporada().addEpisodio(e);
					}else {
						t = new Temporada(all.get(i).split(",")[1]);
						e = new Episodio(all.get(i).split(",")[2], all.get(i).split(",")[3]);
					
						t.addEpisodio(e);
						s.addTemporada(t);
					}
				
				}else {
					if (s.getTemporadas().size() != 0 && s.getUltimaTemporada().getNome().equals(all.get(i).split(",")[1])) {
						e = new Episodio(all.get(i).split(",")[2], "ep sem nome");
						s.getUltimaTemporada().addEpisodio(e);
					}else {
						t = new Temporada(all.get(i).split(",")[1]);
						e = new Episodio(all.get(i).split(",")[2], "ep sem nome");
						
						t.addEpisodio(e);
						s.addTemporada(t);
					}
				}
	
			}else {
				dao.persist(s);
				
				if (all.get(i).split(",").length >= 4) {
				
					s = new Serie(all.get(i).split(",")[0]);
					t = new Temporada(all.get(i).split(",")[1]);
					e = new Episodio(all.get(i).split(",")[2], all.get(i).split(",")[3]);
				
					t.addEpisodio(e);
					s.addTemporada(t);
				
				}else {
					s = new Serie(all.get(i).split(",")[0]);
					t = new Temporada(all.get(i).split(",")[1]);
					e = new Episodio(all.get(i).split(",")[2], "ep sem nome");
					
					t.addEpisodio(e);
					s.addTemporada(t);
				}
			}
			
		}
	}
	
	@Override
	public void onStop(Application app) {
		JPA.withTransaction(new play.libs.F.Callback0() {
		    @Override
		    public void invoke() throws Throwable {
		        Logger.info("Aplicação finalizando...");
		        List<Serie> series = dao.findAllByClassName(Serie.class.getName());

		        for (Serie serie:series) {
		        	dao.removeById(Serie.class, serie.getId());
		       } 
		    }
		}); 
	}
	
}



