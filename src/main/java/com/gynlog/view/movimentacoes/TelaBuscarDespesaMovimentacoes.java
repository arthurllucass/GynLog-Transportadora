package com.gynlog.view.movimentacoes;

import com.gynlog.controller.TipoDespesaController;
import com.gynlog.model.entity.TipoDespesa;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaBuscarDespesaMovimentacoes extends javax.swing.JFrame {

    private String idSelecionado = null;

    public TelaBuscarDespesaMovimentacoes() {
        initComponents();
        setLocationRelativeTo(null);
        carregarTabelaDespesas();
        jTableTelaDespesas.setDefaultEditor(Object.class, null);
    }

    public void carregarTabelaDespesas() {
        try {
            TipoDespesaController tipoDespesaController = new TipoDespesaController();

            List<TipoDespesa> lista = tipoDespesaController.listar();

            DefaultTableModel model = (DefaultTableModel) jTableTelaDespesas.getModel();
            model.setRowCount(0);

            for (TipoDespesa tipoDespesa : lista) {
                model.addRow(new Object[]{tipoDespesa.getIdTipoDespesa(), tipoDespesa.getDescricao()});
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTelaDespesas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTableTelaDespesas.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{{null, null}, {null, null}, {null, null}, {null, null}}, new String[]{"ID", "Descrição"}));
        jScrollPane1.setViewportView(jTableTelaDespesas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));

        pack();
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTelaDespesas;
}