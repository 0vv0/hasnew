package home.olse.hasnew.services;

import home.olse.hasnew.VersionedApp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DPDInfoService {

    @Value("${dpd.path}")
    private String dpdPath;

    public String getDpdPath() {
        return dpdPath;
    }

    public void setDpdPath(String dpdPath) {
        this.dpdPath = dpdPath;
    }

    public List<String> getDPDs(VersionedApp app) {
        return getDPDs(dpdPath, app);
    }

    private static List<String> getDPDs(String path, VersionedApp app) {
//        Logger.getLogger(DPDInfoService.class.getName()).log(Level.INFO, path);
        File f = new File(path);
//        System.out.println(Arrays.toString(f.list()));
        File[] files = f.listFiles((dir, name) -> name != null &&
                app != null &&
                app.getFileMask() != null &&
                name.startsWith(app.getFileMask()));

        if (files != null) {
            List<String> fileNames = new ArrayList<>(files.length);
            for (File file : files) {
                fileNames.add(file.getName());
            }
            return fileNames;
        } else {
            return new ArrayList<>(0);
        }
    }

    public String getLastVersion(VersionedApp app) {
        return getLastVersion(dpdPath, app);
    }

    private static String getLastVersion(String path, VersionedApp app) {
        List<String> fileNames = getDPDs(path, app);
        if (fileNames.size() > 0) {
            return fileNames.get(fileNames.size() - 1).substring(app.getFileMask().length());
        } else {
            return "";
        }
    }
}
