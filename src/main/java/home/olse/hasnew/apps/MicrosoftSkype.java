package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class MicrosoftSkype extends VersionedAppsImpl {
    {
        name = "MicroSoft Skype";
    }

    @Override
    protected String getURL() {
        return "https://get.skype.com/go/getskype-skypeforwindows";
    }

    @Override
    public String getFileMask() {
        return "DPD Microsoft Skype ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here

        String responseHeader = "location";
        String beginWith = "Skype-";
        String endWith = ".exe";
        Connection.Response response = Jsoup.connect(getURL())
                .ignoreContentType(true)
                .followRedirects(false)
                .execute();
//         https://download.skype.com/s4l/download/win/Skype-8.50.0.38.exe
        String linkToCurrentSkypeVersion = response.header(responseHeader);
        if (linkToCurrentSkypeVersion.contains("/")) {
            String[] splitedLink = linkToCurrentSkypeVersion.split("/");
            String ver = splitedLink[splitedLink.length - 1];//Skype-8.50.0.38.exe
            if (ver.contains(beginWith)) {
                ver = ver.substring(beginWith.length());
            }
            if (ver.contains(endWith)) {
                ver = ver.substring(0, ver.length() - endWith.length());
            }
            version = ver;
        } else {
            version = linkToCurrentSkypeVersion != null ? linkToCurrentSkypeVersion : "null";
        }
        date = " ";
    }
}
