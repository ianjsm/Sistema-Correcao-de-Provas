# Programa de Avaliação de Provas Objetivas

Este é um programa que permite ao usuário criar, gerenciar e avaliar provas objetivas de uma disciplina. O programa foi desenvolvido para simplificar a tarefa de coletar e avaliar as respostas dos alunos, gerando automaticamente resultados ordenados alfabeticamente e por notas, além de calcular a média da turma. O programa realiza as seguintes tarefas:

## 1. Criar Arquivo de Respostas

O programa permite que o usuário crie um arquivo de respostas de alunos para uma disciplina específica. O nome do arquivo será o nome da disciplina, e o usuário é livre para inserir quantos alunos ele desejar. O formato do arquivo segue o exemplo abaixo, onde cada linha contém as respostas de um aluno, com as respostas separadas do nome do aluno por um "tab":

```
V V F V F V F V F V     Nome_do_Aluno
F F V V V V F V V V     Outro_Aluno
...
```

O programa oferece a flexibilidade de criar vários arquivos para diferentes disciplinas.

## 2. Gerar Resultado da Disciplina

O programa permite ao usuário gerar o resultado de uma disciplina específica. Para fazer isso, o usuário deve seguir os seguintes passos:

- Escolher a disciplina para a qual deseja gerar o resultado.
- O programa então produzirá dois arquivos como resultado:
  - Um arquivo que contém a lista dos alunos e seus respectivos pontos (número de acertos) ordenados por ordem alfabética.
  - Outro arquivo com as mesmas informações, porém ordenado por ordem decrescente de notas e mostrando a média da turma no final.
  - Os alunos que marcarem todas as questões com "V" ou "F" receberão a nota 0.

## 3. Visualização dos Dados

O programa oferece a funcionalidade de visualizar os resultados diretamente na tela. Isso permite ao usuário conferir os resultados sem a necessidade de abrir os arquivos gerados.

Este programa foi desenvolvido para facilitar o processo de avaliação de provas objetivas, economizando tempo e fornecendo resultados organizados e claros. Para executar o programa, certifique-se de seguir as instruções fornecidas durante a execução e de fornecer os arquivos de respostas e gabaritos corretamente.
