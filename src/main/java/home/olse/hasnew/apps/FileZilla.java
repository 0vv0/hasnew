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

public class FileZilla implements VersionedApp {
    @Autowired
    private Logger logger;
    private String name = "FileZilla Client";
    private String version;
    private String date = "";

    public String getVersion(String webPageHTML) {
        Document doc = Jsoup.parse(webPageHTML);
        Element element = doc.getElementsByTag("blockquote").first();
        Elements elements = element.getElementsByTag("h3");
        for (Element el : elements) {
            if (el.text().contains(name)) {
                date = el.text().substring(0, 10);
                return el.text().substring(13 + name.length() + 1);
            }
        }
        return "";
    }

    public String getURL() {
        return "https://filezilla-project.org/";
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
