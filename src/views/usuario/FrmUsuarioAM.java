package views.usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import enums.AccionFormulario;
import helpers.Validaciones;
import models.Rol;
import models.Usuario;
import services.RolBeanRemote;
import services.UsuarioBean;
import services.UsuarioBeanRemote;
import views.auth.FrmPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextField;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmUsuarioAM extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtPass;
	private JComboBox cbRol;
	private UsuarioBeanRemote usuarioBean = null;
	private JTextField txtMail;

	/**
	 * Create the frame. 
	 */
	public FrmUsuarioAM(AccionFormulario accion, Usuario u) {
		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmUsuarioAM.class.getResource("/views/assets/icons/icon-app-barra.png")));
		setBounds(100, 100, 491, 393);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 491, 393);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 490, 39);
		panel_2.add(panel);
		panel.setBackground(new Color(119, 184, 105));
		panel.setLayout(null);

		JLabel lblTitulo = new JLabel("Alta de usuario");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmUsuarioAM.class.getResource("/views/assets/icons/icon-app-ventana.png")));
		lblNewLabel_2.setBounds(10, 11, 19, 16);
		panel.add(lblNewLabel_2);

		JLabel lblCerrarSesion = new JLabel("X");
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblCerrarSesion.setBounds(461, 11, 19, 14);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		//Llamo al bean
		try {
			usuarioBean = (UsuarioBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/UsuarioBean!services.UsuarioBeanRemote");
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				var palabra = txtNombre.getText();
				
				if(Validaciones.esLetraOEspacio(e.getKeyChar())) {
					txtNombre.setText(palabra);
				}else {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
				
			}
		});
		
		
		txtNombre.setVerifyInputWhenFocusTarget(false);
		txtNombre.setForeground(new Color(0, 0, 0));
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBackground(new Color(255, 255, 255));
		txtNombre.setBounds(31, 91, 199, 33);
		panel_2.add(txtNombre);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 128, 199, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(31, 66, 199, 14);
		panel_2.add(lblNewLabel_1_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCancelar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnCancelar.setBackground(new Color(204, 204, 204));
		btnCancelar.setBounds(143, 352, 102, 33);
		panel_2.add(btnCancelar);

		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				var palabra = txtNombre.getText();
				
				if(Validaciones.esLetraOEspacio(e.getKeyChar())) {
					txtNombre.setText(palabra);
				}else {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
				
			}
		});
		
		txtApellido.setVerifyInputWhenFocusTarget(false);
		txtApellido.setForeground(new Color(0, 0, 0));
		txtApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		txtApellido.setColumns(10);
		txtApellido.setBorder(null);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBounds(263, 91, 199, 33);
		panel_2.add(txtApellido);

		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(263, 66, 199, 14);
		panel_2.add(lblNewLabel_1_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(new Color(248, 248, 255));
		separator_1.setBounds(263, 128, 199, 10);
		panel_2.add(separator_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Usuario");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_2.setBounds(31, 158, 199, 14);
		panel_2.add(lblNewLabel_1_1_2);

		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				var palabra = txtUsuario.getText();
				
				if(Validaciones.esLetra(e.getKeyChar())) {
					txtUsuario.setText(palabra);
				}else {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
			
		});
		txtUsuario.setVerifyInputWhenFocusTarget(false);
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setBounds(31, 183, 199, 33);
		panel_2.add(txtUsuario);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(new Color(248, 248, 255));
		separator_2.setBounds(31, 220, 199, 10);
		panel_2.add(separator_2);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setBackground(new Color(248, 248, 255));
		separator_1_1.setBounds(263, 220, 199, 10);
		panel_2.add(separator_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(263, 158, 199, 14);
		panel_2.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Rol");
		lblNewLabel_1_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_2_1.setBounds(263, 249, 199, 14);
		panel_2.add(lblNewLabel_1_1_2_1);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(new Color(248, 248, 255));
		separator_2_1.setBounds(31, 340, 431, 10);
		panel_2.add(separator_2_1);

		txtPass = new JPasswordField();
		txtPass.setForeground(Color.BLACK);
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPass.setBorder(null);
		txtPass.setBackground(Color.WHITE);
		txtPass.setBounds(263, 183, 199, 33);
		panel_2.add(txtPass);

		cbRol = new JComboBox();
		cbRol.setBorder(null);
		cbRol.setBackground(Color.WHITE);
		cbRol.setBounds(263, 274, 199, 33);
		panel_2.add(cbRol);
		comboRoles();

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					usuarioBean = (UsuarioBeanRemote) InitialContext
							.doLookup("ejb:/IAGROEJB/UsuarioBean!services.UsuarioBeanRemote");
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				var nombre = txtNombre.getText();
				var apellido = txtApellido.getText();
				var usuario = txtUsuario.getText();
				var mail = txtMail.getText();
				var pass = txtPass.getText();
				Rol r = (Rol) cbRol.getSelectedItem();
				

				if (Validaciones.esVacio(nombre) || Validaciones.esVacio(apellido) || Validaciones.esVacio(usuario)
						|| Validaciones.esVacio(pass) || Validaciones.esVacio(mail) || Validaciones.esVacio(r.getNombre()) ) {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Ups!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					
					if(!Validaciones.password(pass)) {
						JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos 8 caracteres alfanumericos", "Ups!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(!Validaciones.esCorreo(mail)) {
						JOptionPane.showMessageDialog(null, "El correo no tiene un formato valido", "Ups!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(usuario.length() < 8) {
						JOptionPane.showMessageDialog(null, "El usuario debe contener al menos 8 caracteres", "Ups!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Usuario u = new Usuario();
					u.setNombre(nombre);
					u.setApellido(apellido);
					u.setUsuario(usuario);
					u.setContrasenia(pass);
					u.setMail(mail);
					u.setRol(r);

					try {

						usuarioBean.crear(u);
						JOptionPane.showMessageDialog(null, "Usuario guardado con exito");
						
						setVisible(false);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "El usuario o correo ya ha sido registrado", "Ups!",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}

			}

		});
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnGuardar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnGuardar.setBackground(new Color(119, 184, 105));
		btnGuardar.setBounds(31, 352, 102, 33);
		panel_2.add(btnGuardar);
		
		JLabel lblId = new JLabel("New label");
		lblId.setVisible(false);
		lblId.setBounds(284, 317, 45, 13);
		panel_2.add(lblId);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				var nombre = txtNombre.getText();
				var apellido = txtApellido.getText();
				var usuario = txtUsuario.getText();
				var pass = txtPass.getText();
				var mail = txtMail.getText();
				Long id = Long.valueOf(lblId.getText());
				Rol r = (Rol) cbRol.getSelectedItem();

				if (Validaciones.esVacio(nombre) || Validaciones.esVacio(apellido) || Validaciones.esVacio(usuario)
						|| Validaciones.esVacio(pass) || Validaciones.esVacio(mail) || Validaciones.esVacio(r.getNombre())) {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Ups!",
							JOptionPane.ERROR_MESSAGE);
				} else {

					Usuario u = new Usuario();
					u.setNombre(nombre);
					u.setApellido(apellido);
					u.setUsuario(usuario);
					u.setContrasenia(pass);
					u.setMail(mail);
					u.setRol(r);
					u.setId(id);

					try {

						usuarioBean.actualizar(u);
						JOptionPane.showMessageDialog(null, "Usuario actualizado con exito");

						setVisible(false);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}

		});
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnModificar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnModificar.setBackground(new Color(119, 184, 105));
		btnModificar.setBounds(31, 352, 102, 33);
		panel_2.add(btnModificar);
		
		txtMail = new JTextField();
		txtMail.setVerifyInputWhenFocusTarget(false);
		txtMail.setForeground(Color.BLACK);
		txtMail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMail.setColumns(10);
		txtMail.setBorder(null);
		txtMail.setBackground(Color.WHITE);
		txtMail.setBounds(31, 274, 199, 33);
		panel_2.add(txtMail);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Correo");
		lblNewLabel_1_1_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_2_2.setBounds(31, 249, 199, 14);
		panel_2.add(lblNewLabel_1_1_2_2);
		
		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2.setBackground(new Color(248, 248, 255));
		separator_2_2.setBounds(31, 311, 199, 10);
		panel_2.add(separator_2_2);
		

		// Configurar ventana
		btnGuardar.setVisible(false);
		btnModificar.setVisible(false);

		if (accion == AccionFormulario.Alta) {
			lblTitulo.setText("Alta de usuario");
			btnGuardar.setVisible(true);
		} else if (accion == AccionFormulario.Modificar) {
			lblTitulo.setText("Modificar usuario");
			btnModificar.setVisible(true);
			
			// Cargo datos
			
			lblId.setText(String.valueOf(u.getId()));
			txtUsuario.setText(u.getUsuario());
			txtNombre.setText(u.getNombre());
			txtApellido.setText(u.getApellido());
			txtMail.setText(u.getMail());
			txtPass.setText(u.getContrasenia());
			selectedInCombo(u.getRol().getNombre());
		}

	}

	/**
	 * Carga el combo de seleccionar roles
	 */
	public void comboRoles() {

		RolBeanRemote rolBean = null;
		try {
			rolBean = (RolBeanRemote) InitialContext.doLookup("ejb:/IAGROEJB/RolBean!services.RolBeanRemote");
		} catch (Exception e) {
			// TODO: handle exception
		}

		List<Rol> roles = rolBean.obtenerTodos();

		for (Rol r : roles) {
			cbRol.addItem(r);
		}
	}

	public void limpiarTextFields(ArrayList<JTextField> campos) {

		for (JTextField tf : campos) {
			tf.setText("");
		}

	}

	public void selectedInCombo(String value) {
		Rol r = null;
		for (int i = 0; i < cbRol.getItemCount(); i++) {
			r = (Rol) cbRol.getItemAt(i);
			if (r.getNombre().equals(value)) {
				cbRol.setSelectedIndex(i);
				break;
			}
		}
	}
}
