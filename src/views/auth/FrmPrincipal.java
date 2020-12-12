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
import java.awt.SystemColor;

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
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 0, 846, 523);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_usuarios = new JPanel();
		panel_usuarios.setBorder(new LineBorder(new Color(119, 184, 105)));
		panel_usuarios.setBackground(SystemColor.control);
		panel_usuarios.setBounds(42, 129, 217, 218);
		panel_2.add(panel_usuarios);
		panel_usuarios.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuarios");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
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
		btnUsuarioAlta.setBorderPainted(false);
		btnUsuarioAlta.setToolTipText("Agregar Usuario");
		btnUsuarioAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUsuarioAM frm = new FrmUsuarioAM(AccionFormulario.Alta, null);
				frm.setVisible(true);
			}
		});
		btnUsuarioAlta.setBounds(20, 177, 37, 30);
		panel_usuarios.add(btnUsuarioAlta);
		btnUsuarioAlta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/anadir.png")));
		btnUsuarioAlta.setForeground(Color.WHITE);
		btnUsuarioAlta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioAlta.setBorder(new LineBorder(new Color(0, 255, 0)));
		btnUsuarioAlta.setBackground(SystemColor.control);

		JButton btnUsuarioListar = new JButton("");
		btnUsuarioListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListado frm = new FrmListado(AccionFormulario.Listar);
				frm.setVisible(true);

			}
		});
		btnUsuarioListar.setToolTipText("Listar Usuarios");
		btnUsuarioListar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/listar.png")));
		btnUsuarioListar.setForeground(Color.WHITE);
		btnUsuarioListar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioListar.setBorder(null);
		btnUsuarioListar.setBackground(SystemColor.control);
		btnUsuarioListar.setBounds(67, 177, 37, 30);
		panel_usuarios.add(btnUsuarioListar);

		JButton btnUsuarioEditar = new JButton("");
		btnUsuarioEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListado frm = new FrmListado(AccionFormulario.Modificar);
				frm.setVisible(true);

			}
		});
		btnUsuarioEditar.setToolTipText("Modificar Usuario");
		btnUsuarioEditar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/edit.png")));
		btnUsuarioEditar.setForeground(Color.WHITE);
		btnUsuarioEditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioEditar.setBorder(null);
		btnUsuarioEditar.setBackground(SystemColor.control);
		btnUsuarioEditar.setBounds(114, 177, 37, 30);
		panel_usuarios.add(btnUsuarioEditar);

		JButton btnUsuarioEliminar = new JButton("");
		btnUsuarioEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListado frm = new FrmListado(AccionFormulario.Eliminar);
				frm.setVisible(true);

			}
		});
		btnUsuarioEliminar.setToolTipText("Eliminar Usuario");
		btnUsuarioEliminar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/eliminar.png")));
		btnUsuarioEliminar.setForeground(Color.WHITE);
		btnUsuarioEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUsuarioEliminar.setBorder(null);
		btnUsuarioEliminar.setBackground(SystemColor.control);
		btnUsuarioEliminar.setBounds(161, 177, 37, 30);
		panel_usuarios.add(btnUsuarioEliminar);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 846, 39);
		panel_2.add(panel);
		panel.setBackground(new Color(119, 184, 105));
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Principal");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(39, 11, 347, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/icon-app-ventana.png")));
		lblNewLabel_2.setBounds(10, 11, 19, 16);
		panel.add(lblNewLabel_2);

		JLabel lblCerrarSesion = new JLabel("Cerrar sesi\u00F3n");
		lblCerrarSesion.setForeground(Color.WHITE);
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
		lblCerrarSesion.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));

		JPanel panel_formularios = new JPanel();
		panel_formularios.setBorder(new LineBorder(new Color(119, 184, 105)));
		panel_formularios.setLayout(null);
		panel_formularios.setBackground(SystemColor.control);
		panel_formularios.setBounds(300, 129, 217, 218);
		panel_2.add(panel_formularios);

		JLabel lblFormularios = new JLabel("Formularios");
		lblFormularios.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularios.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
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

		btnFormularioAlta.setToolTipText("Alta de Formulario");
		btnFormularioAlta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/anadir.png")));
		btnFormularioAlta.setForeground(Color.WHITE);
		btnFormularioAlta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioAlta.setBorderPainted(false);
		btnFormularioAlta.setBorder(null);
		btnFormularioAlta.setBackground(SystemColor.control);
		btnFormularioAlta.setBounds(20, 177, 37, 30);
		panel_formularios.add(btnFormularioAlta);

		JButton btnFormularioListar = new JButton("");
		btnFormularioListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListadoForm frmListado = new FrmListadoForm(AccionFormulario.Listar, usuario);
				frmListado.setVisible(true);

			}
		});
		btnFormularioListar.setToolTipText("Listar formularios");
		btnFormularioListar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/listar.png")));
		btnFormularioListar.setForeground(Color.WHITE);
		btnFormularioListar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioListar.setBorder(null);
		btnFormularioListar.setBackground(SystemColor.control);
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
		btnFormularioEditar.setToolTipText("Editar Formulario");
		btnFormularioEditar.setForeground(Color.WHITE);
		btnFormularioEditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEditar.setBorder(null);
		btnFormularioEditar.setBackground(SystemColor.control);
		btnFormularioEditar.setBounds(114, 177, 37, 30);
		panel_formularios.add(btnFormularioEditar);

		JButton btnFormularioEliminar = new JButton("");
		btnFormularioEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoForm frmListado = new FrmListadoForm(AccionFormulario.Eliminar, usuario);
				frmListado.setVisible(true);
			}
		});
		btnFormularioEliminar
				.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/eliminar.png")));
		btnFormularioEliminar.setToolTipText("Eliminar Usuario");
		btnFormularioEliminar.setForeground(Color.WHITE);
		btnFormularioEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEliminar.setBorder(null);
		btnFormularioEliminar.setBackground(SystemColor.control);
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

		JPanel panel_formularios_1 = new JPanel();
		panel_formularios_1.setBorder(new LineBorder(new Color(119, 184, 105)));
		panel_formularios_1.setLayout(null);
		panel_formularios_1.setBackground(SystemColor.control);
		panel_formularios_1.setBounds(559, 129, 217, 218);
		panel_2.add(panel_formularios_1);

		JLabel lblActividad = new JLabel("Actividades de Campo");
		lblActividad.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividad.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
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
				FrmActividadCampoAM frmReporte = new FrmActividadCampoAM(AccionFormulario.Alta, usuario, null);
				frmReporte.setVisible(true);
			}
		});
		btnFormularioAlta_1.setToolTipText("Alta de Actividad de Campo");
		btnFormularioAlta_1.setForeground(Color.WHITE);
		btnFormularioAlta_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioAlta_1.setBorderPainted(false);
		btnFormularioAlta_1.setBorder(null);
		btnFormularioAlta_1.setBackground(SystemColor.control);
		btnFormularioAlta_1.setBounds(20, 177, 37, 30);
		panel_formularios_1.add(btnFormularioAlta_1);

		JButton btnFormularioListar_1 = new JButton("");
		btnFormularioListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoActividad frmActividad = new FrmListadoActividad(AccionFormulario.Listar, usuario);
				frmActividad.setVisible(true);
			}
		});
		btnFormularioListar_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/listar.png")));
		btnFormularioListar_1.setToolTipText("Listar Actividades de Campo");
		btnFormularioListar_1.setForeground(Color.WHITE);
		btnFormularioListar_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioListar_1.setBorder(null);
		btnFormularioListar_1.setBackground(SystemColor.control);
		btnFormularioListar_1.setBounds(67, 177, 37, 30);
		panel_formularios_1.add(btnFormularioListar_1);

		JButton btnFormularioEditar_1 = new JButton("");
		btnFormularioEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmListadoActividad frmActividad = new FrmListadoActividad(AccionFormulario.Modificar, usuario);
				frmActividad.setVisible(true);

			}
		});
		btnFormularioEditar_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/edit.png")));
		btnFormularioEditar_1.setToolTipText("Editar Actividades de Campo");
		btnFormularioEditar_1.setForeground(Color.WHITE);
		btnFormularioEditar_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEditar_1.setBorder(null);
		btnFormularioEditar_1.setBackground(SystemColor.control);
		btnFormularioEditar_1.setBounds(114, 177, 37, 30);
		panel_formularios_1.add(btnFormularioEditar_1);

		JButton btnFormularioEliminar_1 = new JButton("");
		btnFormularioEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoActividad frm = new FrmListadoActividad(AccionFormulario.Eliminar, usuario);
				frm.setVisible(true);
			}
		});
		btnFormularioEliminar_1
				.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/views/assets/icons/eliminar.png")));
		btnFormularioEliminar_1.setToolTipText("Eliminar Activdad de Campo");
		btnFormularioEliminar_1.setForeground(Color.WHITE);
		btnFormularioEliminar_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFormularioEliminar_1.setBorder(null);
		btnFormularioEliminar_1.setBackground(SystemColor.control);
		btnFormularioEliminar_1.setBounds(161, 177, 37, 30);
		panel_formularios_1.add(btnFormularioEliminar_1);

		JLabel lblNewLabel_5 = new JLabel(" Bienvenido  ");
		lblNewLabel_5.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(0, 40, 94, 39);
		panel_2.add(lblNewLabel_5);

		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setBounds(82, 40, 239, 39);
		panel_2.add(lblNombreUsuario);
		lblNombreUsuario.setForeground(Color.BLACK);
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));

		lblNombreUsuario.setText(usuario.getNombre() + " " + usuario.getApellido());

		if (usuario.getRol().getId() == 2L) {
			btnUsuarioAlta.setEnabled(false);
			btnUsuarioEditar.setEnabled(false);
			btnUsuarioListar.setEnabled(false);
			btnUsuarioEliminar.setEnabled(false);
		} else if (usuario.getRol().getId() == 3L) {
			btnUsuarioAlta.setEnabled(false);
			btnUsuarioEditar.setEnabled(false);
			btnUsuarioListar.setEnabled(false);
			btnUsuarioEliminar.setEnabled(false);
			btnFormularioAlta.setEnabled(false);
			btnFormularioEditar.setEnabled(true);
			btnFormularioListar.setEnabled(true);
			btnFormularioEliminar.setEnabled(false);
		}
	}
}
