package views.actividad;

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
import helpers.DevuelveTipoDato;
import helpers.Validaciones;
import models.Formulario;
import models.Parametro;
import models.ActividadCampo;
import models.Rol;
import models.TipoParametro;
import models.Usuario;
import services.FormularioBeanRemote;
import services.ParametroBeanRemote;
import services.ActividadCampoBeanRemote;
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

public  class FrmActividadCampoAM extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private FormularioBeanRemote formularioBean = null;
	private TipoParametroBeanRemote tipoParametroBean = null;
	private ParametroBeanRemote parametroBean = null;
	private ActividadCampoBeanRemote actividadBean = null;
	public static Set setParametros;
	public static List<Formulario> listaFormularios;
	public static ArrayList<JCheckBox> listaChecks;
	public static String tituloForm;
	public static String descripcionForm;
	
	private JScrollPane scrollPaneParam;
	private JTable jtable_param;
	public static Formulario f;
	
	/**
	 * Create the frame. 
	 */
	public FrmActividadCampoAM(AccionFormulario accion, Usuario u) {
		
		listaFormularios = new ArrayList<Formulario>();
		
		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmActividadCampoAM.class.getResource("/views/assets/icons/icon-app-barra.png")));
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

		JLabel lblTitulo = new JLabel("Alta de actividad de campo");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmActividadCampoAM.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		lblCerrarSesion.setBounds(869, 11, 19, 14);
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
			actividadBean = (ActividadCampoBeanRemote) InitialContext
					.doLookup("ejb:/IAGROEJB/ActividadCampoBean!services.ActividadCampoBeanRemote");
			
		} catch (NamingException e2) {
			e2.printStackTrace();
		}
		
		txtTitulo = new JTextField();
		txtTitulo.setToolTipText("Selecciona el formulario y haz clic en \"Crear\"");
		txtTitulo.setVerifyInputWhenFocusTarget(false);
		txtTitulo.setForeground(new Color(0, 0, 0));
		txtTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTitulo.setColumns(10);
		txtTitulo.setBorder(null);
		txtTitulo.setBackground(new Color(255, 255, 255));
		txtTitulo.setBounds(31, 131, 330, 33);
		panel_2.add(txtTitulo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 170, 330, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Titulo");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(31, 112, 199, 14);
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
		btnCancelar.setBounds(31, 441, 162, 33);
		panel_2.add(btnCancelar);

		JLabel lblNewLabel_1_1_2 = new JLabel("Descripcion");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(31, 203, 199, 14);
		panel_2.add(lblNewLabel_1_1_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(new Color(248, 248, 255));
		separator_2_1.setBounds(31, 384, 848, 10);
		panel_2.add(separator_2_1);
		
		JTextArea taDescripcion = new JTextArea();
		taDescripcion.setBorder(new LineBorder(new Color(102, 204, 51)));
		taDescripcion.setBounds(31, 224, 330, 130);
		panel_2.add(taDescripcion);
		
		JLabel lblId = new JLabel("New label");
		lblId.setVisible(false);
		lblId.setBounds(468, 418, 45, 13);
		panel_2.add(lblId);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(new Color(248, 248, 255));
		separator_1.setBounds(31, 364, 330, 10);
		panel_2.add(separator_1);
		
		scrollPaneParam = new JScrollPane();
		scrollPaneParam.setBounds(395, 108, 485, 267);
		panel_2.add(scrollPaneParam);
		
		jtable_param = new JTable();
		jtable_param.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Identificador", "Titulo", "Descripcion"
			}
		));
		scrollPaneParam.setViewportView(jtable_param);
		construirTabla();
		
		JButton btnPreview = new JButton("Crear");
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = jtable_param.getSelectedRow(); 

				if(row >= 0) {
				    // Obtiene el valor de la celda 0 de la fila seleccionada
				    Long idForm = Long.parseLong(jtable_param.getModel().getValueAt(row, 0).toString()); 
				    
				    try {
					Formulario f = formularioBean.getFormularioById(idForm);
					
					ActividadCampo ac = new ActividadCampo();
					ac.setNombre(txtTitulo.getText());
					ac.setDescripcion(taDescripcion.getText());
					ac.setUsuario(u);
					ac.setFormulario(f);
					
					actividadBean.crear(ac);
					JOptionPane.showMessageDialog(null, "Actividad de campo guardada con exito");
					
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}	
				
			}
		});
		btnPreview.setForeground(Color.WHITE);
		btnPreview.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnPreview.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnPreview.setBackground(new Color(102, 204, 0));
		btnPreview.setBounds(717, 441, 162, 33);
		panel_2.add(btnPreview);
		
		JLabel lblNewLabel_1 = new JLabel("Formularios disponibles");
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(395, 73, 485, 28);
		panel_2.add(lblNewLabel_1);
		
		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setOrientation(SwingConstants.VERTICAL);
		separator_2_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2.setBackground(new Color(248, 248, 255));
		separator_2_2.setBounds(371, 107, 14, 267);
		panel_2.add(separator_2_2);

	}
	
	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTabla() {
		
		String titulos [] = { "Identificador", "Titulo", "Descripcion"};
		
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
		ArrayList<Formulario> miLista = null;	
		miLista = (ArrayList<Formulario>) formularioBean.obtenerTodos();
		
		String matrizInfo[][] = new String [miLista.size()][3];
		
		for( int i = 0 ; i< miLista.size() ; i++) {
			
			matrizInfo[i][0] = miLista.get(i).getId() + "";
			matrizInfo[i][1] = miLista.get(i).getTitulo() + "";
			matrizInfo[i][2] = miLista.get(i).getDescripcion() + "";
			
		}
		return matrizInfo;
	}

	public void limpiarTextFields(ArrayList<JTextField> campos) {
		for (JTextField tf : campos) {
			tf.setText("");
		}
	}
	
}
