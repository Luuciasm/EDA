import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapaPubli {

    //Atributos: Declaracion de HashMaps
	private HashMap<String, ArrayList<String>> mapaPublisCitadas; //Relaciona el código con publicacion citada (String)
	private HashMap<String, Publicacion> mapaPublicaciones;//Relaciona el código con la publicacion (Publicacion)
	private HashMap<String, ArrayList<String>> mapaPublisAutor; //Relaciona el codigo con las publicaciones del Autor (String)
                                                                // Lo hacemos de tipo String para evitar bulces infinitos
	//Constructora
    // Los HashMaps son como los ArrayList, pero mas eficientes
	public MapaPubli() {
		mapaPublisCitadas = new HashMap<>();
		mapaPublicaciones = new HashMap<>();
		mapaPublisAutor = new HashMap<>();	
	}
	
	public void cargarCitadas(String nombre) { //Crea o añade a la lista de citadas de una publi
		Scanner entrada; //Abrimos el scanner
		try {
			entrada = new Scanner(new FileReader(nombre)); //nombre=nombre del fichero
			String linea;
			while (entrada.h::sNextLine()) { //Mientras exista una siguiente linea que leer
				linea = entrada.nextLine(); //Guardio la linea del fichero en la variable fichero
				String info[]= linea.split(" # "); //Cojo ambos datos separados por un #
				if (!mapaPublisCitadas.containsKey(info[0])) { //Si el primer codigo que he guardado no esta guardado
					mapaPublisCitadas.put(info[0], new ArrayList<String>()); //Añado el primer codigo y creo un ArrayList
                                                                            // donde guardaré las publis citadas
				}
				mapaPublisCitadas.get(info[0]).add(info[1]); // Si ya esta guardada, le añado la citada (el segundo codigo).
                                                            // Siempre se ejecuta.
			}
			entrada.close(); //Cerramos el scanner
	}catch (IOException e) { //Excepcion si no se puede leer el fichero
			e.printStackTrace();
		}
	}
	
	public void guardarCitadas(String nombre) {
		try {
			PrintWriter salida = new PrintWriter(new File(nombre));
			for (String  idPublis:mapaPublisCitadas.keySet()) {
				for (String idCitas: mapaPublisCitadas.get(idPublis)) {
					salida.println(idPublis+" # "+idCitas);
				}
			}
			salida.flush();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cargarPublicaciones(String nombre) { //Relaciona publis con sus codigos
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String info[] = linea.split(" # ");
				Publicacion p = new Publicacion(info[0], info[1]);
				mapaPublicaciones.put(info[0], p); //Relacionamos el codigo y la publi
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarPublicaciones(String nombre) {
		try {
			PrintWriter salida = new PrintWriter(new File(nombre));
			for (Publicacion p: mapaPublicaciones.values()) {
				salida.println(p.getId()+" # "+p.getTitulo());
			}
			salida.flush();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cargarPublisAutor(String nombre) { //Crea la lista de Autores de cada publi
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String info[] = linea.split(" # ");
				String idPubli = info[0]; //El primero codigo es el idPubli
				String idAutor = info[1]; //El segundo codigo es el idAutor
				if (!mapaPublisAutor.containsKey(idPubli)) { //Si el idPubli no se ha guardado antes
					mapaPublisAutor.put(idPubli, new ArrayList<>()); //Añadimos al mapa el idPubli y un array
				}                                                   //donde se guardaran los autores de esa publi
				mapaPublisAutor.get(idPubli).add(idAutor); //Añadimos el idAutor aesa publi
			}                                               // se ejecuta siempre.
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarAutores(String nombre) {
		try {
			PrintWriter salida = new PrintWriter(new File(nombre));
			for (String idPublis: mapaPublisAutor.keySet()) {
				for (String idAutor: mapaPublisAutor.get(idPublis)) {
					salida.println(idPublis+" # "+idAutor);
				}
			}
			salida.flush();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}



















