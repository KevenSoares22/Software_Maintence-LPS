package com.ifcolab.safesoft.components;

import com.ifcolab.safesoft.utils.Colors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class SecondaryCustomButton extends JButton implements Serializable {

    private static final int BORDER_RADIUS = 20;  // Raio da borda
    private boolean isHovered = false;

    public SecondaryCustomButton() {
        this("Button");
        setupButton();
    }

    public SecondaryCustomButton(String text) {
        super(text);
        setupButton();
    }

    private void setupButton() {
        // Configurar fonte
        setFont(new Font("Segoe UI", Font.PLAIN, 14));

        setBackground(new Color(0, 0, 0, 0)); // Transparente
        setForeground(Colors.GRAY_500); // Texto em cinza escuro

        // Aumentando o padding para tornar o botão mais largo
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Aumentando as margens esquerda e direita

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenhar background com bordas arredondadas
        if (isHovered) {
            g2.setColor(new Color(0, 0, 0, 20)); // Cinza bem suave para hover
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS));
        }

        // Desenhar borda mais destacada
        g2.setColor(Colors.GRAY_300); // Cor da borda
        g2.setStroke(new BasicStroke(2)); // Espessura da borda
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, BORDER_RADIUS, BORDER_RADIUS));

        g2.dispose();

        super.paintComponent(g);
    }
}
