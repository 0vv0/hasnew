package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SuisseID extends VersionedAppsImpl {
    {
        name = "SuisseID";
    }

    @Override
    public String getFileMask() {
        return "DPD Post SuisseID ";
    }

    @Override
    public String getURL() {
        return "https://postsuisseid.ch/en/support/application";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "Release date ";

        Document doc = Jsoup.connect(getURL()).get();
        Element element = doc.getElementById("psi_content");
        if (element != null) {
            Elements els = element.getElementsByTag("table");
            if (els != null && els.size() > 0) {
                for (Element e : els.first().getElementsByTag("td")) {
                    if (e.text().startsWith(searchString)) {
                        date = e.text().substring(searchString.length());
                        if (e.nextElementSibling() != null && e.nextElementSibling().text().startsWith("Version ")) {
                            version = e.nextElementSibling().text().substring("Version ".length());
                        }
                        return;
                    }
                }
            }
        } else {
            date = " ";
        }
    }


}
