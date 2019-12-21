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

import DTO.ClienteDTO;
import DTO.ContabilidadeDTO;
import DTO.FuncionarioDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class PDFNotaFiscal {

	public void pdfNotaFiscal (FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO, VendaDTO vendaDTO) {
		
		if (vendaDTO != null) {
			Document document = new Document(PageSize.NOTE);
			try {

				Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, BaseColor.BLACK);

				PdfWriter.getInstance(document, new FileOutputStream("ABoutiquePDFNotaFiscal.pdf"));
				document.open();

				Paragraph aBoutique = new Paragraph(new Phrase(12F, "A BOUTIQUE\n\nR. Pref. Ernesto da Silveira, 2-84,\n\n Monteiro - PB, 58500-000\n\nCNPJ: ", fontText));
				aBoutique.setAlignment(Element.ALIGN_LEFT);
				document.add(aBoutique);
				
				Paragraph divisor = new Paragraph("_____________________________________________________________________");
				document.add(divisor);
				
				Paragraph rVenda = new Paragraph(new Phrase(12F, "\n\nDocumento Auxiliar Da Nota Fiscal Do Consumidor\n\n", fontText));
				rVenda.setAlignment(Element.ALIGN_CENTER);
				document.add(rVenda);
				
				document.add(divisor);
				
				Paragraph venda = new Paragraph(new Phrase(11F, "\n\nCód. Venda - " + vendaDTO.getIdVenda() + " | Produto(s) - \n\n", fontText));
				venda.setAlignment(Element.ALIGN_LEFT);
				document.add(venda);

				String consumidor = clienteDTO.getNome();
				String dataEmissao = vendaDTO.getData_venda();
				String listaProdutos = "";
				for (ProdutoDTO produtos: vendaDTO.getProdutos()) {
					listaProdutos += "\nProduto -  " + produtos.getNome() + "\n\nQuant. Unid. - " + produtos.getQtdPedida() + "\n\nUnit (R$) - " + produtos.getPreco() + "\n\n\n";
				}

				Font fontTextP = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL, BaseColor.BLACK);
				Paragraph lProdutos = new Paragraph(new Phrase(11F, listaProdutos, fontTextP));
				lProdutos.setAlignment(Element.ALIGN_LEFT);
				document.add(lProdutos);

				document.add(divisor);
				
				Paragraph totalVenda = new Paragraph(new Phrase(11F, "\n\nTOTAL - R$ " + vendaDTO.getPrecoTotal() + "\n\n", fontText));
				totalVenda.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(totalVenda);
				
				document.add(divisor);
				
				String dataFormat[] = vendaDTO.getData_venda().split("-");
				dataEmissao = dataFormat[2] + "/" + dataFormat[1] + "/" + dataFormat[0];
				Paragraph data = new Paragraph(new Phrase(11F, "\n\nEmissão: " + dataEmissao + "\n\n", fontText));
				data.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(data);
				
				document.add(divisor);
				
				Paragraph cliente = new Paragraph(new Phrase(11F, "\n\nCONSUMIDOR - " + consumidor + "\n\n", fontText));
				cliente.setAlignment(Element.ALIGN_CENTER);
				document.add(cliente);
				
				document.add(divisor);

			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
			try {
				Desktop.getDesktop().open(new File("ABoutiquePDFNotaFiscal.pdf"));
				JOptionPane.showMessageDialog(null, "NOTA FISCAL GERADA", "NOTA FISCAL", JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*public static void main(String[] args) {
		PDFNotaFiscal notaFiscal = new PDFNotaFiscal();
		notaFiscal.pdfNotaFiscal(new FuncionarioDTO(), new ClienteDTO(), new VendaDTO());
	}*/
}
