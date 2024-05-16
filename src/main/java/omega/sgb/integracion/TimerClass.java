package omega.sgb.integracion;

import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {

    Timer timer1;
    private int timecounter = 0;

    TimerTask Task1 = new TimerTask() {
        @Override
        public void run() {
            setTimecounter(getTimecounter()+1);
        }
    };

    public TimerClass(){
        timer1 = new Timer();

    }

    public int getTimecounter() {
        return timecounter;
    }

    public void setTimecounter(int timecounter) {
        this.timecounter = timecounter;
    }

}
