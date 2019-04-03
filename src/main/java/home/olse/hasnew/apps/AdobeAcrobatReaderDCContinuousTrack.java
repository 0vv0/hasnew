package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AdobeAcrobatReaderDCContinuousTrack extends VersionedAppsImpl {
    {
        name = "Adobe Acrobat Reader DC (Continuous Track)";
    }

    @Override
    protected String getURL() {
        return "https://supportdownloads.adobe.com/product.jsp?platform=windows&product=10";
    }

    @Override
    public String getFileMask() {
        return "DPD Adobe Reader DC ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        Document doc = Jsoup.connect(getURL()).get();
        for (Element tr : doc.getElementsByTag("tr")) {
            String t = tr.text();
            if (t != null && t.startsWith("Adobe Acrobat Reader DC (Continuous Track)")) {
                Elements tds = tr.getElementsByTag("td");
                date = tds.last() != null ? tds.last().text() : "";
                version = tr.parent().getElementsByTag("tr").first().text();
                return;
            }
        }

    }


}
