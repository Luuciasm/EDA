import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class MapaAutores {

<<<<<<< HEAD:ListaAutores.java
	private ArrayList<Autor> listaAutores; //Atributos
	
	public ListaAutores() {
		listaAutores = new ArrayList<>();
	} //Constructora
	
	public void cargarAutores(String nombre) { //Leer del fichero los codigos de AUTORES
		try {                                  // con el Scanner
			Scanner entrada = new Scanner(new FileReader(nombre)); //Abrimos el scanner. nombre=nombre del fichero
			String linea; //Declaramos un tipo string llamado línea, será la línea que leamos
			while (entrada.hasNextLine()) { //Mientras en el fichero exista una siguiente linea que leer
				linea = entrada.nextLine(); //linea es la linea que estamos leyendo
				String info[] = linea.split(" # "); //Guardamos en la variable info el código y el nombre del autor
                                                            // separando ambos datos con el .split cuando aparezca un #
				Autor a = new Autor(info[0], info[1]);  //Relacionamos código y nombre en el objeto Autor
				listaAutores.add(a); //Lo añadimos a la listaAutores
=======
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
>>>>>>> 30b79d2a82d388f8b52dd3913fea5010a5ed6ab0:MapaAutores.java
			}
			entrada.close(); //Cerramos el scanner
		} catch (IOException e) {   //Excepcion que salta (si no se puede leer el fichero)
			e.printStackTrace();
		}
	}
	
	public void guardarAutores(String nombre) { //
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
	
	public void eliminarAutor (Autor a) {
		mapaAutores.remove(a.getId());
	}
	
}
