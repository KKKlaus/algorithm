import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SnapshotArray {

    TreeMap<Integer, Integer>[] A;
    int snap_id = 0;
    public SnapshotArray(int length) {
        A = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            A[i] = new TreeMap<Integer, Integer>();
            A[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        A[index].put(snap_id, val);
    }

    public int snap() {
        return snap_id++;
    }

    public int get(int index, int snap_id) {
        return A[index].floorEntry(snap_id).getValue();
    }


    // 自己写二分
    List<int[]>[] record;
    int snapId;
    public void SnapshotArray(int length) {
        record = new List[length];
        snapId = 0;
        for (int i = 0; i < length; i++) {
            record[i] = new ArrayList<>();
            record[i].add(new int[]{0, 0});
        }
    }

    public void set_2(int index, int val) {
        if (record[index].get(record[index].size() - 1)[0] == snapId) {
            record[index].get(record[index].size() - 1)[1] = val;
        } else {
            record[index].add(new int[]{snapId, val});
        }
    }

    public int snap_2() {
        return snapId++;
    }

    public int get_2(int index, int snap_id) {
        List<int[]> snaps = record[index];
        int l = 0, r = snaps.size() - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(snaps.get(mid)[0] < snap_id) {
                l = mid;
            } else {
                r = mid;
            }
        }

        if (snaps.get(r)[0] <= snap_id) {
            return snaps.get(r)[1];
        } else {
            return snaps.get(l)[1];
        }
    }
}
