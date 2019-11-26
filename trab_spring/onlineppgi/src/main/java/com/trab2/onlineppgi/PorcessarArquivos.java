package com.trab2.onlineppgi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sisPPGI.Sistema;
import sisPPGI.excecoes.CodigoDocenteIndefinido;
import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.QualisDesconhecidoRegra;
import sisPPGI.excecoes.QualisDesconhecidoVeiculo;
import sisPPGI.excecoes.SiglaIndefinidaVeiculo;
import sisPPGI.excecoes.SiglaRepetidaVeiculo;
import sisPPGI.excecoes.SiglaVeiculoPublicacaoIndefinida;
import sisPPGI.excecoes.TipoVeiculoDesconhecido;

@RestController
public class PorcessarArquivos {
	
	@RequestMapping(value = "/processar")
    public String processarArquivos(HttpServletResponse response, @RequestParam("files") MultipartFile[] files,
            @RequestParam("number") String number, ModelMap modelMap) {
        modelMap.addAttribute("files", files);
        modelMap.addAttribute("number", number);
        boolean houveExcecao = false;
        Exception esaida = null;
        Sistema ppgi = new Sistema();
        try {
            Scanner docentes = new Scanner(files[0].getInputStream());
            Scanner veiculos = new Scanner(files[1].getInputStream());
            Scanner publicacoes = new Scanner(files[2].getInputStream());
            Scanner qualis = new Scanner(files[3].getInputStream());
            Scanner regras = new Scanner(files[4].getInputStream());
            int ano = Integer.parseInt(number);

            try {
                ppgi.carregaArquivoArquivoDocentes(docentes);
                ppgi.carregaArquivoVeiculos(veiculos);
                ppgi.carregaArquivoPublicacao(publicacoes);
                ppgi.carregaArquivoQualis(qualis);
                ppgi.carregaArquivoRegra(regras);
            } catch (CodigoRepetidoDocente e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (SiglaRepetidaVeiculo e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (CodigoDocenteIndefinido e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (SiglaVeiculoPublicacaoIndefinida e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (TipoVeiculoDesconhecido e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (QualisDesconhecidoVeiculo e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (QualisDesconhecidoRegra e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (SiglaIndefinidaVeiculo e3) {
                System.out.println(e3.getMessage());
                esaida = e3;
                houveExcecao = true;
            } catch (InputMismatchException e2) {
                System.out.println("Erro de formatação");
                esaida = e2;
                houveExcecao = true;
            } catch (IllegalArgumentException e2) {
                System.out.println("Erro de formatação");
                esaida = e2;
                houveExcecao = true;
            } catch (ArrayIndexOutOfBoundsException e2) {
                System.out.println("Erro de formatação");
                esaida = e2;
                houveExcecao = true;
            }
            finally {
                docentes.close();
                publicacoes.close();
                veiculos.close();
                qualis.close();
                regras.close();
            }
            if(!houveExcecao) {
	            try {
	                ppgi.imprimirEstatisticas();
	                ppgi.imprimirPublicacoes();
	                ppgi.imprimirRecredenciamento(ano);
	            } catch (IOException e) {
	                e.printStackTrace();
	                esaida = e;
	            }
            }

        } catch (IOException e) {
            e.printStackTrace();
            esaida = e;
        }
        if(houveExcecao) {
        	return esaida.getMessage();
        }else {
        	return "Arquivos processados com sucesso<br>"
        			+ "<a target=\"blank_\" href=\"/1-recredenciamento.csv\">Recredenciamento</a><br>"
        			+ "<a target=\"blank_\" href=\"/2-publicacoes.csv\">Publicacoes</a><br>"
        			+ "<a target=\"blank_\" href=\"/3-estatisticas.csv\">Estatisticas</a>";
        }
    }
	
	@RequestMapping("/3-estatisticas.csv")
	public String estatisticas(HttpServletResponse response) {
		InputStream estatisticas;
		try {
			response.setContentType("text/csv");
			estatisticas = new FileInputStream("3-estatisticas.csv");
			IOUtils.copy(estatisticas, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/1-recredenciamento.csv")
	public String recredenciamento(HttpServletResponse response) {
		InputStream recredenciamento;
		try {
			response.setContentType("text/csv");
			recredenciamento = new FileInputStream("1-recredenciamento.csv");
			IOUtils.copy(recredenciamento, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/2-publicacoes.csv")
	public String publicacoes(HttpServletResponse response) {
		InputStream publicacoes;
		try {
			response.setContentType("text/csv");
			publicacoes = new FileInputStream("2-publicacoes.csv");
			IOUtils.copy(publicacoes, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
