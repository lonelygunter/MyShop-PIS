package Business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.ListDAO;
import Model.Item;
import Model.List;
import Model.User;

public class PdfBoxAPI implements PdfAPI{

    private String outputPath;

    public PdfBoxAPI(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
	public void createPdf(List list, User buyer) {
		try{
			Document document = new Document(PageSize.A4, 10f, 10f, 10f, 10f);

			PdfWriter.getInstance(document, new FileOutputStream(outputPath));
			document.open();

			//per aggiungere il logo dello shop
			document.add(Image.getInstance("/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/myshoplogo.png"));

			//per aggiungere una stringa introduttiva
			Paragraph nomeUtente = new Paragraph("Caro " + buyer.getSurname() + " "+ buyer.getName() + ", grazie per aver pensato di comprare articoli dal nostro negozio.\n" +
												"La preghiamo di esibire questa lista ai nostri collaboratori presenti in cassa, per poter effettuare il pagamento della sua lista \"" + "NOME LISTA" + "\", della quale può vedere un riassunto qui sotto:",
												FontFactory.getFont(FontFactory.COURIER, 11));
			nomeUtente.setSpacingAfter(35F);
			document.add(nomeUtente);

			//per aggiungere la tabella  
			PdfPTable table = new PdfPTable(2);
			PdfPCell c1 = new PdfPCell(new Phrase("Articolo"));
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Prezzo"));
			table.addCell(c1);
			table.setHeaderRows(1);

			ArrayList<Item> items = ListDAO.getInstance().getListItemsByName(list.getName());
			Iterator<Item> iterator = items.iterator();

            while (iterator.hasNext()) {
                Item item = iterator.next();
                table.addCell(item.getName());
                table.addCell(item.getPrice() + " €");
            }

			table.setSpacingAfter(5F);

			document.add(table);

			//per generare il qrcode
			String mail = "mail";
			String subject = "Pagamento effettuato della lista: " + list.getName();
			String body = "Caro Sig/a Manager, \n la informiamo che la seguente lista è stata pagata dal seguente cliente:" +
							"Dati Cliente:" + "\nId: " + buyer.getId()
											+ "\nNome: " + buyer.getName()
											+ "\nCognome: " + buyer.getSurname()
											+ "\nEmail: " + buyer.getEmail()
											+ "\nTelefono: " + buyer.getTelephone()
							+ "\nDati Lista:" + "\nId: " + list.getId()
											+ "\nNome: " + list.getName()
											+ "\nPrezzo totale: " + list.getPrice()
							+ "\n\nLa preghiamo di impostare come pagato (\"P\") lo stato della lista. \nSaluti.";

			//dati da immagazzinare nel QR code 
			String str= "mailto: " + mail + "?subject=" + subject + "&body=" + body;  

			//path del QR Code  
			String path = "/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/qrcode.png";  

			//Encoding di caratteri usato 
			String charset = "UTF-8";  
			Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>(); 

			//per generare il QR code con un basso livello di errore
			hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  

			//per generare il qrcode 
			generateQRcode(str, path, charset, 200, 200);   

			//per aggiungere il qrcode
			document.add(Image.getInstance("/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/qrcode.png"));

			document.close();

		}catch(Exception e){
			System.err.println(e);
		}
	}

	@Override
	public void generateQRcode(String data, String path, String charset, int h, int w) throws WriterException, IOException {  
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
    }

    public String getPdfPath() {
        return outputPath;
    }
}