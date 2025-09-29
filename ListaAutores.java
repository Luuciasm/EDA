import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaAutores {

	private ArrayList<Autor> listaAutores;
	
	public ListaAutores() {
		listaAutores = new ArrayList<>();
	}
	
	public void cargarAutores(String nombre) {
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String info[] = linea.split(" # ");
				Autor a = new Autor(info[0], info[1]);
				listaAutores.add(a);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarAutores(String nombre) {
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
