import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
		try {
			Scanner entrada; //Abrimos el scanner
			entrada = new Scanner(new FileReader(nombre)); //nombre=nombre del fichero
			String linea;
			while (entrada.hasNextLine()) { //Mientras exista una siguiente linea que leer
				linea = entrada.nextLine(); //Guardo la linea del fichero en la variable linea
				String info[]= linea.split("\\s+#\\s+"); //Cojo ambos datos separados por un #
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
	
	//Guarda las publicaciones citadas actualizadas en el fichero.
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
				String info[] = linea.split("\\s+#\\s+");
				Publicacion p = new Publicacion(info[0], info[1]);
				mapaPublicaciones.put(info[0], p); //Relacionamos el codigo y la publi
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Guarda las publicaciones actualizadas en el fichero.
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
				String info[] = linea.split("\\s+#\\s+");
				String idPubli = info[0]; //El primero codigo es el idPubli
				if (info.length == 2) {
					String idAutor = info[1]; //El segundo codigo es el idAutor
					if (!mapaPublisAutor.containsKey(idPubli)) { //Si el idPubli no se ha guardado antes
						mapaPublisAutor.put(idPubli, new ArrayList<>()); //Añadimos al mapa el idPubli y un array
					}                                                   //donde se guardaran los autores de esa publi
					mapaPublisAutor.get(idPubli).add(idAutor); //Añadimos el idAutor aesa publi
				}                                               // se ejecuta siempre.
			}
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
	
	public Publicacion buscarPubli (String id) {
		return mapaPublicaciones.get(id);
	}
	
	public void aniadirPubli (String id, String titulo) {
		Publicacion p = new Publicacion(id, titulo);
		mapaPublicaciones.put(id, p);
	}
	
	public void aniadirCita (String idPubli, String idCita) {
		if (!mapaPublisCitadas.containsKey(idPubli)) {
			mapaPublisCitadas.put(idPubli, new ArrayList<>());
		}
		mapaPublisCitadas.get(idPubli).add(idCita);
	}
	
	public void aniadirAutorAPubli (String idPubli, String idAutor) {
		if (!mapaPublisAutor.containsKey(idPubli)) {
			mapaPublisAutor.put(idPubli, new ArrayList<>());
		}
		mapaPublisAutor.get(idPubli).add(idAutor);
	}
	
	public ArrayList<String> obtenerListaCitas (String idPubli) {
		ArrayList<String> lista = mapaPublisCitadas.get(idPubli);
		return lista;
		
	}
	
	public ArrayList<String> obtenerListaAutores (Publicacion p){
		ArrayList<String> lista = mapaPublisAutor.get(p.getId());
		return lista;
	}
	
	public ArrayList<Publicacion> obtenerPublisDeAutor (Autor a){
		ArrayList<Publicacion> listaPublis = new ArrayList<>();
		for (String idPubli:mapaPublisAutor.keySet()) {
			ArrayList<String> listaAutores = mapaPublisAutor.get(idPubli);
			int pos = listaAutores.indexOf(a.getId());
			if(pos != -1) {
				Publicacion p =mapaPublicaciones.get(idPubli);
				listaPublis.add(p);
			}
		}
		return listaPublis;
	}
	
	public ArrayList<String> obtenerAutoresdePubli(String idPubli){
		return mapaPublisAutor.get(idPubli);
	}
	
	public void eliminarPubli (Publicacion p) {
		mapaPublicaciones.remove(p.getId());
	}
	
	public List<Publicacion> ordenarPublis (){
		List<Publicacion> listaOrdenada = new LinkedList<>();
		for (Publicacion p: mapaPublicaciones.values()) {
			if(listaOrdenada.size() == 0) {
				listaOrdenada.add(p);
			}else {
				int inicio = 0;
				int fin = listaOrdenada.size()-1;
				int mitad = 0;
				boolean encontrado = false;
				while (inicio <= fin && !encontrado) {
					mitad = (inicio + fin)/2;
					Publicacion publi =listaOrdenada.get(mitad);
					if (publi.getId().compareTo(p.getId()) < 0) {
						inicio = mitad + 1;
					}else {
						if (mitad == 0 || listaOrdenada.get(mitad-1).getId().compareTo(p.getId()) < 0) {
							encontrado = true;
						}else {
							fin = mitad -1;
						}
					}
				}
				if (!encontrado) {
					listaOrdenada.add(p);
				}else {
					listaOrdenada.add(mitad, p);
				}
				
			}
		}
		return listaOrdenada;
	}
	
	
	//metodo para comprobar qie se ha cargado el fichero de publicaciones
		public int comprobarNumPublicaciones () {
			return mapaPublicaciones.keySet().size();
		}
	
	//metodo para comprobar qie se ha cargado el fichero de citas
		public int comprobarNumCitas () {
			return mapaPublisCitadas.keySet().size();
		}

	//metodo para comprobar qie se ha cargado el fichero de Publis-autores
			public int comprobarNumPublisAutores () {
				return mapaPublisAutor.keySet().size();
			}
}



















