
public class PPal {
	
	public static void main(String[] args) {
		//MapaAutores ma = new MapaAutores();
		MapaPubli mp = new MapaPubli();
		//Obtener la fecha actual del sistema
		mp.cargarPublicaciones("Datuak/publications-titles-all.txt");
		long milisInicio = System.currentTimeMillis();
		mp.ordenarPublis();
		long milisFin = System.currentTimeMillis();
		
		//ma.cargarAutores("Datuak/authors-name-all.txt");
		//mp.cargarPublisAutor("Datuak/publications-authors-all-final.txt");
		//mp.cargarCitadas("Datuak/publications-citedPubs-all.txt");
		//List<Publicacion> po = mp.obtenerPublicacionesOrdenadas();
		//System.out.println("Publicaciones ordenadas");
		//System.out.println(po.get(0).getIdentificador());
		//System.out.println(po.get(po.size()-1).getIdentificador());
		System.out.println("Tiempo de ejecución: "+(milisFin-milisInicio)+" milisegundos");
		
		
	}
}

