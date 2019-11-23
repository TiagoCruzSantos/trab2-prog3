package com.atiliotiago.trab2.onlinesisppgi;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sisPPGI.Sistema;
import sisPPGI.excecoes.CodigoDocenteIndefinido;
import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaVeiculoRepetido;

@RestController
public class ControladorEntradas {
	
	@RequestMapping("/")
	public String index() {
		return "<form id=\"form_90287\" class=\"appnitro\" enctype=\"multipart/form-data\" method=\"post\" action=\"/processar\">\n" + 
				"					<div class=\"form_description\">\n" + 
				"			<h2>Sistema PPGI</h2>\n" + 
				"			<p></p>\n" + 
				"		</div>						\n" + 
				"			<ul >\n" + 
				"			\n" + 
				"					<li id=\"li_1\" >\n" + 
				"		<label class=\"description\" for=\"element_1\">Arquivo de docentes </label>\n" + 
				"		<div>\n" + 
				"			<input id=\"element_1\" name=\"files\" class=\"element file\" type=\"file\"/> \n" + 
				"		</div>  \n" + 
				"		</li>		<li id=\"li_2\" >\n" + 
				"		<label class=\"description\" for=\"element_2\">Arquivo de veiculos </label>\n" + 
				"		<div>\n" + 
				"			<input id=\"element_2\" name=\"files\" class=\"element file\" type=\"file\"/> \n" + 
				"		</div>  \n" + 
				"		</li>		<li id=\"li_3\" >\n" + 
				"		<label class=\"description\" for=\"element_3\">Arquivo de publicações </label>\n" + 
				"		<div>\n" + 
				"			<input id=\"element_3\" name=\"files\" class=\"element file\" type=\"file\"/> \n" + 
				"		</div>  \n" + 
				"		</li>		<li id=\"li_4\" >\n" + 
				"		<label class=\"description\" for=\"element_4\">Arquivo de qualis </label>\n" + 
				"		<div>\n" + 
				"			<input id=\"element_4\" name=\"files\" class=\"element file\" type=\"file\"/> \n" + 
				"		</div>  \n" + 
				"		</li>		<li id=\"li_5\" >\n" + 
				"		<label class=\"description\" for=\"element_5\">Arquivo de regras </label>\n" + 
				"		<div>\n" + 
				"			<input id=\"element_5\" name=\"files\" class=\"element file\" type=\"file\"/> \n" + 
				"		</div>  \n" + 
				"		</li>		<li id=\"li_6\" >\n" + 
				"		<label class=\"description\" for=\"element_6\">Ano </label>\n" + 
				"		<div>\n" + 
				"			<input id=\"element_6\" name=\"number\" class=\"element text medium\" type=\"text\" maxlength=\"255\" value=\"\"/> \n" + 
				"		</div> \n" + 
				"		</li>\n" + 
				"			\n" + 
				"					<li class=\"buttons\">\n" + 
				"			    <input type=\"hidden\" name=\"form_id\" value=\"90287\" />\n" + 
				"			    \n" + 
				"				<input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Submit\" />\n" + 
				"		</li>\n" + 
				"			</ul>\n" + 
				"		</form>	";
	}
	
	@RequestMapping("/processar")
	public String processarArquivos(HttpServletResponse response, @RequestParam("files") MultipartFile[] files, @RequestParam("number") String number,ModelMap modelMap) {
		modelMap.addAttribute("files", files);
		modelMap.addAttribute("number", number);
		Sistema ppgi = new Sistema();
		try {
			Scanner docentes = new Scanner(files[0].getInputStream());
			Scanner veiculos = new Scanner(files[1].getInputStream());
			Scanner publicacoes = new Scanner(files[2].getInputStream());
			Scanner qualis = new Scanner(files[3].getInputStream());
			Scanner regras = new Scanner(files[4].getInputStream());
			int ano = Integer.parseInt(number);
			
			try{
                ppgi.carregaArquivoArquivoDocentes(docentes);
                ppgi.carregaArquivoVeiculos(veiculos);
                ppgi.carregaArquivoPublicacao(publicacoes);
                ppgi.carregaArquivoQualis(qualis);
                ppgi.carregaArquivoRegra(regras);
            }catch(CodigoRepetidoDocente e1){
                System.out.println(e1.getMessage());
            }catch (SiglaVeiculoRepetido e2) {
                System.out.println(e2.getMessage());
            
            } catch (CodigoDocenteIndefinido e3) {
                System.out.println(e3.getMessage());
            /*} catch (CodigoRepetidoVeiculo e4) {
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
			
			try {
				ppgi.imprimirEstatisticas();
				ppgi.imprimirPublicacoes();
				ppgi.imprimirRecredenciamento(ano);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return "fileUploadView";
	}
}
