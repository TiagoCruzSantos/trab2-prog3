package sisPPGI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaVeiculoRepetido;

public class Programa {
	
	
	public static void main(String[] args) {
		Sistema ppgi = new Sistema();
		Scanner docentes = null;
		Scanner qualis = null;
		Scanner veiculos = null;
		Scanner publicacoes = null;
		Scanner regras = null;
		int ano;
		boolean readOnly = false;
		boolean writeOnly = false;
		boolean excecao = false;
		for(int i = 0; i < args.length; i++) {
			String argAtual = args[i];
			switch(argAtual) {
				case "-d":
					try {
						docentes = new Scanner(new FileReader(args[i + 1]));
						i++;
						break;
					} catch (FileNotFoundException e) {
						System.out.println("Arquivo de docentes não foi encontrado");
						excecao = true;
						break;
					}
				case "-r":
					try {
						regras = new Scanner(new FileReader(args[i + 1]));
						i++;
						break;
					} catch (FileNotFoundException e) {
						System.out.println("Arquivo de regras não foi encontrado");
						excecao = true;
						break;
					}
				case "-q":
					try {
						qualis = new Scanner(new FileReader(args[i + 1]));
						i++;
						break;
					} catch (FileNotFoundException e) {
						System.out.println("Arquivo de qualis não foi encontrado");
						excecao = true;
						break;
					}
				case "-v":
					try {
						veiculos = new Scanner(new FileReader(args[i + 1]));
						i++;
						break;
					} catch (FileNotFoundException e) {
						System.out.println("Arquivo de veiculos não foi encontrado");
						excecao = true;
						break;
					}
				case "-p":
					try {
						publicacoes = new Scanner(new FileReader(args[i + 1]));
						i++;
						break;
					} catch (FileNotFoundException e) {
						System.out.println("Arquivo de publicações não foi encontrado");
						excecao = true;
						break;
					}
				case "-a":
					ano = Integer.parseInt(args[i + 1]);
					i++;
					break;
				case "--read-only":
					readOnly = true;
					break;
				case "--write-only":
					writeOnly = true;
					break;
			}
		}
		if(args.length > 0 && !excecao) {
			try {
				ppgi.carregaDocentes(docentes);
				ppgi.carregaVeiculos(veiculos);
				ppgi.carregaPublicacao(publicacoes);
				ppgi.carregaQualis(qualis);
			} catch (CodigoRepetidoDocente e1) {
				System.out.println(e1.getMessage());
				excecao = true;
			} catch (SiglaVeiculoRepetido e2) {
				System.out.println(e2.getMessage());
				excecao = true;
			}
			//System.out.println(ppgi);
			FileWriter a = null;
			FileWriter b = null;
			FileWriter c = null;
			if(!readOnly && !excecao){
				try {
					a = new FileWriter("1-recredenciamento.csv");
					b = new FileWriter("2-publicacoes.csv");
					c = new FileWriter("3-estatisticas.csv");
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					a.append("SADSADFYGUA");
					b.append("SSSSSS");
					c.append("AAAAAAA");
					a.flush();
					b.flush();
					c.flush();
					a.close();
					b.close();
					c.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
