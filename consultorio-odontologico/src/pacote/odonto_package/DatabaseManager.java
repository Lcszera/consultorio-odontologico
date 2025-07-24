package pacote.odonto_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:consultorio.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static void criarTabelas() {
        String sqlPacientes = "CREATE TABLE IF NOT EXISTS pacientes ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " nome TEXT NOT NULL,"
                + " sobrenome TEXT,"
                + " cpf TEXT,"
                + " telefone TEXT,"
                + " endereco TEXT,"
                + " data_nascimento TEXT"
                + ");";

        String sqlConsultas = "CREATE TABLE IF NOT EXISTS consultas ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " paciente_id INTEGER NOT NULL,"
                + " data TEXT NOT NULL,"
                + " hora TEXT NOT NULL,"
                + " procedimento TEXT,"
                + " status TEXT DEFAULT 'Agendada',"
                + " FOREIGN KEY (paciente_id) REFERENCES pacientes (id)"
                + ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlPacientes);
            stmt.execute(sqlConsultas);

        } catch (SQLException e) {
            System.out.println("Erro ao criar as tabelas: " + e.getMessage());
        }
    }
}