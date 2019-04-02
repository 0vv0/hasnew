package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;

public class ITunes extends VersionedAppsImpl {
    {
        name = "iTunes";
    }

    @Override
    public String getFileMask() {
        return "DPD Apple iTunes ";
    }

    @Override
    public String getURL() {
        return "https://support.apple.com/en-us/HT201222";
    }

    @Override
    public void reReadData() {

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
            if (logger == null) {
                System.out.println(e);
            } else {
                logger.log(Level.INFO, e.getMessage() != null ? e.getMessage() : e.toString());
            }
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }


}
