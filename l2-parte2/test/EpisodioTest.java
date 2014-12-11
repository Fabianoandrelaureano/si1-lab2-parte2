import junit.framework.Assert;
import models.Episodio;

import org.junit.Test;


public class EpisodioTest {
	
	@Test
	public void aSerieDeveEstaSendoAcompanhada() {
		Episodio ep = new Episodio("1", "ep1");
		
		Assert.assertEquals(ep.assistido(), "nao");
		
		ep.setStatus();
		
		Assert.assertEquals(ep.assistido(), "sim");
		
	}

}
