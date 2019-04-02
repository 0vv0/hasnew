package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;

public class AdobeShockwavePlayer extends VersionedAppsImpl {
    public String getURL() {
        return "https://get.adobe.com/shockwave/";
    }

    public void reReadData() {
        name = "Adobe Shockwave Player";
        try {
            Document doc = Jsoup.connect(getURL()).get();
            if (doc.getElementById("AUTO_ID_columnleft_p_version") != null) {
                version = doc.getElementById("AUTO_ID_columnleft_p_version").text();
            }
            date = " ";
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }

    @Override
    public String getFileMask() {
        return "DPD Adobe Shockwave Player ";
    }
}
