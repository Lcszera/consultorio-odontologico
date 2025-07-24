package pacote.odonto_package;

import pacote.odonto_package.model.Paciente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaRegistrarPaciente extends JFrame {

    private JTextField campoNomeCompleto;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextField campoEndereco;
    private JTextField campoDataNascimento;

    public TelaRegistrarPaciente() {
        setTitle("Registrar Novo Paciente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        painelFormulario.setBorder(new EmptyBorder(25, 25, 25, 25));

        Font fonteLabels = new Font("Arial", Font.PLAIN, 14);

        JLabel labelNome = new JLabel("Nome Completo:");
        labelNome.setFont(fonteLabels);
        campoNomeCompleto = new JTextField();
        painelFormulario.add(labelNome);
        painelFormulario.add(campoNomeCompleto);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setFont(fonteLabels);
        campoCpf = new JTextField();
        painelFormulario.add(labelCpf);
        painelFormulario.add(campoCpf);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(fonteLabels);
        campoTelefone = new JTextField();
        painelFormulario.add(labelTelefone);
        painelFormulario.add(campoTelefone);

        JLabel labelEndereco = new JLabel("Endereço:");
        labelEndereco.setFont(fonteLabels);
        campoEndereco = new JTextField();
        painelFormulario.add(labelEndereco);
        painelFormulario.add(campoEndereco);

        JLabel labelDataNascimento = new JLabel("Data de Nascimento (dd/mm/aaaa):");
        labelDataNascimento.setFont(fonteLabels);
        campoDataNascimento = new JTextField();
        painelFormulario.add(labelDataNascimento);
        painelFormulario.add(campoDataNascimento);

        JPanel painelContainer = new JPanel(new GridBagLayout());
        painelContainer.add(painelFormulario);
        JPanel painelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar Paciente");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnVoltar);
        btnSalvar.addActionListener(this::salvarPaciente);
        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });
        add(painelContainer, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void salvarPaciente(ActionEvent e) {

        String nomeCompleto = campoNomeCompleto.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String endereco = campoEndereco.getText();
        String dataNasc = campoDataNascimento.getText();

        if (nomeCompleto.trim().isEmpty() || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome Completo e CPF são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente novoPaciente = new Paciente(0, nomeCompleto, "", cpf, telefone, endereco, dataNasc);

        RepositorioPacientes repositorio = new RepositorioPacientes();
        repositorio.adicionarPaciente(novoPaciente);

        JOptionPane.showMessageDialog(this, "Paciente '" + nomeCompleto + "' salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private void limparCampos() {
        campoNomeCompleto.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
        campoEndereco.setText("");
        campoDataNascimento.setText("");
    }
}