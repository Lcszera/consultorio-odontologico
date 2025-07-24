package pacote.odonto_package;

import pacote.odonto_package.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPacientes {

    public void adicionarPaciente(Paciente paciente) {
        String sql = "INSERT INTO pacientes(nome, sobrenome, cpf, telefone, endereco, data_nascimento) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getSobrenome());
            pstmt.setString(3, paciente.getCpf());
            pstmt.setString(4, paciente.getTelefone());
            pstmt.setString(5, paciente.getEndereco());
            pstmt.setString(6, paciente.getDataNascimento());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar paciente: " + e.getMessage());
        }
    }

    public List<Paciente> listarTodosPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, nome, sobrenome, cpf, telefone, endereco, data_nascimento FROM pacientes";

        try (Connection conn = DatabaseManager.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pacientes.add(new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("data_nascimento")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return pacientes;
    }
}