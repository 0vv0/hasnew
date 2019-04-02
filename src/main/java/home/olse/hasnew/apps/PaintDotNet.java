package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.util.logging.Level;

public class PaintDotNet extends VersionedAppsImpl {
    {
        name = "Paint.NET";
    }

    @Override
    public String getFileMask() {
        return "DPD dotPDN Paint.Net ";
    }

    @Override
    protected String getURL() {
        return "https://www.getpaint.net/download.html";
    }


    @Override
    public void reReadData() {
//        name, date, value should be set here
        try {
            String id = "table8";
            Document doc = Jsoup.connect(getURL()).get();
            Element element = doc.getElementById(id);
            if (element.getElementsByTag("tr") != null &&
                    element.getElementsByTag("tr").size() > 1) {

                Element el = element.getElementsByTag("tr").get(1);

                Element td = el.getElementsByTag("td").first();
                if (td != null) {
                    version = td.text();
                    if (td.nextElementSibling() != null) {
                        date = td.nextElementSibling().text();
                    } else {
                        date = " ";
                    }
                }
            }


        } catch (IOException e) {
            if (logger == null) {
                System.out.println(e);
            } else {
                logger.log(Level.INFO, e.getMessage() != null ? e.getMessage() : e.toString());
            }
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }


}
