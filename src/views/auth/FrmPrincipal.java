package views.auth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import enums.AccionFormulario;
import models.Usuario;
import views.actividad.FrmActividadCampoAM;
import views.actividad.FrmListadoActividad;
import views.form.FrmFormAM;
import views.form.FrmListadoForm;
import views.usuario.FrmListado;
import views.usuario.FrmUsuarioAM;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FrmPrincipal(Usuario usuario) {
		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmPrincipal.class.getResource("/views/assets/icons/icon-app-barra.png")));
		setBounds(100, 100, 818, 523);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 817, 523);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_usuarios = new JPanel();
		panel_usuarios.setBackground(new Color(234, 234, 234));
		panel_usuarios.setBounds(42, 77, 217, 218);
		panel_2.add(panel_usuarios);
		panel_usuarios.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuarios");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 197, 24);
		panel_usuarios.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/principal_user.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 46, 197, 120);
		panel_usuarios.add(lblNewLabel_3);

		JButton btnUsuarioAlta = new JButton("");
		btnUsuarioAlta.setToolTipText("Agregar usuario");
		btnUsuarioAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUsuarioAM frm = new FrmUsuarioAM(AccionFormulario.Alta, null);
				frm.setVisible(true);
			}
		});
		btnUsuarioAlta.setBorderPainted(false);
		btnUsuarioAlta.setBounds(20, 177, 37, 30);
		panel_usuarios.add(btnUsuarioAlta);
		btnUsuarioAlta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/anadir.png")));
		btnUsuarioAlta.setForeground(Color.WHITE);
		btnUsuarioAlta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioAlta.setBorder(null);
		btnUsuarioAlta.setBackground(new Color(255, 255, 255));

		JButton btnUsuarioListar = new JButton("");
		btnUsuarioListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListado frm = new FrmListado(AccionFormulario.Listar);
				frm.setVisible(true);

			}
		});
		btnUsuarioListar.setToolTipText("Listar usuarios");
		btnUsuarioListar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/listar.png")));
		btnUsuarioListar.setForeground(Color.WHITE);
		btnUsuarioListar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioListar.setBorder(null);
		btnUsuarioListar.setBackground(Color.WHITE);
		btnUsuarioListar.setBounds(67, 177, 37, 30);
		panel_usuarios.add(btnUsuarioListar);

		JButton btnUsuarioEditar = new JButton("");
		btnUsuarioEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListado frm = new FrmListado(AccionFormulario.Modificar);
				frm.setVisible(true);

			}
		});
		btnUsuarioEditar.setToolTipText("Modificar usuario");
		btnUsuarioEditar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/edit.png")));
		btnUsuarioEditar.setForeground(Color.WHITE);
		btnUsuarioEditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioEditar.setBorder(null);
		btnUsuarioEditar.setBackground(Color.WHITE);
		btnUsuarioEditar.setBounds(114, 177, 37, 30);
		panel_usuarios.add(btnUsuarioEditar);

		JButton btnUsuarioEliminar = new JButton("");
		btnUsuarioEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListado frm = new FrmListado(AccionFormulario.Eliminar);
				frm.setVisible(true);

			}
		});
		btnUsuarioEliminar.setToolTipText("Eliminar usuario");
		btnUsuarioEliminar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/eliminar.png")));
		btnUsuarioEliminar.setForeground(Color.WHITE);
		btnUsuarioEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioEliminar.setBorder(null);
		btnUsuarioEliminar.setBackground(Color.WHITE);
		btnUsuarioEliminar.setBounds(161, 177, 37, 30);
		panel_usuarios.add(btnUsuarioEliminar);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 912, 39);
		panel_2.add(panel);
		panel.setBackground(new Color(119, 184, 105));
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Principal");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(39, 11, 347, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/icon-app-ventana.png")));
		lblNewLabel_2.setBounds(10, 11, 19, 16);
		panel.add(lblNewLabel_2);

		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreUsuario.setBounds(457, 11, 239, 14);
		panel.add(lblNombreUsuario);
		lblNombreUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JLabel lblCerrarSesion = new JLabel("Cerrar sesi\u00F3n");
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				FrmLogin frm = new FrmLogin();
				frm.setVisible(true);
			}
		});
		lblCerrarSesion.setBounds(706, 11, 96, 14);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JPanel panel_formularios = new JPanel();
		panel_formularios.setLayout(null);
		panel_formularios.setBackground(new Color(234, 234, 234));
		panel_formularios.setBounds(300, 77, 217, 218);
		panel_2.add(panel_formularios);

		JLabel lblFormularios = new JLabel("Formularios");
		lblFormularios.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFormularios.setBounds(10, 11, 197, 24);
		panel_formularios.add(lblFormularios);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1
				.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/principal_form.png")));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3_1.setBackground(Color.WHITE);
		lblNewLabel_3_1.setBounds(10, 46, 197, 120);
		panel_formularios.add(lblNewLabel_3_1);

		JButton btnFormularioAlta = new JButton("");
		btnFormularioAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmFormAM frm = new FrmFormAM(AccionFormulario.Alta, usuario, null);
				frm.setVisible(true);

			}
		});

		btnFormularioAlta.setToolTipText("Alta de formulario");
		btnFormularioAlta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/anadir.png")));
		btnFormularioAlta.setForeground(Color.WHITE);
		btnFormularioAlta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioAlta.setBorderPainted(false);
		btnFormularioAlta.setBorder(null);
		btnFormularioAlta.setBackground(Color.WHITE);
		btnFormularioAlta.setBounds(20, 177, 37, 30);
		panel_formularios.add(btnFormularioAlta);

		JButton btnFormularioListar = new JButton("");
		btnFormularioListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListadoForm frmListado = new FrmListadoForm(AccionFormulario.Modificar, usuario);
				frmListado.setVisible(true);
				
			}
		});
		btnFormularioListar.setToolTipText("Listar formularios");
		btnFormularioListar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/listar.png")));
		btnFormularioListar.setForeground(Color.WHITE);
		btnFormularioListar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioListar.setBorder(null);
		btnFormularioListar.setBackground(Color.WHITE);
		btnFormularioListar.setBounds(67, 177, 37, 30);
		panel_formularios.add(btnFormularioListar);

		JButton btnFormularioEditar = new JButton("");
		btnFormularioEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmListadoForm frmListado = new FrmListadoForm(AccionFormulario.Modificar, usuario);
				frmListado.setVisible(true);
				
			}
		});
		btnFormularioEditar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/edit.png")));
		btnFormularioEditar.setToolTipText("Editar formulario");
		btnFormularioEditar.setForeground(Color.WHITE);
		btnFormularioEditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEditar.setBorder(null);
		btnFormularioEditar.setBackground(Color.WHITE);
		btnFormularioEditar.setBounds(114, 177, 37, 30);
		panel_formularios.add(btnFormularioEditar);

		JButton btnFormularioEliminar = new JButton("");
		btnFormularioEliminar
				.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/eliminar.png")));
		btnFormularioEliminar.setToolTipText("Eliminar usuario");
		btnFormularioEliminar.setForeground(Color.WHITE);
		btnFormularioEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEliminar.setBorder(null);
		btnFormularioEliminar.setBackground(Color.WHITE);
		btnFormularioEliminar.setBounds(161, 177, 37, 30);
		panel_formularios.add(btnFormularioEliminar);

		JLabel lblNewLabel_4 = new JLabel("Desarrollado por");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						"Ignacio Martins\nLucas Piegas\nGian Rios\nAlina Sainz\nAndres Calixto", "Equipo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_4.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/logo-team-chico.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(604, 498, 203, 14);
		panel_2.add(lblNewLabel_4);

		lblNombreUsuario.setText(usuario.getNombre() + " " + usuario.getApellido());
		
		JPanel panel_formularios_1 = new JPanel();
		panel_formularios_1.setLayout(null);
		panel_formularios_1.setBackground(new Color(234, 234, 234));
		panel_formularios_1.setBounds(559, 77, 217, 218);
		panel_2.add(panel_formularios_1);
		
		JLabel lblActividad = new JLabel("Actividades de campo");
		lblActividad.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblActividad.setBounds(10, 11, 197, 24);
		panel_formularios_1.add(lblActividad);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("");
		lblNewLabel_3_1_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/ac.png")));
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3_1_1.setBackground(Color.WHITE);
		lblNewLabel_3_1_1.setBounds(10, 46, 197, 120);
		panel_formularios_1.add(lblNewLabel_3_1_1);
		
		JButton btnFormularioAlta_1 = new JButton("");
		btnFormularioAlta_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/anadir.png")));
		btnFormularioAlta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmActividadCampoAM frmReporte = new FrmActividadCampoAM(AccionFormulario.Alta, usuario);
				frmReporte.setVisible(true);
			}
		});
		btnFormularioAlta_1.setToolTipText("Alta de formulario");
		btnFormularioAlta_1.setForeground(Color.WHITE);
		btnFormularioAlta_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioAlta_1.setBorderPainted(false);
		btnFormularioAlta_1.setBorder(null);
		btnFormularioAlta_1.setBackground(Color.WHITE);
		btnFormularioAlta_1.setBounds(20, 177, 37, 30);
		panel_formularios_1.add(btnFormularioAlta_1);
		
		JButton btnFormularioListar_1 = new JButton("");
		btnFormularioListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoActividad frmActivdad = new FrmListadoActividad(null);
				frmActivdad.setVisible(true);
			}
		});
		btnFormularioListar_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/listar.png")));
		btnFormularioListar_1.setToolTipText("Listar formularios");
		btnFormularioListar_1.setForeground(Color.WHITE);
		btnFormularioListar_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioListar_1.setBorder(null);
		btnFormularioListar_1.setBackground(Color.WHITE);
		btnFormularioListar_1.setBounds(67, 177, 37, 30);
		panel_formularios_1.add(btnFormularioListar_1);
		
		JButton btnFormularioEditar_1 = new JButton("");
		btnFormularioEditar_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/edit.png")));
		btnFormularioEditar_1.setToolTipText("Editar formulario");
		btnFormularioEditar_1.setForeground(Color.WHITE);
		btnFormularioEditar_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEditar_1.setBorder(null);
		btnFormularioEditar_1.setBackground(Color.WHITE);
		btnFormularioEditar_1.setBounds(114, 177, 37, 30);
		panel_formularios_1.add(btnFormularioEditar_1);
		
		JButton btnFormularioEliminar_1 = new JButton("");
		btnFormularioEliminar_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/eliminar.png")));
		btnFormularioEliminar_1.setToolTipText("Eliminar usuario");
		btnFormularioEliminar_1.setForeground(Color.WHITE);
		btnFormularioEliminar_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEliminar_1.setBorder(null);
		btnFormularioEliminar_1.setBackground(Color.WHITE);
		btnFormularioEliminar_1.setBounds(161, 177, 37, 30);
		panel_formularios_1.add(btnFormularioEliminar_1);

	}
}
