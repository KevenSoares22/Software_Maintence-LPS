package com.ifcolab.safesoft.components;

import com.ifcolab.safesoft.utils.Colors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class PrimaryCustomButton extends JButton implements Serializable {

    private static final int BORDER_RADIUS = 8; // Valor para bordas levemente arredondadas
    private boolean isHovered = false;

    public PrimaryCustomButton() {
        this("Button");
        setupButton();
    }

    public PrimaryCustomButton(String text) {
        super(text);
        setupButton();
    }

    private void setupButton() {
        // Configurar fonte
        setFont(new Font("Segoe UI", Font.BOLD, 16));  // Fonte mais forte e maior

        // Configurar cores (verde bem escuro)
        setBackground(new Color(0, 100, 0)); // Verde bem escuro
        setForeground(Color.WHITE);

        // Remover borda padrão e adicionar padding
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Configurar aparência
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        // Adicionar efeito hover
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

        // Desenhar background com bordas levemente arredondadas
        g2.setColor(isHovered ? new Color(0, 80, 0) : new Color(0, 100, 0)); // Efeito de hover com verde um pouco mais claro
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS));

        g2.dispose();

        super.paintComponent(g);
    }
}
