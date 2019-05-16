package home.olse.hasnew.services;

import home.olse.hasnew.VersionedApp;
import home.olse.hasnew.services.keeper.KeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VersionSaver {
    @Autowired
    private KeeperService<String, String> keeper;

    public String getSavedVersion(VersionedApp app) {
        String s = keeper.getData(app.getName());
        return s == null ? "unknown" : s;
    }

    public String saveVersion(String name, String version) {
        return keeper.saveData(name, version);
    }
}
