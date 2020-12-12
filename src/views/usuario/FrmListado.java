package views.usuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import enums.AccionFormulario;
import models.Usuario;
import services.UsuarioBeanRemote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;

public class FrmListado extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable jtable_usuarios;
	private JScrollPane scrollPaneUsuarios;
	private UsuarioBeanRemote uBean;
	private ArrayList<Usuario> miLista = null;
	private JLabel lblTitulo;

	/**
	 * Create the frame.
	 */
	public FrmListado(AccionFormulario accion) {

		try {
			uBean = (UsuarioBeanRemote) InitialContext.doLookup("ejb:/IAGROEJB/UsuarioBean!services.UsuarioBeanRemote");
			miLista = (ArrayList<Usuario>) uBean.obtenerTodos();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmListado.class.getResource("/views/assets/icons/icon-app-barra.png")));
		setBounds(100, 100, 738, 393);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(119, 184, 105)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 738, 393);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 738, 39);
		panel_2.add(panel);
		panel.setBackground(new Color(119, 184, 105));
		panel.setLayout(null);

		if (AccionFormulario.Eliminar.equals(accion))
			lblTitulo = new JLabel("Eliminar usuario");
		else if (AccionFormulario.Modificar.equals(accion))
			lblTitulo = new JLabel("Modificar usuario");
		else if (AccionFormulario.Alta.equals(accion))
			lblTitulo = new JLabel("Alta de usuario");
		else
			lblTitulo = new JLabel("Listado de usuario");
		lblTitulo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmListado.class.getResource("/views/assets/icons/icon-app-ventana.png")));
		lblNewLabel_2.setBounds(10, 11, 19, 16);
		panel.add(lblNewLabel_2);

		JLabel lblCerrarSesion = new JLabel("X");
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setBackground(Color.WHITE);
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblCerrarSesion.setBounds(707, 2, 31, 25);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Gill Sans MT", Font.BOLD, 14));

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (txtBuscar.getText().length() > 0) {
					/*
					 * Filtro de miLista, a los que cumplan alguna condicion de .filter(), 
					 * los agrego a la lista filtradoACtividad  con la funcion de la clase Collectors.toList(), 
					 * y vuelvo a construir la tabla 
					 */
					var filtradoPersona = miLista.stream()
							.filter(p -> p.getNombre().contains(txtBuscar.getText())
									|| p.getUsuario().contains(txtBuscar.getText())
									|| p.getApellido().contains(txtBuscar.getText())
									|| p.getMail().contains(txtBuscar.getText()))
							.collect(Collectors.toList());
					miLista = (ArrayList<Usuario>) filtradoPersona;
					construirTabla();
				} else {
					miLista = (ArrayList<Usuario>) uBean.obtenerTodos();
					construirTabla();
				}
			}
		});
		txtBuscar.setToolTipText("Buscar por Nombre, Apellido, Rol");
		txtBuscar.setVerifyInputWhenFocusTarget(false);
		txtBuscar.setForeground(new Color(0, 0, 0));
		txtBuscar.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtBuscar.setColumns(10);
		txtBuscar.setBorder(null);
		txtBuscar.setBackground(new Color(255, 255, 255));
		txtBuscar.setBounds(31, 67, 431, 33);
		panel_2.add(txtBuscar);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 102, 682, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Buscar");
		lblNewLabel_1_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(31, 50, 199, 14);
		panel_2.add(lblNewLabel_1_1);

		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = jtable_usuarios.getSelectedRow();

				if (row >= 0) {
					// Obtiene el valor de la celda 0 de la fila seleccionada
					String usuarioNombre = jtable_usuarios.getModel().getValueAt(row, 2).toString();

					// Preguntamos si quiere eliminar el rol
					int pregunta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar este usuario?",
							"Eliminar usuario", JOptionPane.YES_NO_OPTION);

					// Si la opcion es "YES" entonces llamamos al metodo del controlador de eliminar
					if (pregunta == JOptionPane.YES_OPTION) {

						Usuario usu;
						var eliminar = false;
						try {
							usu = uBean.getUsuario(usuarioNombre);
							uBean.borrar(usu.getId());
							eliminar = true;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						if (eliminar) {
							JOptionPane.showMessageDialog(null, "Usuario eliminado", "Eliminar usuario",
									JOptionPane.INFORMATION_MESSAGE);

							miLista = (ArrayList<Usuario>) uBean.obtenerTodos();
							construirTabla();

						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar un usuario", "Eliminar usuario",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usted debe seleccionar el usuario que desea eliminar",
							"Eliminar usuario", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEliminar.setIcon(new ImageIcon(FrmListado.class.getResource("/views/assets/icons/eliminar.png")));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(31, 111, 46, 33);
		panel_2.add(btnEliminar);

		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jtable_usuarios.getSelectedRow();

				if (row >= 0) {
					// Obtiene el valor de la celda 0 de la fila seleccionada
					String usuario = jtable_usuarios.getModel().getValueAt(row, 2).toString();
					Usuario u = null;
					try {
						u = uBean.getUsuario(usuario);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					FrmUsuarioAM frm = new FrmUsuarioAM(AccionFormulario.Modificar, u);
					frm.setVisible(true);
					setVisible(false);
					

				} else {
					JOptionPane.showMessageDialog(null, "Usted debe seleccionar el usuario que desea modificar",
							"Modificar usuario", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmListado.class.getResource("/views/assets/icons/edit.png")));
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditar.setBorder(null);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(41, 111, 35, 33);
		panel_2.add(btnEditar);

		scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(31, 155, 682, 227);
		panel_2.add(scrollPaneUsuarios);

		jtable_usuarios = new JTable();
		jtable_usuarios.setBackground(Color.WHITE);
		jtable_usuarios.setSelectionBackground(new Color(119, 184, 105));
		jtable_usuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_usuarios.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		jtable_usuarios.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Apellido", "Usuario", "Correo","CI", "Instituto", "Profesión", "Rol" }));
		scrollPaneUsuarios.setViewportView(jtable_usuarios);
		miLista = (ArrayList<Usuario>) uBean.obtenerTodos();
		construirTabla();

		// Mostrar boton
		btnEditar.setVisible(false);
		btnEliminar.setVisible(false);

		if (accion == AccionFormulario.Eliminar) {
			btnEliminar.setVisible(true);
		} else if (accion == AccionFormulario.Modificar) {
			btnEditar.setVisible(true);
		}

	}

	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTabla() {

		String titulos[] = { "Nombre", "Apellido", "Usuario", "Correo", "CI", "Instituto", "Profesión", "Rol" };

		String informacion[][] = obtenerMatriz();

		jtable_usuarios = new JTable(informacion, titulos);
		jtable_usuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_usuarios.setSelectionBackground(new Color(119, 184, 105));
		jtable_usuarios.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		jtable_usuarios.setBackground(Color.WHITE);
		scrollPaneUsuarios.setViewportView(jtable_usuarios);

		JTableHeader th;
		th = jtable_usuarios.getTableHeader();
		Font fuente = new Font("Gill Sans MT", Font.PLAIN, 15);
		th.setFont(fuente);
		th.setBackground(SystemColor.control);

		scrollPaneUsuarios.getViewport().setBackground(SystemColor.control);

	}

	

	/**
	 * Completamos la tabla con datos
	 * 
	 * @return matriz [][]
	 */
	private String[][] obtenerMatriz() {

		String matrizInfo[][] = new String[miLista.size()][8];

		for (int i = 0; i < miLista.size(); i++) {
			

			matrizInfo[i][0] = miLista.get(i).getNombre() + "";
			matrizInfo[i][1] = miLista.get(i).getApellido() + "";
			matrizInfo[i][2] = miLista.get(i).getUsuario() + "";
			matrizInfo[i][3] = miLista.get(i).getMail() + "";
			if (miLista.get(i).getCi() == null) {
				matrizInfo[i][4] = "" + "";
			} else {
				matrizInfo[i][4] = miLista.get(i).getCi() + ""; 
			}
			if (miLista.get(i).getInstituto() == null) {
				matrizInfo[i][5] = "" + "";
			} else {
				matrizInfo[i][5] = miLista.get(i).getInstituto() + ""; 
			}
			if (miLista.get(i).getProfesion() == null) {
				matrizInfo[i][6] = "" + "";
			} else {
				matrizInfo[i][6] = miLista.get(i).getProfesion() + ""; 
			}
			matrizInfo[i][7] = miLista.get(i).getRol().getNombre() + "";

		}
		return matrizInfo;
	}
}
