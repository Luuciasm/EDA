import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapaPubliTest {
	
	private MapaPubli mp;
	private Publicacion p;

	@Before
	public void setUp() throws Exception {
		mp = new MapaPubli();
		p = new Publicacion("Q40536987" , "Circadian desynchronization");
	}

	@After
	public void tearDown() throws Exception {
		mp = null;
		p = null;
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
		mp.aniadirPubli("Q40536987", "Circadian desynchronization");
		mp.aniadirPubli("Q51050711", "A diagnostic dilemma of syncope");
		mp.aniadirPubli("Q51011108", "Of viruses, gloves, and crêpes");
		assertEquals(p.getId(), mp.buscarPubli("Q40536987").getId());

	}

	@Test
	public void testAniadirPubli() {
		mp.aniadirPubli("Q40536987", "Circadian desynchronization");
		assertEquals(p.getId(), mp.buscarPubli("Q40536987").getId());
	}

	@Test
	public void testAniadirCita() {
		mp.aniadirCita("Q21136163", "Q24600704");
		mp.aniadirCita("Q21136163", "Q28250818");
		mp.aniadirCita("Q21136163", "Q29038534");
		mp.aniadirCita("Q24657774", "Q35557791");
		assertTrue(mp.comprobarFicheroCargadoCitas() == 2);
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
