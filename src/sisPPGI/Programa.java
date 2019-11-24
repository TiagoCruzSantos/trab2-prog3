package sisPPGI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;

import sisPPGI.excecoes.CodigoDocenteIndefinido;
import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaRepetidaVeiculo;
import sisPPGI.excecoes.QualisDesconhecidoRegra;
import sisPPGI.excecoes.QualisDesconhecidoVeiculo;
import sisPPGI.excecoes.SiglaIndefinidaVeiculo;
import sisPPGI.excecoes.SiglaVeiculoPublicacaoIndefinida;
import sisPPGI.excecoes.SiglaRepetidaVeiculo;
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
        if (!houveExcecao && !writeOnly) {
            try {
                ppgi.carregaArquivoArquivoDocentes(docentes);
                ppgi.carregaArquivoVeiculos(veiculos);
                ppgi.carregaArquivoPublicacao(publicacoes);
                ppgi.carregaArquivoQualis(qualis);
                ppgi.carregaArquivoRegra(regras);
            } catch (CodigoRepetidoDocente e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (SiglaRepetidaVeiculo e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (CodigoDocenteIndefinido e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (SiglaVeiculoPublicacaoIndefinida e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (TipoVeiculoDesconhecido e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (QualisDesconhecidoVeiculo e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (QualisDesconhecidoRegra e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (SiglaIndefinidaVeiculo e3) {
                System.out.println(e3.getMessage());
                houveExcecao = true;
            } catch (InputMismatchException e2) {
                System.out.println("Erro de formatação");
                houveExcecao = true;
            } catch (IllegalArgumentException e2) {
                System.out.println("Erro de formatação");
                houveExcecao = true;
            } catch (ArrayIndexOutOfBoundsException e2) {
                System.out.println("Erro de formatação");
                houveExcecao = true;
            }
        }
        if (!writeOnly && readOnly && !houveExcecao) {
            try {
                FileOutputStream readOnlyDat = new FileOutputStream("recredenciamento.dat");
                ObjectOutputStream dat = new ObjectOutputStream(readOnlyDat);
                dat.writeObject(ppgi);
                dat.writeObject(ano);
                dat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (writeOnly && !houveExcecao) {
            try {
                FileInputStream writeOnlyDat = new FileInputStream("recredenciamento.dat");
                ObjectInputStream dat = new ObjectInputStream(writeOnlyDat);
                ppgi = (Sistema) dat.readObject();
                ano = (Integer) dat.readObject();
                dat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ((writeOnly || !readOnly) && !houveExcecao) {
            try {
                ppgi.imprimirEstatisticas();
                ppgi.imprimirPublicacoes();
                ppgi.imprimirRecredenciamento(ano);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
