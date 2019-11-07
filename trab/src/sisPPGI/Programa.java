package sisPPGI;

import java.util.Scanner;

public class Programa {
	
	
	public static void main(String[] args) {
		Scanner docentes;
		Scanner qualis;
		Scanner veiculos;
		Scanner publicacoes;
		Scanner regras;
		boolean readOnly = false;
		boolean writeOnly = false;
		for(int i = 0; i < args.length; i++) {
			String argAtual = args[i];
			switch(argAtual) {
				case "-d":
					docentes = new Scanner(args[i + 1]);
					i++;
					break;
				case "-r":
					regras = new Scanner(args[i + 1]);
					i++;
					break;
				case "-q":
					qualis = new Scanner(args[i + 1]);
					i++;
					break;
				case "-v":
					veiculos = new Scanner(args[i + 1]);
					i++;
					break;
				case "-p":
					publicacoes = new Scanner(args[i + 1]);
					i++;
					break;
				case "--read-only":
					readOnly = true;
					break;
				case "--write-only":
					writeOnly = true;
			}
		}
		
		
	}

}
