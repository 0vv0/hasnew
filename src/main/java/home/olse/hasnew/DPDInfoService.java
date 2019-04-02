package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DPDInfoService {
    @Value("${dpd.path}")
    private String path;

    public String getPath() {
        return path;
    }

    public List<String> getDPDs(VersionedApp app) {
        File f = new File(path);
        File[] files = f.listFiles((dir, name) ->
                name != null &&
                app != null &&
                app.getFileMask() != null &&
                name.contains(app.getFileMask()));

        if(files!=null) {
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
        List<String> fileNames = getDPDs(app);
        if(fileNames.size()>0) {
            return fileNames.get(fileNames.size() - 1).substring(app.getFileMask().length());
        } else {
            return "";
        }
    }
}
