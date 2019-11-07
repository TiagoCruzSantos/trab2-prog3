package sisPPGI;

import java.util.Date;

public class Programa {
	
	
	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		Sistema a = new Sistema();
		Docente b = new Docente(13, "roberto", new Date(1212), new Date(12321), true);
		a.cadastrarDocente(b);
		//System.out.println(a);
	}

}
