package pacote.odonto_package;

import pacote.odonto_package.model.Paciente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaAgendarConsulta extends JFrame {

    private JComboBox<Paciente> comboPacientes;
    private JTextField campoData;
    private JTextField campoHora;
    private JTextArea areaProcedimento;

    private RepositorioPacientes repositorioPacientes;
    private RepositorioConsultas repositorioConsultas;

    public TelaAgendarConsulta() {
        setTitle("Agendar Nova Consulta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 230, 235));

        repositorioPacientes = new RepositorioPacientes();
        repositorioConsultas = new RepositorioConsultas();

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setBackground(Color.WHITE);
        painelConteudo.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel painelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        painelFormulario.setBackground(Color.WHITE);
        Font fonteLabels = new Font("Arial", Font.PLAIN, 14);

        painelFormulario.add(new JLabel("Selecione o Paciente:")).setFont(fonteLabels);
        comboPacientes = new JComboBox<>();
        painelFormulario.add(comboPacientes);

        painelFormulario.add(new JLabel("Data (dd/mm/aaaa):")).setFont(fonteLabels);
        campoData = new JTextField();
        painelFormulario.add(campoData);

        painelFormulario.add(new JLabel("Hora (hh:mm):")).setFont(fonteLabels);
        campoHora = new JTextField();
        painelFormulario.add(campoHora);

        painelFormulario.add(new JLabel("Procedimento:")).setFont(fonteLabels);
        areaProcedimento = new JTextArea(3, 20);
        painelFormulario.add(new JScrollPane(areaProcedimento));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.WHITE);
        JButton btnSalvar = new JButton("Salvar Agendamento");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnVoltar);

        painelConteudo.add(painelFormulario, BorderLayout.CENTER);
        painelConteudo.add(painelBotoes, BorderLayout.SOUTH);
        add(painelConteudo, new GridBagConstraints());

        carregarPacientesNoComboBox();
        btnSalvar.addActionListener(this::salvarAgendamento);
        btnVoltar.addActionListener(e -> { new TelaMenu().setVisible(true); this.dispose(); });
    }

    private void carregarPacientesNoComboBox() {
        List<Paciente> pacientes = repositorioPacientes.listarTodosPacientes();

        for (Paciente p : pacientes) {
            comboPacientes.addItem(p);
        }
    }

    private void salvarAgendamento(ActionEvent e) {
        Paciente pacienteSelecionado = (Paciente) comboPacientes.getSelectedItem();
        String data = campoData.getText();
        String hora = campoHora.getText();
        String procedimento = areaProcedimento.getText();

        if (pacienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um paciente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (data.trim().isEmpty() || hora.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data e Hora são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int pacienteId = pacienteSelecionado.getId();

        repositorioConsultas.agendarConsulta(pacienteId, data, hora, procedimento);

        JOptionPane.showMessageDialog(this, "Consulta agendada com sucesso para " + pacienteSelecionado.getNome() + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        campoData.setText("");
        campoHora.setText("");
        areaProcedimento.setText("");
    }
}