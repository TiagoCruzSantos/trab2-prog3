package com.trab2.onlineppgi;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
public class ControladorEntradas {
    
	@GetMapping("/")
    public String homePage() {
        return "index";
    }

	@RequestMapping(value = "/processar")
    public String processarArquivos(Model model, HttpServletResponse response, @RequestParam("files") MultipartFile[] files,
            @RequestParam("number") String number, ModelMap modelMap) {
        modelMap.addAttribute("files", files);
        modelMap.addAttribute("number", number);
        boolean houveExcecao = false;
        Exception esaida = null;
        int ano = 0;
        Sistema ppgi = new Sistema();
        for(int i = 0; i < files.length; i++){
            if(files[i].getOriginalFilename().compareTo("") == 0){
                houveExcecao = true;
                esaida = new Exception("Menos de 5 arquivos informados");
                break;
            }
        }
        if(number.compareTo("") == 0){
            houveExcecao = true;
            esaida = new Exception("Ano não infomado");
        }
        if(!houveExcecao){
            try {
                Scanner docentes = new Scanner(files[0].getInputStream());
                Scanner veiculos = new Scanner(files[1].getInputStream());
                Scanner publicacoes = new Scanner(files[2].getInputStream());
                Scanner qualis = new Scanner(files[3].getInputStream());
                Scanner regras = new Scanner(files[4].getInputStream());
                ano = Integer.parseInt(number.trim());

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
        }
        if(houveExcecao) {
        	model.addAttribute("erro", esaida.getMessage());
        	return "erro";
        }else {
        	model.addAttribute("ano", ano);
        	return "sucesso";
        }
    }
	
}