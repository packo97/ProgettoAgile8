package it.unical.demacs.inf.asd.ProgettoAgile8.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.ItemRicevuta;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.ListaItemPrescrizione;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.ListaItemRicevuta;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

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
            Font fontTitolo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 28, BaseColor.BLACK);
            Paragraph titolo = new Paragraph("PRESCRIZIONE MEDICO VETERINARIA", fontTitolo);
            titolo.setAlignment(Element.ALIGN_CENTER);
            document.add(titolo);

            Font bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            Paragraph dati = new Paragraph("MEDICO VETERINARIO", bold);
            dati.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tabellaMedico = new PdfPTable(2);
            Stream.of("MEDICO VETERINARIO", "DESTINATARIO")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle,bold));
                        tabellaMedico.addCell(header);
                    });
            tabellaMedico.addCell(dottoreDTO.getNome() + " " + dottoreDTO.getCognome());
            tabellaMedico.addCell("PROPRIETARIO DEGLI ANIMALI:\n"+
                    pazienteDTO.getNome() + " " + pazienteDTO.getCognome() + "\n" +
                    "Codice fiscale: "+pazienteDTO.getCodice_fiscale());
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
            Paragraph secondo = new Paragraph("Prescrizione per acquisto medicinali",bold);
            secondo.setAlignment(Element.ALIGN_CENTER);
            document.add(secondo);
            document.add(new Paragraph(("\n")));
            PdfPTable medicinali = new PdfPTable(5);
            medicinali.setWidthPercentage(100);
            Stream.of("MEDICINALE", "QUANTITA'", "DOSE DI IMPIEGO", "DURATA DEL TRATTAMENTO (giorni)", "TEMPO DI SOSPENSIONE (giorni)")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle,bold));
                        medicinali.addCell(header);
                    });
            for(int i=0; i<listaItemPrescrizione.getLista_item_prescrizione().size(); i++){
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getMedicinale());
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getQuantita()+"");
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getDose_di_impiego()+"");
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getGiorni_trattamento()+"");
                medicinali.addCell(listaItemPrescrizione.getLista_item_prescrizione().get(i).getGiorni_sospensione()+"");
            }
            for(int i=0; i<4-listaItemPrescrizione.getLista_item_prescrizione().size(); i++){
                medicinali.addCell(" ");
                medicinali.addCell(" ");
                medicinali.addCell(" ");
                medicinali.addCell(" ");
                medicinali.addCell(" ");
            }
            document.add(medicinali);
            document.add(new Paragraph(("\n")));
            Paragraph intestazioneAnimali = new Paragraph("IDENTIFICAZIONE DEGLI ANIMALI",bold);
            intestazioneAnimali.setAlignment(Element.ALIGN_CENTER);
            document.add(intestazioneAnimali);
            document.add(new Paragraph(("\n")));
            PdfPTable animali = new PdfPTable(4);
            animali.setWidthPercentage(100);
            Stream.of("SPECIE", "ALTEZZA", "PESO", "SESSO")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle,bold));
                        animali.addCell(header);
                    });

            animali.addCell(animaleDTO.getTipo());
            animali.addCell(Integer.toString(animaleDTO.getAltezza()));
            animali.addCell(Integer.toString(animaleDTO.getPeso()));
            animali.addCell(animaleDTO.getGenere());

            document.add(animali);
            document.add(new Paragraph(("\n")));
            Font bold_piccolo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
            PdfPCell vuoto = new PdfPCell(new Phrase(""));
            vuoto.setBorder(0);
            PdfPTable firme = new PdfPTable(5);
            firme.setWidthPercentage(100);
            PdfPCell data = new PdfPCell(new Phrase("Data",bold_piccolo));
            data.setBorder(0);
            data.setBorderWidthTop(1);
            data.setHorizontalAlignment(Element.ALIGN_CENTER);
            firme.addCell(data);
            firme.addCell(vuoto);
            PdfPCell località = new PdfPCell(new Phrase("Località",bold_piccolo));
            località.setBorder(0);
            località.setBorderWidthTop(1);
            località.setHorizontalAlignment(Element.ALIGN_CENTER);
            firme.addCell(località);
            firme.addCell(vuoto);
            PdfPCell firma_veterinario = new PdfPCell(new Phrase("Firma e timbro del veterinario",bold_piccolo));
            firma_veterinario.setBorder(0);
            firma_veterinario.setBorderWidthTop(1);
            firma_veterinario.setHorizontalAlignment(Element.ALIGN_CENTER);
            firme.addCell(firma_veterinario);
            //firme.addCell(LocalDate.now().format(DateTimeFormatter.ofPattern("d/MM/uuuu")).toString());

            document.add(new Paragraph(("\n")));
            document.add(new Paragraph(("\n")));
            document.add(firme);
            document.add(new Paragraph(("\n")));
            document.add(new Paragraph(("\n")));

            Paragraph forniture = new Paragraph("Parte da compilarsi a cura del titolare dell'impianto solo nel caso di fornitura per scorta ai sensi dell'art. 34",bold);
            document.add(forniture);
            document.add(new Paragraph(("\n")));
            PdfPTable firme2 = new PdfPTable(3);
            firme2.setWidthPercentage(100);
            PdfPCell estremi_usl = new PdfPCell(new Phrase("Estremi autorizzazione U.S.L.", bold_piccolo));
            estremi_usl.setBorder(0);
            estremi_usl.setBorderWidthTop(1);
            estremi_usl.setHorizontalAlignment(Element.ALIGN_CENTER);
            firme2.addCell(estremi_usl);
            firme2.addCell(vuoto);
            PdfPCell firma_titolare = new PdfPCell(new Phrase("Firma e timbro del titolare", bold_piccolo));
            firma_titolare.setBorder(0);
            firma_titolare.setBorderWidthTop(1);
            firma_titolare.setHorizontalAlignment(Element.ALIGN_CENTER);
            firme2.addCell(firma_titolare);

            document.add(firme2);
            document.add(new Paragraph(("\n")));
            document.add(new Paragraph(("\n")));

            Paragraph farmacista = new Paragraph("Parte da compilarsi a cura del farmacista",bold);
            document.add(farmacista);
            document.add(new Paragraph(("\n")));
            PdfPTable firme3 = new PdfPTable(7);
            firme3.setWidthPercentage(100);
            PdfPCell timbro_venditore = new PdfPCell(new Phrase("Timbro venditore", bold_piccolo));
            timbro_venditore.setBorder(0);
            timbro_venditore.setBorderWidthTop(1);
            timbro_venditore.setHorizontalAlignment(Element.ALIGN_CENTER);
            firme3.addCell(timbro_venditore);
            firme3.addCell(vuoto);

            firme3.addCell(località);
            firme3.addCell(vuoto);
            firme3.addCell(data);
            firme3.addCell(vuoto);
            PdfPCell firma_farmacista = new PdfPCell(new Phrase("Firma",bold_piccolo));
            firma_farmacista.setBorder(0);
            firma_farmacista.setBorderWidthTop(1);
            firma_farmacista.setHorizontalAlignment(Element.ALIGN_CENTER);
            firme3.addCell(firma_farmacista);


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
            Font bold_piccolo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
            Paragraph titolo = new Paragraph("Ricevuta medica", fontTitolo);
            titolo.setAlignment(Element.ALIGN_CENTER);
            document.add(titolo);


            Paragraph data_ricevuta = new Paragraph("Data: " + LocalDate.now().toString());
            data_ricevuta.setAlignment(Element.ALIGN_RIGHT);

            document.add(new Paragraph(("\n")));

            Paragraph nome_clinica = new Paragraph("Nome clinica: Veterinary Clinic");
            Paragraph medico = new Paragraph("Dottore: " + dottoreDTO.getNome() + " " + dottoreDTO.getCognome());
            Paragraph codice_medico = new Paragraph("Codice medico: " + dottoreDTO.getCodice_identificativo());
            Paragraph indirizzo_clinica = new Paragraph("Indirizzo: " + "Via Pietro Bucci, Rende (CS), 87036 ");

            Font fontLabel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
            Paragraph label_paziente = new Paragraph("Informazioni Paziente", fontLabel);

            Paragraph paziente = new Paragraph("Paziente: " + pazienteDTO.getNome() + " " + pazienteDTO.getCognome());


            document.add(data_ricevuta);
            document.add(new Paragraph("\n"));

            document.add(nome_clinica);
            document.add(medico);
            document.add(codice_medico);
            document.add(indirizzo_clinica);
            document.add(new Paragraph("\n"));

            document.add(label_paziente);
            document.add(paziente);
            document.add(new Paragraph("\n"));

            //tabella
            PdfPTable table = new PdfPTable(new float[]{20,50,10,10,10});
            table.setWidthPercentage(100);
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


            Paragraph subtotale = new Paragraph("Sub-totale: " + somma+"€");
            subtotale.setAlignment(Element.ALIGN_RIGHT);
            Paragraph iva = new Paragraph("IVA: " + valoreIVA+"€");
            iva.setAlignment(Element.ALIGN_RIGHT);
            double valoreTotale = somma + valoreIVA;
            Paragraph totale = new Paragraph("Totale: " + valoreTotale+"€");
            totale.setAlignment(Element.ALIGN_RIGHT);
            Paragraph importo_pagato = new Paragraph("Importo pagato: " + listaItemRicevuta.getImporto_pagato()+"€") ;
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
        Font bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        Stream.of("Codice", "Descrizione medicina/servizio", "Qta", "Prezzo", "Totale linea")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setPhrase(new Phrase(columnTitle,bold));
                    table.addCell(header);
                });
    }

    static private void addRows(PdfPTable table, ItemRicevuta item) {

        PdfPCell cell=new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        if(item!=null){
            cell.setPhrase(new Phrase(item.getCodice().toString()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(item.getDescrizione()));
            table.addCell(cell);
            cell.setPhrase(new Phrase((item.getQuantita()+"")));
            table.addCell(cell);
            cell.setPhrase(new Phrase(item.getPrezzo()+"€"));
            table.addCell(cell);
            cell.setPhrase(new Phrase(item.getTotale()+"€"));
            table.addCell(cell);
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
