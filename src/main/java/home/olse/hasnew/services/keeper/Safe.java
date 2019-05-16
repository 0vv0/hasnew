package home.olse.hasnew.services.keeper;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Safe<K, V> {
    private Map<K, V> safe = new HashMap<>();

    public V get(K key) {
        return safe.get(key);
    }

    public V put(K key, V value) {
        return safe.put(key, value);
    }
}
