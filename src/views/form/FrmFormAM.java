package views.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import enums.AccionFormulario;
import enums.TipoDato;
import enums.Visibilidad;
import helpers.ItemSelectedListener;
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
import views.form.param.FrmParamAM;
import views.usuario.FrmUsuarioAM;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public  class FrmFormAM extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private FormularioBeanRemote formularioBean = null;
	private TipoParametroBeanRemote tipoParametroBean = null;
	private ParametroBeanRemote parametroBean = null;
	public static List<Parametro> listaParametros;
	private JScrollPane scrollPaneParam;
	private JTable jtable_param;
	private JTextField txtNombre;
	private JComboBox cbRol;

	/**
	 * Create the frame. 
	 */
	public FrmFormAM(AccionFormulario accion, Usuario u) {
		
		listaParametros = new ArrayList<Parametro>();
		
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

		JLabel lblTitulo = new JLabel("Alta de formulario");
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
			e2.printStackTrace();
		}
		
		

		txtTitulo = new JTextField();
		txtTitulo.setVerifyInputWhenFocusTarget(false);
		txtTitulo.setForeground(new Color(0, 0, 0));
		txtTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTitulo.setColumns(10);
		txtTitulo.setBorder(null);
		txtTitulo.setBackground(new Color(255, 255, 255));
		txtTitulo.setBounds(31, 68, 199, 33);
		panel_2.add(txtTitulo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 107, 199, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Titulo");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(31, 49, 199, 14);
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
		btnCancelar.setBackground(new Color(102, 204, 0));
		btnCancelar.setBounds(143, 352, 102, 33);
		panel_2.add(btnCancelar);

		JLabel lblNewLabel_1_1_2 = new JLabel("Descripcion");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_2.setBounds(31, 117, 199, 14);
		panel_2.add(lblNewLabel_1_1_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(new Color(248, 248, 255));
		separator_2_1.setBounds(31, 340, 431, 10);
		panel_2.add(separator_2_1);
		
		JCheckBox checkBoxObligatorio = new JCheckBox("Es obligatorio");
		checkBoxObligatorio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkBoxObligatorio.setBackground(Color.WHITE);
		checkBoxObligatorio.setBounds(31, 303, 144, 21);
		panel_2.add(checkBoxObligatorio);
		
		JTextArea taDescripcion = new JTextArea();
		taDescripcion.setBorder(new LineBorder(new Color(102, 204, 51)));
		taDescripcion.setBounds(31, 138, 199, 65);
		panel_2.add(taDescripcion);
		
		JButton btnAgregarParam = new JButton("Agregar Parametro");
		btnAgregarParam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean obligatorio = checkBoxObligatorio.isSelected();
				TipoDato tDato = (TipoDato) cbRol.getSelectedItem();
				
				TipoParametro tp = new TipoParametro(txtNombre.getText(), tDato);
				
				try {
					tp = tipoParametroBean.crear(tp);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				Parametro p = new Parametro(obligatorio,tp);
				
				try {
					p = parametroBean.crear(p);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				FrmFormAM.listaParametros.add(p);
				construirTabla();
					
			}
		});
		btnAgregarParam.setForeground(Color.WHITE);
		btnAgregarParam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAgregarParam.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnAgregarParam.setBackground(new Color(102, 204, 0));
		btnAgregarParam.setBounds(251, 303, 144, 33);
		panel_2.add(btnAgregarParam);
		

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
		lblId.setBounds(255, 364, 45, 13);
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
		separator_1.setBounds(31, 213, 450, 10);
		panel_2.add(separator_1);
		
		scrollPaneParam = new JScrollPane();
		scrollPaneParam.setBounds(251, 49, 230, 154);
		panel_2.add(scrollPaneParam);
		
		jtable_param = new JTable();
		jtable_param.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre del parametro", "Tipo de Dato", "Obligatorio"
			}
		));
		scrollPaneParam.setViewportView(jtable_param);
		
		txtNombre = new JTextField();
		txtNombre.setVerifyInputWhenFocusTarget(false);
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setBounds(31, 250, 199, 33);
		panel_2.add(txtNombre);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(new Color(248, 248, 255));
		separator_2.setBounds(31, 284, 199, 10);
		panel_2.add(separator_2);
		construirTabla();
		
		cbRol = new JComboBox();
		cbRol.setBorder(null);
		cbRol.setBackground(new Color(102, 204, 51));
		cbRol.setBounds(251, 250, 230, 33);
		panel_2.add(cbRol);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Nombre del parametro");
		lblNewLabel_1_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_2_1.setBounds(31, 230, 199, 14);
		panel_2.add(lblNewLabel_1_1_2_1);
		
		
		comboTipoParam();


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
	
	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTabla() {
		
		String titulos [] = { "Nombre", "Tipo de Parametro", "Obligatorio"};
		
		String informacion [][] = obtenerMatriz();
		
		jtable_param = new JTable(informacion, titulos);
		jtable_param.setBackground(SystemColor.activeCaptionBorder);
		scrollPaneParam.setViewportView(jtable_param);
	}
	
	/**
	 * Completamos la tabla con datos
	 * @return matriz [][]
	 */
	private String[][] obtenerMatriz() {
		ArrayList<Parametro> miLista = null;	
		miLista = (ArrayList<Parametro>) FrmFormAM.listaParametros;
		
		String matrizInfo[][] = new String [miLista.size()][3];
		
		for( int i = 0 ; i< miLista.size() ; i++) {
			
			String sn = "No";
			if (miLista.get(i).isObligatorio()) {
				sn = "Si";
			}
			
			matrizInfo[i][0] = miLista.get(i).getTipo().getNombre() + "";
			matrizInfo[i][1] = miLista.get(i).getTipo().getTipo() + "";
			matrizInfo[i][2] = sn + "";
			
		}
		return matrizInfo;
	}


	public void limpiarTextFields(ArrayList<JTextField> campos) {
		for (JTextField tf : campos) {
			tf.setText("");
		}
	}
	
	public static void listarParametros() {	
		for (Parametro p : listaParametros) {
			System.out.println(p.getTipo().getNombre());
		}
	}
	
	/**
	 * Carga el combo de seleccionar TipoParametro
	 */
	public void comboTipoParam() {
		
		List<TipoDato> tiposDatos = Arrays.asList(TipoDato.values());

		for (TipoDato tp : tiposDatos) {
			cbRol.addItem(tp);
		}
	}
}
