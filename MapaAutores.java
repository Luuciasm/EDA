import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class MapaAutores {

	private HashMap<String, Autor> mapaAutores;
	
	public MapaAutores() {
		mapaAutores = new HashMap<>();
	}
	//Lee los datos del fichero que relaciona el identificador de cada autor con su nombre.
	//y los guarda en un mapa que tiene como clave el identificador del autor y el propio autor como valor.
	public void cargarAutores(String nombre) {
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String info[] = linea.split(" # ");
				Autor a = new Autor(info[0], info[1]);
				mapaAutores.put(info[0], a);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Guarda los autores actualizados en el fichero.
	public void guardarAutores(String nombre) {
		try {
			PrintWriter salida = new PrintWriter(new File(nombre));
			for (Autor a: mapaAutores.values()) {
				salida.println(a.getId()+" # "+a.getNombre());
			}
			salida.flush();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Dado un autor, devolver una lista con sus publicaciones
	public void aniadirAutor (String id, String nom) {
		Autor a = new Autor(id, nom);
		mapaAutores.put(id, a);
	}
	
	//Borrar un autor
	public void eliminarAutor (Autor a) {
		mapaAutores.remove(a.getId());
	}
	
}
