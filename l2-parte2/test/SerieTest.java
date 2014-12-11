import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import models.Serie;
import models.Temporada;
import models.dao.GenericDAO;

public class SerieTest {
	
	/*private GenericDAO dao = new GenericDAO();
	
	@Test
    public void deveSalvarSerieNoBD() throws Exception {
        Serie serie = new Serie("The Simpsons");
        dao.persist(serie);

        List<Serie> series = dao.findAllByClassName(Serie.class.getName());
        assertThat(series.size()).isEqualTo(1);
        assertThat(series.get(0).getNome()).isEqualTo("The Simpsons");
    }*/
	
	@Test
	public void deveAdicionarTemporada() {
		
		Serie serie = new Serie("The Simpsons");
		Temporada temporada = new Temporada("1");
		
		serie.addTemporada(temporada);
		
		Assert.assertEquals(serie.getTemporadas().size(), 1);
		
	}
	
	@Test
	public void deveMostrarAUltimaTemporadaDaSerie() {
		
		Serie serie = new Serie("The Simpsons");
		Temporada temporada1 = new Temporada("1");
		Temporada temporada2 = new Temporada("2");
		
		serie.addTemporada(temporada1);
		serie.addTemporada(temporada2);
		
		Assert.assertEquals(serie.getUltimaTemporada(), temporada2);
		
	}
	
	@Test
	public void aSerieDeveEstaSendoAcompanhada() {
		Serie serie = new Serie("The Simpsons");
		
		Assert.assertEquals(serie.isAcompanhada(), "nao");
		
		serie.setStatus();
		
		Assert.assertEquals(serie.isAcompanhada(), "sim");
		
	}

}
