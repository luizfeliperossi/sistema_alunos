package controller;

import model.*;
import persistence.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoManager {

    public static void InserirDados() {
        List<Aluno> alunos = new ArrayList<>();

        System.out.println("Informe o Nome:");
        String nome = Input.nextLine();
        System.out.println("Informe o CPF:");
        String cpf = Input.nextLine();
        System.out.println("Informe a Cidade:");
        String cidade = Input.nextLine();
        System.out.println("Informe a Rua:");
        String rua = Input.nextLine();
        System.out.println("Informe o Numero:");
        String numero = Input.nextLine();

        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement enderecoStatement = connection.prepareStatement("INSERT INTO endereco (cidade, rua, numero) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS); 
             PreparedStatement alunoStatement = connection.prepareStatement("INSERT INTO aluno (nome, cpf, fk_idendereco) VALUES (?, ?, ?)")) {

            enderecoStatement.setString(1, cidade);
            enderecoStatement.setString(2, rua);
            enderecoStatement.setString(3, numero);
            enderecoStatement.executeUpdate();

            ResultSet generatedKeys = enderecoStatement.getGeneratedKeys();
            int idEndereco = generatedKeys.next() ? generatedKeys.getInt(1) : 0;

            alunoStatement.setString(1, nome);
            alunoStatement.setString(2, cpf);
            alunoStatement.setInt(3, idEndereco);
            alunoStatement.executeUpdate();

            try (PreparedStatement selectStatement = connection.prepareStatement("SELECT aluno.*, endereco.cidade, endereco.rua, endereco.numero FROM aluno LEFT JOIN endereco ON endereco.idendereco = aluno.fk_idendereco"); 
                 ResultSet result = selectStatement.executeQuery()) {

                while (result.next()) {
                    int idAluno = result.getInt("idaluno");
                    String nomeAluno = result.getString("nome");
                    String cpfAluno = result.getString("cpf");
                    int idEnderecoAluno = result.getInt("fk_idendereco");
                    String cidadeAluno = result.getString("cidade");
                    String ruaAluno = result.getString("rua");
                    String numeroAluno = result.getString("numero");

                    Endereco endereco = new Endereco(idEndereco, cidadeAluno, ruaAluno, numeroAluno);

                    Aluno al = new Aluno(idAluno, nomeAluno, cpfAluno, endereco);
                    alunos.add(al);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public static void RemoverAluno(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM aluno WHERE idaluno = ?")) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public static void EditarAluno(String cpf) {
        RemoverAlunoPorCPF(cpf);
        InserirDados();
    }

    private static void RemoverAlunoPorCPF(String cpf) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM aluno WHERE cpf = ?")) {

            statement.setString(1, cpf);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public static void ListaAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement st = connection.createStatement();
             ResultSet result = st.executeQuery("SELECT aluno.*, endereco.cidade, endereco.rua, endereco.numero FROM aluno LEFT JOIN endereco ON endereco.idendereco = aluno.fk_idendereco")) {

            while (result.next()) {
                int idAluno = result.getInt("idaluno");
                String nomeAluno = result.getString("nome");
                String cpfAluno = result.getString("cpf");
                int idEnderecoAluno = result.getInt("fk_idendereco");
                String cidadeAluno = result.getString("cidade");
                String ruaAluno = result.getString("rua");
                String numeroAluno = result.getString("numero");

                Endereco endereco = new Endereco(idEnderecoAluno, cidadeAluno, ruaAluno, numeroAluno);

                Aluno al = new Aluno(idAluno, nomeAluno, cpfAluno, endereco);
                alunos.add(al);
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}