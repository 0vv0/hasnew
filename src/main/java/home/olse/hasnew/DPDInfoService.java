package home.olse.hasnew;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DPDInfoService {
    public static List<String> getDPDs(String path, VersionedApp app) {
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

    public static String getLastVersion(String path, VersionedApp app) {
        List<String> fileNames = getDPDs(path, app);
        if (fileNames.size() > 0) {
            return fileNames.get(fileNames.size() - 1).substring(app.getFileMask().length());
        } else {
            return "";
        }
    }
}
