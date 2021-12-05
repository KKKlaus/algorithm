package Locker;

import java.sql.Time;
import java.util.*;

public class LockerManager {

    // 3 * 3
    int size;
    HashMap<String, Locker> lockerMap;
    HashMap<String, Locker> smallLockerMap;
    HashMap<String, Locker> mediumLockerMap;
    HashMap<String, Locker> largeLockerMap;

    Queue<Locker> SmallLockerQueue;
    Queue<Locker> MediumLockerQueue;
    Queue<Locker> LargeLockerQueue;

    HashMap<String, String> codeToIdMap;
    public LockerManager() {
        lockerMap = new HashMap<>();
        size = 3;
        SmallLockerQueue = new LinkedList<>();
        MediumLockerQueue = new LinkedList<>();
        LargeLockerQueue = new LinkedList<>();
        codeToIdMap = new HashMap<>();
    }

    // init其实从数据库里读取出来
    public void initLocker(int size) {
        for (int i = 0; i < size / 3; i++) {
            String id = "Amazon" + i;
            Locker locker = new Locker(id, LockerEnum.SIZETYPE.SMALL, LockerEnum.Status.EMPTY);
            lockerMap.put(id, locker);
            smallLockerMap.put(id, locker);  // 或者这里不同locker类型根据不同数据库。
            SmallLockerQueue.offer(locker);
        }
        for (int i = size / 3; i < size / 3 * 2; i++) {
            String id = "Amazon" + i;
            Locker locker = new Locker(id, LockerEnum.SIZETYPE.MEDIUM, LockerEnum.Status.EMPTY);
            lockerMap.put(id, locker);
            mediumLockerMap.put(id, locker);  // 或者这里不同locker类型根据不同数据库。
            MediumLockerQueue.offer(locker);
        }
        for (int i = size / 3 * 2; i < size; i++) {
            String id = "Amazon" + i;
            Locker locker = new Locker(id, LockerEnum.SIZETYPE.LARGE, LockerEnum.Status.EMPTY);
            lockerMap.put(id, locker);
            largeLockerMap.put(id, locker);  // 或者这里不同locker类型根据不同数据库。
            LargeLockerQueue.offer(locker);
        }
    }

    public String assignNewLocker(Package pkg) {
        // check pkg size
        if (pkg.getSize() == LockerEnum.SIZETYPE.SMALL) {
            if (SmallLockerQueue.isEmpty()) {
                return "All Lockers are full";
            } else {
                Locker assignedLocker = SmallLockerQueue.poll();
                String code = UUID.randomUUID().toString();
                assignedLocker.setStartSaveTime(new Time(System.currentTimeMillis()));
                assignedLocker.setCode(code);
                codeToIdMap.put(code,assignedLocker.getId());
                return code; // or trigger a code message
            }
        } else if (pkg.getSize() == LockerEnum.SIZETYPE.MEDIUM) {
            return "Same logic";
        } else {
            return "Same logic";
        }
    }

    public String getPackageByCode(String code) {
        if (!codeToIdMap.containsKey(code)) {
            return "Invalid code";
        }
        Locker locker = lockerMap.get(codeToIdMap.get(code));
        // get locker/ open it and check locker size and enqueue


        // empty locker info
        // calculate time and charge
        Time startTime = locker.getStartSaveTime();
        locker.setStartSaveTime(null);

        return "test";
    }
}
