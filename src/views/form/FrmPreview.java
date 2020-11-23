package views.form;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import enums.AccionFormulario;
import enums.TipoDato;
import models.Departamento;
import models.Equipamiento;
import models.Estacion;
import models.Metodo;
import models.Parametro;
import models.Region;
import models.Usuario;
import services.DepartamentoBeanRemote;
import services.EquipamientoBeanRemote;
import services.EstacionBeanRemote;
import services.FormularioBeanRemote;
import services.MetodoBeanRemote;
import services.RegionBeanRemote;
import services.UsuarioBeanRemote;
import templates.TemplateString;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JComboBox;

public class FrmPreview extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPaneEquip;
	private JTable jtable_equip;
	private JScrollPane scrollPaneMetodo;
	private JTable jtable_metodo;
	private UsuarioBeanRemote uBean;
	private FormularioBeanRemote formularioBean;
	private DepartamentoBeanRemote deptoBean;
	private EquipamientoBeanRemote equipBean;
	private MetodoBeanRemote metodoBean;
	private RegionBeanRemote regionBean;
	private EstacionBeanRemote estacionBean;
	private JComboBox cbDepto;
	private JComboBox cbEstacion;
	private JComboBox cbRegion;
	private JComboBox cbEquip;
	private JComboBox cbMetodo;
	private List<Metodo> metodos;
	private List<Equipamiento> equipamientos;
	private JTextField txtEquip;
	private JTextField txtMetodo;

	/**
	 * Create the frame.
	 */
	public FrmPreview(AccionFormulario accion) {

		metodos = new ArrayList<Metodo>();
		equipamientos = new ArrayList<Equipamiento>();

		try {
			estacionBean = (EstacionBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/EstacionBean!services.EstacionBeanRemote");
			regionBean = (RegionBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/RegionBean!services.RegionBeanRemote");
			equipBean = (EquipamientoBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/EquipamientoBean!services.EquipamientoBeanRemote");
			metodoBean = (MetodoBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/MetodoBean!services.MetodoBeanRemote");
			deptoBean = (DepartamentoBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/DepartamentoBean!services.DepartamentoBeanRemote");
			uBean = (UsuarioBeanRemote) InitialContext.doLookup("ejb:/IAGROEJB/UsuarioBean!services.UsuarioBeanRemote");

			formularioBean = (FormularioBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/FormularioBean!services.FormularioBeanRemote");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmListadoForm.class.getResource("/views/assets/icons/icon-app-barra.png")));
		setBounds(100, 100, 931, 571);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 931, 571);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 931, 39);
		panel_2.add(panel);
		panel.setBackground(new Color(119, 184, 105));
		panel.setLayout(null);

		JLabel lblTitulo = new JLabel("Vista previa del formulario");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmListadoForm.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		lblCerrarSesion.setBounds(883, 11, 19, 14);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 36, 931, 147);
		panel_2.add(panel_1);
		panel_1.setLayout(null);

		cbDepto = new JComboBox();
		cbDepto.setBounds(752, 31, 169, 32);
		panel_1.add(cbDepto);
		comboDepartamento();

		cbEstacion = new JComboBox();
		cbEstacion.setBounds(557, 31, 169, 32);
		panel_1.add(cbEstacion);
		comboEstacion();

		cbRegion = new JComboBox();
		cbRegion.setBounds(752, 98, 169, 32);
		panel_1.add(cbRegion);
		comboRegion();

		JLabel lblNewLabel = new JLabel("Departamento");
		lblNewLabel.setBounds(752, 9, 169, 13);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("Estacion");
		lblNewLabel_3.setBounds(557, 9, 169, 13);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Region");
		lblNewLabel_5.setBounds(752, 73, 169, 13);
		panel_1.add(lblNewLabel_5);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 9, 259, 128);
		panel_1.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Equipamiento");
		lblNewLabel_1.setBounds(10, 10, 96, 13);
		panel_7.add(lblNewLabel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 182, 931, 98);
		panel_2.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 280, 931, 98);
		panel_2.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 377, 931, 98);
		panel_2.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 474, 931, 98);
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		JButton btnCrearForm = new JButton("Crear formulario");
		btnCrearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				var depto = (Departamento) (cbDepto.getSelectedItem());
				var reg = (Region) (cbRegion.getSelectedItem());
				var est = (Estacion) (cbEstacion.getSelectedItem());
				

				FrmFormAM.f.setMetodos(metodos);
				FrmFormAM.f.setEquipamientos(equipamientos);
				FrmFormAM.f.setEstacion(est);
				FrmFormAM.f.setDepartamento(depto);
				FrmFormAM.f.setRegion(reg);

				try {
					formularioBean.crear(FrmFormAM.f);
					JOptionPane.showMessageDialog(null, "Formulario guardado con exito");
					
					FrmFormAM.f = null;
					
					setVisible(false);
					

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Ups!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnCrearForm.setBounds(10, 51, 155, 37);
		panel_6.add(btnCrearForm);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(766, 51, 155, 37);
		panel_6.add(btnVolver);
		setUndecorated(true);

		// Tabla equipamientos
		scrollPaneEquip = new JScrollPane();
		scrollPaneEquip.setBounds(131, 3, 118, 115);
		panel_7.add(scrollPaneEquip);

		jtable_equip = new JTable();
		jtable_equip.setModel(new DefaultTableModel(new Object[][] {}, new String[] { " Equipamientos " }));
		scrollPaneEquip.setViewportView(jtable_equip);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setLayout(null);
		panel_7_1.setBounds(279, 9, 259, 128);
		panel_1.add(panel_7_1);
		
		//Tabla metodo
		scrollPaneMetodo = new JScrollPane();
		scrollPaneMetodo.setBounds(131, 3, 118, 115);
		panel_7_1.add(scrollPaneMetodo);
		
		jtable_metodo = new JTable();
		jtable_metodo.setModel(new DefaultTableModel(new Object[][] {}, new String[] { " Metodos " }));
		scrollPaneMetodo.setViewportView(jtable_metodo);
		
		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Metodo mt = new Metodo();
				mt.setNombre(txtMetodo.getText());
				metodos.add(mt);

				construirTablaMetodo();
			}
		});
		
		

		btnAgregar_1.setBounds(10, 88, 111, 30);
		panel_7_1.add(btnAgregar_1);

		JLabel lblNewLabel_4 = new JLabel("Metodo");
		lblNewLabel_4.setBounds(10, 3, 107, 13);
		panel_7_1.add(lblNewLabel_4);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Equipamiento eq = new Equipamiento();
				eq.setNombre(txtEquip.getText());
				equipamientos.add(eq);

				construirTablaEquipamiento();

			}
		});
		btnAgregar.setBounds(10, 88, 111, 30);
		panel_7.add(btnAgregar);
		
		cbEquip = new JComboBox();
		cbEquip.setVisible(false);
		cbEquip.setBounds(10, 33, 111, 32);
		panel_7.add(cbEquip);
		
		txtEquip = new JTextField();
		txtEquip.setBounds(10, 33, 111, 32);
		panel_7.add(txtEquip);
		txtEquip.setColumns(10);
		comboEquip();

		
		cbMetodo = new JComboBox();
		cbMetodo.setVisible(false);
		cbMetodo.setBounds(10, 26, 111, 32);
		panel_7_1.add(cbMetodo);
		
		txtMetodo = new JTextField();
		txtMetodo.setColumns(10);
		txtMetodo.setBounds(10, 26, 111, 32);
		panel_7_1.add(txtMetodo);
		comboMetodo();


		// Cada 5 parametro cambia de panel donde se insertan los parametros
		int cont = 0;
		for (Parametro p : FrmFormAM.listaParametros) {

			if (cont < 5) {
				TemplateString ts = new TemplateString();
				ts.lblNewLabel.setText(p.getTipo().getNombre());
				panel_3.add(ts);
				cont++;

			}

			if (cont > 4 && cont < 10) {
				TemplateString ts = new TemplateString();
				ts.lblNewLabel.setText(p.getTipo().getNombre());
				panel_4.add(ts);
				cont++;

			}

			if (cont > 9 && cont < 15) {
				TemplateString ts = new TemplateString();
				ts.lblNewLabel.setText(p.getTipo().getNombre());
				panel_5.add(ts);
				cont++;

			}

			if (cont > 14 && cont < 20) {
				TemplateString ts = new TemplateString();
				ts.lblNewLabel.setText(p.getTipo().getNombre());
				panel_6.add(ts);
				cont++;
			}
		}

	}

	/**
	 * Carga el combo de seleccionar TipoParametro
	 */
	public void comboDepartamento() {

		List<Departamento> departamentos = deptoBean.obtenerTodos();

		for (Departamento d : departamentos) {
			cbDepto.addItem(d);
		}
	}

	public void comboEstacion() {

		List<Estacion> estaciones = estacionBean.obtenerTodos();

		for (Estacion e : estaciones) {
			cbEstacion.addItem(e);
		}
	}

	public void comboRegion() {

		List<Region> regiones = regionBean.obtenerTodos();

		for (Region r : regiones) {
			cbRegion.addItem(r);
		}
	}
	
	public void comboEquip() {

		List<Equipamiento> equips = equipBean.obtenerTodos();

		for (Equipamiento r : equips) {
			cbEquip.addItem(r);
		}
	}
	
	public void comboMetodo() {

		List<Metodo> metodos = metodoBean.obtenerTodos();

		for (Metodo r : metodos) {
			cbMetodo.addItem(r);
		}
	}

	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTablaEquipamiento() {

		String titulos[] = { " Nombre " };

		String informacion[][] = obtenerMatrizEquipamineto();

		jtable_equip = new JTable(informacion, titulos);
		jtable_equip.setBackground(SystemColor.activeCaptionBorder);
		scrollPaneEquip.setViewportView(jtable_equip);
	}

	/**
	 * Completamos la tabla con datos
	 * 
	 * @return matriz [][]
	 */
	private String[][] obtenerMatrizEquipamineto() {
		ArrayList<Equipamiento> miLista = null;
		miLista = (ArrayList<Equipamiento>) equipamientos;

		String matrizInfo[][] = new String[miLista.size()][1];

		for (int i = 0; i < miLista.size(); i++) {

			matrizInfo[i][0] = miLista.get(i) + "";

		}
		return matrizInfo;
	}

	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTablaMetodo() {

		String titulos[] = { " Nombre " };

		String informacion[][] = obtenerMatrizMetodo();

		jtable_metodo = new JTable(informacion, titulos);
		jtable_metodo.setBackground(SystemColor.activeCaptionBorder);
		scrollPaneMetodo.setViewportView(jtable_metodo);
	}

	/**
	 * Completamos la tabla con datos
	 * 
	 * @return matriz [][]
	 */
	private String[][] obtenerMatrizMetodo() {
		ArrayList<Metodo> miLista = null;
		miLista = (ArrayList<Metodo>) metodos;

		String matrizInfo[][] = new String[miLista.size()][1];

		for (int i = 0; i < miLista.size(); i++) {

			matrizInfo[i][0] = miLista.get(i) + "";

		}
		return matrizInfo;
	}
}
