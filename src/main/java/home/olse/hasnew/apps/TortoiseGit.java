package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TortoiseGit extends VersionedAppsImpl {
    {
        name = "TortoiseGit";
    }

    @Override
    public String getFileMask() {
        return "DPD TortoiseGIT ";
    }

    @Override
    public String getURL() {
        return "https://tortoisegit.org/download/";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "The current stable version is: ";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByTag("strong");
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
