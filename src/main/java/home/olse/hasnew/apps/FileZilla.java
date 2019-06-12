package home.olse.hasnew.apps;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FileZilla extends VersionedAppsImpl {
    {
        name = "FileZilla Client";
    }

    @Override
    public String getFileMask() {
        return "DPD FileZilla ";
    }

    @Override
    public String getURL() {
        return "https://filezilla-project.org/";
    }

    @Override
    public void reReadData() throws IOException {

            Document doc = Jsoup.connect(getURL()).get();
            Element element = doc.getElementsByTag("blockquote").first();
            Elements elements = element.getElementsByTag("h3");
            for (Element el : elements) {
                if (el.text().contains(name)) {
                    date = el.text().substring(0, 10);
                    version = el.text().substring(13 + name.length() + 1);
                    return;
                }
            }

    }


}
