package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Ghostscript extends VersionedAppsImpl {
    {
        name = "Ghostscript";
    }

    @Override
    protected String getURL() {
        return "https://www.ghostscript.com/releases.html";
    }

    @Override
    public String getFileMask() {
        return "DPD GPL Ghostscript ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String searchText = "The latest AGPL release is ";
        Document doc = Jsoup.connect(getURL()).get();
        for (Element p : doc.getElementsByTag("p")) {
            String t = p.text();
            if (t != null && t.startsWith(searchText)) {
                version = p.getElementsByTag("a").first().text();
//                    System.out.println(version);
                date = t.substring(searchText.length() + version.length() + 1);
                return;
            }
        }

    }


}
