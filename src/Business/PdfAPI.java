package Business;

import java.io.IOException;

import com.google.zxing.WriterException;

import Model.List;
import Model.User;

public interface PdfAPI {

    void createPdf(List list, User buyer);

    void generateQRcode(String data, String path, String charset, int h, int w) throws WriterException, IOException;
}
