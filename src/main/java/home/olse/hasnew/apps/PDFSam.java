package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class PDFSam extends VersionedAppsImpl {
    {
        name = "PDFSam";
    }

    @Override
    public String getFileMask() {
        return "DPD PDFsam Basic ";
    }

    @Override
    public String getURL() {
        return "https://pdfsam.org/downloads/";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "PDFsam Basic";

        Document doc = Jsoup.connect(getURL()).get();
        for (Element e : doc.getElementsByTag("h4")) {
            if (e.text().startsWith(searchString) && e.nextElementSibling() != null) {
                version = e.nextElementSibling().text();
                date = " ";
                return;
            }
        }

    }


}
