package pacote.odonto_package;

import pacote.odonto_package.model.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioConsultas {

    public List<Consulta> listarTodasConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT c.id, p.nome, p.sobrenome, c.data, c.hora, c.procedimento, c.status " +
                "FROM consultas c " +
                "JOIN pacientes p ON c.paciente_id = p.id " +
                "ORDER BY c.data, c.hora";

        try (Connection conn = DatabaseManager.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String data = rs.getString("data");
                String hora = rs.getString("hora");
                String procedimento = rs.getString("procedimento");
                String status = rs.getString("status");

                String nomeCompleto = (nome + " " + sobrenome).trim();

                consultas.add(new Consulta(id, nomeCompleto, data, hora, procedimento, status));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }
        return consultas;
    }

    public void agendarConsulta(int pacienteId, String data, String hora, String procedimento) {
        String sql = "INSERT INTO consultas(paciente_id, data, hora, procedimento, status) VALUES(?, ?, ?, ?, 'Agendada')";

        try (Connection conn = DatabaseManager.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pacienteId);
            pstmt.setString(2, data);
            pstmt.setString(3, hora);
            pstmt.setString(4, procedimento);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao agendar consulta: " + e.getMessage());
        }
    }

}

