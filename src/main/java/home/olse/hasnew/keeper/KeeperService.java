package home.olse.hasnew.keeper;

import org.springframework.stereotype.Service;

@Service
public interface KeeperService<K, V> {
    default V saveData(K key, V value){
        return null;
    }
    default V getData(K key){
        return null;
    }
}
