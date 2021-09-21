package Business;

import Model.EmailNotification;
import Model.List;
import Model.User;

public class BuyingListDocument implements Document {

    private User buyer;
    private List list;

    public BuyingListDocument(List list, User buyer) {
        this.buyer = buyer;
        this.list = list;
    }

    @Override
    public void send(String recipient, String object, String body) throws Exception{
        String path = "/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/listPdf.pdf";

        if(path!=null) {
            PdfBoxAPI pba = new PdfBoxAPI(path);
            pba.createPdf(list, buyer);
        }

        // codice per inviare mail all'indirizzo specificato

        EmailNotification mail = new EmailNotification();
        mail.setupServerProperties();
        mail.sendEmail(recipient, object, body, path);
    }
}
