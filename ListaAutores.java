import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaAutores {

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
			}
			entrada.close(); //Cerramos el scanner
		} catch (IOException e) {   //Excepcion que salta (si no se puede leer el fichero)
			e.printStackTrace();
		}
	}
	
	public void guardarAutores(String nombre) { //
		try {
			PrintWriter salida = new PrintWriter(new File(nombre));
			for (Autor a: listaAutores) {
				salida.println(a.getId()+" # "+a.getNombre());
			}
			salida.flush();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
