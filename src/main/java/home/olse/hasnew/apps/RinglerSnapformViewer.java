package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class RinglerSnapformViewer extends VersionedAppsImpl {
    {
        name = "Ringler Snapform Viewer";
    }

    @Override
    public String getFileMask() {
        return "DPD Ringler Snapform Viewer ";
    }

    @Override
    public String getURL() {
        return "http://www.snapform.com/en/downloads/";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = " for Windows 64bit (Version SFViewer_";
        Document doc = Jsoup.connect(getURL()).get();
        for (Element e : doc.body().getElementsByTag("li")) {
            if (e.text().contains(searchString)) {
                int i = e.text().indexOf(searchString);
                version = e.text().substring(i + searchString.length(), e.text().length()-1);
                date = " ";
                return;
            }
        }

    }


}
