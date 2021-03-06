package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;

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
        String[] jsonObject = result.split(":");
	if(jsonObject.length>3){
		result = jsonObject[3];
		String[] subJson = result.split("/");

		if(subJson.length>4){
			result = subJson[4];
		}
	}

        date = " ";
        version = result;
    }
}
