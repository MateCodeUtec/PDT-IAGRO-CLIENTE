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
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
import models.Localidad;
import models.Metodo;
import models.Parametro;
import models.Region;
import models.Usuario;
import services.DepartamentoBeanRemote;
import services.EquipamientoBeanRemote;
import services.EstacionBeanRemote;
import services.FormularioBeanRemote;
import services.LocalidadBeanRemote;
import services.MetodoBeanRemote;
import services.RegionBeanRemote;
import services.UsuarioBeanRemote;
import templates.TemplateBlob;
import templates.TemplateBoolean;
import templates.TemplateDate;
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
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class FrmPreview extends JFrame {

	private JPanel contentPane;
	private UsuarioBeanRemote uBean;
	private FormularioBeanRemote formularioBean;
	private DepartamentoBeanRemote deptoBean;
	private EquipamientoBeanRemote equipBean;
	private MetodoBeanRemote metodoBean;
	private RegionBeanRemote regionBean;
	private EstacionBeanRemote estacionBean;
	private LocalidadBeanRemote localidadBean;
	private JComboBox cbDepto;
	private JComboBox cbEstacion;
	private JComboBox cbRegion;
	private JComboBox cbMetodo;
	private JComboBox cbLocalidad;
	private JComboBox cbEquip = new JComboBox<Equipamiento>();
	private Set<Metodo> metodos;
	private Set<Equipamiento> equipamientos;
	private JButton btnCrearForm;

	/**
	 * Create the frame.
	 */
	public FrmPreview(AccionFormulario accion) {

		metodos = new HashSet<Metodo>();
		equipamientos = new HashSet<Equipamiento>();

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
			localidadBean = (LocalidadBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/LocalidadBean!services.LocalidadBeanRemote");

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
		panel_2.setBorder(null);
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
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblTitulo.setBounds(35, 0, 257, 35);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmListadoForm.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		lblCerrarSesion.setBounds(904, 0, 27, 25);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Gill Sans MT", Font.BOLD, 14));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 36, 931, 147);
		panel_2.add(panel_1);
		panel_1.setLayout(null);

		cbDepto = new JComboBox();
		cbDepto.setBackground(Color.WHITE);
		cbDepto.setVisible(false);
		cbDepto.setBounds(597, 119, 151, 23);
		panel_1.add(cbDepto);
		comboDepartamento();

		cbEstacion = new JComboBox();
		cbEstacion.setBackground(Color.WHITE);
		cbEstacion.setVisible(false);
		cbEstacion.setBounds(591, 42, 151, 23);
		panel_1.add(cbEstacion);
		comboEstacion();

		cbRegion = new JComboBox();
		cbRegion.setBackground(Color.WHITE);
		cbRegion.setVisible(false);
		cbRegion.setBounds(418, 119, 151, 23);
		panel_1.add(cbRegion);
		comboRegion();

		JLabel lblDepto = new JLabel("Departamento");
		lblDepto.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblDepto.setBounds(597, 88, 151, 23);
		lblDepto.setVisible(false);
		panel_1.add(lblDepto);

		JLabel lblEstacion = new JLabel("Estaci\u00F3n");
		lblEstacion.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblEstacion.setBounds(591, 10, 151, 23);
		lblEstacion.setVisible(false);
		panel_1.add(lblEstacion);

		JLabel lblRegion = new JLabel("Regi\u00F3n");
		lblRegion.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblRegion.setBounds(418, 88, 151, 23);
		lblRegion.setVisible(false);
		panel_1.add(lblRegion);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(6, 190, 302, 322);
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(314, 190, 302, 322);
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(null);
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(622, 190, 302, 322);
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		setUndecorated(true);

		JLabel lblEquip = new JLabel("Equipamiento");
		lblEquip.setVisible(false);
		lblEquip.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblEquip.setBounds(239, 10, 151, 23);
		panel_1.add(lblEquip);
		comboEquip();

		cbMetodo = new JComboBox();
		cbMetodo.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		cbMetodo.setVisible(false);
		cbMetodo.setBackground(Color.WHITE);
		cbMetodo.setBounds(418, 42, 151, 23);
		panel_1.add(cbMetodo);
		comboMetodo();

		JLabel lblMetodo = new JLabel("M\u00E9todo");
		lblMetodo.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblMetodo.setBounds(418, 10, 151, 23);
		lblMetodo.setVisible(false);
		panel_1.add(lblMetodo);

		JDateChooser dtFecha = new JDateChooser();
		dtFecha.setBounds(770, 119, 151, 23);
		dtFecha.setDate(new Date());
		dtFecha.setVisible(false);
		panel_1.add(dtFecha);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblFecha.setBounds(770, 88, 151, 23);
		lblFecha.setVisible(false);
		panel_1.add(lblFecha);

		cbLocalidad = new JComboBox();
		cbLocalidad.setBackground(Color.WHITE);
		cbLocalidad.setVisible(false);
		cbLocalidad.setBounds(770, 42, 151, 23);
		panel_1.add(cbLocalidad);
		comboLocalidad();

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblLocalidad.setBounds(770, 10, 151, 23);
		lblLocalidad.setVisible(false);
		panel_1.add(lblLocalidad);

		cbEquip = new JComboBox();
		cbEquip.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		cbEquip.setVisible(false);
		cbEquip.setBackground(Color.WHITE);
		cbEquip.setBounds(239, 42, 151, 23);
		panel_1.add(cbEquip);
		comboMetodo();

		JLabel lblTituloForm = new JLabel("");
		lblTituloForm.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblTituloForm.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblTituloForm.setBounds(10, 42, 198, 23);
		lblTituloForm.setText(FrmFormAM.tituloForm);
		panel_1.add(lblTituloForm);

		JTextArea taDescForm = new JTextArea();
		taDescForm.setBorder(new LineBorder(Color.LIGHT_GRAY));
		taDescForm.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		taDescForm.setEditable(false);
		taDescForm.setBackground(Color.WHITE);
		taDescForm.setBounds(10, 91, 374, 51);
		taDescForm.setText(FrmFormAM.descripcionForm);
		panel_1.add(taDescForm);

		JLabel lblNewLabel = new JLabel("Descripci\u00F3n");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 70, 151, 23);
		panel_1.add(lblNewLabel);

		JLabel lblTitulo_1 = new JLabel("T\u00EDtulo");
		lblTitulo_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblTitulo_1.setBackground(Color.WHITE);
		lblTitulo_1.setBounds(10, 10, 151, 23);
		panel_1.add(lblTitulo_1);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBorder(null);
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setForeground(Color.DARK_GRAY);
		btnVolver.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnVolver.setBounds(10, 523, 172, 38);
		panel_2.add(btnVolver);

		btnCrearForm = new JButton("Crear formulario");
		btnCrearForm.setForeground(Color.WHITE);
		if (AccionFormulario.Modificar.equals(accion))
			btnCrearForm = new JButton("Modificar formulario");
		else
			btnCrearForm = new JButton("Crear formulario");
		// btnCrearForm.setBounds(726, 523, 198, 38);
		btnCrearForm.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnCrearForm.setBorder(null);
		btnCrearForm.setVisible(true);
		btnCrearForm.setBackground(new Color(119, 184, 105));
		btnCrearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Set<Parametro> hashSetParametros = new HashSet<>(FrmFormAM.listaParametros);
					FrmFormAM.f.setParametros(hashSetParametros);
					if (AccionFormulario.Modificar.equals(accion)) {
						formularioBean.actualizar(FrmFormAM.f);
						JOptionPane.showMessageDialog(null, "Formulario modificado con exito");
					} else if (AccionFormulario.Alta.equals(accion)) {
						formularioBean.crear(FrmFormAM.f);
						JOptionPane.showMessageDialog(null, "Formulario guardado con exito");
					}

					FrmFormAM.f = null;

					setVisible(false);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Ups!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnCrearForm);

		btnCrearForm.setBounds(749, 523, 172, 38);
		panel_2.add(btnCrearForm);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		if (FrmFormAM.checkDepto.isSelected()) {
			cbDepto.setVisible(true);
			lblDepto.setVisible(true);
			FrmFormAM.f.setUsaDepto("S");
		}
		if (FrmFormAM.checkEquip.isSelected()) {
			cbEquip.setVisible(true);
			lblEquip.setVisible(true);
			FrmFormAM.f.setUsaEquip("S");
		}
		if (FrmFormAM.checkMetodo.isSelected()) {
			cbMetodo.setVisible(true);
			lblMetodo.setVisible(true);
			FrmFormAM.f.setUsaMetodo("S");
		}
		if (FrmFormAM.checkRegion.isSelected()) {
			cbRegion.setVisible(true);
			lblRegion.setVisible(true);
			FrmFormAM.f.setUsaRegion("S");
		}
		if (FrmFormAM.checkLocalidad.isSelected()) {
			cbLocalidad.setVisible(true);
			lblLocalidad.setVisible(true);
			FrmFormAM.f.setUsaLocalidad("S");
		}
		if (FrmFormAM.checkEstacion.isSelected()) {
			cbEstacion.setVisible(true);
			lblEstacion.setVisible(true);
			FrmFormAM.f.setUsaEstacion("S");
		}
		if (FrmFormAM.checkFecha.isSelected()) {
			dtFecha.setVisible(true);
			lblFecha.setVisible(true);
			FrmFormAM.f.setUsaFecha("S");
		}
		comboEquip();

		// Cada 5 parametro cambia de panel donde se insertan los parametros
		int cont = 0;
		for (Parametro p : FrmFormAM.listaParametros) {

			if (cont < 5) {
				if (p.getTipo().getTipo().equals(TipoDato.BOOLEAN)) {
					TemplateBoolean ts = new TemplateBoolean();
					if (p.isObligatorio()) {
						ts.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						ts.lblNombre.setText(p.getTipo().getNombre());

					panel_3.add(ts);
				} else if (p.getTipo().getTipo().equals(TipoDato.DATE)) {
					TemplateDate td = new TemplateDate();
					if (p.isObligatorio()) {
						td.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						td.lblNombre.setText(p.getTipo().getNombre());
					panel_3.add(td);
				} else if (p.getTipo().getTipo().equals(TipoDato.BLOB)) {
					TemplateBlob tb = new TemplateBlob();
					if (p.isObligatorio()) {
						tb.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						tb.lblNombre.setText(p.getTipo().getNombre());
					panel_3.add(tb);
				} else {
					TemplateString ts = new TemplateString();
					if (p.isObligatorio()) {
						ts.lblNewLabel.setText(p.getTipo().getNombre() + " *");
					} else
						ts.lblNewLabel.setText(p.getTipo().getNombre());
					panel_3.add(ts);
				}
				cont++;
			} else if (cont > 4 && cont < 10) {

				if (p.getTipo().getTipo().equals(TipoDato.BOOLEAN)) {
					TemplateBoolean ts = new TemplateBoolean();
					if (p.isObligatorio()) {
						ts.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						ts.lblNombre.setText(p.getTipo().getNombre());

					panel_3.add(ts);
				} else if (p.getTipo().getTipo().equals(TipoDato.DATE)) {
					TemplateDate td = new TemplateDate();
					if (p.isObligatorio()) {
						td.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						td.lblNombre.setText(p.getTipo().getNombre());
					panel_3.add(td);
				} else if (p.getTipo().getTipo().equals(TipoDato.BLOB)) {
					TemplateBlob tb = new TemplateBlob();
					if (p.isObligatorio()) {
						tb.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						tb.lblNombre.setText(p.getTipo().getNombre());
					panel_3.add(tb);
				} else {
					TemplateString ts = new TemplateString();
					if (p.isObligatorio()) {
						ts.lblNewLabel.setText(p.getTipo().getNombre() + " *");
					} else
						ts.lblNewLabel.setText(p.getTipo().getNombre());
					panel_3.add(ts);
				}
				cont++;

			} else if (cont > 9 && cont < 15) {
				if (p.getTipo().getTipo().equals(TipoDato.BOOLEAN)) {
					TemplateBoolean ts = new TemplateBoolean();
					if (p.isObligatorio()) {
						ts.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						ts.lblNombre.setText(p.getTipo().getNombre());

					panel_3.add(ts);
				} else if (p.getTipo().getTipo().equals(TipoDato.DATE)) {
					TemplateDate td = new TemplateDate();
					if (p.isObligatorio()) {
						td.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						td.lblNombre.setText(p.getTipo().getNombre());
					panel_3.add(td);
				} else if (p.getTipo().getTipo().equals(TipoDato.BLOB)) {
					TemplateBlob tb = new TemplateBlob();
					if (p.isObligatorio()) {
						tb.lblNombre.setText(p.getTipo().getNombre() + " *");
					} else
						tb.lblNombre.setText(p.getTipo().getNombre());
					panel_3.add(tb);
				} else {
					TemplateString ts = new TemplateString();
					if (p.isObligatorio()) {
						ts.lblNewLabel.setText(p.getTipo().getNombre() + " *");
					} else
						ts.lblNewLabel.setText(p.getTipo().getNombre());
					panel_3.add(ts);
				}
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
		List<Equipamiento> equipamietos = equipBean.obtenerTodos();
		for (Equipamiento r : equipamietos) {
			cbEquip.addItem(r);
		}
	}

	public void comboMetodo() {
		List<Metodo> metodos = metodoBean.obtenerTodos();
		for (Metodo r : metodos) {
			cbMetodo.addItem(r);
		}
	}

	public void comboLocalidad() {
		List<Localidad> localidades = localidadBean.obtenerTodos();
		for (Localidad r : localidades) {
			cbLocalidad.addItem(r);
		}
	}
}
