package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;

public class FireFox extends VersionedAppsImpl {
    {
        name = "Mozilla Firefox ESR";
    }

    @Override
    public String getFileMask() {
        return "DPD Mozilla Firefox ESR ";
    }

    @Override
    public String getURL() {
        return "https://www.mozilla.org/en-US/firefox/organizations/all/";
    }

    @Override
    public void reReadData() {
        try {
            Document doc = Jsoup.connect(getURL()).get();
            Element element = doc.getElementsByTag("html").first();
            String attrib = "data-esr-versions";
            if (element != null && element.hasAttr(attrib)) {
                version = element.attr(attrib);
                date = " ";
            }

        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }


}
