import junit.framework.Assert;
import models.Episodio;
import models.Temporada;

import org.junit.Test;


public class TemporadaTest {
	
	@Test
	public void deveAdicionarTemporada() {
		
		Temporada temporada = new Temporada("1");
		
		Episodio episodio = new Episodio("1", "ep1");
		
		temporada.addEpisodio(episodio);
		
		Assert.assertEquals(temporada.getEpisodios().size(), 1);
		
	}
	
	@Test
	public void aTemporadaNaoDeveEstaSendoAcompanhada() {
		Temporada temporada = new Temporada("1");
		
		Episodio episodio = new Episodio("1", "ep1");
		
		temporada.addEpisodio(episodio);
		
		Assert.assertEquals(temporada.status(), "nao assistida");
		
	}
	
	@Test
	public void aTemporadaDeveEstaSendoAcompanhada() {
		Temporada temporada = new Temporada("1");
		
		Episodio episodio1 = new Episodio("1", "ep1");
		
		episodio1.setStatus();
		
		temporada.addEpisodio(episodio1);
		
		Episodio episodio2 = new Episodio("2", "ep2");
		
		temporada.addEpisodio(episodio2);
		
		Assert.assertEquals(temporada.status(), "assistindo");
		
	}
	
	@Test
	public void aTemporadaDeveEstaAssistida() {
		Temporada temporada = new Temporada("1");
		
		Episodio episodio1 = new Episodio("1", "ep1");
		
		episodio1.setStatus();
		
		temporada.addEpisodio(episodio1);
		
		Episodio episodio2 = new Episodio("2", "ep2");
		
		temporada.addEpisodio(episodio2);
		
		episodio2.setStatus();
		
		Assert.assertEquals(temporada.status(), "assistida");
		
	}

}
