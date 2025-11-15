package vista.componentes_vista;

import java.awt.Dimension;
import javax.swing.JComponent;

public class Spacer extends JComponent {
    public Dimension getMinimumSize() {
        return new Dimension(0, 0);
    }

    public final Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
}