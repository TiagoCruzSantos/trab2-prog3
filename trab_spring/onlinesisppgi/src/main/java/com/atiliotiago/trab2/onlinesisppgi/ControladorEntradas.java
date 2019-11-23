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

@Controller
public class ControladorEntradas {
    @GetMapping("/")

    public String homePage() {
        return "index";
    }

    @RequestMapping("/processar")
    public String processarArquivos(HttpServletResponse response, @RequestParam("files") MultipartFile[] files,
            @RequestParam("number") String number, ModelMap modelMap) {
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

            try {
                ppgi.carregaArquivoArquivoDocentes(docentes);
                ppgi.carregaArquivoVeiculos(veiculos);
                ppgi.carregaArquivoPublicacao(publicacoes);
                ppgi.carregaArquivoQualis(qualis);
                ppgi.carregaArquivoRegra(regras);
            } catch (CodigoRepetidoDocente e1) {
                System.out.println(e1.getMessage());
            } catch (SiglaVeiculoRepetido e2) {
                System.out.println(e2.getMessage());

            } catch (CodigoDocenteIndefinido e3) {
                System.out.println(e3.getMessage());
                /*
                 * } catch (CodigoRepetidoVeiculo e4) { System.out.println(e4.getMessage());
                 * houveExcecao = true; } catch (QualisDesconhecidoRegra e5) {
                 * System.out.println(e5.getMessage()); houveExcecao = true; } catch
                 * (QualisDesconhecidoVeiculo e6) { System.out.println(e6.getMessage());
                 * houveExcecao = true; } catch (SiglaVeiculoPublicacaoIndefinida e7) {
                 * System.out.println(e7.getMessage()); houveExcecao = true; } catch
                 * (TipoVeiculoDesconhecido e8) { System.out.println(e8.getMessage());
                 * houveExcecao = true;
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
