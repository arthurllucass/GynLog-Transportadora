package com.gynlog.view.movimentacoes;

import com.gynlog.controller.VeiculoController;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.model.enums.StatusVeiculo;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaBuscarVeiculoMovimentacoes extends javax.swing.JFrame {

    private String idSelecionado = null;
    private JTextField campoDestino;


    public TelaBuscarVeiculoMovimentacoes() {

        initComponents();

        setLocationRelativeTo(null);

        carregarTabelaVeiculosAtivos();

        configurarCliqueNaTabela();

        jTableCadastroVeiculos.setDefaultEditor(Object.class, null);
    }

    public TelaBuscarVeiculoMovimentacoes(JTextField campoDestino) {

        initComponents();

        this.campoDestino = campoDestino;

        setLocationRelativeTo(null);

        carregarTabelaVeiculosAtivos();

        configurarCliqueNaTabela();

        jTableCadastroVeiculos.setDefaultEditor(Object.class, null);
    }

    public void carregarTabelaVeiculosAtivos() {
        try {
            VeiculoController veiculoController = new VeiculoController();

            List<Veiculo> listaVeiculos = veiculoController.buscarPorStatus(StatusVeiculo.ATIVO);

            DefaultTableModel model = (DefaultTableModel) jTableCadastroVeiculos.getModel();
            model.setRowCount(0);

            for (Veiculo veiculos : listaVeiculos) {
                model.addRow(new Object[]{
                        veiculos.getIdVeiculo(),
                        veiculos.getPlaca(),
                        veiculos.getMarca(),
                        veiculos.getModelo(),
                        veiculos.getAnoDeFrabicacao()
                });
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public void configurarCliqueNaTabela() {

        jTableCadastroVeiculos.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Duplo clique
                    int linha = jTableCadastroVeiculos.getSelectedRow();
                    if (linha >= 0) {
                        String id = jTableCadastroVeiculos.getValueAt(linha, 0).toString();
                        campoDestino.setText(id);
                        dispose();
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCadastroVeiculos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de veículos");
        setResizable(false);

        jTableCadastroVeiculos.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String [] {
                        "ID", "Placa", "Marca", "Modelo", "Ano"
                }
        ));
        jScrollPane1.setViewportView(jTableCadastroVeiculos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCadastroVeiculos;
}