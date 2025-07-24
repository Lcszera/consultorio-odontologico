package pacote.odonto_package;

import pacote.odonto_package.model.Consulta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaConsultasAgendadas extends JFrame {

    private JTable tabelaConsultas;
    private DefaultTableModel tableModel;

    public TelaConsultasAgendadas() {
        setTitle("Consultas Agendadas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        String[] colunas = {"ID", "Paciente", "Data", "Hora", "Procedimento", "Status"};

        tableModel = new DefaultTableModel(colunas, 0);
        tabelaConsultas = new JTable(tableModel);

        add(new JScrollPane(tabelaConsultas), BorderLayout.CENTER);

        JPanel painelBotao = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });
        painelBotao.add(btnVoltar);
        add(painelBotao, BorderLayout.SOUTH);

        carregarConsultas();
    }

    private void carregarConsultas() {
        RepositorioConsultas repositorio = new RepositorioConsultas();
        List<Consulta> consultas = repositorio.listarTodasConsultas();

        tableModel.setRowCount(0);

        for (Consulta consulta : consultas) {
            Object[] rowData = {
                    consulta.getId(),
                    consulta.getNomePaciente(),
                    consulta.getData(),
                    consulta.getHora(),
                    consulta.getProcedimento(),
                    consulta.getStatus()
            };
            tableModel.addRow(rowData);
        }
    }
}