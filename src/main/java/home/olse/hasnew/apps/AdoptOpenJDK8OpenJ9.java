package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class AdoptOpenJDK8OpenJ9 extends VersionedAppsImpl {
    {
        name = "Adopt Open JDK 8 (JVM: OpenJ9)";
    }

    @Override
    protected String getURL() {
        return "https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=openj9";
    }

    @Override
    public String getFileMask() {
        return "DPD AdoptOpenJDK JDK ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String id = "dl-version-text";
        Document doc = Jsoup.connect(getURL()).get();
        Element el = doc.getElementById(id);
        if(el!=null&&el.text()!=null){
            version = el.text();
            date = " ";
        }

    }
}
