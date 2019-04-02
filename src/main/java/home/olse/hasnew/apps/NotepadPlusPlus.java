package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;

public class NotepadPlusPlus extends VersionedAppsImpl {
    {
        name = "Notepad++";
    }

    @Override
    public String getFileMask() {
        return "DPD Notepad++ ";
    }

    @Override
    public String getURL() {
        return "https://notepad-plus-plus.org/download/";
    }

    @Override
    public void reReadData() {
        try {
            Document doc = Jsoup.connect(getURL()).get();
            Element element = doc.getElementById("main");
            String searchVersionString = "Download Notepad++ ";
            String searchDateString = "Release Date: ";
            Elements els = element.children();
            for (Element el : els) {
                if (el.text() == null ||
                        !(
                                el.text().startsWith(searchVersionString) ||
                                        el.text().startsWith(searchDateString))
                ) {
                    continue;
                }
                if (el.text().startsWith(searchVersionString)) {
                    version = el.text().substring(searchVersionString.length());
                }
                if (el.text().startsWith(searchDateString)) {
                    date = el.text().substring(searchDateString.length());
                }
            }


        } catch (IOException e) {
            if (logger == null) {
                System.out.println(e);
            } else {
                logger.log(Level.INFO, e.getMessage() != null ? e.getMessage() : e.toString());
            }
            version = e.getMessage().substring(0, e.getMessage().length() > 100 ? 100 : e.getMessage().length());
        }
    }


}
