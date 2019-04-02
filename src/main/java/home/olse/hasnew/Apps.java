package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Apps {
    @Value("${apps.path}")
    private String path;

    public String getPath() {
        return path;
    }

    @Autowired
    private Logger logger;

    private static List<File> files(String path) {
//        Logger.getLogger(Apps.class.getName()).log(Level.INFO, "path to apps classes: " + path);
        File f = new File(path);
        File[] files = f.listFiles(
                (dir, name) -> name != null && name.endsWith(".java")
        );
        if (files != null) {
            return Arrays.asList(files);
        } else {
            return new ArrayList<>(0);
        }

    }

    private static Map<File, VersionedApp> compile(final File[] files) {
        if (files != null) {
            Map<File, VersionedApp> apps = new HashMap<>(files.length);
            for (File file : files) {
                VersionedApp app = compile(file);
                if (app != null) {
                    apps.put(file, app);
                }
            }
            return apps;
        } else {
            return new HashMap<>(0);
        }
    }

    private static Map<File, VersionedApp> compile(final List<File> files) {
        if (files != null) {
            Map<File, VersionedApp> apps = new HashMap<>(files.size());
            for (File file : files) {
                VersionedApp app = compile(file);
                if (app != null) {
                    apps.put(file, app);
                }
            }
            return apps;
        } else {
            return new HashMap<>(0);
        }
    }

    private static VersionedApp compile(final File file) {
        try {
            VersionedApp app = AppsDynamicCompiler.compileFrom(file);
            return app;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
            Logger.getLogger(Apps.class.getName()).log(Level.INFO, e.toString());
        }
        return null;
    }


    public List<String> files() {
        List<File> files = files(path);
        List<String> list = new ArrayList<>(files.size());
        for (File file : files) {
            list.add(file.getName());
        }
        return list;
    }

    public List<VersionedApp> apps() {
        Map<File, VersionedApp> apps = compile(files(path));
//        List<VersionedApp> versionedAppList = new ArrayList<>(apps.values());
        return new ArrayList<>(apps.values());
    }

    public VersionedApp getByFileName(String fileName) {
        for (File file : files(path)) {
//            logger.log(Level.INFO, file.getName());
            if (file.getName().equals(fileName + ".java")) {
                return compile(file);
            }
        }
        return null;
    }


}
