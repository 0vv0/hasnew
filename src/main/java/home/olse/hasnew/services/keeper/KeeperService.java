package home.olse.hasnew.services.keeper;

public interface KeeperService<K, V> {
    default V saveData(K key, V value){
        return null;
    }
    default V getData(K key){
        return null;
    }
}
