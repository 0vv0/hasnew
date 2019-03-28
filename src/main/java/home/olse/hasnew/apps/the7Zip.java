package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedApp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class the7Zip implements VersionedApp {
    @Autowired
    private Logger logger;
    private String name = "7 Zip";
    private String version;
    private String date = "";
    private String searchString = "Download 7-Zip ";

    public String getVersion(String webPageHTML) {
        Document doc = Jsoup.parse(webPageHTML);
        for (Element b : doc.getElementsByTag("b")) {
            if(b.text().startsWith(searchString)){
//                Download 7-Zip 19.00 (2019-02-21) for Windows:
                version = b.text().substring(searchString.length(), searchString.length()+5);
                date = b.text().substring(searchString.length()+7, searchString.length()+7+10);
                return version;
            }
        }
        return "";
    }

    public String getURL() {
        return "https://www.7-zip.org/download.html";
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
        if(date.equals("")){
            reReadData();
        }
        return date;
    }
}
