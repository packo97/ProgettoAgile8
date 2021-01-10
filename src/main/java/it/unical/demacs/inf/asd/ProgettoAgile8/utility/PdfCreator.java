package it.unical.demacs.inf.asd.ProgettoAgile8.utility;

import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.kernel.colors.Color;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.ItemRicevuta;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.ListaItemPrescrizione;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.ListaItemRicevuta;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Stream;

public class PdfCreator {


    static public byte[] creaPrescrizionePDF(ListaItemPrescrizione listaItemPrescrizione){
        DottoreDTO dottoreDTO = listaItemPrescrizione.getDottore();
        PazienteDTO pazienteDTO = listaItemPrescrizione.getPaziente();
        AnimaleDTO animaleDTO = listaItemPrescrizione.getAnimale();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            Font fontTitolo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 32, BaseColor.BLACK);
            Paragraph titolo = new Paragraph("PRESCRIZIONE MEDICO VETERINARIA", fontTitolo);
            titolo.setAlignment(Element.ALIGN_CENTER);
            document.add(titolo);

            Font bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
            Paragraph dati = new Paragraph("MEDICO VETERINARIO", bold);
            dati.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tabellaMedico = new PdfPTable(2);
            Stream.of("MEDICO VETERINARIO", "DESTINATARIO")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        tabellaMedico.addCell(header);
                    });
            tabellaMedico.addCell(dottoreDTO.getNome() + " " + dottoreDTO.getCognome());
            tabellaMedico.addCell("PROPRIETARIO DEGLI ANIMALI:\n"+
                    pazienteDTO.getNome() + " " + pazienteDTO.getCognome() + "\n" +
                    "via: \n" + "provicia \n" + "USL \n");
            tabellaMedico.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabellaMedico.setWidthPercentage(100);

            /*
            PdfPTable tabellaPaziente = new PdfPTable(1);
            Stream.of("DESTINTARIO")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        tabellaPaziente.addCell(header);
                    });
            tabellaPaziente.addCell("PROPRIETARIO DEGLI ANIMALI\n"+
                                        pazienteDTO.getNome() + " " + pazienteDTO.getCognome() + "\n" +
                                        "via: \n" + "provicia \n" + "USL \n");

            tabellaPaziente.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabellaPaziente.setWidthPercentage(60);
*/
            Paragraph p = new Paragraph();
            p.add(tabellaMedico);

            //p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            //p.add(tabellaPaziente);
            document.add(new Paragraph(("\n")));
            document.add(p);

            //dati.add(tabellaMedico);
            //dati.add(tabellaPaziente);

            //document.add(dati);
            document.add(new Paragraph(("\n")));
            Paragraph secondo = new Paragraph("Prescrizione per acquisto medicinali");
            document.add(secondo);
            document.add(new Paragraph(("\n")));
            PdfPTable medicinali = new PdfPTable(5);
            Stream.of("MEDICINALE", "QUANTITA'", "DOSE DI IMPIEGO", "DURATA DEL TRATTAMENTO (giorni)", "TEMPO DI SOSPENSIONE (giorni)")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        medicinali.addCell(header);
                    });
            for(int i=0; i<listaItemPrescrizione.getLista_item_prescrizione().size(); i++){
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getMedicinale());
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getQuantita()+"");
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getDose_di_impiego()+"");
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getGiorni_trattamento()+"");
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getGiorni_sospensione()+"");
            }
            document.add(medicinali);
            document.add(new Paragraph(("\n")));
            Paragraph intestazioneAnimali = new Paragraph("IDENTIFICAZIONE DEGLI ANIMALI");
            document.add(intestazioneAnimali);
            document.add(new Paragraph(("\n")));
            PdfPTable animali = new PdfPTable(4);
            Stream.of("SPECIE", "ALTEZZA", "PESO", "SESSO")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        animali.addCell(header);
                    });

            animali.addCell(animaleDTO.getTipo());
            animali.addCell(Integer.toString(animaleDTO.getAltezza()));
            animali.addCell(Integer.toString(animaleDTO.getPeso()));
            animali.addCell(animaleDTO.getGenere());

            document.add(animali);
            document.add(new Paragraph(("\n")));
            PdfPTable firme = new PdfPTable(3);
            /*
            Cell cell = new Cell().add("DATA");
            cell.setBorderTop(new SolidBorder(Color.RED, 1));

             */
            firme.addCell("DATA");
            firme.addCell("LOCALITA'");
            firme.addCell("FIRMA");
            firme.addCell(LocalDate.now().format(DateTimeFormatter.ofPattern("d/MM/uuuu")).toString());
            firme.addCell("via Isaac Newton n 2");
            firme.addCell("");
            document.add(new Paragraph(("\n")));
            document.add(new Paragraph(("\n")));
            document.add(firme);
            document.add(new Paragraph(("\n")));
            document.add(new Paragraph(("\n")));
            Paragraph forniture = new Paragraph("Parte da compilarsi a cura del titolare dell'impianto solo nel caso di fornitura per scorta ai sensi dell'art. 34");
            document.add(forniture);
            document.add(new Paragraph(("\n")));
            PdfPTable firme2 = new PdfPTable(2);
            firme2.addCell("Estremi autorizzazione USL");
            firme2.addCell("FIRMA");
            firme2.addCell("  ");
            firme2.addCell("  ");

            document.add(firme2);
            document.add(new Paragraph(("\n")));
            Paragraph farmacista = new Paragraph("Parte da compilarsi a cura del farmacista");
            document.add(farmacista);
            document.add(new Paragraph(("\n")));
            PdfPTable firme3 = new PdfPTable(4);
            firme3.addCell("Timbro venditore");
            firme3.addCell("Località");
            firme3.addCell("Data");
            firme3.addCell("Firma");
            firme3.addCell("   ");
            firme3.addCell("   ");
            firme3.addCell("  ");
            firme3.addCell("  ");

            document.add(firme3);
            document.add(new Paragraph(("\n")));
            document.close();
            byte[] pdfBytes = out.toByteArray();
            return pdfBytes;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }



    static public byte[] creaRicevutaPDF(ListaItemRicevuta listaItemRicevuta){
        DottoreDTO dottoreDTO = listaItemRicevuta.getDottore();
        PazienteDTO pazienteDTO = listaItemRicevuta.getPaziente();

        Document document = new Document();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);
            document.open();
            Font fontTitolo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 32, BaseColor.BLACK);
            Paragraph titolo = new Paragraph("Ricevuta medica", fontTitolo);
            titolo.setAlignment(Element.ALIGN_MIDDLE);
            document.add(titolo);


            Paragraph numero_ricevuta = new Paragraph("Numero Ricevuta: " + new Random().nextInt(9999999));
            numero_ricevuta.setAlignment(Element.ALIGN_RIGHT);

            Paragraph data_ricevuta = new Paragraph("Data: " + LocalDate.now().toString());
            data_ricevuta.setAlignment(Element.ALIGN_RIGHT);

            document.add(new Paragraph(("\n")));

            Paragraph nome_clinica = new Paragraph("Nome clinica: Veterinary Clinic");
            Paragraph medico = new Paragraph("Dottore: " + dottoreDTO.getNome() + " " + dottoreDTO.getCognome());
            Paragraph codice_medico = new Paragraph("Codice medico: " + dottoreDTO.getCodice_identificativo());
            Paragraph indirizzo_clinica = new Paragraph("Indirizzo: " + "Via Pietro Bucci");

            Font fontLabel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
            Paragraph label_paziente = new Paragraph("Informazioni Paziente", fontLabel);

            Paragraph paziente = new Paragraph("Paziente: " + pazienteDTO.getNome() + " " + pazienteDTO.getCognome());
            Paragraph indirizzo_paziente = new Paragraph("Indirizzo: " + "Via Isaac Newton");


            document.add(numero_ricevuta);
            document.add(data_ricevuta);
            document.add(new Paragraph("\n"));

            document.add(nome_clinica);
            document.add(medico);
            document.add(codice_medico);
            document.add(indirizzo_clinica);
            document.add(new Paragraph("\n"));

            document.add(label_paziente);
            document.add(paziente);
            document.add(indirizzo_paziente);
            document.add(new Paragraph("\n"));

            //tabella
            PdfPTable table = new PdfPTable(5);
            addTableHeader(table);
            double somma = 0;
            for (int i=0; i<listaItemRicevuta.getLista_item_ricevuta().size(); i++){
                addRows(table, listaItemRicevuta.getLista_item_ricevuta().get(i));
                somma+= listaItemRicevuta.getLista_item_ricevuta().get(i).getPrezzo() * listaItemRicevuta.getLista_item_ricevuta().get(i).getQuantita();
            }

            double valoreIVA = somma * 4 /100;

            for(int i=0; i<10 - listaItemRicevuta.getLista_item_ricevuta().size(); i++)
                addRows(table, null);

            document.add(table);
            document.add(new Paragraph("\n"));


            Paragraph subtotale = new Paragraph("Sub-totale: " + somma);
            subtotale.setAlignment(Element.ALIGN_RIGHT);
            Paragraph iva = new Paragraph("Iva: " + valoreIVA);
            iva.setAlignment(Element.ALIGN_RIGHT);
            double valoreTotale = somma + valoreIVA;
            Paragraph totale = new Paragraph("Totale: " + valoreTotale);
            totale.setAlignment(Element.ALIGN_RIGHT);
            Paragraph importo_pagato = new Paragraph("Importo pagato: " + listaItemRicevuta.getImporto_pagato()) ;
            importo_pagato.setAlignment(Element.ALIGN_RIGHT);

            document.add(subtotale);
            document.add(iva);
            document.add(totale);
            document.add(importo_pagato);

            document.close();
            byte[] pdfBytes = out.toByteArray();
            return pdfBytes;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }


    static private void addTableHeader(PdfPTable table) {
        Stream.of("Codice", "Descrizione medicina/servizio", "Qta", "Prezzo", "Totale linea")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    static private void addRows(PdfPTable table, ItemRicevuta item) {

        if(item!=null){
            table.addCell(item.getCodice().toString());
            table.addCell(item.getDescrizione());
            table.addCell(item.getQuantita() + "");
            table.addCell(item.getPrezzo() + "€");
            table.addCell(item.getTotale() + "€");
        }
        else{
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        }

    }

}
