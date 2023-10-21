package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Aluno {
	private int numero;
	private int pontuacao;
	private List<Integer> pontuacoes = new ArrayList<>();
	
	public Aluno() {

	}

	public Aluno(int numero, int pontuacao) {
		this.numero = numero;
		this.pontuacao = pontuacao;
	}

	public int getNumero() {
		return numero;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void compararGabaritoResposta() {
	    List<String> respostasDosAlunos = new ArrayList<>();
	    List<Integer> pontuacoes = new ArrayList<>(); // Lista para armazenar as pontuações dos alunos
	    List<String> nomesDosAlunos = new ArrayList<>(); // Lista para armazenar os nomes dos alunos
	    Scanner teclado = new Scanner(System.in);

	    System.out.println("Digite o nome da disciplina que deseja: ");
	    String nome = teclado.nextLine();
	    String caminhoArquivoGabarito = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq\\" + "Gabarito_" + nome
	            + ".txt";
	    String caminhoArquivoRespostas = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq\\" + nome + ".txt";

	    // Crie um StringBuilder para armazenar o conteúdo do arquivo
	    StringBuilder conteudo = new StringBuilder();

	    try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivoGabarito))) {
	        int caractere;
	        while ((caractere = reader.read()) != -1) {
	            conteudo.append((char) caractere);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // Agora, você tem o conteúdo do arquivo do gabarito no StringBuilder
	    String conteudoDoArquivo = conteudo.toString();

	    try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivoRespostas))) {
	        String linhaAtual;
	        while ((linhaAtual = reader.readLine()) != null) {
	            // Dividir a linha em respostas e nome do aluno usando o tab como separador
	            String[] partes = linhaAtual.split("\t");
	            if (partes.length == 2) {
	                String respostas = partes[0];
	                String nomeAluno = partes[1];
	                respostasDosAlunos.add(respostas);
	                nomesDosAlunos.add(nomeAluno);
	                int acertos = calcularAcertos(respostas, conteudoDoArquivo);
	                pontuacoes.add(acertos); // Adicione o número de acertos à lista de pontuações
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Agora, vamos imprimir os acertos para cada aluno
	    for (int i = 0; i < pontuacoes.size(); i++) {
	        int acertos = pontuacoes.get(i);
	        String nomeAluno = nomesDosAlunos.get(i);
	        System.out.println("Aluno " + nomeAluno + ": Acertos = " + acertos);
	    }
	}

	private int calcularAcertos(String respostaAluno, String gabarito) {
		int acertos = 0;
		int tamanhoMinimo = Math.min(respostaAluno.length(), gabarito.length());

		for (int i = 0; i < tamanhoMinimo; i++) {
			if (Character.toLowerCase(respostaAluno.charAt(i)) == Character.toLowerCase(gabarito.charAt(i))) {
				acertos++;
			}
		}
		return acertos;
	}
}