package principal;

import controller.AlunoManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int op;
        do {
            System.out.println("1. Inserir aluno");
            System.out.println("2. Remover aluno");
            System.out.println("3. Editar aluno");
            System.out.println("4. Listar alunos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    AlunoManager.InserirDados();
                    break;
                case 2:
                    System.out.print("Informe o ID do aluno que deseja remover: ");
                    int idRemover = scanner.nextInt();
                    AlunoManager.RemoverAluno(idRemover);
                    break;
                case 3:
                    System.out.print("Informe o CPF do aluno que deseja editar: ");
                    scanner.nextLine();
                    String cpfEditar = scanner.nextLine();
                    AlunoManager.EditarAluno(cpfEditar);
                    break;
                case 4:
                    AlunoManager.ListaAlunos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        } while (op != 0);
    }
}