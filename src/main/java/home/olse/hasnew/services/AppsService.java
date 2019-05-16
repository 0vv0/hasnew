package home.olse.hasnew.services;

import home.olse.hasnew.VersionedApp;
import home.olse.hasnew.apps.Apps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AppsService {
    private Apps apps;

    public AppsService(@Value("${apps.list}") String[] appsList) {
        this.apps = new Apps(appsList);
    }


    public List<String> names() {
        return new ArrayList<>(apps.getAll().keySet());
    }

    public VersionedApp getByName(String name) {
        return apps.getAll().get(name);
    }

    public List<VersionedApp> getList() {
        return new ArrayList<>(apps.getAll().values());
    }

}
