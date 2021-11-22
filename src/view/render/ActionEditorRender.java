
package view.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import util.Resource;

public class ActionEditorRender extends AbstractCellEditor implements TableCellEditor {
    
    public void actionVisualizar(JTable table, int row, int col) {}
    
    public void actionEditar(JTable table, int row, int col) {}
    
    public void actionDeletar(JTable table, int row, int col) {}
    
    @Override
    public Object getCellEditorValue() {
        return null;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object o, boolean bln, int i, int j) {
        JPanel panel = (JPanel) getRenderer()
                .getTableCellRendererComponent(table, o, bln, false, i, j);
        
        JButton btnVisualizar = (JButton)panel.getComponent(0);
        JButton btnEditar = (JButton)panel.getComponent(1);
        JButton btnExcluir = (JButton)panel.getComponent(2);
        
        btnVisualizar.addActionListener((ActionEvent ae) -> {
            this.actionVisualizar(table, i, j);
        });
        
        btnEditar.addActionListener((ActionEvent ae) -> {
            this.actionEditar(table, i, j);
        });
        
        btnExcluir.addActionListener((ActionEvent ae) -> {
            this.actionDeletar(table, i, j);
        });
        
        this.stopCellEditing();
        
        return panel;
    }
    
    public TableCellRenderer getRenderer() {
        return (JTable table, Object o, boolean bln, boolean bln1, int i, int j) -> {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            var border = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY);
            var emptyBorder = BorderFactory.createEmptyBorder();
            
            JButton btnVisualizar = new JButton(
                    Resource.loadIcon("img/visualiza_36.png", 24, 24));
            btnVisualizar.setBackground(Color.decode("#DAE4FD"));
            btnVisualizar.setBorder(emptyBorder);
            btnVisualizar.setFocusPainted(false);
           
            JButton btnEditar = new JButton(
                    Resource.loadIcon("img/edit_36.png", 24, 24));
            btnEditar.setBackground(Color.decode("#DAE4FD"));
            btnEditar.setBorder(border);
            btnEditar.setFocusPainted(false);
            
            JButton btnDeletar = new JButton(
                    Resource.loadIcon("img/delete_36.png", 24, 24));
            btnDeletar.setBackground(Color.decode("#F5727B"));
            btnDeletar.setBorder(emptyBorder);
            btnDeletar.setFocusPainted(false);

            panel.add(btnVisualizar);
            panel.add(btnEditar);
            panel.add(btnDeletar);
            
            return panel;
        };
    }
}
