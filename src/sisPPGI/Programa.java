package sisPPGI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sisPPGI.excecoes.CodigoDocenteIndefinido;
import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.CodigoRepetidoVeiculo;
import sisPPGI.excecoes.QualisDesconhecidoRegra;
import sisPPGI.excecoes.QualisDesconhecidoVeiculo;
import sisPPGI.excecoes.SiglaIndefinida;
import sisPPGI.excecoes.SiglaVeiculoRepetido;
import sisPPGI.excecoes.TipoVeiculoDesconhecido;

public class Programa {
    public static void main(String[] args) {
        Sistema ppgi = new Sistema();
        Scanner docentes = null;
        Scanner qualis = null;
        Scanner veiculos = null;
        Scanner publicacoes = null;
        Scanner regras = null;
        int ano = 0;
        boolean readOnly = false;
        boolean writeOnly = false;
        boolean houveExcecao = false;

        for (int i = 0; i < args.length; i++) {
            String argAtual = args[i];

            switch (argAtual) {
            case "-d":
                try {
                    docentes = new Scanner(new FileReader(args[i + 1]));
                    i++;
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Erro de I/O - Não foi possível abrir o arquivo de docentes.");
                    houveExcecao = true;
                    break;
                }
            case "-r":
                try {
                    regras = new Scanner(new FileReader(args[i + 1]));
                    i++;
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Erro de I/O - Não foi possível abrir o arquivo de regras.");
                    houveExcecao = true;
                    break;
                }
            case "-q":
                try {
                    qualis = new Scanner(new FileReader(args[i + 1]));
                    i++;
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Erro de I/O - Não foi possível abrir o arquivo de qualis.");
                    houveExcecao = true;
                    break;
                }
            case "-v":
                try {
                    veiculos = new Scanner(new FileReader(args[i + 1]));
                    i++;
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Erro de I/O - Não foi possível abrir o arquivo de veículos.");
                    houveExcecao = true;
                    break;
                }
            case "-p":
                try {
                    publicacoes = new Scanner(new FileReader(args[i + 1]));
                    i++;
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Erro de I/O - Não foi possível abrir o arquivo de publicações.");
                    houveExcecao = true;
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

        if (args.length > 0 && !houveExcecao) {
            try {
                ppgi.carregaDocentes(docentes);
                ppgi.carregaVeiculos(veiculos);
                ppgi.carregaPublicacao(publicacoes);
                ppgi.carregaQualis(qualis);
                ppgi.carregaRegra(regras);
            } catch (CodigoRepetidoDocente e1) {
                System.out.println(e1.getMessage());
                houveExcecao = true;
            } catch (SiglaVeiculoRepetido e2) {
                System.out.println(e2.getMessage());
                houveExcecao = true;
            /*
            } catch (CodigoDocenteIndefinido e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (CodigoRepetidoVeiculo e4) {
                System.out.println(e4.getMessage());
                houveExcecao = true;
            } catch (QualisDesconhecidoRegra e5) {
                System.out.println(e5.getMessage());
                houveExcecao = true;
            } catch (QualisDesconhecidoVeiculo e6) {
                System.out.println(e6.getMessage());
                houveExcecao = true;
            } catch (SiglaIndefinida e7) {
                System.out.println(e7.getMessage());
                houveExcecao = true;
            } catch (TipoVeiculoDesconhecido e8) {
                System.out.println(e8.getMessage());
                houveExcecao = true;
            */
            }

            if ((!readOnly && !houveExcecao) || (writeOnly && !houveExcecao)) {
                try {
                    ppgi.imprimirPublicacoes();
                    ppgi.imprimirEstatisticas();
                    ppgi.imprimirRecredenciamento(ano);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
