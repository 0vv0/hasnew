package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Gimp extends VersionedAppsImpl {
    {
        name = "Gimp";
    }

    @Override
    protected String getURL() {
        return "https://www.gimp.org/downloads/";
    }

    @Override
    public String getFileMask() {
        return "DPD Gimp ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

            String searchText = "The current stable release of GIMP is ";
            Document doc = Jsoup.connect(getURL()).get();
            for (Element p : doc.getElementsByTag("p")) {
                String t = p.text();
                if (t != null && t.startsWith(searchText)) {
                    version = p.getElementsByTag("b").first().text();
//                    System.out.println(version);
                    date = t.substring(searchText.length() + version.length() + 1);
                    return;
                }
            }

    }


}
