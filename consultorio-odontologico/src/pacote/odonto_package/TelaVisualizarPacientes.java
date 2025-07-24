package pacote.odonto_package;

import pacote.odonto_package.model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaVisualizarPacientes extends JFrame {

    private JTable tabelaPacientes;
    private DefaultTableModel tableModel;

    public TelaVisualizarPacientes() {
        setTitle("Visualizar Pacientes Cadastrados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        String[] colunas = {"ID", "Nome Completo", "CPF", "Telefone", "Endereço"};

        tableModel = new DefaultTableModel(colunas, 0);
        tabelaPacientes = new JTable(tableModel);

        add(new JScrollPane(tabelaPacientes), BorderLayout.CENTER);

        JPanel painelBotao = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });
        painelBotao.add(btnVoltar);

        add(painelBotao, BorderLayout.SOUTH);
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