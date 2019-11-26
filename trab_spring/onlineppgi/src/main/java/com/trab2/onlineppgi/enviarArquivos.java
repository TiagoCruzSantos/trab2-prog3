package com.trab2.onlineppgi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class enviarArquivos {
	@RequestMapping("/3-estatisticas.csv")
	public String estatisticas(HttpServletResponse response) {
		InputStream estatisticas;
		try {
			response.setContentType("text/csv");
			estatisticas = new FileInputStream("3-estatisticas.csv");
			IOUtils.copy(estatisticas, response.getOutputStream());
			response.flushBuffer();
			estatisticas.close();
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
			recredenciamento.close();
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
			publicacoes.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
