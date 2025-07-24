package pacote.odonto_package;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class JButtonDegrade extends JButton {

    private Color cor1 = new Color(255, 135, 175);
    private Color cor2 = Color.WHITE;

    public JButtonDegrade(String text) {
        super(text);
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradiente = new GradientPaint(
                0, 0, cor1,
                0, getHeight(), cor2
        );

        g2.setPaint(gradiente);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}