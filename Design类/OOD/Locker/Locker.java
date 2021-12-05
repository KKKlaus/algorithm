package Locker;

import java.sql.Time;

public class Locker {

    private String id;
    private Locker.LockerEnum.SIZETYPE size;
    private Locker.LockerEnum.Status status;
    private Time startSaveTime;
    private String code;

    public Locker(String id, Locker.LockerEnum.SIZETYPE size, Locker.LockerEnum.Status status) {
        this.id = id;
        this.size = size;
        this.status = status;
    }

    //getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LockerEnum.SIZETYPE getSize() {
        return size;
    }

    public void setSize(LockerEnum.SIZETYPE size) {
        this.size = size;
    }

    public LockerEnum.Status getStatus() {
        return status;
    }

    public void setStatus(LockerEnum.Status status) {
        this.status = status;
    }

    public Time getStartSaveTime() {
        return startSaveTime;
    }

    public void setStartSaveTime(Time startSaveTime) {
        this.startSaveTime = startSaveTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
