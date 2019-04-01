package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public abstract class VersionedAppsImpl implements VersionedApp {
    @Autowired
    protected Logger logger;
    protected String name = "";
    protected String version = "";
    protected String date = "";

    @Override
    public String getName() {
        if (name.equals("")) {
            reReadData();
        }
        return this.name;
    }

    @Override
    public String getVersion() {
        if (version.equals("")) {
            reReadData();
        }
        return this.version;
    }

    @Override
    public String getDate() {
        if (date.equals("")) {
            reReadData();
        }
        return this.date;
    }

    protected abstract void reReadData();

    protected abstract String getURL();
}
