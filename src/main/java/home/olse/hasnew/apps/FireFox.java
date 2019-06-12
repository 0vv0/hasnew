package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FireFox extends VersionedAppsImpl {
    {
        name = "Mozilla Firefox ESR";
    }

    @Override
    public String getFileMask() {
        return "DPD Mozilla Firefox ESR ";
    }

    @Override
    public String getURL() {
        return "https://www.mozilla.org/en-US/firefox/organizations/all/";
    }

    @Override
    public void reReadData() throws IOException {

        Document doc = Jsoup.connect(getURL()).get();
        Element element = doc.getElementsByTag("html").first();
        String attrib = "data-esr-versions";
        if (element != null && element.hasAttr(attrib)) {
            version = element.attr(attrib);
            date = " ";
        }


    }


}
