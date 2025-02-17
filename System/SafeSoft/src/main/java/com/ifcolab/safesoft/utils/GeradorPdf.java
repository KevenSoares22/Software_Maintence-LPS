package com.ifcolab.safesoft.utils;

import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Pagamento;
import com.ifcolab.safesoft.model.Item;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeradorPdf implements IGeradorDocumento {
    
    private static final Font TITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
    private static final Font SUBTITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
    private static final Font NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 12);
    private static final Font DESTAQUE = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
    private static final Font RODAPE = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10);

    @Override
    public void gerarDocumento(String caminho, String... conteudo) {
        Document documento = new Document();
        try {
            String caminhoArquivo = caminho + File.separator + "recibo.pdf";
            new File(caminhoArquivo).getParentFile().mkdirs();
            PdfWriter.getInstance(documento, new FileOutputStream(caminhoArquivo));
            documento.open();

            for (String texto : conteudo) {
                documento.add(new Paragraph(texto));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }

    @Override
    public void combinarDocumentos(String caminhoSaida, List<String> caminhosEntrada) {
        Document documento = new Document();
        try {
            if (new File(caminhoSaida).isDirectory()) {
                caminhoSaida = caminhoSaida + File.separator + "relatorio.pdf";
            }

            File arquivoSaida = new File(caminhoSaida);
            arquivoSaida.getParentFile().mkdirs();

            PdfCopy copia = new PdfCopy(documento, new FileOutputStream(arquivoSaida));
            documento.open();

            for (String inputPath : caminhosEntrada) {
                PdfReader reader = new PdfReader(inputPath);
                copia.addDocument(reader);
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
    
    public void gerarReciboPagamento(String caminho, Servico servico, Pagamento pagamento) {
        Document documento = new Document();
        try {
            String caminhoArquivo = caminho + File.separator + "recibo_pagamento.pdf";
            new File(caminhoArquivo).getParentFile().mkdirs();
            PdfWriter.getInstance(documento, new FileOutputStream(caminhoArquivo));
            documento.open();
            
            Paragraph cabecalho = new Paragraph("SAFESOFT", TITULO);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            documento.add(cabecalho);
            
            Paragraph subtitulo = new Paragraph("Sistema de Gestão  ", RODAPE);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);
            
            Paragraph cnpj = new Paragraph("CNPJ: 11.111.111/0001-88", RODAPE);
            cnpj.setAlignment(Element.ALIGN_CENTER);
            documento.add(cnpj);
            documento.add(new Paragraph("\n"));
            
            Paragraph numeroRecibo = new Paragraph(
                "Recibo Nº: " + String.format("%06d", pagamento.getId()), DESTAQUE);
            numeroRecibo.setAlignment(Element.ALIGN_RIGHT);
            documento.add(numeroRecibo);
            documento.add(new Paragraph("\n"));
            
            Paragraph titulo = new Paragraph("RECIBO DE PAGAMENTO", TITULO);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("Dados do Cliente", SUBTITULO));
            adicionarLinha(documento, "Nome:", servico.getCliente().getNome());
            adicionarLinha(documento, "CPF:", servico.getCliente().getCpf());
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("Dados da Servico", SUBTITULO));
            adicionarLinha(documento, "Data/Hora:", 
                servico.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            adicionarLinha(documento, "Tecnicoa:", servico.getTecnico().getNome());
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("Itens Realizados", SUBTITULO));
            double totalItens = 0;
            for (Item proc : servico.getItens()) {
                adicionarLinha(documento, "• " + proc.getNome() + ":",
                    "R$ " + String.format("%.2f", proc.getValor()));
                totalItens += proc.getValor();
            }
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("Dados do Pagamento", SUBTITULO));
            adicionarLinha(documento, "Valor Total:", "R$ " + String.format("%.2f", pagamento.getValor()));
            adicionarLinha(documento, "Método de Pagamento:", pagamento.getMetodoPagamento().toString());
            adicionarLinha(documento, "Data do Pagamento:",
                pagamento.getDataPagamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            
            if (pagamento.getDetalhes() != null && !pagamento.getDetalhes().isEmpty()) {
                documento.add(new Paragraph("\nObservações:", DESTAQUE));
                documento.add(new Paragraph(pagamento.getDetalhes(), NORMAL));
            }

            documento.add(new Paragraph("\n"));
            Paragraph rodape = new Paragraph(
                "Este documento é um recibo oficial do pagamento realizado.", RODAPE);
            rodape.setAlignment(Element.ALIGN_CENTER);
            documento.add(rodape);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
    
    private void adicionarLinha(Document documento, String label, String valor) {
        try {
            Paragraph p = new Paragraph();
            p.add(new Paragraph(label, DESTAQUE));
            p.add(new Paragraph(" " + valor, NORMAL));
            documento.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioItem(String caminho, Servico servico, Item item, String resultado, String observacoes) {
        Document documento = new Document();
        try {
            String caminhoArquivo = caminho + File.separator + "relatorio_item.pdf";
            new File(caminhoArquivo).getParentFile().mkdirs();
            PdfWriter.getInstance(documento, new FileOutputStream(caminhoArquivo));
            documento.open();
            
            Paragraph cabecalho = new Paragraph("SAFESOFT", TITULO);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            documento.add(cabecalho);
            
            Paragraph subtitulo = new Paragraph("Sistema de Gestão  ", RODAPE);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);
            

            Paragraph cnpj = new Paragraph("CNPJ: 11.111.111/0001-88", RODAPE);
            cnpj.setAlignment(Element.ALIGN_CENTER);
            documento.add(cnpj);
            documento.add(new Paragraph("\n"));
            

            Paragraph titulo = new Paragraph("RELATÓRIO DE item", TITULO);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("\n"));
            
  
            Paragraph dataHora = new Paragraph(
                "Data/Hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                DESTAQUE
            );
            dataHora.setAlignment(Element.ALIGN_RIGHT);
            documento.add(dataHora);
            documento.add(new Paragraph("\n"));
            
  
            documento.add(new Paragraph("Dados do Cliente", SUBTITULO));
            adicionarLinha(documento, "Nome:", servico.getCliente().getNome());
            adicionarLinha(documento, "CPF:", servico.getCliente().getCpf());
            documento.add(new Paragraph("\n"));
            

            documento.add(new Paragraph("Responsável pelo Item", SUBTITULO));
            adicionarLinha(documento, "Tecnico :", "Dr(a). " + servico.getTecnico().getNome());
            adicionarLinha(documento, "COD:", servico.getTecnico().getCod());
            documento.add(new Paragraph("\n"));
            

            documento.add(new Paragraph("Detalhes do Item", SUBTITULO));
            adicionarLinha(documento, "Tipo:", item.getTipo().toString());
            adicionarLinha(documento, "Descrição:", item.getDescricao());


            documento.add(new Paragraph("\n"));
            
 
            documento.add(new Paragraph("Resultado do Item", SUBTITULO));
            documento.add(new Paragraph(resultado, NORMAL));
            documento.add(new Paragraph("\n"));
            
  
            if (observacoes != null && !observacoes.isEmpty()) {
                documento.add(new Paragraph("Observações", SUBTITULO));
                documento.add(new Paragraph(observacoes, NORMAL));
                documento.add(new Paragraph("\n"));
            }
            

            
       
            Paragraph rodape = new Paragraph(
                "Este documento é um relatório oficial do item realizado.", RODAPE);
            rodape.setAlignment(Element.ALIGN_CENTER);
            documento.add(rodape);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
} 