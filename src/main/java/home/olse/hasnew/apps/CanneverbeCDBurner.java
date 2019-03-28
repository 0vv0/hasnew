package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedApp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CanneverbeCDBurner implements VersionedApp {
    @Autowired
    private Logger logger;
    private String name = "Canneverbe CDBurner";
    private String version;
    private String date = "";

    public String getVersion(String webPageHTML) {
        Document doc = Jsoup.parse(webPageHTML);
        return doc.getElementsByTag("small").get(1).text();

//        return "";
    }

    public String getURL() {
        return "https://cdburnerxp.se/en/download";
    }

    public void reReadData() {
        try {
            Document doc = Jsoup.connect(getURL()).get();
            version = getVersion(doc.outerHtml());
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length()>100?100:e.getMessage().length());
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
