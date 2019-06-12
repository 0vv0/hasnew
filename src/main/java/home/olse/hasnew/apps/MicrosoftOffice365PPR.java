package home.olse.hasnew.apps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MicrosoftOffice365PPR extends VersionedAppsImpl {
    {
        name = "MS Office 365 P+R";
    }

    @Override
    protected String getURL() {
        return "https://docs.microsoft.com/en-us/officeupdates/update-history-office365-proplus-by-date";
    }

    @Override
    public String getFileMask() {
        return "DPD Microsoft Office 365 ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here
        String searchString = "Semi-Annual";
        String absentString = "Targeted";

        Document doc = Jsoup.connect(getURL()).get();
        for (Element p : doc.getElementsByTag("table")) {
            Element tBody = p.getElementsByTag("tbody").first();
            for (Element tr : tBody.getElementsByTag("tr")) {
                Elements tds = tr.getElementsByTag("td");
                if (tds.size() == 5) {
                    if (tds.get(0).text().contains(searchString) && !tds.get(0).text().contains(absentString)) {
                        version += "\n" + tds.get(2).text();
                        date += "\n" + tds.get(3).text();
                    }
                }
            }
        }
//        remove first NewLine symbol if any
        if (version.startsWith("\n")) {
            version = version.substring(1);
        }
        if (date.startsWith("\n")) {
            date = date.substring(1);
        }
    }
}
