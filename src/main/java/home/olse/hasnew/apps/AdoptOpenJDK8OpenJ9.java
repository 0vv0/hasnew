package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AdoptOpenJDK8OpenJ9 extends VersionedAppsImpl {
    {
        name = "Adopt Open JDK 8 (JVM: OpenJ9)";
    }

    @Override
    protected String getURL() {
        return "https://api.adoptopenjdk.net/v2/info/releases/openjdk8?release=latest&openjdk_impl=openj9";
    }

    @Override
    public String getFileMask() {
        return "DPD AdoptOpenJDK JDK ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String searchString = "release_name";
        Document doc = Jsoup.connect(getURL()).ignoreContentType(true).get();
        String body = doc.text();
        if (body != null) {
            String[] jsonPairs = body.substring(1, body.length() - 1).split(",");
            String releasePair = null;
            for (String jsonpair : jsonPairs) {
                if (jsonpair.contains(searchString)) {
                    releasePair = jsonpair;
                    break;
                }
            }
            if (releasePair != null) {
                String[] jsonPair = releasePair.split(":");
                if (jsonPair.length == 2) {
                    version = jsonPair[1];
                    version = version.trim();
                    if(version.startsWith("\"")){
                        version = version.substring(1);
                    }
                    if(version.endsWith("\"")){
                        version = version.substring(0, version.length()-1);
                    }
                    date = " ";
                }
            }
        }
    }
}
