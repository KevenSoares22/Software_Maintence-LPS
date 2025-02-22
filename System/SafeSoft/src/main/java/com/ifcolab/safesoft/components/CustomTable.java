package com.ifcolab.safesoft.components;

import com.ifcolab.safesoft.utils.Colors;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class CustomTable extends JTable implements Serializable {
    
    private static final int BORDER_RADIUS = 8;
    
    public CustomTable() {
        super();
        setupTable();
    }
    
    public CustomTable(TableModel model) {
        super(model);
        setupTable();
    }
    
    private void setupTable() {
        
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        getTableHeader().setFont(new Font("Segoe UI", Font.HANGING_BASELINE, 14));
        
        setBackground(Colors.WHITE);
        setForeground(Colors.GRAY_500);
        setGridColor(Colors.GRAY_100);
        
        getTableHeader().setBackground(Colors.GRAY_100);
        getTableHeader().setForeground(Colors.GRAY_500);
        getTableHeader().setBorder(null);
        
        setRowHeight(50);
        setIntercellSpacing(new Dimension(0, 0));
        setShowVerticalLines(false);
        setSelectionBackground(new Color(0, 122, 255, 20));
        setSelectionForeground(Colors.GRAY_500);
        
        setBorder(BorderFactory.createEmptyBorder());
        
        // Customizar o renderer das células
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
                    label.setHorizontalAlignment(SwingConstants.LEFT);
                }
                
                return c;
            }
        });
        
        // Customizar o header renderer
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                    label.setHorizontalAlignment(SwingConstants.LEFT);
                }
                
                return c;
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS));
        
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        // Não fazer nada aqui para remover a borda
    }
}