package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Keepass extends VersionedAppsImpl {
    {
        name = "KeePass";
    }

    @Override
    public String getFileMask() {
        return "DPD KeePass ";
    }

    @Override
    protected String getURL() {
        return "https://keepass.info/index.html";
    }


    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String searchText = "KeePass 2";
        Document doc = Jsoup.connect(getURL()).get();
        for (Element p : doc.getElementsByTag("a")) {
            String t = p.text();
            String tail = " released";
            if (t != null && t.startsWith(searchText) && t.endsWith(tail)) {
                int i = t.indexOf(tail);
                version = t.substring(searchText.length() - 1, i);
//                    System.out.println(version);
                if (p.nextElementSibling() != null && p.nextElementSibling().nextElementSibling() != null) {
                    date = p.nextElementSibling().nextElementSibling().text();
                } else {
                    date = " ";
                }
                return;
            }
        }

    }


}
