import java.util.LinkedHashMap;

class LRUCache {

    LinkedHashMap<Integer,Integer> map;
    int maxLen;

    public LRUCache(int capacity) {
        this.maxLen = capacity;
        map = new LinkedHashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        int value = map.get(key);
        map.remove(key);
        map.putFirst(key,value);
        return value;
    }
    
    public void put(int key, int value) {
        map.remove(key);
        map.putFirst(key,value);
        if (map.size() > maxLen) {
            map.pollLastEntry();
        }
        
    }
}