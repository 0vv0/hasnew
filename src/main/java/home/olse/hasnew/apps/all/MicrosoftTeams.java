package home.olse.hasnew.apps.all;

import home.olse.hasnew.apps.VersionedAppsImpl;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MicrosoftTeams extends VersionedAppsImpl {
    {
        name = "Microsoft Teams";
    }

    @Override
    protected String getURL() {
        return "https://teams.microsoft.com/desktopclient/update/1.2.00.0/windows/x64?ring=genera";
    }

    @Override
    public String getFileMask() {
        return "DPD Microsoft Teams ";
    }

    @Override
    public void reReadData() throws IOException {
//        name, date, value should be set here
        URLConnection conn = new URL(getURL()).openConnection();
        conn.connect();
        String result = "";
        try (Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A")) {
            result = s.hasNext() ? s.next() : "";
        }
        JSONObject jsonObject = new JSONObject(result);

        date = " ";
        version = jsonObject.getString("nugetPackagePath");
    }
}
