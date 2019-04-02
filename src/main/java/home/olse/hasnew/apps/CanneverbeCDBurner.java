package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;

public class CanneverbeCDBurner extends VersionedAppsImpl {
    {
        name = "Canneverbe CDBurner";
    }

    @Override
    public String getFileMask() {
        return "DPD Canneverbe CDBurnerXP ";
    }

    @Override
    public String getURL() {
        return "https://cdburnerxp.se/en/download";
    }

    @Override
    public void reReadData() {

        try {
            Document doc = Jsoup.connect(getURL()).get();
            version = doc.getElementsByTag("small").get(1).text();
            date = " ";
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
