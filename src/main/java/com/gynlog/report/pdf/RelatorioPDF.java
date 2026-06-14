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

import java.time.LocalDate;
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
                getClass().getResource("/icons/logo-200x200.png")
        );

        logo.scaleToFit(120, 120);
        logo.setAlignment(Image.ALIGN_CENTER);

        documentoPDF.add(logo);
        documentoPDF.add(Chunk.NEWLINE);
    }

    public void adicionarTitulo(String titulo) throws Exception {

        Paragraph paragraph = new Paragraph(titulo, tituloFont);

        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        documentoPDF.add(paragraph);
        documentoPDF.add(Chunk.NEWLINE);
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


    }
}
