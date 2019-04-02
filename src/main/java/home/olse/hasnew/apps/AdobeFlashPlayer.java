package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;

public class AdobeFlashPlayer extends VersionedAppsImpl {
    {
        name = "Adobe Flash Player";
    }

    @Override
    public String getURL() {
        return "https://get.adobe.com/flashplayer/";
    }

    @Override
    public String getFileMask() {
        return "DPD Adobe Flash Player ";
    }

    @Override
    public void reReadData() {

        try {
            Document doc = Jsoup.connect(getURL()).get();
            if (doc.getElementById("AUTO_ID_columnleft_p_version") != null) {
                version = doc.getElementById("AUTO_ID_columnleft_p_version").text();
            }
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
