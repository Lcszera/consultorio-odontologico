package pacote.odonto_package;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class TelaRegistrarPaciente extends JFrame {

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
        JTextField campoNome = new JTextField();
        painelFormulario.add(labelNome);
        painelFormulario.add(campoNome);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setFont(fonteLabels);
        JTextField campoCpf = new JTextField();
        painelFormulario.add(labelCpf);
        painelFormulario.add(campoCpf);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(fonteLabels);
        JTextField campoTelefone = new JTextField();
        painelFormulario.add(labelTelefone);
        painelFormulario.add(campoTelefone);

        JLabel labelDataNascimento = new JLabel("Data de Nascimento (dd/mm/aaaa):");
        labelDataNascimento.setFont(fonteLabels);
        JTextField campoDataNascimento = new JTextField();
        painelFormulario.add(labelDataNascimento);
        painelFormulario.add(campoDataNascimento);

        JPanel painelContainer = new JPanel(new GridBagLayout());
        painelContainer.add(painelFormulario);

        JPanel painelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar Paciente");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnVoltar);

        btnSalvar.addActionListener(e -> {
            String nome = campoNome.getText();
            JOptionPane.showMessageDialog(this,
                    "Paciente '" + nome + "' salvo com sucesso! (simulação)",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            campoNome.setText("");
            campoCpf.setText("");
            campoTelefone.setText("");
            campoDataNascimento.setText("");
        });

        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });

        add(painelContainer, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }
}