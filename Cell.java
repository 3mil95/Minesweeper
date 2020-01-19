import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;
import java.util.HashMap;



class Cell extends JButton{
    /**
     *
     */
    private static final long serialVersionUID = -2833113053055912643L;
    private HashMap<String, Color> colorMap = new HashMap<String, Color>(); 
    private boolean open;
    private boolean flagged;

    public Cell() {
        setSize(50, 50);
        setFocusable(false);
        colorMap.put("1", new Color(0,0,255));
        colorMap.put("2", new Color(0,255,0));
        colorMap.put("3", new Color(255,0,0));
        colorMap.put("4", new Color(0,0,100));
        colorMap.put("5", new Color(0,100,0));
        colorMap.put("6", new Color(100,0,0));
        colorMap.put("7", new Color(0,0,0));
        colorMap.put("8", new Color(100,100,100));
    }

    public void openCell(boolean open) {
        this.open = open;
        setText("");
        if (open) {
            setBackground(Color.white);
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        } else {
            setBackground(Color.LIGHT_GRAY);
            setBorder(BorderFactory.createRaisedBevelBorder());
        }
    } 

    public void flaggedCell(boolean flagged) {
        this.flagged = flagged;
        setIcon(null);
        if (flagged) {
            setIcon(new ImageIcon("Icons/flag.png"));
        }
    }

    public void contentCell(String cont) {
        if (!open) {
            return;
        }
        if (cont.equals("-1")) {
            setBackground(Color.red);
            if (flagged) {
                setIcon(new ImageIcon("Icons/flaggedMine.png"));
            } else {
                setIcon(new ImageIcon("Icons/mine.png"));
            }
        } else {
            setForeground(colorMap.get(cont));
            setText(cont);
        }
    }
}