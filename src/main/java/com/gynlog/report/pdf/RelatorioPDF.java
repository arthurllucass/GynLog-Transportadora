package com.gynlog.report.pdf;

import com.gynlog.model.entity.Veiculo;
import org.openpdf.text.*;
import org.openpdf.text.Font;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.Image;

import java.awt.*;
import java.io.FileOutputStream;
import com.gynlog.model.entity.Movimentacao;

import javax.swing.*;
import java.util.List;

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

    public void gerarRelatorioPorFiltro(String caminhoSalvar, List<Movimentacao> listaFiltrada, String dataInicial,
                                        String dataFinal, String placa, String despesa) {


    }

    public void gerarRelatorioPorFiltro(String caminhoSalvar, List<Movimentacao> listaFiltrada,
                                        String placa, String despesa) {

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

            System.out.println(veiculo.toString());
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
}
