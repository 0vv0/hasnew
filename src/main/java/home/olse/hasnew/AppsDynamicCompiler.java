package home.olse.hasnew;

import net.openhft.compiler.CompilerUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AppsDynamicCompiler {
    private static Logger logger = Logger.getLogger(AppsDynamicCompiler.class.getName());

    public static VersionedApp compileFrom(final File file) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        // dynamically you can call
        String className = "home.olse.hasnew.apps." + file.getName()
                .replace(".class", "")
                .replace(".java", "");
        logger.log(Level.INFO, "className is " + className);

        String javaCode = new String(Files.readAllBytes(file.toPath()));
        logger.log(Level.INFO, "try to compile class:" + file.getName());

        Class aClass = CompilerUtils.CACHED_COMPILER
                .loadFromJava(AppsDynamicCompiler.class.getClassLoader(), className, javaCode);

        return (VersionedApp) aClass.newInstance();
    }

    public static VersionedApp compileFrom(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (VersionedApp) Class.forName("home.olse.hasnew.apps." + className).newInstance();
    }


}