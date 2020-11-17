package views.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import enums.AccionFormulario;
import enums.TipoDato;
import enums.Visibilidad;
import helpers.Validaciones;
import models.Formulario;
import models.Parametro;
import models.Rol;
import models.TipoParametro;
import models.Usuario;
import services.FormularioBeanRemote;
import services.ParametroBeanRemote;
import services.RolBeanRemote;
import services.TipoParametroBeanRemote;
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
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class FrmFormAM extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private FormularioBeanRemote formularioBean = null;
	private TipoParametroBeanRemote tipoParametroBean = null;
	private ParametroBeanRemote parametroBean = null;

	/**
	 * Create the frame. 
	 */
	public FrmFormAM(AccionFormulario accion, Usuario u) {
		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmFormAM.class.getResource("/views/assets/icons/icon-app-barra.png")));
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
				.setIcon(new ImageIcon(FrmFormAM.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
			formularioBean = (FormularioBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/FormularioBean!services.FormularioBeanRemote");
			parametroBean = (ParametroBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/ParametroBean!services.ParametroBeanRemote");
			tipoParametroBean = (TipoParametroBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/TipoParametroBean!services.TipoParametroBeanRemote");
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		txtTitulo = new JTextField();
		txtTitulo.setVerifyInputWhenFocusTarget(false);
		txtTitulo.setForeground(new Color(0, 0, 0));
		txtTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTitulo.setColumns(10);
		txtTitulo.setBorder(null);
		txtTitulo.setBackground(new Color(255, 255, 255));
		txtTitulo.setBounds(31, 91, 199, 33);
		panel_2.add(txtTitulo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 128, 199, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Titulo");
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

		JLabel lblNewLabel_1_1_2 = new JLabel("Descripcion");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_2.setBounds(31, 158, 199, 14);
		panel_2.add(lblNewLabel_1_1_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(new Color(248, 248, 255));
		separator_2_1.setBounds(31, 340, 431, 10);
		panel_2.add(separator_2_1);
		
		
		JTextArea taDescripcion = new JTextArea();
		taDescripcion.setBorder(new LineBorder(new Color(102, 204, 51)));
		taDescripcion.setBounds(31, 182, 402, 104);
		panel_2.add(taDescripcion);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				var titulo = txtTitulo.getText();
				var descripcion = taDescripcion.getText();
				
				/*
				if (Validaciones.esVacio(nombre) || Validaciones.esVacio(apellido) || Validaciones.esVacio(usuario)
						|| Validaciones.esVacio(pass) || Validaciones.esVacio(r.getNombre())) {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Ups!",
							JOptionPane.ERROR_MESSAGE);
				} else {
				*/
					//Armo el objeto formulario
					var listaParametros = new ArrayList<Parametro>();
					
					TipoParametro tp = new TipoParametro("Cantidad de agua", TipoDato.STRING);
					try {
						tp = tipoParametroBean.crear(tp);
						JOptionPane.showMessageDialog(null, tp.toString());
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Ups!",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					
					Parametro p = new Parametro(true, tp);
					try {
						p = parametroBean.crear(p);
						JOptionPane.showMessageDialog(null, tp.toString());
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Ups!",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					listaParametros.add(p);
					
					Formulario f = new Formulario();
					f.setTitulo(titulo);
					f.setDescripcion(descripcion);
					f.setVisibilidad(Visibilidad.PUBLICO);
					f.setUsuario(u);
					f.setParametros(listaParametros);
					
					
					try {
						formularioBean.crear(f);
						JOptionPane.showMessageDialog(null, "Formulario guardado con exito");
						
						
						setVisible(false);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Ups!",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
		//		}

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
		lblId.setBounds(388, 317, 45, 13);
		panel_2.add(lblId);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnModificar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnModificar.setBackground(new Color(119, 184, 105));
		btnModificar.setBounds(31, 352, 102, 33);
		panel_2.add(btnModificar);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(new Color(248, 248, 255));
		separator_1.setBounds(31, 296, 402, 10);
		panel_2.add(separator_1);
		

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
			
		}

	}



	public void limpiarTextFields(ArrayList<JTextField> campos) {

		for (JTextField tf : campos) {
			tf.setText("");
		}

	}

	
}
