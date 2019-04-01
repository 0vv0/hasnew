package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;

public class ITunes extends VersionedAppsImpl {
    public String getURL() {
        return "https://support.apple.com/en-us/HT201222";
    }

    public void reReadData() {
        name = "iTunes";
        try {
            Document doc = Jsoup.connect(getURL()).get();
            for (Element tr : doc.getElementsByTag("tr")) {
                String t = tr.text();
                if (t != null && t.startsWith(name) && t.contains("for Windows")) {
                    Elements tds = tr.getElementsByTag("td");
                    date = tds.last() != null ? tds.last().text() : "";
                    version = tds.first() != null ? tds.first().text() : "";
                    return;
                }
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }

}
