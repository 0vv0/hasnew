package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class PuTTY extends VersionedAppsImpl {
    {
        name = "PuTTY";
    }

    @Override
    public String getFileMask() {
        return "DPD Putty Suite ";
    }

    @Override
    public String getURL() {
        return "https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "This page contains download links for the latest released version of PuTTY. Currently this is ";
        String dateMarker = ", released on ";
        Document doc = Jsoup.connect(getURL()).get();
        for (Element e : doc.body().getElementsByTag("p")) {
            if (e.text().startsWith(searchString)) {
                version = e.text().substring(searchString.length(), searchString.length() + 4);
                if (e.text().contains(dateMarker)) {
                    int i = e.text().indexOf(dateMarker);
                    date = e.text().substring(i + dateMarker.length());
                }
                return;
            }
        }

    }


}
