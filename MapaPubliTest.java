import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapaPubliTest {
	
	private MapaPubli mp;

	@Before
	public void setUp() throws Exception {
		mp = new MapaPubli();
	}

	@After
	public void tearDown() throws Exception {
		mp = null;
	}

	@Test
	public void testMapaPubli() {
		assertNotNull(mp);
	}

	@Test
	public void testCargarCitadas() {
		mp.cargarCitadas("Datuak/publications-citedPubs-all.txt");
		assertTrue(mp.comprobarFicheroCargadoCitas() != 0);
	}

	@Test
	public void testCargarPublicaciones() {
		mp.cargarPublicaciones("Datuak/publications-titles-all.txt");
		assertTrue(mp.comprobarFicheroCargadoPublicaciones() !=0);
	}

	@Test
	public void testCargarPublisAutor() {
		mp.cargarPublisAutor("Datuak/publications-authors-all-final.txt");
		assertTrue(mp.comprobarFicheroCargadoPublisAutores() !=0);
	}

	@Test
	public void testBuscarPubli() {
		fail("Not yet implemented");
	}

	@Test
	public void testAniadirPubli() {
		fail("Not yet implemented");
	}

	@Test
	public void testAniadirCita() {
		fail("Not yet implemented");
	}

	@Test
	public void testAniadirAutorAPubli() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtenerListaCitas() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtenerListaAutores() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtenerPublisDeAutor() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarPubli() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrdenarPublis() {
		fail("Not yet implemented");
	}

}
