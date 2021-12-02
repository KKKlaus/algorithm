package Oracle;

import java.util.HashMap;

public class DesignFileSystem {

    class FileSystem {

        HashMap<String, Integer> map;
        public FileSystem() {
            map = new HashMap<>();
            map.put("", -1);
        }

        public boolean createPath(String path, int value) {
            int idx = path.lastIndexOf("/");
            String parent = path.substring(0, idx);
            if (!map.containsKey(parent)) return false;
            if (map.containsKey(path)) return false;
            map.put(path, value);
            return true;
        }

        public int get(String path) {
            return map.getOrDefault(path, -1);
        }
    }
}
