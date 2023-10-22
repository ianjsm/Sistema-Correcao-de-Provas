package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

import util.LimparTerminal;

public class Disciplina {
	private File pastaArquivos;

	public void criarDisciplina() {
		Scanner teclado = new Scanner(System.in);

		// Solicitar o nome do arquivo
		System.out.print("Digite o nome da disciplina: ");
		String nomeArquivo = teclado.nextLine();
		String caminhoPasta = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq";

		try {
			File arquivo = new File(caminhoPasta, nomeArquivo + ".txt");

			if (arquivo.exists()) {
				System.out.println("A disciplina " + nomeArquivo + " já existe.");
			} else {
				if (arquivo.createNewFile()) {
					System.out.println(
							"A disciplina " + nomeArquivo + " foi criado com sucesso em " + arquivo.getAbsolutePath());
				} else {
					System.err.println("Não foi possível criar o arquivo " + nomeArquivo);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void buscarDisciplina() {
		Scanner scan = new Scanner(System.in);
		String caminho = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq";

		System.out.println("Qual disciplina deseja obter o resultado?");
		String nome = scan.nextLine();
		String nomeArquivo = "Gabarito_" + nome + ".txt";

		File pasta = new File(caminho);
		if (pasta.exists() && pasta.isDirectory()) {
			// Listar os arquivos na pasta
			File[] arquivos = pasta.listFiles();

			if (arquivos != null) {
				for (File arquivo : arquivos) {
					// Verificar se o nome do arquivo corresponde à entrada do usuário
					if (arquivo.getName().equalsIgnoreCase(nomeArquivo)) {
						System.out.println("Arquivo encontrado em: " + arquivo.getAbsolutePath());
						return; // Sair do loop assim que encontrar o arquivo
					}
				}
				// Se o loop terminar sem encontrar o arquivo
				System.out.println("Arquivo não encontrado na pasta.");
			} else {
				System.out.println("A pasta está vazia.");
			}
		} else {
			System.out.println("O caminho especificado não existe ou não é uma pasta.");
		}
	}

	public void listarArquivosDisciplinas() {
	    File diretorio = new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq");

	    if (diretorio.exists() && diretorio.isDirectory()) {
	        File[] arquivos = diretorio.listFiles(new FilenameFilter() {
	            @Override
	            public boolean accept(File dir, String name) {
	                // Filtrar apenas arquivos .txt que não são gabaritos
	                return name.endsWith(".txt") && !name.startsWith("Gabarito_") && !name.contains("ordem");
	            }
	        });

	        System.out.println("Disciplinas disponíveis:");
	        for (File arquivo : arquivos) {
	            // Remover a extensão .txt e exibir apenas o nome da disciplina
	            String nomeDisciplina = arquivo.getName().replace(".txt", "");
	            System.out.println(nomeDisciplina);
	        }
	    } else {
	        System.out.println("O diretório não existe ou não é uma pasta válida.");
	    }
	}

	public void adicionarAlunoRespostas() {
	    Scanner teclado = new Scanner(System.in);

	    System.out.print("Digite o nome da disciplina que deseja adicionar alunos: ");
	    String nomeArquivo = teclado.nextLine();
	    try {
	        File pastaArquivos = new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq");
	        File arquivo = new File(pastaArquivos, nomeArquivo + ".txt");

	        if (!arquivo.exists()) {
	            System.out.println("A disciplina " + nomeArquivo + " não existe.");
	            return;
	        }

	        boolean adicionarMaisAlunos = true;

	        while (adicionarMaisAlunos) {
	            System.out.print("Respostas do aluno:");
	            System.out.println();

	            // Abre o arquivo para escrita no modo de anexação (append)
	            try (FileWriter writer = new FileWriter(arquivo, true);
	                 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

	                // Loop para receber as respostas do aluno
	                for (int i = 0; i < 10; i++) {
	                    System.out.println("Digite a questão número " + (i + 1) + ":");
	                    char alternativa = teclado.next().charAt(0);
	                    bufferedWriter.write(alternativa);
	                }
	                bufferedWriter.write("\t");
	                teclado.nextLine(); // Consumir a nova linha
	                System.out.println("Digite o nome do aluno: ");
	                String nomeAluno = teclado.nextLine();
	                bufferedWriter.write(nomeAluno);
	                bufferedWriter.newLine();
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }

	            System.out.print("Deseja adicionar outro aluno? (S para Sim, N para Não): ");
	            String resposta = teclado.nextLine();
	            adicionarMaisAlunos = resposta.equalsIgnoreCase("s");
	        }
	        System.out.println("As respostas dos alunos foram adicionadas com sucesso em " + arquivo.getAbsolutePath());
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	public void definirGabaritoFinal() {
		Scanner teclado = new Scanner(System.in);

		System.out.println("Digite o nome da disciplina para definir o gabarito final:");
		String disciplina = teclado.nextLine();

		String gab = "Gabarito_";

		// Adicione a extensão .txt ao nome do arquivo
		String nomeArquivo = gab + disciplina + ".txt";
		String caminhoPasta = "C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\arq";

		File arquivoDisciplina = new File(caminhoPasta, nomeArquivo);

		try (FileWriter writer = new FileWriter(arquivoDisciplina);
				BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

			if (!arquivoDisciplina.exists()) {
				System.out.println("Arquivo da disciplina não encontrado.");
				return;
			}

			for (int i = 0; i < 10; i++) {
				System.out.println("Digite a questão número " + (i + 1) + ":");
				char alternativa = teclado.next().charAt(0);
				bufferedWriter.write(alternativa);
			}

			System.out.println("Gabarito final definido com sucesso para a disciplina: " + disciplina);
		} catch (IOException e) {
			System.err.println("Erro ao definir o gabarito final: " + e.getMessage());
		}
	}
}