import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapaAutores {

	private HashMap<String, Autor> mapaAutores;
	
	public MapaAutores() {
		mapaAutores = new HashMap<>();
	}
	
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
	
	public void aniadirAutor (String id, String nom) {
		Autor a = new Autor(id, nom);
		mapaAutores.put(id, a);
	}
	
}
