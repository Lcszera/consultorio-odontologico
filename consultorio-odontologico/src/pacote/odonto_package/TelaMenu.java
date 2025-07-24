package pacote.odonto_package;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridBagLayout;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("Menu Principal - Consultório Odontológico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Dimension tamanhoBotao = new Dimension(300, 50);
        Font fonteBotoes = new Font("Arial", Font.BOLD, 16);

        JPanel painelDeBotoes = new JPanel(new GridLayout(6, 1, 15, 15));

        JButton btnRegistrarPaciente = new JButton("Registrar Paciente");
        JButton btnVisualizarPacientes = new JButton("Visualizar Pacientes");
        JButton btnAgendarConsulta = new JButton("Agendar Consulta");
        JButton btnVerConsultas = new JButton("Consultas Agendadas");
        JButton btnFinanceiro = new JButton("Financeiro");
        JButton btnSair = new JButton("Sair do Sistema");

        btnRegistrarPaciente.setPreferredSize(tamanhoBotao);
        btnRegistrarPaciente.setFont(fonteBotoes);

        btnVisualizarPacientes.setPreferredSize(tamanhoBotao);
        btnVisualizarPacientes.setFont(fonteBotoes);

        btnAgendarConsulta.setPreferredSize(tamanhoBotao);
        btnAgendarConsulta.setFont(fonteBotoes);

        btnVerConsultas.setFont(fonteBotoes);
        btnVerConsultas.setPreferredSize(tamanhoBotao);

        btnFinanceiro.setPreferredSize(tamanhoBotao);
        btnFinanceiro.setFont(fonteBotoes);

        btnSair.setPreferredSize(tamanhoBotao);
        btnSair.setFont(fonteBotoes);

        btnRegistrarPaciente.addActionListener(e -> {
            new TelaRegistrarPaciente().setVisible(true);
            this.dispose();
        });

        btnVisualizarPacientes.addActionListener(e -> {
            new TelaVisualizarPacientes().setVisible(true);
            this.dispose();
        });

        btnVerConsultas.addActionListener(e -> {
            new TelaConsultasAgendadas().setVisible(true);
            this.dispose();
        });

        btnSair.addActionListener(e -> System.exit(0));

        painelDeBotoes.add(btnRegistrarPaciente);
        painelDeBotoes.add(btnVisualizarPacientes);
        painelDeBotoes.add(btnAgendarConsulta);
        painelDeBotoes.add(btnVerConsultas);
        painelDeBotoes.add(btnFinanceiro);
        painelDeBotoes.add(btnSair);

        JPanel painelContainer = new JPanel(new GridBagLayout());
        painelContainer.add(painelDeBotoes);

        add(painelContainer);
    }
}