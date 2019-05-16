package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedApp;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Apps {
    private volatile Map<String, VersionedApp> all;

    private String[] appsClasses;

    public Apps(String[] appsClasses) {
        this.appsClasses = appsClasses;
    }

    private static Logger logger = Logger.getLogger(Apps.class.getName());

    public Map<String, VersionedApp> getAll() {
        if (all == null) {
            synchronized (Apps.class) {
                if (all == null) {
                    all = loadList();
                }
            }
        }
        return all;
    }

    private Map<String, VersionedApp> loadList() {
        if (appsClasses == null) {
            return new HashMap<>(0);
        } else {
            Map<String, VersionedApp> apps = new HashMap<>(appsClasses.length);
            for (String appsClass : appsClasses) {
                VersionedApp vApp = loadClass(this.getClass().getPackage().getName() + ".all." + appsClass);
                if (vApp != null) {
                    apps.put(appsClass, vApp);
                }
            }
            return apps;
        }
    }


    private static VersionedApp loadClass(String fqcn) {
        try {
            Class cls = Class.forName(fqcn);
            return (VersionedApp) cls.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
