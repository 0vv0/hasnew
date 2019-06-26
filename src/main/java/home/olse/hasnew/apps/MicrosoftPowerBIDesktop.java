package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MicrosoftPowerBIDesktop extends VersionedAppsImpl {
    {
        name = "MS Power BI Desktop";
    }

    @Override
    protected String getURL() {
        return "https://www.microsoft.com/en-us/download/details.aspx?id=45331";
    }

    @Override
    public String getFileMask() {
        return "DPD Microsoft Power BI Desktop ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String searchVersionText = "Version:";
        String searchDateText = "Date Published:";
        Document doc = Jsoup.connect(getURL()).get();
        for (Element p : doc.getElementsByTag("div")) {
            if (p.text()!= null && p.text().contains(searchVersionText)) {
                if(p.nextElementSibling()!=null&&p.nextElementSibling().tagName().toLowerCase().equals("p")){
                    version = p.nextElementSibling().text()!=null?p.nextElementSibling().text():" ";
                }
            }
            if (p.text()!= null && p.text().contains(searchVersionText)) {
                if(p.nextElementSibling()!=null&&p.nextElementSibling().tagName().toLowerCase().equals("p")){
                    date = p.nextElementSibling().text()!=null?p.nextElementSibling().text():" ";
                }
            }
        }

    }
}
