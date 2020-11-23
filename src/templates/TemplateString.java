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

public class TemplateString extends JPanel {
	public JTextField textField;
	public JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public TemplateString() {
		setBorder(new LineBorder(new Color(153, 255, 153), 1, true));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(102, 255, 153)));
		textField.setForeground(Color.LIGHT_GRAY);
		add(textField);
		textField.setColumns(10);

	}
}
