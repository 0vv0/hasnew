package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class VLC extends VersionedAppsImpl {
    {
        name = "VLC";
    }

    @Override
    public String getFileMask() {
        return "DPD VLC Media Player ";
    }

    @Override
    public String getURL() {
        return "https://www.videolan.org/vlc/";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "Version ";
        Document doc = Jsoup.connect(getURL()).get();
        Element element = doc.getElementById("downloadDetails");
        if (element != null) {
            if (element.text() != null && element.text().contains(searchString)) {
                int i = element.text().indexOf(searchString);
                version = element.text().substring(i + searchString.length(), i + searchString.length() + 5);
                date = " ";
            }

        }
    }


}
