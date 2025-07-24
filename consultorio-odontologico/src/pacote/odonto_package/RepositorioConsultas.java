package pacote.odonto_package;

import pacote.odonto_package.model.Consulta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}