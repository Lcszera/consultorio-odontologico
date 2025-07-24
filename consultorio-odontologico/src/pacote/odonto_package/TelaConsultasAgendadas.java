package pacote.odonto_package;

import pacote.odonto_package.model.Consulta;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 230, 235));

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setBackground(Color.WHITE);
        painelConteudo.setBorder(new EmptyBorder(25, 25, 25, 25));

        String[] colunas = {"ID", "Paciente", "Data", "Hora", "Procedimento", "Status"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaConsultas = new JTable(tableModel);

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(Color.WHITE);
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> { new TelaMenu().setVisible(true); this.dispose(); });
        painelBotao.add(btnVoltar);

        painelConteudo.add(new JScrollPane(tabelaConsultas), BorderLayout.CENTER);
        painelConteudo.add(painelBotao, BorderLayout.SOUTH);
        add(painelConteudo, new GridBagConstraints());

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