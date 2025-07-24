package pacote.odonto_package;

import pacote.odonto_package.model.Financeiro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFinanceiro {

    public void adicionarTransacao(Financeiro transacao) {
        String sql = "INSERT INTO financeiro(descricao, valor, tipo, data) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transacao.getDescricao());
            pstmt.setDouble(2, transacao.getValor());
            pstmt.setString(3, transacao.getTipo());
            pstmt.setString(4, transacao.getData());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar transação: " + e.getMessage());
        }
    }

    public List<Financeiro> listarTransacoesPorMes(int ano, int mes) {
        List<Financeiro> transacoes = new ArrayList<>();
        String anoMes = String.format("%d-%02d", ano, mes);
        String sql = "SELECT * FROM financeiro WHERE strftime('%Y-%m', data) = ? ORDER BY data DESC";

        try (Connection conn = DatabaseManager.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, anoMes);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                transacoes.add(new Financeiro(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getString("data")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar transações por mês: " + e.getMessage());
        }
        return transacoes;
    }

    public double calcularTotalPorMes(String tipo, int ano, int mes) {
        double total = 0.0;
        String anoMes = String.format("%d-%02d", ano, mes);
        String sql = "SELECT SUM(valor) AS total FROM financeiro WHERE tipo = ? AND strftime('%Y-%m', data) = ?";

        try (Connection conn = DatabaseManager.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipo);
            pstmt.setString(2, anoMes);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular total por mês: " + e.getMessage());
        }
        return total;
    }
}