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
import javax.swing.ListSelectionModel;
import com.toedter.calendar.JDateChooser;

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
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblTitulo.setBounds(39, 0, 257, 39);
		panel.add(lblTitulo);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmListadoActividad.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		lblCerrarSesion.setBounds(712, 0, 26, 25);
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
					var filtradoACtividad = miLista.stream()
							.filter(p -> p.getNombre().contains(txtBuscar.getText())
									|| p.getDescripcion().contains(txtBuscar.getText()))
							.collect(Collectors.toList());
					miLista = (ArrayList<ActividadCampo>) filtradoACtividad;
					construirTabla();
				} else {
					miLista = (ArrayList<ActividadCampo>) actividadBean.obtenerTodos();
					construirTabla();
				}
				
			}
		});
		txtBuscar.setToolTipText("Buscar por Nombre, Descripcion, Rol");
		txtBuscar.setVerifyInputWhenFocusTarget(false);
		txtBuscar.setForeground(new Color(0, 0, 0));
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
		txtBuscar.setColumns(10);
		txtBuscar.setBorder(null);
		txtBuscar.setBackground(new Color(255, 255, 255));
		txtBuscar.setBounds(31, 67, 381, 33);
		panel_2.add(txtBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(new Color(248, 248, 255));
		separator.setBounds(31, 102, 381, 10);
		panel_2.add(separator);
		
		JLabel lblNewLabel_1_1 = new JLabel("Buscar");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
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
		            miLista = (ArrayList<ActividadCampo>) actividadBean.obtenerTodos();
		            construirTabla();
				     
				}else {
				    JOptionPane.showMessageDialog(null,"Usted debe seleccionar la actividad de campo que desea eliminar", "Modificar actividad", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnEliminar.setIcon(new ImageIcon(FrmListadoActividad.class.getResource("/views/assets/icons/eliminar.png")));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(31, 131, 45, 33);
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
		btnEditar.setBorder(null);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(31, 131, 45, 33);
		panel_2.add(btnEditar);
		
		scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(31, 174, 682, 208);
		panel_2.add(scrollPaneUsuarios);
		
		jtable_actividad = new JTable();
		jtable_actividad.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		jtable_actividad.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Identificador", "Nombre", "Descripcion", "Formulario"
			}
		));
		scrollPaneUsuarios.setViewportView(jtable_actividad);
		
		JButton btnReporte = new JButton("Reportar actividad");
		btnReporte.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnReporte.setForeground(Color.WHITE);
		btnReporte.setBorder(null);
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jtable_actividad.getSelectedRow(); 

				if(row >= 0) {
				    // Obtiene el valor de la celda 0 de la fila seleccionada
				    Long idActividad = Long.parseLong(jtable_actividad.getModel().getValueAt(row, 0).toString());
				    ActividadCampo ac = actividadBean.getActividadById(idActividad);
				    
				    FrmActividadCampoView frmReporte = new FrmActividadCampoView(AccionFormulario.Alta, idActividad);
					frmReporte.setVisible(true);
				    
				}else {
				    JOptionPane.showMessageDialog(null,"Usted debe seleccionar la actividad de campo que desea utilizar", "Modificar actividad", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnReporte.setBounds(31, 131, 139, 33);
		btnReporte.setBackground(new Color(119, 184, 105));
		btnReporte.setVisible(false);
		if (accion.equals(AccionFormulario.Listar)) {
			btnReporte.setVisible(true);
		}
		panel_2.add(btnReporte);
		
		JDateChooser dcFechaDesde = new JDateChooser();
		dcFechaDesde.setBounds(522, 73, 191, 27);
		panel_2.add(dcFechaDesde);
		
		JDateChooser dcFechaHasta = new JDateChooser();
		dcFechaHasta.setBounds(522, 111, 191, 27);
		panel_2.add(dcFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Fecha desde: ");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel.setBounds(422, 73, 100, 27);
		panel_2.add(lblNewLabel);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta:");
		lblFechaHasta.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFechaHasta.setBounds(422, 111, 100, 27);
		panel_2.add(lblFechaHasta);
		
		JLabel lblBusquedaPorFecha = new JLabel("Busqueda por fecha de inicio de actividad");
		lblBusquedaPorFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBusquedaPorFecha.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBusquedaPorFecha.setBounds(422, 44, 291, 20);
		panel_2.add(lblBusquedaPorFecha);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				miLista = (ArrayList<ActividadCampo>) actividadBean.obtenerTodosRangoDeFechas(dcFechaDesde.getDate(), dcFechaHasta.getDate());
	            construirTabla();
				
				
			}
		});
		btnNewButton.setBounds(628, 144, 85, 27);
		btnNewButton.setBackground(new Color(119, 184, 105));
		panel_2.add(btnNewButton);
		
		//Cargo la lista de actividades usando el bean y llamo al metodo que construye la tabla con esa lista.
		miLista = (ArrayList<ActividadCampo>) actividadBean.obtenerTodos();
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
		jtable_actividad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_actividad.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		jtable_actividad.setBackground(Color.WHITE);
		scrollPaneUsuarios.setViewportView(jtable_actividad);
	}
	
	/**
	 * Completamos la tabla con datos
	 * @return matriz [][]
	 */
	private String[][] obtenerMatriz() {
		
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
