
public class Eliminar {
	
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;
	import java.util.Scanner;

	public class MapaPublicaciones {
		// Mapa con clave identificador de la publicación y valor objeto publicación
		private Map<String, Publicacion> mapaPublicaciones; // Almacena todas las publicaciones identificadas por su
															// identificador
		private Map<String, ArrayList<String>> mapaCitas; // Almacena por cada identificador de publicación una lista con
															// los identificadores de todas las publicaciones citadas
		private Map<String, ArrayList<String>> mapaAutores; // Almacena por cada identificador de publicación una lista con
															// los identificadores de todos los autores que han participado
															// en la publicación

		public MapaPublicaciones() {
			mapaPublicaciones = new HashMap<>();
			mapaCitas = new HashMap<>();
			mapaAutores = new HashMap<>();
		}

		public void cargarFicheroAutoresPorPublicacion(String nom) {
			try {
				Scanner entrada = new Scanner(new FileReader(nom));
				String linea = null;
				while (entrada.hasNextLine()) {
					linea = entrada.nextLine();
					String[] datos = linea.split(" # ");
					String idP = datos[0];
					String idA = datos[1];
					if (!mapaAutores.containsKey(idP)) {
						mapaAutores.put(idP, new ArrayList<>());
					}
					mapaAutores.get(idP).add(idA);
				}
				entrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void guardarFicheroAutoresPorPublicacion(String nom) {
			try {
				PrintWriter salida = new PrintWriter(new File(nom)); //Abrimos el fichero para escritura
				//Recorremos el mapa que relaciona los autores con las publicaciones
				for(String idP: mapaAutores.keySet()) { //Recorremos las claves (id de publicación) del mapa de autores
					for(String idA: mapaAutores.get(idP)) { //Recorremos los identificadores de los autores asociados a idP
						salida.println(idP+" # "+idA);
					}
				}
				salida.flush();
				salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// nom: Nombre del fichero que guarda la información
		public void cargarFicheroPublicacionesCitadas(String nom) {
			try {
				Scanner entrada = new Scanner(new FileReader(nom));
				String linea;
				while (entrada.hasNextLine()) { // Mientras haya líneas que leer
					linea = entrada.nextLine(); // Lee una línea del fichero y avanza a la siguiente
					// linea = "Q43261508 # Q79773097"
					String datos[] = linea.split(" # ");
					// datos[0] = "Q43261508"
					// datos[1] = "Q79773097"
					if (!mapaCitas.containsKey(datos[0])) {
						mapaCitas.put(datos[0], new ArrayList<>());
					}
					mapaCitas.get(datos[0]).add(datos[1]);
				}
				entrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		public void guardarFicheroPublicacionesCitadas(String nom) {
			try {
				PrintWriter salida = new PrintWriter(new File(nom));
				//Recorremos las claves del mapa de citas
				for(String idP: mapaCitas.keySet()) {
					for(String idC: mapaCitas.get(idP)) {
						salida.println(idP+" # "+idC);
					}
				}
				salida.flush();
				salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void cargarPublicacionesDeFichero(String nombre) {
			try {
				Scanner entrada = new Scanner(new FileReader(nombre));
				String linea = null;
				while (entrada.hasNextLine()) {
					linea = entrada.nextLine();
					// linea = "Q36934323 # Quality of life in MAP.3 (Mammary Prevention 3): a
					// randomized, placebocontrolled trial evaluating exemestane for prevention of
					// breast cancer"
					String[] datos = linea.split(" # ");
					Publicacion p = new Publicacion(datos[0], datos[1]);
					mapaPublicaciones.put(datos[0], p);
				}
				entrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void guardarPublicacionesEnFichero(String nom) {
			try {
				PrintWriter salida = new PrintWriter(new File(nom));
				//Recorremos los valores del mapa
				//En este caso los valores son todas las publicaciones, que es lo que queremos escribir en el fichero
				for(Publicacion p: mapaPublicaciones.values()) {
					salida.println(p.getIdentificador()+" # "+p.getTitulo());
				}
				salida.flush();
				salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Buscar una publicación dado su identificador
		public Publicacion buscarPublicacion(String idP) {
			Publicacion p = mapaPublicaciones.get(idP);
			return p;
		}

		// Insertar una nueva publicación (dados su identificador y título)
		public void insertarNuevaPublicacion(String idP, String tituloP) {
			Publicacion p = new Publicacion(idP, tituloP);
			mapaPublicaciones.put(idP, p);
		}

		// Añadir una cita a una publicación (dados sus identificadores)
		public void aniadirCitaAPublicacion(String idP, String idPCitada) {
			/*
			 * if (mapaCitas.containsKey(idP)) { mapaCitas.get(idP).add(idPCitada); } else {
			 * mapaCitas.put(idP, new ArrayList<>()); mapaCitas.get(idP).add(idPCitada); }
			 */

			if (!mapaCitas.containsKey(idP)) {
				mapaCitas.put(idP, new ArrayList<>());
			}
			mapaCitas.get(idP).add(idPCitada);

		}

		// Añadir un autor a una publicación (dados sus identificadores)
		public void aniadirAutorAPublicacion(String idP, String idA) {
			if (!mapaAutores.containsKey(idP)) {
				mapaAutores.put(idP, new ArrayList<>());
			}
			mapaAutores.get(idP).add(idA);
		}

		// Dada una publicación (identificador), devolver una lista con las
		// publicaciones que cita
		public List<Publicacion> obtenerListaPublicacionesCitadas(String idP) {
			List<Publicacion> lp = new ArrayList<>();

			// Obtengo los identificadores de las publicaciones citadas por idP
			ArrayList<String> lCitas = mapaCitas.get(idP);
			// Recorremos todas las citas
			for (String id : lCitas) { // Por cada identificador que haya en la lista de citas
				Publicacion p = mapaPublicaciones.get(id); // Obtengo el objeto Publicacion asociado al id
				lp.add(p); // Añado el objeto Publicacin a la lista que vamos a devolver
			}

			return lp;
		}

		// Dada una publicación (identificador), devolver una lista con las
		// publicaciones que cita
		public List<String> obtenerListaPublicacionesCitadasV2(String idP) {
			// Obtengo los identificadores de las publicaciones citadas por idP
			ArrayList<String> lCitas = mapaCitas.get(idP);

			return lCitas;
		}
		
		//Dada una publicación, devolver una lista con sus autores
		public List<Autor> obtenerAutoresDeLaPublicacion(String idP){
			List<Autor> la = new ArrayList<>();
			
			//Obtengo la lista de identificadores de autor asociada a la publicación idP
			List<String> lAutores = mapaAutores.get(idP);
			for(String id: lAutores) {
				//TODO Método que dado un identificador de autor devuelve el objeto Autor
			}
			return la;
		}
		
		//Borrar una publicación
		public void borrarPublicacion(String idP) {
			mapaPublicaciones.remove(idP);
		}
		
//		Obtener una lista de publicaciones ordenada alfabéticamente (esta operación no debe
//		modificar la lista de publicaciones, sino que debe devolver una nueva lista ordenada, de tipo
//		ArrayList o LinkedList). Se debe implementar un algoritmo de ordenación, es decir, no se
//		puede llamar a una función estándar de ordenación ya implementada.
		public List<Publicacion> obtenerPublicacionesOrdenadas(){
			List<Publicacion> publicacionesOrdenadas = new LinkedList<>();
			
			//TODO Implementar el método
			return publicacionesOrdenadas;
		}
	}
}
