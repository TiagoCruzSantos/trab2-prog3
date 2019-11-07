package meupacote;

import java.util.Date;

public class MinhaClassePrincipal {
	
	
	public static void main(String[] args) {
		System.out.print("AAAAAAAAA\n");
		Sistema a = new Sistema();
		Docente b = new Docente(13, "roberto", new Date(1212), new Date(12321), true);
		a.cadastrarDocente(b);
		System.out.println(a);
	}

}
