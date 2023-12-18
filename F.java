import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdvancedHoverButton extends JButton {

    private Color defaultBackground;
    private CustomToolTip tooltip;

    public AdvancedHoverButton(String tooltipText, String imagePath) {
        super();
        defaultBackground = getBackground();
        addMouseListener(new ButtonMouseListener());
        setToolTip(tooltipText);
        setImage(imagePath);
        addActionListener(new ButtonActionListener());
    }

    private void setToolTip(String text) {
        tooltip = new CustomToolTip(this, text);
    }

    private void setImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagePath);
            setIcon(icon);
        }
    }

    private class ButtonMouseListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(getActiveBackground());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(defaultBackground);
        }
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (getBackground().equals(defaultBackground)) {
                setBackground(getActiveBackground());
            } else {
                setBackground(defaultBackground);
            }

            
        }
    }

    private Color getActiveBackground() {
        return UIManager.getColor("Button.select");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Advanced Hover Button");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            AdvancedHoverButton button = new AdvancedHoverButton("This is an advanced tooltip", "path_to_image.png");
            frame.getContentPane().add(button, BorderLayout.CENTER);

            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
