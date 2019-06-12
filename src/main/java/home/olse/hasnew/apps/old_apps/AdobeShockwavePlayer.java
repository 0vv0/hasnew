package home.olse.hasnew.apps.old_apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AdobeShockwavePlayer extends VersionedAppsImpl {
    {
        name = "Adobe Shockwave Player";
    }

    @Override
    public String getURL() {
        return "https://get.adobe.com/shockwave/";
    }

    @Override
    public String getFileMask() {
        return "DPD Adobe Shockwave Player ";
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
