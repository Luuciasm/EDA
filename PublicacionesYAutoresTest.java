import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PublicacionesYAutoresTest {
	
	private MapaPubli mp;
	private MapaAutores ma;

	@Before
	public void setUp() throws Exception {
		mp = new MapaPubli();
		ma = new MapaAutores();
	}

	@After
	public void tearDown() throws Exception {
		mp = null;
		ma = null;
	}

	@Test
	public void testPublicacionesYAutores() {
		assertNotNull(mp);
		assertNotNull(ma);
	}

	@Test
	public void testObtenerAutoresDePubli() {
		Publicacion p = new Publicacion("Q101088249", "Quasiconformal Homeomorphisms and Dynamics III. The Teichmüller Space of a Holomorphic Dynamical System");
		mp.aniadirAutorAPubli(p.getId(), "Q333959");
		mp.aniadirAutorAPubli(p.getId(), "Q726376");
		assertTrue(mp.obtenerAutoresdePubli(p.getId()));
	}

}
