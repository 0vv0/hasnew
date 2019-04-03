package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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
    public void reReadData() throws IOException {


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

    }


}
