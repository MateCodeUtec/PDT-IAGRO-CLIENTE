package views.form.param;

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
import views.form.FrmFormAM;

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

public class FrmParamAM extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private FormularioBeanRemote formularioBean = null;
	private TipoParametroBeanRemote tipoParametroBean = null;
	private ParametroBeanRemote parametroBean = null;
	private JTextField txtTipoDato;
	private JScrollPane scrollPaneParam;
	private JTable jtable_param;

	/**
	 * Create the frame. 
	 */
	public FrmParamAM() {
		
		
		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmParamAM.class.getResource("/views/assets/icons/icon-app-barra.png")));
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

		JLabel lblTitulo = new JLabel("Alta de parametro");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(FrmParamAM.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		
		

		txtNombre = new JTextField();
		txtNombre.setVerifyInputWhenFocusTarget(false);
		txtNombre.setForeground(new Color(0, 0, 0));
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBackground(new Color(255, 255, 255));
		txtNombre.setBounds(31, 91, 199, 33);
		panel_2.add(txtNombre);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 128, 199, 10);
		panel_2.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
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
		btnCancelar.setBackground(new Color(102, 204, 0));
		btnCancelar.setBounds(360, 352, 102, 33);
		panel_2.add(btnCancelar);

		JLabel lblNewLabel_1_1_2 = new JLabel("Tipo de dato");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1_2.setBounds(31, 130, 199, 14);
		panel_2.add(lblNewLabel_1_1_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(new Color(248, 248, 255));
		separator_2_1.setBounds(31, 340, 431, 10);
		panel_2.add(separator_2_1);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				FrmFormAM.listarParametros();
		
				
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
		
		JButton btnAgregarParam = new JButton("Agregar Parametro");
		btnAgregarParam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TipoParametro tp = new TipoParametro(txtNombre.getText(), TipoDato.STRING);
				
				try {
					tp = tipoParametroBean.crear(tp);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				Parametro p = new Parametro(true,tp);
				
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
		btnAgregarParam.setBounds(31, 244, 144, 33);
		panel_2.add(btnAgregarParam);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(new Color(248, 248, 255));
		separator_2.setBounds(31, 192, 199, 10);
		panel_2.add(separator_2);
		
		txtTipoDato = new JTextField();
		txtTipoDato.setVerifyInputWhenFocusTarget(false);
		txtTipoDato.setForeground(Color.BLACK);
		txtTipoDato.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTipoDato.setColumns(10);
		txtTipoDato.setBorder(null);
		txtTipoDato.setBackground(Color.WHITE);
		txtTipoDato.setBounds(31, 155, 199, 33);
		panel_2.add(txtTipoDato);
		
		scrollPaneParam = new JScrollPane();
		scrollPaneParam.setBounds(251, 49, 211, 258);
		panel_2.add(scrollPaneParam);
		
		jtable_param = new JTable();
		jtable_param.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Tipo de Dato"
			}
		));
		scrollPaneParam.setViewportView(jtable_param);
		construirTabla();
		
		
		/*
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
		*/

	}
	
	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTabla() {
		
		String titulos [] = { "Nombre", "Tipo de Parametro"};
		
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
		
		String matrizInfo[][] = new String [miLista.size()][2];
		
		for( int i = 0 ; i< miLista.size() ; i++) {
			
			matrizInfo[i][0] = miLista.get(i).getTipo().getNombre() + "";
			matrizInfo[i][1] = miLista.get(i).getTipo().getTipo() + "";
			
		}
		return matrizInfo;
	}



	public void limpiarTextFields(ArrayList<JTextField> campos) {

		for (JTextField tf : campos) {
			tf.setText("");
		}

	}
}
