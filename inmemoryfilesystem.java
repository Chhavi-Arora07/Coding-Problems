import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inmemoryfilesystem {

    static class FileSystem {

        private class Node {
            boolean isFile;
            String content = "";
            Map<String, Node> children = new HashMap<>();
        }

        private final Node root = new Node();

        public List<String> ls(String path) {
            Node node = traverse(path);
            if (node.isFile) {
                return List.of(path.substring(path.lastIndexOf("/") + 1));
            }
            List<String> res = new ArrayList<>(node.children.keySet());
            Collections.sort(res);
            return res;
        }

        public void mkdir(String path) {
            String[] parts = path.split("/");
            Node cur = root;
            for (int i = 1; i < parts.length; i++) {
                cur.children.putIfAbsent(parts[i], new Node());
                cur = cur.children.get(parts[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] parts = filePath.split("/");
            Node cur = root;

            for (int i = 1; i < parts.length - 1; i++) {
                cur.children.putIfAbsent(parts[i], new Node());
                cur = cur.children.get(parts[i]);
            }

            String file = parts[parts.length - 1];
            cur.children.putIfAbsent(file, new Node());
            Node node = cur.children.get(file);
            node.isFile = true;
            node.content += content;
        }

        public String readContentFromFile(String filePath) {
            return traverse(filePath).content;
        }

        private Node traverse(String path) {
            String[] parts = path.split("/");
            Node cur = root;
            for (int i = 1; i < parts.length; i++) {
                cur = cur.children.get(parts[i]);
            }
            return cur;
        }
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
    }
}
