package Model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import DTO.ContabilidadeDTO;

public class PDFContabilidade {

	public void pdfContabilidade (ContabilidadeDTO contabilidadeDTO) {

		if (contabilidadeDTO != null) {
			Document document = new Document(PageSize.A4, 72, 72, 72, 72);

			try {

				Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLDITALIC, BaseColor.BLACK);

				PdfWriter.getInstance(document, new FileOutputStream("ABoutiquePDFContabilidade.pdf"));
				document.open();

				Paragraph aBoutique = new Paragraph(new Phrase(20F, "A BOUTIQUE", fontText));
				aBoutique.setAlignment(Element.ALIGN_CENTER);
				document.add(aBoutique);

				Paragraph rVenda = new Paragraph(new Phrase(15F, "\n\n\n\nRELATÓRIO DA CONTABILIDADE\n\n\n\n\n", fontText));
				rVenda.setAlignment(Element.ALIGN_CENTER);
				document.add(rVenda);

				String listaContabilidade = "";
				for (ContabilidadeDTO contabilidade: contabilidadeDTO.getTable()) {
					String data[] = contabilidade.getMesAtual().split("-");
					listaContabilidade += "\nMÊS/ANO: " + data[1] +"/"+ data[0] + "\nLUCRO: R$ " + contabilidade.getLucroMensal() + "\nQUANTIDADE DE PRODUTOS VENDIDOS: " + contabilidade.getQtdVendidos() + " UNIDADE(S)" + "\n\n\n\n";
				}

				Font fontTextP = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL, BaseColor.BLACK);
				Paragraph lProdutos = new Paragraph(new Phrase(15F, listaContabilidade, fontTextP));
				lProdutos.setAlignment(Element.ALIGN_LEFT);
				document.add(lProdutos);

				document.add(aBoutique);

			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
			try {
				Desktop.getDesktop().open(new File("ABoutiquePDFContabilidade.pdf"));
				JOptionPane.showMessageDialog(null, "RELATÓRIO GERADO", "PDF", JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
