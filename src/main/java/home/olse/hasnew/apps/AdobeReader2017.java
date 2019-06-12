package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AdobeReader2017 extends VersionedAppsImpl {
    {
        name = "Adobe Reader 2017 (Classic Track)";
    }

    @Override
    protected String getURL() {
        return "https://supportdownloads.adobe.com/product.jsp?platform=windows&product=10";
    }

    @Override
    public String getFileMask() {
        return "DPD Adobe Reader 2017 ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        Document doc = Jsoup.connect(getURL()).get();
        for (Element tr : doc.getElementsByTag("tr")) {
            String t = tr.text();
            if (t != null && t.startsWith("Adobe Acrobat Reader MUI 2017 (Acrobat 2017 Track)")) {
                Elements tds = tr.getElementsByTag("td");
                date = tds.last() != null ? tds.last().text() : "";
                Element tE = tr.previousElementSibling();
                while (tE != null) {
                    if (tE.text() != null && tE.text().toLowerCase().startsWith("version ")) {
                        version = tE.text().substring("version ".length());
                        break;
                    }
                    tE = tE.previousElementSibling();
                }
                return;
            }
        }

    }


}
