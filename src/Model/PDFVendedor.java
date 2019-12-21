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

import DTO.FuncionarioDTO;
import DTO.VendaDTO;

public class PDFVendedor {

	public void pdfVendedor (FuncionarioDTO funcionarioDTO, VendaDTO vendaDTO) {

		Document document = new Document(PageSize.A4, 72, 72, 72, 72);

		try {

			Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLDITALIC, BaseColor.BLACK);

			PdfWriter.getInstance(document, new FileOutputStream("ABoutiquePDFVendedor.pdf"));
			document.open();

			Paragraph aBoutique = new Paragraph(new Phrase(20F, "A BOUTIQUE", fontText));
			aBoutique.setAlignment(Element.ALIGN_CENTER);
			document.add(aBoutique);

			Paragraph rVenda = new Paragraph(new Phrase(15F, "\n\n\n\nRELATÓRIO DO VENDEDOR\n\n\n\n", fontText));
			rVenda.setAlignment(Element.ALIGN_CENTER);
			document.add(rVenda);

			if (vendaDTO.getVendedor().equals("")) {
				Paragraph rVendedor = new Paragraph(new Phrase(20F, "VENDEDOR(A) - " + funcionarioDTO.getNome(), fontText));
				rVendedor.setAlignment(Element.ALIGN_CENTER);
				document.add(rVendedor);
			} else {
				Paragraph rVendedor = new Paragraph(new Phrase(20F, "VENDEDOR(A) - " + vendaDTO.getVendedor(), fontText));
				rVendedor.setAlignment(Element.ALIGN_CENTER);
				document.add(rVendedor);
			}

			String listaVendas = "";
			for (String venda: vendaDTO.getTable()) {
				listaVendas += venda + "\n\n\n";
			}

			Font fontTextP = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL, BaseColor.BLACK);
			Paragraph lProdutos = new Paragraph(new Phrase(15F, listaVendas, fontTextP));
			lProdutos.setAlignment(Element.ALIGN_LEFT);
			document.add(lProdutos);

			document.add(aBoutique);

		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("ABoutiquePDFVendedor.pdf"));
			JOptionPane.showMessageDialog(null, "RELATÓRIO GERADO", "PDF", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
