package com.gynlog.report.pdf;

import com.gynlog.model.entity.Veiculo;
import org.openpdf.text.*;
import org.openpdf.text.Font;
import org.openpdf.text.Rectangle;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.Image;

import java.awt.*;
import java.io.FileOutputStream;
import com.gynlog.model.entity.Movimentacao;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class RelatorioPDF {

    private final Font tituloFont;
    private final Font corpoFont;
    private Document documentoPDF;

    public RelatorioPDF() {

        tituloFont = FontFactory.getFont(
                FontFactory.HELVETICA_BOLD,
                16
        );

        corpoFont = FontFactory.getFont(
                FontFactory.HELVETICA,
                12
        );
    }

    public void abrirDocumento(String caminhoArquivo) throws Exception {

        documentoPDF = new Document();

        PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminhoArquivo));

        documentoPDF.open();
    }

    public void fecharDocumento() {

        if (documentoPDF != null) {
            documentoPDF.close();
        }
    }

    public void adicionarLogo() throws Exception {

        Image logo = Image.getInstance(
                getClass().getResource("/icons/logo-relatorio-pdf-300x300.png")
        );

        logo.scaleToFit(120, 120);
        logo.setAlignment(Image.ALIGN_CENTER);

        documentoPDF.add(logo);
    }

    public void adicionarTitulo(String titulo) throws Exception {

        Paragraph paragraph = new Paragraph(titulo, tituloFont);

        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        paragraph.setSpacingBefore(5f);
        paragraph.setSpacingAfter(15f);

        documentoPDF.add(paragraph);
    }

    public void adicionarTexto(String texto) throws Exception {

        documentoPDF.add(new Paragraph(texto, corpoFont));
    }

    public void adicionarEspaco() throws Exception {

        documentoPDF.add(Chunk.NEWLINE);
    }

    public void adicionarTabela(PdfPTable tabela) throws Exception {

        documentoPDF.add(tabela);
    }

    public PdfPTable criarTabela(int quantidadeColunas) {

        PdfPTable tabela = new PdfPTable(quantidadeColunas);

        tabela.setWidthPercentage(100);

        return tabela;
    }

    public PdfPCell criarHeader(String texto) {

        Font fonteNegrito =
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD,
                        12
                );

        PdfPCell header =
                new PdfPCell(
                        new Paragraph(
                                texto,
                                fonteNegrito
                        )
                );

        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);

        header.setPadding(5);

        return header;
    }

    public PdfPCell criarCelulaCentralizada(String texto) {

        PdfPCell celula = new PdfPCell(
                        new Paragraph(
                                texto,
                                corpoFont
                        )
                );

        celula.setHorizontalAlignment(Element.ALIGN_CENTER);

        return celula;
    }

    private void adicionarLinhaResumo(PdfPTable tabela, String titulo, double valor, NumberFormat formatoMoeda) {

        Font fonte = new Font(Font.HELVETICA, 12, Font.BOLD);

        PdfPCell vazio = new PdfPCell(new Phrase(""));
        vazio.setColspan(4);
        vazio.setBorder(Rectangle.NO_BORDER);
        tabela.addCell(vazio);

        PdfPCell celulaTitulo = new PdfPCell(new Phrase(titulo, fonte));
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_RIGHT);

        tabela.addCell(celulaTitulo);

        tabela.addCell(criarCelulaCentralizada(formatoMoeda.format((valor))));
    }

    public void gerarRelatorioPorFiltro(String caminhoSalvar, List<Movimentacao> listaFiltrada) {

        if (listaFiltrada == null || listaFiltrada.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum registro encontrado para os filtros informados.");
            return;
        }

        NumberFormat formatoMoeda =
                NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        PdfPTable tabela = new PdfPTable(6);

        tabela.setWidthPercentage(100);
        tabela.setWidths(new float[]{1, 2, 2, 3, 4, 3});

        // Cabeçalho
        tabela.addCell(criarHeader("ID"));
        tabela.addCell(criarHeader("Data"));
        tabela.addCell(criarHeader("Placa"));
        tabela.addCell(criarHeader("Tipo despesa"));
        tabela.addCell(criarHeader("Descrição"));
        tabela.addCell(criarHeader("Valor R$"));


        double somaTotal = adicionarMovimentacoesRecursivo(
                listaFiltrada,
                0,
                tabela,
                formatoMoeda
        );

        double[] estatisticas = calcularEstatisticas(listaFiltrada);

        PdfPCell espaco = new PdfPCell(new Phrase(""));
        espaco.setColspan(6);
        espaco.setBorder(Rectangle.NO_BORDER);
        espaco.setFixedHeight(15f);

        tabela.addCell(espaco);
        tabela.addCell(espaco);

        adicionarLinhaResumo(
                tabela,
                "Total:",
                somaTotal,
                formatoMoeda
        );

        adicionarLinhaResumo(
                tabela,
                "Mínimo:",
                estatisticas[0],
                formatoMoeda
        );

        adicionarLinhaResumo(
                tabela,
                "Média:",
                estatisticas[1],
                formatoMoeda
        );

        adicionarLinhaResumo(
                tabela,
                "Máximo:",
                estatisticas[2],
                formatoMoeda
        );

        try {
            abrirDocumento(caminhoSalvar);
            adicionarLogo();
            adicionarTitulo("Relatório Filtrado");
            adicionarTabela(tabela);
            fecharDocumento();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public void listarVeiculosInativos(String caminhoSalvar, List<Veiculo> listaVeiculos) {

        PdfPTable tabela = new PdfPTable(6);

        tabela.setWidthPercentage(100);
        tabela.setWidths(new float[]{1, 3, 3, 3, 2, 2});

        tabela.addCell(criarHeader("ID"));
        tabela.addCell(criarHeader("Marca"));
        tabela.addCell(criarHeader("Modelo"));
        tabela.addCell(criarHeader("Ano Fabricação"));
        tabela.addCell(criarHeader("Placa"));
        tabela.addCell(criarHeader("Status"));

        for (Veiculo veiculo : listaVeiculos) {
            tabela.addCell(criarCelulaCentralizada(String.valueOf(veiculo.getIdVeiculo())));
            tabela.addCell(criarCelulaCentralizada(veiculo.getMarca().name()));
            tabela.addCell(criarCelulaCentralizada(veiculo.getModelo()));
            tabela.addCell(criarCelulaCentralizada(String.valueOf(veiculo.getAnoDeFrabicacao())));
            tabela.addCell(criarCelulaCentralizada(veiculo.getPlaca()));
            tabela.addCell(criarCelulaCentralizada(veiculo.getStatusVeiculo().name()));

        }

        try {
            this.abrirDocumento(caminhoSalvar);
            this.adicionarLogo();
            this.adicionarTitulo("Veiculos Inativos");
            this.adicionarTabela(tabela);
            this.fecharDocumento();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    private double adicionarMovimentacoesRecursivo( List<Movimentacao> lista, int indice, PdfPTable tabela, NumberFormat formatoMoeda) {

        if (indice >= lista.size()) {
            return 0.0;
        }

        Movimentacao movimento = lista.get(indice);

        tabela.addCell(criarCelulaCentralizada(String.valueOf(movimento.getId())));
        tabela.addCell(criarCelulaCentralizada(movimento.getDataMovimentacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        tabela.addCell(criarCelulaCentralizada(movimento.getVeiculo().getPlaca()));
        tabela.addCell(criarCelulaCentralizada(String.valueOf(movimento.getTipoDespesa())));
        tabela.addCell(criarCelulaCentralizada(movimento.getDescricaoMovimentacao()));
        tabela.addCell(criarCelulaCentralizada(formatoMoeda.format(movimento.getValorMovimentacao())));

        return movimento.getValorMovimentacao() + adicionarMovimentacoesRecursivo(lista,indice + 1, tabela, formatoMoeda);
    }

    private double[] calcularEstatisticas(List<Movimentacao> lista) {

        if (lista == null || lista.isEmpty()) {
            return new double[]{0, 0, 0}; // mínimo, média, máximo
        }

        double minimo = lista.get(0).getValorMovimentacao();
        double maximo = lista.get(0).getValorMovimentacao();
        double soma = 0;

        for (Movimentacao mov : lista) {
            double valor = mov.getValorMovimentacao();

            if (valor < minimo) {
                minimo = valor;
            }

            if (valor > maximo) {
                maximo = valor;
            }

            soma += valor;
        }

        double media = soma / lista.size();

        return new double[]{minimo, media, maximo};
    }

}