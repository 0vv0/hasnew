package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Level;
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
            reReadDataWithIOCatch();
        }
        return this.name;
    }

    @Override
    public String getVersion() {
        if (version.equals("")) {
            reReadDataWithIOCatch();
        }
        return this.version;
    }

    @Override
    public String getDate() {
        if (date.equals("")) {
            reReadDataWithIOCatch();
        }
        return this.date;
    }

    protected abstract void reReadData() throws IOException;

    private void reReadDataWithIOCatch(){
        try {
            reReadData();
        } catch (IOException e) {
            if (logger == null) {
                System.out.println(e);
            } else {
                logger.log(Level.INFO, e.getMessage() != null ? e.getMessage() : e.toString());
            }
            version = e.getMessage().substring(0, e.getMessage().length() > 25 ? 25 : e.getMessage().length());
        }
    }

    protected abstract String getURL();

    @Override
    public String URL() {
        return getURL();
    }
}
