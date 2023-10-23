package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Aluno implements Comparable<Aluno> {
	private String nome;
	private int pontuacao;

	public Aluno() {

	}

	public Aluno(String nome, int pontuacao) {
		this.nome = nome;
		this.pontuacao = pontuacao;
	}

	public Aluno(String nomeNota) {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public int compareTo(Aluno outroAluno) {
		return this.nome.compareTo(outroAluno.nome);
	}

	public void compararGabaritoResposta() {
		List<String> respostasDosAlunos = new ArrayList<>();
		List<Integer> pontuacoes = new ArrayList<>();
		List<String> nomesDosAlunos = new ArrayList<>(); 
		List<Aluno> alunos = new ArrayList<>();
		int acertos = 0;
		Scanner teclado = new Scanner(System.in);

		System.out.println("Digite o nome da disciplina que deseja: ");
		String nome = teclado.nextLine();
		String caminhoArquivoGabarito = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq\\" + "Gabarito_" + nome
				+ ".txt";
		String caminhoArquivoRespostas = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq\\" + nome + ".txt";

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
				String[] partes = linhaAtual.split("\t");
				if (partes.length == 2) {
					String respostas = partes[0];
					if (respostas.equalsIgnoreCase("VVVVVVVVVV") || respostas.equalsIgnoreCase("FFFFFFFFFF")) {
						String nomeAluno = partes[1];
						respostasDosAlunos.add(respostas);
						nomesDosAlunos.add(nomeAluno);
						acertos = 0;
						pontuacoes.add(acertos);
						Aluno aluno = new Aluno(nomeAluno, acertos);
						alunos.add(aluno);
					} else {
						String nomeAluno = partes[1];
						respostasDosAlunos.add(respostas);
						nomesDosAlunos.add(nomeAluno);
						acertos = calcularAcertos(respostas, conteudoDoArquivo);
						pontuacoes.add(acertos);
						Aluno aluno = new Aluno(nomeAluno, acertos);
						alunos.add(aluno);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < pontuacoes.size(); i++) {
			acertos = pontuacoes.get(i);
			String nomeAluno = nomesDosAlunos.get(i);
			System.out.println("Aluno " + nomeAluno + ": Acertos = " + acertos);
		}
		System.out.println();
		System.out.print("A média da turma foi: ");
		double mediaTurma = calcularMedia(pontuacoes);
		System.out.println(mediaTurma);
		System.out.println();
		System.out.println();
		Disciplina disciplina = new Disciplina();
		//ordenarAlfabetica(nomesDosAlunos, pontuacoes);
		 criarArquivoOrdemAlfabetica(alunos, nome);
		System.out.println();
		//ordenarPontuacao(nomesDosAlunos, pontuacoes);
		 criarArquivoOrdemPontuacao(alunos, nome);
	}

	public int calcularAcertos(String respostaAluno, String gabarito) {
		int acertos = 0;
		int tamanhoMinimo = Math.min(respostaAluno.length(), gabarito.length());

		for (int i = 0; i < tamanhoMinimo; i++) {
			if (Character.toLowerCase(respostaAluno.charAt(i)) == Character.toLowerCase(gabarito.charAt(i))) {
				acertos++;
			}
		}
		return acertos;
	}

	public double calcularMedia(List<Integer> pontuacoes) {
		double soma = 0;

		for (Integer pontuacao : pontuacoes) {
			soma += pontuacao;
		}
		double media = soma / pontuacoes.size();
		return media;
	}

	public void ordenarAlfabetica(List<String> nomesDosAlunos, List<Integer> pontuacoes) {
		Collections.sort(nomesDosAlunos);
		System.out.println("Em ordem alfabetica: ");
		for (int i = 0; i < nomesDosAlunos.size(); i++) {
			System.out.println((i + 1) + "- " + nomesDosAlunos.get(i) + ": " + pontuacoes.get(i));
		}
	}

	public void ordenarPontuacao(List<String> nomesDosAlunos, List<Integer> pontuacoes) {
		Collections.sort(pontuacoes, Collections.reverseOrder());
		System.out.println("Em ordem decrescente de notas: ");
		for (int i = 0; i < pontuacoes.size(); i++) {
			System.out.println((i + 1) + "- " + nomesDosAlunos.get(i) + ": " + pontuacoes.get(i));
		}
	}

	public void criarArquivoOrdemAlfabetica(List<Aluno> alunos, String nome) {
	    Collections.sort(alunos);

	    String caminhoArquivoSaida = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq\\" + nome + "_ordemAlfabetica.txt";

	    try {
	        File arquivoSaida = new File(caminhoArquivoSaida);
	        if (arquivoSaida.exists()) {
	            arquivoSaida.delete();
	        }
	        arquivoSaida.createNewFile();

	        PrintWriter writer = new PrintWriter(arquivoSaida);

	        for (int i = 0; i < alunos.size(); i++) {
	            Aluno aluno = alunos.get(i);
	            String linha = aluno.getNome() + "\t" + aluno.getPontuacao();
	            writer.println(linha);
	        }

	        writer.close();

	        System.out.println("Arquivo criado com sucesso: " + caminhoArquivoSaida);
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Erro ao criar o arquivo.");
	    }
	}

	public void criarArquivoOrdemPontuacao(List<Aluno> alunos, String nome) {
	    Collections.sort(alunos, (a1, a2) -> Integer.compare(a2.getPontuacao(), a1.getPontuacao()));

	    String caminhoArquivoSaida = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq\\" + nome + "_ordemPontuacao.txt";

	    try {
	        File arquivoSaida = new File(caminhoArquivoSaida);
	        if (arquivoSaida.exists()) {
	            arquivoSaida.delete();
	        }
	        arquivoSaida.createNewFile();

	        PrintWriter writer = new PrintWriter(arquivoSaida);

	        for (int i = 0; i < alunos.size(); i++) {
	            Aluno aluno = alunos.get(i);
	            String linha = aluno.getNome() + "\t" + aluno.getPontuacao();
	            writer.println(linha);
	        }

	        writer.close();

	        System.out.println("Arquivo criado com sucesso: " + caminhoArquivoSaida);
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Erro ao criar o arquivo.");
	    }
	}
}