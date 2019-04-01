package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedApp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdobeAcrobatReaderDCContinuousTrack implements VersionedApp {
    @Autowired
    private Logger logger;
    private String name = "Adobe Acrobat Reader DC (Continuous Track)";
    private String version;
    private String date = "";

    public String getVersion(String webPageHTML) {
        Document doc = Jsoup.parse(webPageHTML);
        for (Element tr : doc.getElementsByTag("tr")) {
            String t = tr.text();
            if (t != null && t.startsWith("Adobe Acrobat Reader DC (Continuous Track)")) {
                Elements tds = tr.getElementsByTag("td");
                date = tds.last() != null ? tds.last().text() : "";
                return tr.parent().getElementsByTag("tr").first().text();

            }
        }
        return "";
    }

    public String getURL() {
        return "https://supportdownloads.adobe.com/product.jsp?platform=windows&product=10";
    }

    public void reReadData() {
        try {
            Document doc = Jsoup.connect(getURL()).get();
            version = getVersion(doc.outerHtml());
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        reReadData();
        return version;
    }

    @Override
    public String getDate() {
        return date;
    }
}
