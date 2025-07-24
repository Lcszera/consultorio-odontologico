package pacote.odonto_package;

import pacote.odonto_package.model.Financeiro;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class TelaFinanceiro extends JFrame {

    private JTable tabelaFinanceiro;
    private DefaultTableModel tableModel;
    private RepositorioFinanceiro repositorio;
    private JComboBox<String> comboMes;
    private JComboBox<Integer> comboAno;
    private JLabel labelTotalEntradas, labelTotalSaidas, labelSaldoMes;

    public TelaFinanceiro() {
        setTitle("Controle Financeiro");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 230, 235));

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setBackground(Color.WHITE);
        painelConteudo.setBorder(new EmptyBorder(15, 15, 15, 15));

        repositorio = new RepositorioFinanceiro();

        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltros.setBackground(Color.WHITE);
        LocalDate dataAtual = LocalDate.now();
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        comboMes = new JComboBox<>(meses);
        comboMes.setSelectedIndex(dataAtual.getMonthValue() - 1);
        Integer[] anos = {dataAtual.getYear() - 2, dataAtual.getYear() - 1, dataAtual.getYear(), dataAtual.getYear() + 1};
        comboAno = new JComboBox<>(anos);
        comboAno.setSelectedItem(dataAtual.getYear());
        JButton btnFiltrar = new JButton("Filtrar");
        painelFiltros.add(new JLabel("Mês:"));
        painelFiltros.add(comboMes);
        painelFiltros.add(new JLabel("Ano:"));
        painelFiltros.add(comboAno);
        painelFiltros.add(btnFiltrar);

        String[] colunas = {"ID", "Data", "Descrição", "Tipo", "Valor (R$)"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaFinanceiro = new JTable(tableModel);

        JPanel painelResumo = new JPanel(new GridLayout(1, 3, 10, 0));
        painelResumo.setBackground(Color.WHITE);
        painelResumo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Font fonteResumo = new Font("Arial", Font.BOLD, 16);
        labelTotalEntradas = new JLabel("Entradas: R$ 0,00", SwingConstants.CENTER);
        labelTotalEntradas.setFont(fonteResumo);
        labelTotalEntradas.setForeground(new Color(0, 150, 0));
        labelTotalSaidas = new JLabel("Saídas: R$ 0,00", SwingConstants.CENTER);
        labelTotalSaidas.setFont(fonteResumo);
        labelTotalSaidas.setForeground(Color.RED);
        labelSaldoMes = new JLabel("Saldo do Mês: R$ 0,00", SwingConstants.CENTER);
        labelSaldoMes.setFont(fonteResumo);
        painelResumo.add(labelTotalEntradas);
        painelResumo.add(labelTotalSaidas);
        painelResumo.add(labelSaldoMes);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.WHITE);
        JButton btnAddEntrada = new JButton("Adicionar Entrada");
        JButton btnAddSaida = new JButton("Adicionar Saída");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        painelBotoes.add(btnAddEntrada);
        painelBotoes.add(btnAddSaida);
        painelBotoes.add(btnVoltar);

        JPanel painelSul = new JPanel(new BorderLayout());
        painelSul.setBackground(Color.WHITE);
        painelSul.add(painelResumo, BorderLayout.CENTER);
        painelSul.add(painelBotoes, BorderLayout.SOUTH);

        painelConteudo.add(painelFiltros, BorderLayout.NORTH);
        painelConteudo.add(new JScrollPane(tabelaFinanceiro), BorderLayout.CENTER);
        painelConteudo.add(painelSul, BorderLayout.SOUTH);
        add(painelConteudo, new GridBagConstraints());

        btnFiltrar.addActionListener(e -> atualizarDados());
        btnAddEntrada.addActionListener(e -> adicionarNovaTransacao("Entrada"));
        btnAddSaida.addActionListener(e -> adicionarNovaTransacao("Saída"));
        btnVoltar.addActionListener(e -> { new TelaMenu().setVisible(true); this.dispose(); });

        atualizarDados();
    }

    private void atualizarDados() {
        int ano = (int) comboAno.getSelectedItem();
        int mes = comboMes.getSelectedIndex() + 1;

        tableModel.setRowCount(0);
        List<Financeiro> transacoes = repositorio.listarTransacoesPorMes(ano, mes);
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        for (Financeiro t : transacoes) {
            tableModel.addRow(new Object[]{
                    t.getId(),
                    t.getData(),
                    t.getDescricao(),
                    t.getTipo(),
                    formatadorMoeda.format(t.getValor())
            });
        }

        double totalEntradas = repositorio.calcularTotalPorMes("Entrada", ano, mes);
        double totalSaidas = repositorio.calcularTotalPorMes("Saída", ano, mes);
        double saldo = totalEntradas - totalSaidas;

        labelTotalEntradas.setText("Entradas: " + formatadorMoeda.format(totalEntradas));
        labelTotalSaidas.setText("Saídas: " + formatadorMoeda.format(totalSaidas));
        labelSaldoMes.setText("Saldo do Mês: " + formatadorMoeda.format(saldo));

        if (saldo < 0) {
            labelSaldoMes.setForeground(Color.RED);
        } else {
            labelSaldoMes.setForeground(new Color(0, 100, 0));
        }
    }

    private void adicionarNovaTransacao(String tipo) {
        JPanel painelDialogo = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField campoDescricao = new JTextField();
        JTextField campoValor = new JTextField();
        painelDialogo.add(new JLabel("Descrição:"));
        painelDialogo.add(campoDescricao);
        painelDialogo.add(new JLabel("Valor (R$):"));
        painelDialogo.add(campoValor);

        int resultado = JOptionPane.showConfirmDialog(
                this,
                painelDialogo,
                "Adicionar " + tipo,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            String descricao = campoDescricao.getText();
            String valorStr = campoValor.getText().replace(",", ".");

            if (descricao.trim().isEmpty() || valorStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descrição e Valor são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double valor = Double.parseDouble(valorStr);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "O valor deve ser positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalDate dataHoje = LocalDate.now();
                Financeiro novaTransacao = new Financeiro(0, descricao, valor, tipo, dataHoje.toString());
                repositorio.adicionarTransacao(novaTransacao);

                JOptionPane.showMessageDialog(this, tipo + " adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarDados();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido. Por favor, insira um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}