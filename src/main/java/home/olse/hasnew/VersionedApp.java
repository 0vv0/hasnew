package home.olse.hasnew;

public interface VersionedApp{
    String getName();
    String getVersion();
    String getDate();
    default String getFileMask(){
        return null;
    }
}
