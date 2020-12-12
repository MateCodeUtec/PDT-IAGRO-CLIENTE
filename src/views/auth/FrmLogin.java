package views.auth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import org.apache.commons.codec.digest.DigestUtils;

import models.Estacion;
import models.Rol;
import models.Usuario;
import services.EstacionBeanRemote;
import services.RolBeanRemote;
import services.UsuarioBeanRemote;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private UsuarioBeanRemote uBean = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmLogin.class.getResource("/views/assets/icons/icon-app-barra.png")));
		setTitle("IAGRO - Iniciar sesi\u00F3n");
		setBounds(100, 100, 719, 414);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlleft = new JPanel();
		pnlleft.setBounds(0, 0, 357, 414);
		pnlleft.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlleft.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pnlleft.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlleft);
		pnlleft.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/views/assets/icons/logo-app-200x200.png")));
		lblNewLabel.setBounds(10, 57, 316, 247);
		pnlleft.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrmLogin.class.getResource("/views/assets/icons/logo-team-chico.png")));
		lblNewLabel_1.setBounds(10, 375, 92, 28);
		pnlleft.add(lblNewLabel_1);

		JPanel pnlleft_1 = new JPanel();
		pnlleft_1.setVerifyInputWhenFocusTarget(false);
		pnlleft_1.setRequestFocusEnabled(false);
		pnlleft_1.setLayout(null);
		pnlleft_1.setBackground(new Color(119, 184, 105));
		pnlleft_1.setAlignmentY(0.0f);
		pnlleft_1.setAlignmentX(1.0f);
		pnlleft_1.setBounds(357, 0, 362, 414);
		contentPane.add(pnlleft_1);

		txtUsuario = new JTextField();
		txtUsuario.setVerifyInputWhenFocusTarget(false);
		txtUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUsuario.setText("");
			}
		});
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		txtUsuario.setText("Usuario");
		txtUsuario.setBackground(new Color(119, 184, 105));
		txtUsuario.setBounds(87, 152, 218, 33);
		txtUsuario.setBorder(null);
		pnlleft_1.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtPassword.setText("");
			}
		});
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setForeground(new Color(255, 255, 255));
		txtPassword.setBackground(new Color(119, 184, 105));
		txtPassword.setBounds(87, 216, 218, 33);
		txtPassword.setText("Contraseña");
		txtPassword.setBorder(null);
		pnlleft_1.add(txtPassword);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(248, 248, 255));
		separator.setForeground(new Color(248, 248, 255));
		separator.setBounds(87, 189, 218, 10);
		pnlleft_1.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 255, 255));
		separator_1.setBackground(new Color(255, 255, 255));
		separator_1.setBounds(87, 251, 218, 10);
		pnlleft_1.add(separator_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(FrmLogin.class.getResource("/views/assets/icons/icon-password.png")));
		lblNewLabel_2.setBounds(45, 216, 36, 45);
		pnlleft_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(FrmLogin.class.getResource("/views/assets/icons/icon-user.png")));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(45, 152, 36, 45);
		pnlleft_1.add(lblNewLabel_2_1);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnIngresar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setBackground(new Color(119, 184, 105));
		btnIngresar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnIngresar.setBounds(87, 272, 102, 33);
		pnlleft_1.add(btnIngresar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCancelar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnCancelar.setBackground(new Color(119, 184, 105));
		btnCancelar.setBounds(203, 272, 102, 33);
		pnlleft_1.add(btnCancelar);

		JLabel lblNewLabel_2_1_1 = new JLabel("X");
		lblNewLabel_2_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setBounds(324, 11, 28, 24);
		pnlleft_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3 = new JLabel("INICIAR SESI\u00D3N");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(45, 68, 282, 45);
		pnlleft_1.add(lblNewLabel_3);

		try {
			uBean = (UsuarioBeanRemote) InitialContext.doLookup("ejb:/IAGROEJB/UsuarioBean!services.UsuarioBeanRemote");
		} catch (NamingException e1) {
			System.out.println("e00----->" + e1.getStackTrace());
		}

	}

	void login() {

		var usuario = txtUsuario.getText();
		var pass = txtPassword.getText();
		
		// Encriptamos la contraseña
		pass = DigestUtils.md5Hex(pass);
		try {
			Usuario usuarioLogin = uBean.getUsuario(usuario);
			
			var respuesta = uBean.validarLogin(usuarioLogin, usuario, pass);
			if (respuesta) {
				setVisible(false);
				// Abrir la ventana principal
				FrmPrincipal frm = new FrmPrincipal(usuarioLogin);
				frm.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Iniciar sesón",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Iniciar sesión", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

	}

}
