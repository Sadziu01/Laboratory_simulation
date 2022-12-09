package pwr.sadowski;

public class MyThread extends Thread{
    protected Labolatorium l;
    static protected boolean end = false;

    public static void setEnd(boolean end) {
        MyThread.end = end;
    }

    public MyThread(char name, Labolatorium l){
        super(String.valueOf(name));
        this.l = l;
    }


}
