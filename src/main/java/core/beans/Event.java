package core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(int id, String msg, Date date){
        this.id = id;
        this.msg = msg;
        this.date = date;
    }

    public Event(Date date, DateFormat df){
        this.id = AUTO_ID.getAndIncrement();
        this.date = date;
        this.df = df;
    }

    public static void initAutoId(int id) {
        AUTO_ID.set(id);
    }

    public int getId() {
        return id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString(){
        return String.format("Event {id=%d msg='%s' date=%s}", id, msg, df.format(date));
    }
}
