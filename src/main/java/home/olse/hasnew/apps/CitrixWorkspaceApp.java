package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.logging.Level;

public class CitrixWorkspaceApp extends VersionedAppsImpl {
    {
        name = "Citrix Workspace app";
    }

    @Override
    public String getFileMask() {
        return "DPD Citrix Workspace ";
    }

    @Override
    public String getURL() {
        return "https://www.citrix.com/downloads/workspace-app/windows/workspace-app-for-windows-latest.html";
    }

    @Override
    public void reReadData() {

        try {
            Document doc = Jsoup.connect(getURL()).get();
            for (Element el : doc.getElementsByTag("h1")) {
                if (el.text().startsWith(name)) {
                    date = el.nextElementSibling().text();
                    version = el.text().substring(name.length());
                    return;
                }
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }


}
