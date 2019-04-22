package home.olse.hasnew.keeper;

import org.springframework.beans.factory.annotation.Autowired;


public class KeeperServiceImpl<K, V> implements KeeperService<K, V> {
    @Autowired
    private Safe<K, V> safe;

    @Override
    public V saveData(K key, V value) {
        return safe.put(key, value);
    }

    @Override
    public V getData(K key) {
        return safe.get(key);
    }
}
