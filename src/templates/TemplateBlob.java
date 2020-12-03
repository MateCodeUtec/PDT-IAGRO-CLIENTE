package templates;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class TemplateBlob extends JPanel {
	public JTextField textField;
	public JLabel lblNombre;
	private JFileChooser fileChooser;
	public JButton btnNewButton;
	/**
	 * Create the panel.
	 */
	private String nombre;
	public TemplateBlob() {
		setBorder(new LineBorder(Color.GRAY, 2));
		
		int seleccion = 0;
		
		JButton btnNewButton = new JButton("Seleccionar archivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(btnNewButton);
				add(fileChooser);
			}
		});
		setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNombre = new JLabel("New label");
		add(lblNombre);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		add(btnNewButton);
		
		

	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
