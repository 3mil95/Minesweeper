

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopFrame extends JPanel {
    Minesweeper game;
    Clock timer = new Clock();
    JLabel flaggcunt = new JLabel("0000");

    TopFrame(Minesweeper game) {
        this.game = game;
        add(timer);
        add(flaggcunt);
    }

    public Clock getClock() {
        return timer;
    }

    public void setFlaggLabel(String flagges) {
        String text = "";
        int len = flagges.length();
        for (int i = 0; i < 4 - len; i++) {
            text += "0";
        }
        text += flagges;
        flaggcunt.setText(text);
    }
}
