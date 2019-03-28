package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Apps {
    @Value("${apps.path}")
    private String path;


    @Autowired
    private Logger logger;

    private List<VersionedApp> appsList = new ArrayList<>();

    public List<VersionedApp> getList() {

        appsList.clear();

        logger.log(Level.INFO, "path to apps classes: " + path);

        File f = new File(path);
        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    VersionedApp app = AppsDynamicCompiler.compileFrom(file);
                    appsList.add(app);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
                    logger.log(Level.WARNING, e.toString());
                }
            }
        }

        return appsList;
    }
}
