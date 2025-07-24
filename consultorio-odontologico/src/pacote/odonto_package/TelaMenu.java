package pacote.odonto_package;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
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
        painelDeBotoes.setOpaque(false);

        JButton btnRegistrarPaciente = new JButtonDegrade("Registrar Paciente");
        JButton btnVisualizarPacientes = new JButtonDegrade("Visualizar Pacientes");
        JButton btnAgendarConsulta = new JButtonDegrade("Agendar Consulta");
        JButton btnVerConsultas = new JButtonDegrade("Consultas Agendadas");
        JButton btnFinanceiro = new JButtonDegrade("Financeiro");
        JButton btnSair = new JButtonDegrade("Sair do Sistema");

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

        btnRegistrarPaciente.addActionListener(e -> { new TelaRegistrarPaciente().setVisible(true); this.dispose(); });
        btnVisualizarPacientes.addActionListener(e -> { new TelaVisualizarPacientes().setVisible(true); this.dispose(); });
        btnVerConsultas.addActionListener(e -> { new TelaConsultasAgendadas().setVisible(true); this.dispose(); });
        btnFinanceiro.addActionListener(e -> {new TelaFinanceiro().setVisible(true); this.dispose(); });
        btnAgendarConsulta.addActionListener(e -> {new TelaAgendarConsulta().setVisible(true); this.dispose(); });
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