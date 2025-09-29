
public class Eliminar2 {

	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	public class ListaAutores {
		//private ArrayList<Autor> listaAutores;
		private List<Autor> listaAutores;
		//¿Creamos un mapa que relacione cada autor con sus publicacione?
		
		public ListaAutores() {
			//listaAutores = new ArrayList<Autor>();
			listaAutores = new ArrayList<Autor>();
		}
		
		public void mostrar() {
			System.out.println(listaAutores.get(0).getIdentificador());
			System.out.println(listaAutores.get(listaAutores.size()-1).getIdentificador());
		}
		public void cargarFicheroAutores(String nombre) {
			try {
				Scanner entrada = new Scanner(new FileReader(nombre));
				String linea = null;
				while(entrada.hasNextLine()) {
					linea = entrada.nextLine();
					//linea = "Q95147729 # A Einstein"
					String [] datos = linea.split(" # ");
					Autor a = new Autor(datos[0], datos[1]);
					listaAutores.add(a);
					System.out.println(a.getIdentificador());
				}
				entrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void guardarFicheroAutores(String nom) {
			try {
				PrintWriter salida = new PrintWriter(new File(nom));
				//Recorremos la lista de autores
				for(Autor a: listaAutores){
					salida.println(a.getIdentificador()+" # "+a.getNombre());
				}
				salida.flush();
				salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Añadir un nuevo autor (dados su identificador y nombre)
		public void aniadirNuevoAutor(String idA, String nombreA) {
			Autor a = new Autor(idA, nombreA);
			listaAutores.add(a);
		}
		
		//Método que devuelve la posición de la lista en la que se encuentra el autor cuyo id se recibe por parámetro
		public int buscarAutor(String idA) {
			int pos=0;
			boolean enc = false;
			Autor a = null;
			while(!enc && pos<listaAutores.size()) {
				a = listaAutores.get(pos);
				if(a.getIdentificador().equals(idA)) {
					enc = true;
				}else {
					pos++;
				}
			}
			if(!enc) {
				return -1;
			}else {
				return pos;
			}
		}
		
		//Borrar un autor
		public void borrarAutor(String idA) {
			int pos = buscarAutor(idA);
			if(pos!=-1) {
				listaAutores.remove(pos);
			}
		}
	}

}
