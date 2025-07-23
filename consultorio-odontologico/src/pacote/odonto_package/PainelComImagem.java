package pacote.odonto_package;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class PainelComImagem extends JPanel {

    private Image imagemDeFundo;

    public PainelComImagem(String nomeArquivo) {
        try {
            URL imageUrl = getClass().getResource("/assets/" + nomeArquivo);
            if (imageUrl != null) {
                this.imagemDeFundo = new ImageIcon(imageUrl).getImage();
            } else {
                System.err.println("Recurso não encontrado: /assets/" + nomeArquivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemDeFundo != null) {
            g.drawImage(imagemDeFundo, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}