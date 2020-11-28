package views.actividad;


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

import enums.AccionFormulario;
import models.ActividadCampo;
import models.Usuario;
import services.ActividadCampoBeanRemote;
import services.UsuarioBeanRemote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmListadoActividad extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable jtable_actividad;
	private JScrollPane scrollPaneUsuarios;
	private UsuarioBeanRemote uBean;
	private ActividadCampoBeanRemote actividadBean;
	private ArrayList<ActividadCampo> miLista = null;
	
	/**
	 * Create the frame.
	 */
	public FrmListadoActividad(AccionFormulario accion, Usuario usuario) {
		
		try {
			actividadBean = (ActividadCampoBeanRemote) InitialContext.doLookup("ejb:/IAGROEJB/ActividadCampoBean!services.ActividadCampoBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		
		
		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmListadoActividad.class.getResource("/views/assets/icons/icon-app-barra.png")));
		setBounds(100, 100, 738, 393);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 738, 393);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 738, 39);
		panel_2.add(panel);
		panel.setBackground(new Color(119, 184, 105));
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Listado de actividades de campo");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmListadoActividad.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		lblCerrarSesion.setBounds(709, 11, 19, 14);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("Buscar por Nombre, Apellido, Rol");
		txtBuscar.setVerifyInputWhenFocusTarget(false);
		txtBuscar.setForeground(new Color(0, 0, 0));
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
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
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(31, 50, 199, 14);
		panel_2.add(lblNewLabel_1_1);
		
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = jtable_actividad.getSelectedRow(); 

				if(row >= 0) {
				    // Obtiene el valor de la celda 0 de la fila seleccionada
				    Long idActividad = Long.parseLong(jtable_actividad.getModel().getValueAt(row, 0).toString());
				    actividadBean.desactivar(idActividad);
		            JOptionPane.showMessageDialog(null,"Formulario eliminado", "Eliminar formulario", JOptionPane.INFORMATION_MESSAGE);
		   
		            construirTabla();
				     
				}else {
				    JOptionPane.showMessageDialog(null,"Usted debe seleccionar la actividad de campo que desea eliminar", "Modificar actividad", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnEliminar.setIcon(new ImageIcon(FrmListadoActividad.class.getResource("/views/assets/icons/eliminar.png")));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEliminar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnEliminar.setBackground(new Color(204, 204, 204));
		btnEliminar.setBounds(89, 111, 45, 33);
		panel_2.add(btnEliminar);
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jtable_actividad.getSelectedRow(); 

				if(row >= 0) {
				    // Obtiene el valor de la celda 0 de la fila seleccionada
				    Long idActividad = Long.parseLong(jtable_actividad.getModel().getValueAt(row, 0).toString());
				    ActividadCampo ac = actividadBean.getActividadById(idActividad);
				    
				    FrmActividadCampoAM frmReporte = new FrmActividadCampoAM(AccionFormulario.Modificar, usuario, idActividad);
					frmReporte.setVisible(true);
				    
				}else {
				    JOptionPane.showMessageDialog(null,"Usted debe seleccionar la actividad de campo que desea modificar", "Modificar actividad", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmListadoActividad.class.getResource("/views/assets/icons/edit.png")));
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnEditar.setBackground(new Color(204, 204, 204));
		btnEditar.setBounds(31, 111, 45, 33);
		panel_2.add(btnEditar);
		
		scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(31, 155, 682, 227);
		panel_2.add(scrollPaneUsuarios);
		
		jtable_actividad = new JTable();
		jtable_actividad.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Identificador", "Nombre", "Descripcion", "Formulario"
			}
		));
		scrollPaneUsuarios.setViewportView(jtable_actividad);
		construirTabla();
		
		
		//Mostrar boton
		btnEditar.setVisible(false);
		btnEliminar.setVisible(false);
		
		if(accion == AccionFormulario.Eliminar) {
			btnEliminar.setVisible(true);
		} else if (accion == AccionFormulario.Modificar) {
			btnEditar.setVisible(true);
		}
		
		
	}
	
	/**
	 * Contruimos la tabla en el ScrollPane
	 */
	public void construirTabla() {
		
		String titulos [] = { "Identificador", "Nombre", "Descripcion", "Formulario"};
		
		String informacion [][] = obtenerMatriz();
		
		jtable_actividad = new JTable(informacion, titulos);
		jtable_actividad.setBackground(SystemColor.activeCaptionBorder);
		scrollPaneUsuarios.setViewportView(jtable_actividad);
	}
	
	/**
	 * Completamos la tabla con datos
	 * @return matriz [][]
	 */
	private String[][] obtenerMatriz() {
	
		miLista = (ArrayList<ActividadCampo>) actividadBean.obtenerTodos();
		
		String matrizInfo[][] = new String [miLista.size()][4];
		
		for( int i = 0 ; i< miLista.size() ; i++) {
			
			matrizInfo[i][0] = miLista.get(i).getId() + "";
			matrizInfo[i][1] = miLista.get(i).getNombre() + "";
			matrizInfo[i][2] = miLista.get(i).getDescripcion() + "";
			matrizInfo[i][3] = miLista.get(i).getFormulario().getTitulo() + "";
			
		}
		return matrizInfo;
	}
}
