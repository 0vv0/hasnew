package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class RinglerSnapformViewer extends VersionedAppsImpl {
    {
        name = "Ringler Snapform Viewer";
    }

    @Override
    public String getFileMask() {
        return "DPD Ringler Snapform Viewer ";
    }

    @Override
    public String getURL() {
        return "http://download.snapform.com/redirect/download.asp?LINK_ID=100230";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String responseHeader = "location";
        String beginWith = "SFViewer_x64_";
        String endWith = ".exe";
        Connection.Response response = Jsoup.connect(getURL())
                .ignoreContentType(true)
                .followRedirects(false)
                .execute();
//         Location: http://mirror1.steuersoftware.ch/snapform/SFViewer_x64_1_7_39.exe
        String linkToCurrentVersion = response.header(responseHeader);
        if (linkToCurrentVersion.contains("/")) {
            String[] splitedLink = linkToCurrentVersion.split("/");
            String ver = splitedLink[splitedLink.length - 1];//SFViewer_x64_1_7_39.exe
            if (ver.contains(beginWith)) {
                ver = ver.substring(beginWith.length());
            }
            if (ver.contains(endWith)) {
                ver = ver.substring(0, ver.length() - endWith.length());
            }
            version = ver;
        } else {
            version = linkToCurrentVersion != null ? linkToCurrentVersion : "null";
        }
        date = " ";
    }


}
