import java.util.*;

public class DesignInMemoryFileSystem {

    class FileSystem {

        class File {
            Map<String, File> children = new HashMap<>();
            boolean isFile = false;;
            String content = "";
        }

        File root = null;
        public FileSystem() {
            root = new File();
        }

        public List<String> ls(String path) {
            List<String> res = new ArrayList<>();
            String name = "";
            File node = root;
            for (String dir : path.split("/")) {
                if (dir.length() == 0) continue;
                if (!node.children.containsKey(dir)) {
                    return res;
                }
                node = node.children.get(dir);
                name = dir;
            }
            if (node.isFile) {
                res.add(name);
            } else {
                for (String key : node.children.keySet()) {
                    res.add(key);
                }
            }
            Collections.sort(res);
            return res;
        }

        public void mkdir(String path) {
            File node = root;
            for (String dir : path.split("/")) {
                if (dir.length() == 0) continue;
                if (!node.children.containsKey(dir)) {
                    node.children.put(dir, new File());
                }
                node = node.children.get(dir);
            }
        }

        public void addContentToFile(String filePath, String content) {
            File node = root;
            for (String dir : filePath.split("/")) {
                if (dir.length() == 0) continue;
                if (!node.children.containsKey(dir)) {
                    node.children.put(dir, new File());
                }
                node = node.children.get(dir);
            }
            node.isFile = true;
            node.content += content;
        }

        public String readContentFromFile(String filePath) {
            File node = root;
            for (String dir : filePath.split("/")) {
                if (dir.length() == 0) continue;
                if (!node.children.containsKey(dir)) {
                    node.children.put(dir, new File());
                }
                node = node.children.get(dir);
            }
            return node.content;
        }
    }
}
