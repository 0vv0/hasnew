package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class the7Zip extends VersionedAppsImpl {
    {
        name = "7 Zip";
    }

    @Override
    public String getFileMask() {
        return "DPD 7-Zip ";
    }

    @Override
    public String getURL() {
        return "https://www.7-zip.org/download.html";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "Download 7-Zip ";

        Document doc = Jsoup.connect(getURL()).get();
        for (Element b : doc.getElementsByTag("b")) {
            if (b.text().startsWith(searchString)) {
//                Download 7-Zip 19.00 (2019-02-21) for Windows:
                version = b.text().substring(searchString.length(), searchString.length() + 5);
                date = b.text().substring(searchString.length() + 7, searchString.length() + 7 + 10);
                return;
            }
        }

    }


}
