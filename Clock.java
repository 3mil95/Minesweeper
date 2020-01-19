import javax.swing.JLabel;
import java.util.Timer;
import java.util.TimerTask;

class Clock extends JLabel {
    Timer timer;
    int time = 0;
    Task clock;

    Clock() {
        setText("0000");
    }

    public void startTimer() {
        timer = new Timer();
        clock = new Task();
        timer.schedule(clock, 1000, 1000);
    }

    public void stopTimer() {
        time = 0;
        timer.cancel();
    }

    class Task extends TimerTask {
        @Override
        public void run() {
            time++;
            String timeString = Integer.toString(time);
            String text = "";
            int len = timeString.length();
            for (int i = 0; i < 4 - len; i++) {
                text += "0";
            }
            text += timeString;
            setText(text);
        }
    }
}