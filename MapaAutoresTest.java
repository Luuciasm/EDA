import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapaAutoresTest {
	
	private MapaAutores ma;
	private Autor a1;


	@Before
	public void setUp() throws Exception {
		ma = new MapaAutores();
		a1 = new Autor("Q547084", "Kevin Thiele");

	}

	@After
	public void tearDown() throws Exception {
		ma = null;
		a1 = null;

	}

	@Test
	public void testMapaAutores() {
		assertNotNull(ma);
	}

	@Test
	public void testCargarAutores() {
		ma.cargarAutores("Datuak/authors-name-all.txt");
		assertTrue(ma.comprobarFicheroCargado() !=0);
	}


	@Test
	public void testAniadirAutor() {
		ma.aniadirAutor("Q60320741", "Eric Waclawik");
		assertTrue(ma.buscarAutor("Q60320741"));
	}

	@Test
	public void testEliminarAutor() {
		ma.aniadirAutor("Q60320741", "Eric Waclawik");
		ma.eliminarAutor(a1);
		assertFalse(ma.buscarAutor(a1.getId()));
	}

}
