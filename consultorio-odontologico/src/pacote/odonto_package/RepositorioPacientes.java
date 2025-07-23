package pacote.odonto_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPacientes {

    public static void adicionarPaciente(Paciente paciente) {
        String sql = "INSERT INTO pacientes(nome, cpf, telefone, data_nascimento) VALUES(?,?,?,?)";

        try (Connection conn = DatabaseManager.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getCpf());
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getDataNascimento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            String mensagemErro = "Erro ao adicionar paciente: " + e.getMessage();
            System.out.println(mensagemErro);

            if (e.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
                mensagemErro = "Não foi possível registrar: O CPF informado já existe no sistema.";
            }

            javax.swing.JOptionPane.showMessageDialog(null,
                    mensagemErro,
                    "Erro no Banco de Dados",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<Paciente> getPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes ORDER BY nome";

        try (Connection conn = DatabaseManager.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("data_nascimento")
                );
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacientes: " + e.getMessage());
        }
        return pacientes;
    }
}