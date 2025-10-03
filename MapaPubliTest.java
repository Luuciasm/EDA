import static org.junit.Assert.*;

import java.util.ArrayList;

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
		assertTrue(mp.comprobarNumCitas() != 0);
	}

	@Test
	public void testCargarPublicaciones() {
		mp.cargarPublicaciones("Datuak/publications-titles-all.txt");
		assertTrue(mp.comprobarNumPublicaciones() !=0);
	}

	@Test
	public void testCargarPublisAutor() {
		mp.cargarPublisAutor("Datuak/publications-authors-all-final.txt");
		assertTrue(mp.comprobarNumPublisAutores() !=0);
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
		assertTrue(mp.comprobarNumCitas() == 2);
	}

	@Test
	public void testAniadirAutorAPubli() {
		mp.aniadirAutorAPubli("Q101088249", "Q333959");
		mp.aniadirAutorAPubli("Q101088249", "Q726376");
		mp.aniadirAutorAPubli("Q104699038", "Q1384951");
		mp.aniadirAutorAPubli("Q106812488", "Q102300903");
		assertTrue(mp.comprobarNumPublisAutores() == 3);
	}

	@Test
	public void testObtenerListaCitas() {
		
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
		ArrayList<Publicacion> lista = mp.ordenarPublis();
		assertTrue(lista.get(0).getId().compareTo(lista.get(1).getId()) < 0);
	}

}
