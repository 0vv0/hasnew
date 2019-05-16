package home.olse.hasnew.apps.all;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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
    public void reReadData() throws IOException {

        Document doc = Jsoup.connect(getURL()).get();
        if (doc.getElementById("AUTO_ID_columnleft_p_version") != null) {
            version = doc.getElementById("AUTO_ID_columnleft_p_version").text();
        }
        date = " ";

    }


}
