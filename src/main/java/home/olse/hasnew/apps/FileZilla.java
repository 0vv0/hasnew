package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;

public class FileZilla extends VersionedAppsImpl {
    public String getURL() {
        return "https://filezilla-project.org/";
    }

    public void reReadData() {
        name = "FileZilla Client";
        try {
            Document doc = Jsoup.connect(getURL()).get();
            Element element = doc.getElementsByTag("blockquote").first();
            Elements elements = element.getElementsByTag("h3");
            for (Element el : elements) {
                if (el.text().contains(name)) {
                    date = el.text().substring(0, 10);
                    version = el.text().substring(13 + name.length() + 1);
                    return;
                }
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }

}
