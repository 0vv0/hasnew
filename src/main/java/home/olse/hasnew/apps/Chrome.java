package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Chrome extends VersionedAppsImpl {
    {
        name = "Google Chrome";
    }

    @Override
    protected String getURL() {
        return "https://chromereleases.googleblog.com/search/label/Stable%20updates";
    }

    @Override
    public String getFileMask() {
        return "DPD Google Chrome ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String searchText = "Stable Channel Update for Desktop";
        Document doc = Jsoup.connect(getURL()).get();
        for (Element p : doc.getElementsByTag("h2")) {
            String t = p.text();
            if (t != null && t.startsWith(searchText)) {
                Elements els = p.parent().getElementsByTag("div");
                String s1 = "The stable channel has been updated to ";
                String tail1 = "for Windows, Mac";
                String s2 = "";
                String tail2 = "contains a number of fixes and improvements";
                for (Element el : els) {
                    if (el.text().contains(s1) && el.text().contains(tail1)) {
                        version = el.text().substring(s1.length(), el.text().indexOf(tail1));
                    } else if (el.text().contains(tail2)) {
                        int i = el.text().indexOf(tail2);
                        String temp = el.text().substring(0, i - 1);
                        int count = temp.split(" ").length;
                        version = temp.split(" ")[count-1];
                    }

                }
                date = p.nextElementSibling().text();
                return;
            }
        }

    }


}
