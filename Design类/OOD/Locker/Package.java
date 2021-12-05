package Locker;

import Locker.LockerEnum.SIZETYPE;

public class Package {

    String id;
    String lockerId;
    SIZETYPE size;

    public Package(String id, SIZETYPE size) {
        this.id = id;
        this.size = size;
    }


    // getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public SIZETYPE getSize() {
        return size;
    }

    public void setSize(SIZETYPE size) {
        this.size = size;
    }
}