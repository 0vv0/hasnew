package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.logging.Level;

public class Gimp extends VersionedAppsImpl {
    {
        name = "Gimp";
    }

    @Override
    protected String getURL() {
        return "https://www.gimp.org/downloads/";
    }


    @Override
    public void reReadData() {
//        name, date, value should be set here
        try {
            String searchText = "The current stable release of GIMP is ";
            Document doc = Jsoup.connect(getURL()).get();
            for (Element p : doc.getElementsByTag("p")) {
                String t = p.text();
                if (t != null && t.startsWith(searchText)) {
                    version = p.getElementsByTag("b").first().text();
//                    System.out.println(version);
                    date = t.substring(searchText.length()+version.length()+1);
                    return;
                }
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }

    @Override
    public String getFileMask() {
        return "DPD Gimp ";
    }
}
