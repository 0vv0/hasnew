package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WinSCP extends VersionedAppsImpl {
    {
        name = "WinSCP";
    }

    @Override
    public String getFileMask() {
        return "DPD WinSCP ";
    }

    @Override
    public String getURL() {
        return "https://winscp.net/eng/download.php";
    }

    @Override
    public void reReadData() throws IOException {
        String searchString = "WinSCP ";
        String tail = " Download";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByTag("h1");
        if (els != null && els.size() > 0) {
            for (Element el : els) {
                if (el.text() != null && el.text().startsWith(searchString) && el.text().endsWith(tail)) {
                    int i = el.text().indexOf(searchString);
                    version = el.text().substring(i + searchString.length(), el.text().length() - tail.length());
                    date = " ";
                }
            }

        }
    }


}
