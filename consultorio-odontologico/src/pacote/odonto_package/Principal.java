package pacote.odonto_package;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Principal extends JFrame {

    public Principal() {

        setTitle("Tela Principal do Consultório");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {

            String caminhoAbsoluto = "C:\\Users\\Lucas\\Desktop\\Projetos GHub\\consultorio-odontologico\\resources\\logocons.jpg";

            javax.swing.ImageIcon icone = new javax.swing.ImageIcon(caminhoAbsoluto);

            if (icone.getIconWidth() == -1) {
                throw new java.io.IOException("Não foi possível carregar a imagem do caminho especificado. Verifique o caminho e as barras \\\\.");
            }

            javax.swing.JLabel labelDaImagem = new javax.swing.JLabel(icone);
            javax.swing.JPanel painelPrincipal = new javax.swing.JPanel();
            painelPrincipal.add(labelDaImagem);
            this.add(painelPrincipal);

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro ao carregar a imagem por caminho absoluto:\n" + e.getMessage(),
                    "Erro Crítico",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Principal telaInicial = new Principal();
            telaInicial.setVisible(true);
        });
    }

}