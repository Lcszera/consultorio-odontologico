package pacote.odonto_package;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

public class TelaVisualizarPacientes extends JFrame {

    public TelaVisualizarPacientes() {
        setTitle("Pacientes Registrados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        String[] colunas = {"Nome", "CPF", "Telefone", "Data de Nascimento"};

        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);

        JTable tabelaPacientes = new JTable(tableModel);
        tabelaPacientes.setEnabled(false);

        List<Paciente> pacientes = RepositorioPacientes.getPacientes();

        for (Paciente paciente : pacientes) {
            Object[] rowData = {
                    paciente.getNome(),
                    paciente.getCpf(),
                    paciente.getTelefone(),
                    paciente.getDataNascimento()
            };
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(tabelaPacientes);

        JPanel painelBotao = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Menu");
        painelBotao.add(btnVoltar);

        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });

        add(scrollPane, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);
    }
}