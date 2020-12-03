package templates;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import javax.swing.JRadioButton;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TemplateBoolean extends JPanel {

	/**
	 * Create the panel.
	 */
	private String nombre;
	public JLabel lblNombre;
	public TemplateBoolean() {
		setBorder(new LineBorder(Color.GRAY, 2));
		ButtonGroup g = new ButtonGroup();
		setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNombre = new JLabel("");
		add(lblNombre);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Verdadero");
		rdbtnNewRadioButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Falso");
		rdbtnNewRadioButton_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		add(rdbtnNewRadioButton_1);
		g.add(rdbtnNewRadioButton_1);
		
		g.add(rdbtnNewRadioButton);
		


	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
