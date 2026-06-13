package com.gynlog.view;

import com.gynlog.view.movimentacoes.TelaMovimentacao;
import com.gynlog.view.relatorios.TelaRelatorios;
import com.gynlog.view.tipodespesas.TelaTipoDeDespesas;
import com.gynlog.view.veiculo.TelaVeiculo;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.*;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {

        configurarTela();

        initComponents();

        setLocationRelativeTo(null);
    }

    private void configurarTela() {

        try {
            Image icone = ImageIO.read(
                    getClass().getResource("/icons/logo-40x40.png")
            );

            setIconImage(icone);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar ícone: " + erro.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButtonTelaVeiculos = new javax.swing.JButton();
        jButtonTelaTipoDeDespesa = new javax.swing.JButton();
        jButtonTelaMovimentacoes = new javax.swing.JButton();
        jButtonRelatorios = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);

        jDesktopPane1.setBackground(new java.awt.Color(0, 0, 51));

        jButtonTelaVeiculos.setBackground(new java.awt.Color(102, 102, 102));
        jButtonTelaVeiculos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTelaVeiculos.setText("Cadastro de Veículos");
        jButtonTelaVeiculos.addActionListener(this::jButtonTelaVeiculosActionPerformed);

        jButtonTelaTipoDeDespesa.setBackground(new java.awt.Color(102, 102, 102));
        jButtonTelaTipoDeDespesa.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTelaTipoDeDespesa.setText("Tipo de Despesa");
        jButtonTelaTipoDeDespesa.addActionListener(this::jButtonTelaTipoDeDespesaActionPerformed);

        jButtonTelaMovimentacoes.setBackground(new java.awt.Color(102, 102, 102));
        jButtonTelaMovimentacoes.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTelaMovimentacoes.setText("Movimentações");
        jButtonTelaMovimentacoes.addActionListener(this::jButtonTelaMovimentacoesActionPerformed);

        jButtonRelatorios.setBackground(new java.awt.Color(102, 102, 102));
        jButtonRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRelatorios.setText("Relatórios");
        jButtonRelatorios.addActionListener(this::jButtonRelatoriosActionPerformed);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo-300x300.png"))); // NOI18N

        jDesktopPane1.setLayer(jButtonTelaVeiculos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonTelaTipoDeDespesa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonTelaMovimentacoes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonRelatorios, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addGap(259, 259, 259)
                                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButtonTelaVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonTelaTipoDeDespesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonTelaMovimentacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addGap(185, 185, 185)
                                                .addComponent(jLabel1)))
                                .addContainerGap(195, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonTelaVeiculos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonTelaTipoDeDespesa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonTelaMovimentacoes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRelatorios)
                                .addContainerGap(164, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1)
        );

        pack();
    }

    private void jButtonRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {
        TelaRelatorios telarelatorios = new TelaRelatorios();
//        telarelatorios.setVisible(true);
        this.dispose();
    }

    private void jButtonTelaMovimentacoesActionPerformed(java.awt.event.ActionEvent evt) {
        TelaMovimentacao telaMovimentacao = new TelaMovimentacao();
        telaMovimentacao.setVisible(true);
        this.dispose();
    }

    private void jButtonTelaTipoDeDespesaActionPerformed(java.awt.event.ActionEvent evt) {
        TelaTipoDeDespesas telaTipoDespesas = new TelaTipoDeDespesas();
        telaTipoDespesas.setVisible(true);
        this.dispose();
    }

    private void jButtonTelaVeiculosActionPerformed(java.awt.event.ActionEvent evt) {
        TelaVeiculo telaVeiculo = new TelaVeiculo();
        telaVeiculo.setVisible(true);
        this.dispose();
    }

    private javax.swing.JButton jButtonRelatorios;
    private javax.swing.JButton jButtonTelaMovimentacoes;
    private javax.swing.JButton jButtonTelaTipoDeDespesa;
    private javax.swing.JButton jButtonTelaVeiculos;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
}