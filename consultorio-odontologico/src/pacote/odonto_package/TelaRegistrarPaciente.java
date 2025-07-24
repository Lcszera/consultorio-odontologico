package pacote.odonto_package;

import pacote.odonto_package.model.Paciente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaRegistrarPaciente extends JFrame {

    private final JTextField campoNomeCompleto;
    private final JTextField campoCpf;
    private final JTextField campoTelefone;
    private final JTextField campoEndereco;
    private final JTextField campoDataNascimento;

    public TelaRegistrarPaciente() {
        setTitle("Registrar Novo Paciente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 230, 235));

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setBackground(Color.WHITE);
        painelConteudo.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel painelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        painelFormulario.setBackground(Color.WHITE);
        Font fonteLabels = new Font("Arial", Font.PLAIN, 14);

        painelFormulario.add(new JLabel("Nome Completo:")).setFont(fonteLabels);
        campoNomeCompleto = new JTextField();
        painelFormulario.add(campoNomeCompleto);

        painelFormulario.add(new JLabel("CPF:")).setFont(fonteLabels);
        campoCpf = new JTextField();
        painelFormulario.add(campoCpf);

        painelFormulario.add(new JLabel("Telefone:")).setFont(fonteLabels);
        campoTelefone = new JTextField();
        painelFormulario.add(campoTelefone);

        painelFormulario.add(new JLabel("Endereço:")).setFont(fonteLabels);
        campoEndereco = new JTextField();
        painelFormulario.add(campoEndereco);

        painelFormulario.add(new JLabel("Data de Nascimento (dd/mm/aaaa):")).setFont(fonteLabels);
        campoDataNascimento = new JTextField();
        painelFormulario.add(campoDataNascimento);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.WHITE);
        JButton btnSalvar = new JButton("Salvar Paciente");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnVoltar);

        btnSalvar.addActionListener(e -> salvarPaciente());
        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });

        painelConteudo.add(painelFormulario, BorderLayout.CENTER);
        painelConteudo.add(painelBotoes, BorderLayout.SOUTH);
        add(painelConteudo, new GridBagConstraints());
    }

    private void salvarPaciente() {
        String nomeCompleto = campoNomeCompleto.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String endereco = campoEndereco.getText();
        String dataNasc = campoDataNascimento.getText();

        if (nomeCompleto.trim().isEmpty() || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos Nome Completo e CPF são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
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