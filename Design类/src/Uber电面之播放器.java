import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class Uber电面之播放器 {

    /*
    "Jay Chou", "ABCDE"
    "Maroon 5", "Test"
    "Marron 5", "Sugar"
    "Tylor Swift", "Blank Space"
    "JJ lin", "HAHAHA"

    next():
    previous():
    repeat_song() : toggle button
    repeat_list() : toggle button
    filter() : filter by Singer

     */

    class MusicPlayer {

        Node head = new Node();
        Node tail = new Node();
        Node cur;
        boolean repeatSong = false;
        boolean repeatList = false;

        HashMap<String, Node> map = new HashMap<>();  // key(singer) - Node
        HashMap<String, Node> idToNodeMap = new HashMap<>();
        public MusicPlayer() {
            head.next = tail;
            tail.prev = head;
            cur = head;
        }

        // return current music
        public String next() {
            if (idToNodeMap.size() == 0) return "No Music";
            if (!repeatSong) {
                if (cur == tail.prev) {
                    if (repeatList) cur = head.next;
                    else return "To The End";
                } else {
                    cur = cur.next;
                }
                if (!singerSet.contains(cur.singer)) {
                    return next();
                }
            }
            return cur.singer + "," + cur.song;
        }

        public String previous() {
            if (idToNodeMap.size() == 0) return "No Music";
            if (!repeatSong) {
                if (cur == head.next) {
                    if (repeatList) cur = tail.prev;
                    else return "To the Head";
                } else {
                    cur = cur.prev;
                }
            }
            return cur.singer + "," + cur.song;
        }

        // Filter 有两种想法：
        // （1）完整list跳过其余的，作用在next / previous上(这两个方法复杂度为O(n)), 这样导致用户体验差
         HashSet<String> singerSet = new HashSet<>(); // 单个可以就用个String即可
        // （2）基于完整list再
        public void filter(String singer) {
            singerSet.add(singer);
        }

        public void filterV2(List<String> singerList) {

        }

        public void repeatSongButton() {
            repeatSong = !repeatSong;
        }

        public void repeatListButton() {
            repeatList = !repeatList;
        }

        public void addNewSong(String key, String value) {
            Node node = new Node(key, value);
            addToTail(node);
            String uuid = key + value;
            if (idToNodeMap.containsKey(uuid)) return;
            idToNodeMap.put(uuid, node);

            if (cur == head) cur = head.next; // init current first music
        }

        public void chooseStart(String key, String value) {
            String uuid = key + value;
            if (idToNodeMap.containsKey(uuid)) {
                cur = idToNodeMap.get(uuid);
            }
        }

        private void addToTail(Node node) {
            Node last = tail.prev;
            last.next = node;
            node.prev = last;
            node.next = tail;
            tail.prev = node;
        }
    }

    class Node {
        Node prev, next;
        String singer;
        String song;
        public Node() {}
        public Node(String key, String val) {
            this.singer = key;
            this.song = val;
        }
    }
}
