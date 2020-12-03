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
import javax.swing.table.JTableHeader;

import enums.AccionFormulario;
import enums.TipoDato;
import enums.Visibilidad;
import helpers.DevuelveTipoDato;
import helpers.Validaciones;
import models.ActividadCampo;
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
import java.util.Set;
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
import javax.swing.ListSelectionModel;

public class FrmFormAM extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private FormularioBeanRemote formularioBean = null;
	private TipoParametroBeanRemote tipoParametroBean = null;
	private ParametroBeanRemote parametroBean = null;
	public static Set setParametros;
	public static ArrayList<Parametro> listaParametros;
	public static ArrayList<JCheckBox> listaChecks;
	public static JCheckBox checkFecha;
	public static JCheckBox checkEstacion;
	public static JCheckBox checkDepto;
	public static JCheckBox checkRegion;
	public static JCheckBox checkMetodo;
	public static JCheckBox checkLocalidad;
	public static JCheckBox checkEquip;
	public static String tituloForm;
	public static String descripcionForm;

	private JScrollPane scrollPaneParam;
	private JTable jtable_param;
	private JTextField txtNombre;
	private JComboBox cbRol;
	private JComboBox cbVisibilidad;
	private ArrayList<Parametro> miLista = null;
	public static Formulario f;

	/**
	 * Create the frame.
	 */
	public FrmFormAM(AccionFormulario accion, Usuario u, Formulario formParam) {

		listaParametros = new ArrayList<Parametro>();

		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmFormAM.class.getResource("/views/assets/icons/icon-app-barra.png")));
		setBounds(100, 100, 913, 484);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 913, 484);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 913, 39);
		panel_2.add(panel);
		panel.setBackground(new Color(119, 184, 105));
		panel.setLayout(null);

		JLabel lblTitulo = new JLabel("Alta de Formulario");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmFormAM.class.getResource("/views/assets/icons/icon-app-ventana.png")));
		lblNewLabel_2.setBounds(10, 11, 19, 16);
		panel.add(lblNewLabel_2);

		JLabel lblCerrarSesion = new JLabel("X");
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblCerrarSesion.setBounds(858, 0, 45, 25);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Gill Sans MT", Font.BOLD, 13));

		// Llamo al bean
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
		txtTitulo.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtTitulo.setColumns(10);
		txtTitulo.setBorder(null);
		txtTitulo.setBackground(new Color(255, 255, 255));
		txtTitulo.setBounds(31, 131, 199, 33);
		panel_2.add(txtTitulo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 170, 199, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("T\u00EDtulo");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(31, 112, 199, 14);
		panel_2.add(lblNewLabel_1_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(204, 204, 204));
		btnCancelar.setBounds(31, 441, 162, 33);
		panel_2.add(btnCancelar);

		JLabel lblNewLabel_1_1_2 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(31, 190, 199, 27);
		panel_2.add(lblNewLabel_1_1_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(new Color(248, 248, 255));
		separator_2_1.setBounds(10, 384, 893, 10);
		panel_2.add(separator_2_1);

		JCheckBox checkBoxObligatorio = new JCheckBox("\u00BFEs obligatorio?");
		checkBoxObligatorio.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkBoxObligatorio.setBackground(Color.WHITE);
		checkBoxObligatorio.setBounds(461, 348, 144, 21);
		panel_2.add(checkBoxObligatorio);

		JTextArea taDescripcion = new JTextArea();
		taDescripcion.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		taDescripcion.setBorder(new LineBorder(Color.LIGHT_GRAY));
		taDescripcion.setBounds(31, 224, 199, 85);
		panel_2.add(taDescripcion);

		JButton btnAgregarParam = new JButton("Agregar");
		btnAgregarParam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nombre de Parámetro obligatorio", "Ups!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				boolean obligatorio = checkBoxObligatorio.isSelected();
				TipoDato tDato = DevuelveTipoDato.devuelveTipoDato(((String) (cbRol.getSelectedItem())));

				TipoParametro tp = new TipoParametro(txtNombre.getText(), tDato);

				Parametro p = new Parametro(obligatorio, tp);

				FrmFormAM.listaParametros.add(p);
				construirTabla();
				txtNombre.setText("");
				txtNombre.requestFocus();
				checkBoxObligatorio.setSelected(false);
				

			}
		});
		btnAgregarParam.setForeground(Color.WHITE);
		btnAgregarParam.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnAgregarParam.setBorder(null);
		btnAgregarParam.setBackground(new Color(119, 184, 105));
		btnAgregarParam.setBounds(796, 340, 107, 33);
		panel_2.add(btnAgregarParam);

		JLabel lblIdForm = new JLabel("New label");
		lblIdForm.setVisible(false);
		lblIdForm.setBounds(468, 418, 45, 13);
		panel_2.add(lblIdForm);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(new Color(248, 248, 255));
		separator_1.setBounds(31, 319, 199, 10);
		panel_2.add(separator_1);

		scrollPaneParam = new JScrollPane();
		scrollPaneParam.setBounds(461, 112, 442, 154);
		panel_2.add(scrollPaneParam);

		jtable_param = new JTable();
		jtable_param.setBackground(Color.WHITE);
		jtable_param.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		jtable_param.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre del parámetro", "Tipo de Dato", "Obligatorio" }));
		scrollPaneParam.setViewportView(jtable_param);

		txtNombre = new JTextField();
		txtNombre.setVerifyInputWhenFocusTarget(false);
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setBounds(461, 296, 199, 33);
		panel_2.add(txtNombre);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(new Color(248, 248, 255));
		separator_2.setBounds(461, 332, 199, 10);
		panel_2.add(separator_2);
		construirTabla();

		cbRol = new JComboBox();
		cbRol.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		cbRol.setBorder(null);
		cbRol.setBackground(Color.WHITE);
		cbRol.setBounds(673, 276, 230, 21);
		panel_2.add(cbRol);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Nombre del par\u00E1metro");
		lblNewLabel_1_1_2_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblNewLabel_1_1_2_1.setBounds(461, 276, 199, 14);
		panel_2.add(lblNewLabel_1_1_2_1);

		JButton btnPreview = new JButton("Vista previa");
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPreview frmPreview = null;
				tituloForm = txtTitulo.getText();
				descripcionForm = taDescripcion.getText();
				Visibilidad vis = (Visibilidad) cbVisibilidad.getSelectedItem();

				if (Validaciones.esVacio(tituloForm)) {
					JOptionPane.showMessageDialog(null, "Título Obligatorio", "Ups!", JOptionPane.ERROR_MESSAGE);
				} else {

					if (accion.equals(AccionFormulario.Modificar))
						f = formParam;
					else
						f = new Formulario();
					f.setTitulo(tituloForm);
					f.setDescripcion(descripcionForm);
					f.setVisibilidad(vis);
					f.setUsuario(u);

					if (accion.equals(AccionFormulario.Modificar))
						frmPreview = new FrmPreview(AccionFormulario.Modificar);
					else
						frmPreview = new FrmPreview(AccionFormulario.Alta);
					frmPreview.setVisible(true);
				}
			}
		});
		btnPreview.setForeground(Color.WHITE);
		btnPreview.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnPreview.setBorder(null);
		btnPreview.setBackground(new Color(119, 184, 105));
		btnPreview.setBounds(741, 441, 162, 33);
		panel_2.add(btnPreview);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(240, 112, 199, 262);
		panel_2.add(panel_1);

		cbVisibilidad = new JComboBox();
		cbVisibilidad.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		cbVisibilidad.setBorder(null);
		cbVisibilidad.setBackground(Color.WHITE);
		cbVisibilidad.setBounds(31, 348, 199, 21);
		panel_2.add(cbVisibilidad);
		panel_1.setLayout(null);

		checkMetodo = new JCheckBox("M\u00E9todos de muestreo");
		checkMetodo.setBackground(new Color(255, 255, 255));
		checkMetodo.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkMetodo.setBounds(3, 49, 190, 21);
		panel_1.add(checkMetodo);

		checkRegion = new JCheckBox("Regiones");
		checkRegion.setBackground(new Color(255, 255, 255));
		checkRegion.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkRegion.setBounds(3, 84, 190, 21);
		panel_1.add(checkRegion);

		checkLocalidad = new JCheckBox("Localidades");
		checkLocalidad.setBackground(new Color(255, 255, 255));
		checkLocalidad.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkLocalidad.setBounds(3, 154, 190, 21);
		panel_1.add(checkLocalidad);

		checkEstacion = new JCheckBox("Estaciones de muestreo");
		checkEstacion.setBackground(new Color(255, 255, 255));
		checkEstacion.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkEstacion.setBounds(3, 119, 190, 21);
		panel_1.add(checkEstacion);

		checkFecha = new JCheckBox("Fecha");
		checkFecha.setBackground(new Color(255, 255, 255));
		checkFecha.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkFecha.setBounds(3, 189, 190, 21);
		panel_1.add(checkFecha);

		checkDepto = new JCheckBox("Departamentos");
		checkDepto.setBackground(new Color(255, 255, 255));
		checkDepto.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkDepto.setBounds(3, 224, 190, 21);
		panel_1.add(checkDepto);

		checkEquip = new JCheckBox("Equipamientos");
		checkEquip.setBackground(new Color(255, 255, 255));
		checkEquip.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		checkEquip.setBounds(3, 14, 190, 21);
		panel_1.add(checkEquip);

		JLabel lblNewLabel = new JLabel("Selecciona los campos ");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblNewLabel.setBounds(240, 79, 201, 34);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Agregar mas campos");
		lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(461, 79, 442, 34);
		panel_2.add(lblNewLabel_1);

		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setOrientation(SwingConstants.VERTICAL);
		separator_2_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2.setBackground(new Color(248, 248, 255));
		separator_2_2.setBounds(447, 89, 14, 285);
		panel_2.add(separator_2_2);
		
		JButton btnEliminarParmetro = new JButton("Eliminar");
		btnEliminarParmetro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jtable_param.getSelectedRow(); 

				if(row >= 0) {
				    // Convierto el elemento selecciondo de la lista a su objeto correspondiente;
				    Parametro parametro = miLista.get(jtable_param.convertRowIndexToModel(row));
				    //Lo remuevo de las listas
				    FrmFormAM.listaParametros.remove(parametro);
				    miLista.remove(parametro);
				    
		            construirTabla();
				     
				}else {
				    JOptionPane.showMessageDialog(null,"Seleccionar el parametro que desea quitar", "Quitar parametro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnEliminarParmetro.setForeground(Color.DARK_GRAY);
		btnEliminarParmetro.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnEliminarParmetro.setBorder(null);
		btnEliminarParmetro.setBackground(new Color(204,204,204));
		btnEliminarParmetro.setBounds(673, 340, 107, 33);
		panel_2.add(btnEliminarParmetro);

		comboTipoParam();
		comboVisibilidad();

		if (accion == AccionFormulario.Modificar) {
			txtTitulo.setText(formParam.getTitulo());
			taDescripcion.setText(formParam.getDescripcion());
			if (formParam.getUsaDepto().equals("S"))
				checkDepto.setSelected(true);
			if (formParam.getUsaEquip().equals("S"))
				checkEquip.setSelected(true);
			if (formParam.getUsaEstacion().equals("S"))
				checkEstacion.setSelected(true);
			if (formParam.getUsaFecha().equals("S"))
				checkFecha.setSelected(true);
			if (formParam.getUsaLocalidad().equals("S"))
				checkLocalidad.setSelected(true);
			if (formParam.getUsaMetodo().equals("S"))
				checkMetodo.setSelected(true);
			if (formParam.getUsaRegion().equals("S"))
				checkRegion.setSelected(true);
			cbVisibilidad.setSelectedItem(formParam.getVisibilidad());
			if (formParam.getParametros() != null) {
				FrmFormAM.listaParametros = new ArrayList<Parametro>();
				FrmFormAM.listaParametros.addAll(formParam.getParametros());
				construirTabla();
			}

		}

	}

	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTabla() {

		String titulos[] = { "Nombre", "Tipo de Parámetro", "Obligatorio" };

		String informacion[][] = obtenerMatriz();

		jtable_param = new JTable(informacion, titulos);
		jtable_param.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_param.setSelectionBackground(new Color(119, 184, 105));
		jtable_param.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		jtable_param.setBackground(Color.WHITE);
		scrollPaneParam.setViewportView(jtable_param);
		JTableHeader th;
		th = jtable_param.getTableHeader();
		Font fuente = new Font("Gill Sans MT", Font.PLAIN, 14);
		th.setFont(fuente);
		th.setBackground(SystemColor.control);

		scrollPaneParam.getViewport().setBackground(SystemColor.control);
	}

	/**
	 * Completamos la tabla con datos
	 * 
	 * @return matriz [][]
	 */
	private String[][] obtenerMatriz() {
		miLista = (ArrayList<Parametro>) FrmFormAM.listaParametros;

		String matrizInfo[][] = new String[miLista.size()][3];

		for (int i = 0; i < miLista.size(); i++) {

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
			cbRol.addItem(DevuelveTipoDato.devuelveString(tp.toString()));
		}
	}

	public void comboVisibilidad() {

		List<Visibilidad> visibilidad = Arrays.asList(Visibilidad.values());

		for (Visibilidad v : visibilidad) {
			cbVisibilidad.addItem((v));
		}
	}
}
