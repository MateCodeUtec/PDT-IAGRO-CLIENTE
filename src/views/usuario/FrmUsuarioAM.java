package views.usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import org.apache.commons.codec.digest.DigestUtils;

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
	private JTextField ciTXT;
	private JTextField profesionTXT;
	private JTextField institutoTXT;

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
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmUsuarioAM.class.getResource("/views/assets/icons/icon-app-ventana.png")));
		lblNewLabel_2.setBounds(10, 11, 19, 16);
		panel.add(lblNewLabel_2);

		JLabel lblCerrarSesion = new JLabel("X");
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblCerrarSesion.setBounds(461, 0, 29, 25);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		
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
		txtNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBackground(new Color(255, 255, 255));
		txtNombre.setBounds(31, 80, 199, 22);
		panel_2.add(txtNombre);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 102, 199, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(31, 66, 199, 14);
		panel_2.add(lblNewLabel_1_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
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
		txtApellido.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtApellido.setColumns(10);
		txtApellido.setBorder(null);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBounds(263, 80, 199, 22);
		panel_2.add(txtApellido);

		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(263, 66, 199, 14);
		panel_2.add(lblNewLabel_1_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(new Color(248, 248, 255));
		separator_1.setBounds(263, 102, 199, 10);
		panel_2.add(separator_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Usuario");
		lblNewLabel_1_1_2.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(31, 123, 199, 14);
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
		txtUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setBounds(31, 137, 199, 22);
		panel_2.add(txtUsuario);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(new Color(248, 248, 255));
		separator_2.setBounds(31, 159, 199, 10);
		panel_2.add(separator_2);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setBackground(new Color(248, 248, 255));
		separator_1_1.setBounds(263, 159, 199, 10);
		panel_2.add(separator_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1_1_1_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(263, 123, 199, 14);
		panel_2.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Rol");
		lblNewLabel_1_1_2_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1_2_1.setBounds(263, 173, 199, 14);
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
		txtPass.setBounds(263, 137, 199, 22);
		panel_2.add(txtPass);
		
		ciTXT = new JTextField();
		ciTXT.setVerifyInputWhenFocusTarget(false);
		ciTXT.setForeground(Color.BLACK);
		ciTXT.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		ciTXT.setColumns(10);
		ciTXT.setBorder(null);
		ciTXT.setBackground(Color.WHITE);
		ciTXT.setBounds(31, 244, 199, 22);
		panel_2.add(ciTXT);
		
		JSeparator separadorCI = new JSeparator();
		separadorCI.setForeground(Color.LIGHT_GRAY);
		separadorCI.setBackground(new Color(248, 248, 255));
		separadorCI.setBounds(31, 266, 199, 10);
		panel_2.add(separadorCI);
	
		
		JLabel ciLabel = new JLabel("CI");
		ciLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		ciLabel.setBounds(31, 231, 199, 14);
		panel_2.add(ciLabel);
		
		JSeparator separadorProfesion = new JSeparator();
		separadorProfesion.setForeground(Color.LIGHT_GRAY);
		separadorProfesion.setBackground(new Color(248, 248, 255));
		separadorProfesion.setBounds(263, 266, 199, 10);
		panel_2.add(separadorProfesion);
		
		profesionTXT = new JTextField();
		profesionTXT.setVerifyInputWhenFocusTarget(false);
		profesionTXT.setForeground(Color.BLACK);
		profesionTXT.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		profesionTXT.setColumns(10);
		profesionTXT.setBorder(null);
		profesionTXT.setBackground(Color.WHITE);
		profesionTXT.setBounds(263, 244, 199, 22);
		panel_2.add(profesionTXT);
		
		JLabel profesionLabel = new JLabel("Profesi\u00F3n");
		profesionLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		profesionLabel.setBounds(263, 231, 199, 14);
		panel_2.add(profesionLabel);
		
		JSeparator separadorInstituto = new JSeparator();
		separadorInstituto.setForeground(Color.LIGHT_GRAY);
		separadorInstituto.setBackground(new Color(248, 248, 255));
		separadorInstituto.setBounds(31, 322, 199, 10);
		panel_2.add(separadorInstituto);
		
		institutoTXT = new JTextField();
		institutoTXT.setVerifyInputWhenFocusTarget(false);
		institutoTXT.setForeground(Color.BLACK);
		institutoTXT.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		institutoTXT.setColumns(10);
		institutoTXT.setBorder(null);
		institutoTXT.setBackground(Color.WHITE);
		institutoTXT.setBounds(31, 300, 199, 22);
		panel_2.add(institutoTXT);
		
		JLabel institutoLabel = new JLabel("Instituto");
		institutoLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		institutoLabel.setBounds(31, 287, 199, 14);
		panel_2.add(institutoLabel);

		cbRol = new JComboBox();
		cbRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = cbRol.getSelectedIndex();
				 
				if (index == 0) {
					
			ciLabel.setEnabled(true);
			ciTXT.setEnabled(true);
			separadorCI.setEnabled(true);
			
			institutoLabel.setEnabled(true);
			institutoTXT.setEnabled(true);
			separadorInstituto.setEnabled(true);
			
			profesionLabel.setEnabled(false);
			profesionTXT.setEnabled(false);
			separadorProfesion.setEnabled(false);		
			profesionTXT.setText("");
					
				}
				else if(index == 1) {
					
					ciLabel.setEnabled(true);
					ciTXT.setEnabled(true);
					separadorCI.setEnabled(true);
					
					profesionLabel.setEnabled(true);
					profesionTXT.setEnabled(true);
					separadorProfesion.setEnabled(true);
					
					institutoLabel.setEnabled(false);
					institutoTXT.setEnabled(false);
					separadorInstituto.setEnabled(false);
					institutoTXT.setText("");
					
					
				
				}else {
					
					ciLabel.setEnabled(false);
					ciTXT.setEnabled(false);
					separadorCI.setEnabled(false);
					
					profesionLabel.setEnabled(false);
					profesionTXT.setEnabled(false);
					separadorProfesion.setEnabled(false);
					
					institutoLabel.setEnabled(false);
					institutoTXT.setEnabled(false);
					separadorInstituto.setEnabled(false);
					
					profesionTXT.setText("");
					institutoTXT.setText("");
					ciTXT.setText("");
				}
			}
		});
		cbRol.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		cbRol.setBorder(null);
		cbRol.setBackground(Color.WHITE);
		cbRol.setBounds(263, 188, 199, 22);
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
				var ci = ciTXT.getText();
				var inst = institutoTXT.getText();
				var prof = profesionTXT.getText();
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
						JOptionPane.showMessageDialog(null, "El correo no tiene un formato válido", "Ups!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(usuario.length() < 8) {
						JOptionPane.showMessageDialog(null, "El usuario debe contener al menos 8 caracteres", "Ups!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Encriptamos la contraseña
					pass = DigestUtils.md5Hex(pass);
					
					Usuario u = new Usuario();
					u.setNombre(nombre);
					u.setApellido(apellido);
					u.setUsuario(usuario);
					u.setContrasenia(pass);
					u.setMail(mail);
					u.setCi(ci);
					u.setInstituto(inst);
					u.setProfesion(prof);
					u.setRol(r);

					try {

						usuarioBean.crear(u);
						JOptionPane.showMessageDialog(null, "Usuario guardado con éxito");
						
						setVisible(false);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Usuario o Correo existente, intente denuevo", "Ups!",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}

			}

		});
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
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
				var ci = ciTXT.getText();
				var inst = institutoTXT.getText();
				var prof = profesionTXT.getText();
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
					u.setCi(ci);
					u.setInstituto(inst);
					u.setProfesion(prof);
					u.setRol(r);
					u.setId(id);

					try {

						usuarioBean.actualizar(u);
						JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito");

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
		txtMail.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtMail.setColumns(10);
		txtMail.setBorder(null);
		txtMail.setBackground(Color.WHITE);
		txtMail.setBounds(31, 188, 199, 22);
		panel_2.add(txtMail);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Correo");
		lblNewLabel_1_1_2_2.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1_2_2.setBounds(31, 175, 199, 14);
		panel_2.add(lblNewLabel_1_1_2_2);
		
		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2.setBackground(new Color(248, 248, 255));
		separator_2_2.setBounds(31, 210, 199, 10);
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
			ciTXT.setText(u.getCi());
			profesionTXT.setText(u.getProfesion());
			institutoTXT.setText(u.getInstituto());
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
