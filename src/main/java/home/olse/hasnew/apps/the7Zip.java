package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.logging.Level;

public class the7Zip extends VersionedAppsImpl {
    private String searchString = "Download 7-Zip ";

    public String getURL() {
        return "https://www.7-zip.org/download.html";
    }

    public void reReadData() {
        name = "7 Zip";
        try {
            Document doc = Jsoup.connect(getURL()).get();
            for (Element b : doc.getElementsByTag("b")) {
                if(b.text().startsWith(searchString)){
//                Download 7-Zip 19.00 (2019-02-21) for Windows:
                    version = b.text().substring(searchString.length(), searchString.length()+5);
                    date = b.text().substring(searchString.length()+7, searchString.length()+7+10);
                    return ;
                }
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length()>100?100:e.getMessage().length());
        }
    }

    @Override
    public String getFileMask() {
        return "DPD 7-Zip ";
    }
}
