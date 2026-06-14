package com.gynlog.view.relatorios;

import com.gynlog.config.DependencyInjector;
import com.gynlog.controller.MovimentacaoController;
import com.gynlog.controller.TipoDespesaController;
import com.gynlog.controller.VeiculoController;
import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.report.controller.RelatorioController;
import com.gynlog.view.TelaPrincipal;

import javax.swing.*;
import java.util.List;

public class TelaRelatorios extends javax.swing.JFrame {

    private VeiculoController veiculoController = new VeiculoController();
    private TipoDespesaController tipoDespesaController = new TipoDespesaController();
    private MovimentacaoController movimentacaoController = DependencyInjector.getMovimentacaoController();
    private RelatorioController relatorioController = DependencyInjector.getRelatorioController();
    private String caminhoSalvar;

    public TelaRelatorios() {

        try {

            java.awt.Image icone = javax.imageio.ImageIO.read(getClass().getResource("/icons/logo-40x40.png"));

            this.setIconImage(icone);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

        initComponents();
        setLocationRelativeTo(null);
        adicionarPlacas();
        adicionarTiposDeDespesas();

    }

    public final void adicionarPlacas() {

        try {

            List<Veiculo> listaVeiculos = veiculoController.listar();

            for (Veiculo veiculo : listaVeiculos)
                jComboBoxPlaca.addItem(veiculo.getPlaca());

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public final void adicionarTiposDeDespesas() {

        try {

            List<TipoDespesa> listaTipoDespesas = tipoDespesaController.listar();

            for (TipoDespesa tipoDeDespesa : listaTipoDespesas)
                jComboBoxDespesa.addItem(tipoDeDespesa.getDescricao());

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxPlaca = new javax.swing.JComboBox<>();
        jComboBoxDespesa = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButtonRelatorioInativos = new javax.swing.JButton();
        jFormattedTextFieldDataInicial = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataFinal = new javax.swing.JFormattedTextField();
        jButtonVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Relatórios");
        setPreferredSize(new java.awt.Dimension(680, 480));

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jComboBoxPlaca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"TODOS"}));

        jComboBoxDespesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"TODOS"}));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("GERAR RELATÓRIO");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButtonRelatorioInativos.setBackground(new java.awt.Color(102, 102, 102));
        jButtonRelatorioInativos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRelatorioInativos.setText("GERAR RELATÓRIO VEICULOS INATIVOS ");
        jButtonRelatorioInativos.addActionListener(this::jButtonRelatorioInativosActionPerformed);

        try {
            jFormattedTextFieldDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            jFormattedTextFieldDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(this::jButtonVoltarActionPerformed);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo 300 x 300.png"))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATA INICIAL");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DATA FINAL");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TIPO DE DESPESA");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PLACA DO VEICULO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(198, 198, 198)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(jFormattedTextFieldDataInicial)
                                                                                        .addComponent(jFormattedTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jComboBoxPlaca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jComboBoxDespesa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(0, 0, Short.MAX_VALUE))))
                                                                .addComponent(jButtonRelatorioInativos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(69, 69, 69)
                                                                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(jLabel1)))
                                .addContainerGap(196, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRelatorioInativos)
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jFormattedTextFieldDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jFormattedTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonVoltar)
                                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        this.dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        try {

            String dataInicial = jFormattedTextFieldDataInicial.getText();
            String dataFinal = jFormattedTextFieldDataFinal.getText();
            String placa = String.valueOf(jComboBoxPlaca.getSelectedItem());
            String despesa = String.valueOf(jComboBoxDespesa.getSelectedItem());

            //String caminhoSalvar = ... // Augusto

            boolean dataInicialPreenchida = campoPreenchido(dataInicial);
            boolean dataFinalPreenchida = campoPreenchido(dataFinal);

            if (dataInicialPreenchida && dataFinalPreenchida) {

                relatorioController.gerarRelatorioPorFiltro(caminhoSalvar, dataInicial, dataFinal, placa, despesa);

            } else if (!dataInicialPreenchida && !dataFinalPreenchida) {

                relatorioController.gerarRelatorioPorFiltro(caminhoSalvar, placa, despesa);

            } else {
                throw new IllegalArgumentException("Preencha as duas datas ou deixe ambas em branco!");
            }

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    private boolean campoPreenchido(String texto) {
        return texto != null && !texto.trim().isEmpty() && !texto.contains("_");
    }

    private void jButtonRelatorioInativosActionPerformed(java.awt.event.ActionEvent evt) {

        try {

            //String caminhoSalvar = ... // Augusto

            relatorioController.gerarVeiculosInativos(caminhoSalvar);

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonRelatorioInativos;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxDespesa;
    private javax.swing.JComboBox<String> jComboBoxPlaca;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataFinal;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
}
