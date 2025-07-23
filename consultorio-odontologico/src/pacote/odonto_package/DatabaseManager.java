package pacote.odonto_package;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String DATABASE_FILE_PATH = USER_HOME + File.separator + "Desktop" + File.separator + "consultorio.db";
    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_FILE_PATH;


    public static Connection conectar() {

        System.out.println("Conectando ao banco de dados em: " + DATABASE_URL);

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conn;
    }

    public static void criarTabelaPacientes() {
        String sql = "CREATE TABLE IF NOT EXISTS pacientes ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " nome TEXT NOT NULL,"
                + " cpf TEXT NOT NULL UNIQUE,"
                + " telefone TEXT,"
                + " data_nascimento TEXT"
                + ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela de pacientes: " + e.getMessage());
        }
    }
}