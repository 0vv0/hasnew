package home.olse.hasnew.apps.all;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CanneverbeCDBurner extends VersionedAppsImpl {
    {
        name = "Canneverbe CDBurner";
    }

    @Override
    public String getFileMask() {
        return "DPD Canneverbe CDBurnerXP ";
    }

    @Override
    public String getURL() {
        return "https://cdburnerxp.se/en/download";
    }

    @Override
    public void reReadData() throws IOException {

        Document doc = Jsoup.connect(getURL()).get();
        version = doc.getElementsByTag("small").get(1).text();
        date = " ";

    }


}
