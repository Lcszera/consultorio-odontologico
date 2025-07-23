package pacote.odonto_package;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Font;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("Menu Principal - Consultório Odontológico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new GridLayout(5, 1, 15, 15));
        painelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));

        JButton btnRegistrarPaciente = new JButton("Registrar Paciente");
        JButton btnVisualizarPacientes = new JButton("Visualizar Pacientes");
        JButton btnAgendarConsulta = new JButton("Agendar Consulta");
        JButton btnFinanceiro = new JButton("Financeiro");
        JButton btnSair = new JButton("Sair do Sistema");

        Font fonteBotoes = new Font("Arial", Font.BOLD, 18);
        btnRegistrarPaciente.setFont(fonteBotoes);
        btnVisualizarPacientes.setFont(fonteBotoes);
        btnAgendarConsulta.setFont(fonteBotoes);
        btnFinanceiro.setFont(fonteBotoes);
        btnSair.setFont(fonteBotoes);

        btnRegistrarPaciente.addActionListener(e -> {
            new TelaRegistrarPaciente().setVisible(true);
            this.dispose();
        });

        btnVisualizarPacientes.addActionListener(e -> {
            new TelaVisualizarPacientes().setVisible(true);
            this.dispose();
        });

        btnSair.addActionListener(e -> {
            System.exit(0);
        });

        painelPrincipal.add(btnRegistrarPaciente);
        painelPrincipal.add(btnVisualizarPacientes);
        painelPrincipal.add(btnAgendarConsulta);
        painelPrincipal.add(btnFinanceiro);
        painelPrincipal.add(btnSair);

        add(painelPrincipal);
    }
}