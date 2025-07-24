package pacote.odonto_package;

import pacote.odonto_package.model.Paciente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.util.List;

public class TelaVisualizarPacientes extends JFrame {

    private JTable tabelaPacientes;
    private DefaultTableModel tableModel;

    public TelaVisualizarPacientes() {
        setTitle("Visualizar Pacientes Cadastrados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 230, 235));

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setBackground(Color.WHITE);
        painelConteudo.setBorder(new EmptyBorder(25, 25, 25, 25));

        String[] colunas = {"ID", "Nome Completo", "CPF", "Telefone", "Endereço"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaPacientes = new JTable(tableModel);

        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(Color.WHITE);
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> { new TelaMenu().setVisible(true); this.dispose(); });
        painelBotao.add(btnVoltar);

        painelConteudo.add(new JScrollPane(tabelaPacientes), BorderLayout.CENTER);
        painelConteudo.add(painelBotao, BorderLayout.SOUTH);
        add(painelConteudo, new GridBagConstraints());

        carregarPacientes();
    }

    private void carregarPacientes() {
        RepositorioPacientes repositorio = new RepositorioPacientes();
        List<Paciente> pacientes = repositorio.listarTodosPacientes();
        tableModel.setRowCount(0);
        for (Paciente paciente : pacientes) {
            Object[] rowData = {
                    paciente.getId(),
                    (paciente.getNome() + " " + paciente.getSobrenome()).trim(),
                    paciente.getCpf(),
                    paciente.getTelefone(),
                    paciente.getEndereco()
            };
            tableModel.addRow(rowData);
        }
    }
}