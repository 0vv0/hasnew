package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Wireshark extends VersionedAppsImpl {
    {
        name = "Wireshark";
    }

    @Override
    public String getFileMask() {
        return "DPD Wireshark ";
    }

    @Override
    public String getURL() {
        return "https://www.wireshark.org/download.html";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "Stable Release (";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByTag("a");
        if (els != null && els.size() > 0) {
            for (Element el : els) {
                if (el.text() != null && el.text().startsWith(searchString)) {
                    version = el.text().substring(searchString.length(), el.text().length()-1);
                    date = " ";
                    return;
                }
            }

        }
    }


}
