package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TortoiseSVN extends VersionedAppsImpl {
    {
        name = "TortoiseSVN";
    }

    @Override
    public String getFileMask() {
        return "DPD TortoiseSVN ";
    }

    @Override
    public String getURL() {
        return "https://tortoisesvn.net/downloads.html";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "The current version is ";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByTag("h1");
        if (els != null && els.size() > 0) {
            for (Element el : els) {
                if (el.text() != null && el.text().startsWith(searchString)) {
                    version = el.text().substring(searchString.length());
                    date = " ";
                    return;
                }
            }

        }
    }


}
