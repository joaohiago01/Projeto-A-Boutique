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

import DTO.ProdutoDTO;

public class PDFProduto {

	public void pdfProduto (ProdutoDTO produtoDTO) {

		if (produtoDTO != null) {
			Document document = new Document(PageSize.A4, 72, 72, 72, 72);

			try {

				Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLDITALIC, BaseColor.BLACK);

				PdfWriter.getInstance(document, new FileOutputStream("ABoutiquePDFProduto.pdf"));
				document.open();

				Paragraph aBoutique = new Paragraph(new Phrase(20F, "A BOUTIQUE", fontText));
				aBoutique.setAlignment(Element.ALIGN_CENTER);
				document.add(aBoutique);

				Paragraph rProdutos = new Paragraph(new Phrase(15F, "\n\n\n\nRELATÓRIO DE PRODUTOS\n\n\n\n\n", fontText));
				rProdutos.setAlignment(Element.ALIGN_CENTER);
				document.add(rProdutos);

				String listaProdutos = "";
				for (String produto: produtoDTO.getTable()) {
					String p[] = produto.split("%");
					double precoFormat = Double.parseDouble(p[2]);
					listaProdutos += "\nPRODUTO: " + p[1] + "\n\nPREÇO: R$ " + precoFormat + "\n\nQUANTIDADE NO ESTOQUE: " + p[3] + " UNIDADE(S)" + "\n\n\n\n";
				}

				Font fontTextP = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL, BaseColor.BLACK);
				Paragraph lProdutos = new Paragraph(new Phrase(15F, listaProdutos, fontTextP));
				lProdutos.setAlignment(Element.ALIGN_LEFT);
				document.add(lProdutos);

				document.add(aBoutique);

			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
			try {
				Desktop.getDesktop().open(new File("ABoutiquePDFProduto.pdf"));
				JOptionPane.showMessageDialog(null, "RELATÓRIO GERADO", "PDF", JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
