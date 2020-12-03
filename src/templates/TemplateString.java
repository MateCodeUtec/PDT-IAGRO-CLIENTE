package templates;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.BoxLayout;

public class TemplateString extends JPanel {
	public JTextField textField;
	public JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public TemplateString() {
		setBorder(new LineBorder(Color.GRAY, 2));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setForeground(Color.LIGHT_GRAY);
		add(textField);
		textField.setColumns(10);

	}
}
