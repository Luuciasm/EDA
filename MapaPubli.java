import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapaPubli {

	private HashMap<String, ArrayList<String>> mapaPublisCitadas;
	private HashMap<String, Publicacion> mapaPublicaciones;
	private HashMap<String, ArrayList<String>> mapaPublisAutor;
	
	public MapaPubli() {
		mapaPublisCitadas = new HashMap<>();
		mapaPublicaciones = new HashMap<>();
		mapaPublisAutor = new HashMap<>();	
	}
	
	//Lee los datos del fichero que relaciona los ids de las publicaciones, con los ids de las publicaciones que son citadas.
	//Guarda como key el id de la publicacion, y como valor la lista de publicaciones que son citadas.
	public void cargarCitadas(String nombre) {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(nombre));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String info[]= linea.split(" # ");
				if (!mapaPublisCitadas.containsKey(info[0])) {
					mapaPublisCitadas.put(info[0], new ArrayList<String>());
				}
				mapaPublisCitadas.get(info[0]).add(info[1]);
			}
			entrada.close();
	}catch (IOException e) {
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
	
	//Lee los datos de fichero que relaciona los ids de las publicaciones con los titulos,
	//guardando como clave el id de la publicacion, y como valor la publicacion en si.
	public void cargarPublicaciones(String nombre) {
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String info[] = linea.split(" # ");
				Publicacion p = new Publicacion(info[0], info[1]);
				mapaPublicaciones.put(info[0], p);
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
	
	//Lee los datos del fichero que relaciona, los ids de las publicaciones con los ids
	public void cargarPublisAutor(String nombre) {
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String info[] = linea.split(" # ");
				String idPubli = info[0];
				String idAutor = info[1];
				if (!mapaPublisAutor.containsKey(idPubli)) {
					mapaPublisAutor.put(idPubli, new ArrayList<>());
				}
				mapaPublisAutor.get(idPubli).add(idAutor);
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
	
	public Publicacion buscarPubli (String id, String titulo) {
		Publicacion p =mapaPublicaciones.get(id);
		return p;
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
	
	public void aniadirAutorAPubli (String idAutor, String idPubli) {
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
	
	public void eliminarPubli (Publicacion p) {
		mapaPublicaciones.remove(p.getId());
	}
	
	public ArrayList<Publicacion> ordenarPublis (){
		ArrayList<Publicacion> listaOrdenada = new ArrayList<Publicacion>();
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
						inicio = mitad;
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
	
}



















