package application;

import java.io.IOException;
import java.util.Scanner;
import entities.Aluno;
import entities.Disciplina;
import util.LimparTerminal;
import util.Timer;

public class Main {
	public static void main(String[] args) throws IOException {
		Disciplina disciplina = new Disciplina();
		Aluno aluno = new Aluno();
		Scanner teclado = new Scanner(System.in);

		int opcao = 999;
		do {
			System.out.println("---------------MENU------------------");
			System.out.println("01. Criar Disciplina");
			System.out.println("02. Inserir alunos em disciplina");
			System.out.println("03. Criar gabarito final de disciplinas.");
			System.out.println("04. Gerar resultado de disciplina.");
			System.out.println("05. Informar localização do arquivo.");
			System.out.println("0. Sair do programa.");
			System.out.println("Digite a opção escolhida: ");
			opcao = teclado.nextInt();
			Timer.transicao(1000);

			switch (opcao) {
			case 1:
				disciplina.criarDisciplina();
				break;
			case 2:
				disciplina.listarArquivosDisciplinas();
				disciplina.adicionarAlunoRespostas();
				break;
			case 3:
				LimparTerminal.limparTerminal();
				disciplina.listarArquivosDisciplinas();
				disciplina.definirGabaritoFinal();
				break;
			case 4:
				disciplina.listarArquivosDisciplinas();
				aluno.compararGabaritoResposta();
				break;
			case 5:
				disciplina.listarArquivosDisciplinas();
				disciplina.buscarDisciplina();
				break;
			case 0:
				System.out.println("Programa encerrado.");
				System.exit(0);
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 0);
	}
}