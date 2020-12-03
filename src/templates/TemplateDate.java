package templates;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TemplateDate extends JPanel {

	/**
	 * Create the panel.
	 */
	private String nombre;
	public JLabel lblNombre;
	
	public TemplateDate() {
		setBorder(new LineBorder(Color.GRAY, 2));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNombre = new JLabel("New label");
		add(lblNombre);
		
		JDateChooser dateChooser = new JDateChooser();
		add(dateChooser);

	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
