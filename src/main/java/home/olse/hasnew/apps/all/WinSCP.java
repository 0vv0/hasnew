package home.olse.hasnew.apps.all;

import home.olse.hasnew.apps.VersionedAppsImpl;
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
        String searchString = "/download/WinSCP-";
        String tail = "-Setup.exe";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByAttributeValueStarting("href", searchString);
        if (els != null && els.size() > 0) {
            for (Element el : els) {
                if (el.attr("href").endsWith(tail)) {
                    version = el.attr("href").substring(searchString.length(), el.attr("href").length() - tail.length());
                    date = " ";
                }
            }

        }
    }


}
