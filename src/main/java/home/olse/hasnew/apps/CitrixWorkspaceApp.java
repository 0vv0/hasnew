package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

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
    public void reReadData() throws IOException {

        Document doc = Jsoup.connect(getURL()).get();
        for (Element el : doc.getElementsByTag("h1")) {
            if (el.text().startsWith(name)) {
                date = el.nextElementSibling().text();
                version = el.text().substring(name.length());
                return;
            }
        }

    }


}
