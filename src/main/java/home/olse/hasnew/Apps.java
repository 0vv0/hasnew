package home.olse.hasnew;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static home.olse.hasnew.AppsDynamicCompiler.compileFrom;

@Component
public class Apps {
    private static List<File> files(String path) {
        File f = new File(path);
        File[] files = f.listFiles(
                (dir, name) -> name != null && (name.endsWith(".java") || name.endsWith(".class"))

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
            VersionedApp app = compileFrom(file);
            return app;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
            Logger.getLogger(Apps.class.getName()).log(Level.INFO, e.toString());
        }
        return null;
    }
    private static VersionedApp compile(final String file) {
        try {
            VersionedApp app = compileFrom(file);
            return app;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            Logger.getLogger(Apps.class.getName()).log(Level.INFO, e.toString());
        }
        return null;
    }

    public static List<String> filesNames(String path) {
        List<File> files = files(path);
        List<String> list = new ArrayList<>(files.size());
        for (File file : files) {
            list.add(file.getName());
        }
        return list;
    }

    public static List<VersionedApp> apps(String path) {
        Map<File, VersionedApp> apps = compile(files(path));
        return new ArrayList<>(apps.values());
    }

    public VersionedApp getByFileName(String path, String fileName) {
        for (File file : files(path)) {
            if (file.getName().equals(fileName + ".java")) {
                return compile(file);
            } else if(file.getName().equals(fileName + ".class")){
                VersionedApp app;
                app = compile(file.getName());

                return app;
            }
        }
        return null;
    }

}
