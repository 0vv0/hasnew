package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DbVisualizer extends VersionedAppsImpl {
    {
        name = "DbVis DbVisualizer";
    }

    @Override
    public String getFileMask() {
        return "DPD DbVis DbVisualizer ";
    }

    @Override
    public String getURL() {
        return "https://www.dbvis.com/download";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "Latest Version: ";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByAttributeValue("name", "selectedVersion");
        if (els != null && els.size() > 0) {
            for (Element el : els) {
                if (el.children() != null) {
                    for (Element child : el.children()) {
                        if (child.text() != null && child.text().startsWith(searchString)) {
                            version = el.text().substring(searchString.length(), searchString.length() + 7);
                            date = el.text().substring(searchString.length() + 8, searchString.length() + 8 + 10);
                            return;
                        }
                    }

                }
            }

        } else {
            date = " ";
        }
    }


}
