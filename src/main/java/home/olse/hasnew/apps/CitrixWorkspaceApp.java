package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedApp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CitrixWorkspaceApp implements VersionedApp {
    @Autowired
    private Logger logger;
    private String name = "Citrix Workspace App";
    private String version;
    private String date = "";

    public String getVersion(String webPageHTML) {
        Document doc = Jsoup.parse(webPageHTML);
        for (Element el : doc.getElementsByTag("h1")) {
            if(el.text().startsWith(name)){
                date = doc.getElementsByTag("h3").first().text();
                return el.text().substring(name.length());
            }
        }
        return "";
    }

    public String getURL() {
        return "https://www.citrix.com/downloads/workspace-app/windows/workspace-app-for-windows-latest.html";
    }

    public void reReadData() {
        try {
            Document doc = Jsoup.connect(getURL()).get();
            version = getVersion(doc.outerHtml());
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        reReadData();
        return version;
    }

    @Override
    public String getDate() {
        return date;
    }
}
