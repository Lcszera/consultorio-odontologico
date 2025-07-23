package pacote.odonto_package;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Principal extends JFrame {

    public Principal() {

        setUndecorated(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        PainelComImagem painelPrincipal = new PainelComImagem("logocons.jpg");
        this.setContentPane(painelPrincipal);

        iniciarTemporizadorDeSplash();
    }

    private void iniciarTemporizadorDeSplash() {
        java.awt.event.ActionListener acaoDoTimer = e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        };

        Timer temporizador = new Timer(3000, acaoDoTimer);
        temporizador.setRepeats(false);
        temporizador.start();
    }

    public static void main(String[] args) {

        DatabaseManager.criarTabelaPacientes();

        SwingUtilities.invokeLater(() -> {
            Principal telaInicial = new Principal();
            telaInicial.setVisible(true);
        });
    }
}