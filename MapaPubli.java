import java.io.File;
import java.io.FileNotFoundException;
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
	
}



















