package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;

public class CanneverbeCDBurner extends VersionedAppsImpl {
    public String getURL() {
        return "https://cdburnerxp.se/en/download";
    }

    public void reReadData() {
        name = "Canneverbe CDBurner";
        try {
            Document doc = Jsoup.connect(getURL()).get();
            version = doc.getElementsByTag("small").get(1).text();
            date = " ";
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }
}
