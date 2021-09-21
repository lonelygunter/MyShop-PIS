package Test;

import org.junit.Test;

import Business.PdfBoxAPI;
import Model.List;
import Model.User;

public class GeneratePDFTest {

    @Test
    public void GeneratePDFTest() {
        List list = new List();
        PdfBoxAPI pdf = new PdfBoxAPI("");
        pdf.createPdf(list, new User("pippo", "test", "Filippo", "Goffredo", null, null, null, null, null, null, 0));
    }
    
}
