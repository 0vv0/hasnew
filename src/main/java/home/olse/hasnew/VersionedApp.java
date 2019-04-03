package home.olse.hasnew;

public interface VersionedApp{
    String getName();
    String getVersion();
    String getDate();
    default String URL(){
        return "";
    };
    default String getFileMask(){
        return null;
    }
}
