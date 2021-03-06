package home.olse.hasnew.services.keeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
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
