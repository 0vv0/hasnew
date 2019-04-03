package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Greenshot extends VersionedAppsImpl {
    {
        name = "Greenshot";
    }

    @Override
    public String getFileMask() {
        return "DPD Greenshot ";
    }

    @Override
    public String getURL() {
        return "https://getgreenshot.org/downloads/";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "Greenshot-RELEASE-";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByTag("div");
        if (els != null && els.size() > 0) {
            for (Element el : els) {
                if (el.text() != null && el.text().contains(searchString)) {
                    int i = el.text().indexOf(searchString);
                    version = el.text().substring(i + searchString.length(), i + searchString.length() + 8);
                    i = i + searchString.length() + version.length() +1;
                    date = el.text().substring(i, i+ "01 Jan 0000".length()  );
                    return;
                }
            }

        }
    }


}
