package views.form;


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
import models.Formulario;
import models.Usuario;
import services.FormularioBeanRemote;
import services.UsuarioBeanRemote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmListadoForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable jtable_form;
	private JScrollPane scrollPaneForm;
	private FormularioBeanRemote formBean;
	private JLabel lblTitulo;
	private ArrayList<Formulario> miLista = null;	
	
	/**
	 * Create the frame.
	 */
	public FrmListadoForm(AccionFormulario accion, Usuario u) {
		
		try {
			formBean = (FormularioBeanRemote) InitialContext.doLookup("ejb:/IAGROEJB/FormularioBean!services.FormularioBeanRemote");			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		
		setUndecorated(true);
		setTitle("IAGRO - Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmListadoForm.class.getResource("/views/assets/icons/icon-app-barra.png")));
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
		
		if(AccionFormulario.Eliminar.equals(accion))
			lblTitulo = new JLabel("Eliminar formulario");
		else if (AccionFormulario.Modificar.equals(accion))
			lblTitulo = new JLabel("Modificar formulario");
		else	
			lblTitulo = new JLabel("Listado de formulario");	
			lblTitulo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
			lblTitulo.setForeground(Color.WHITE);
			lblTitulo.setBounds(39, 11, 257, 14);
		    panel.add(lblTitulo);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmListadoForm.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		lblCerrarSesion.setBounds(707, 0, 31, 25);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if (txtBuscar.getText().length() > 0) {

					var filtradoForm = miLista.stream()
							.filter(p -> p.getTitulo().contains(txtBuscar.getText())
									|| p.getDescripcion().contains(txtBuscar.getText()))
							.collect(Collectors.toList());
					miLista = (ArrayList<Formulario>) filtradoForm;
					construirTabla();
				} else {
					miLista = (ArrayList<Formulario>) formBean.obtenerTodos();
					construirTabla();
				}
				
			}
		});
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
		btnEliminar.setHorizontalAlignment(SwingConstants.LEFT);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = jtable_form.getSelectedRow(); 

				if(row >= 0) {
				    // Obtiene el valor de la celda 0 de la fila seleccionada
				    Long idForm = Long.parseLong(jtable_form.getModel().getValueAt(row, 0).toString()); 

				    // Preguntamos si quiere eliminar el rol
				    int pregunta = JOptionPane.showConfirmDialog(null,"¿Seguro que quieres eliminar este formulario?", "Eliminar formulario", JOptionPane.YES_NO_OPTION);

				    // Si la opcion es "YES" entonces llamamos al metodo del controlador de eliminar
				    if(pregunta == JOptionPane.YES_OPTION){
				    	
				        var eliminar = false;
						try {
							formBean.desactivar(idForm);
							eliminar = true;
						} catch (Exception e1) {

							e1.printStackTrace();
						}
				        
				        if(eliminar) {
				            JOptionPane.showMessageDialog(null,"Formulario eliminado", "Eliminar formulario", JOptionPane.INFORMATION_MESSAGE);
				            
				            miLista = (ArrayList<Formulario>) formBean.obtenerTodos();
							construirTabla();
				            
				        }else {
				            JOptionPane.showMessageDialog(null,"Error al eliminar un formulario", "Eliminar formulario", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				    } else {
				    JOptionPane.showMessageDialog(null,"Usted debe seleccionar el formulario que desea eliminar", "Eliminar formulario", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		btnEliminar.setIcon(new ImageIcon(FrmListadoForm.class.getResource("/views/assets/icons/eliminar.png")));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(41, 111, 45, 33);
		panel_2.add(btnEliminar);
		
		JButton btnEditar = new JButton("");
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jtable_form.getSelectedRow(); 

				if(row >= 0) {
				    // Obtiene el valor de la celda 0 de la fila seleccionada
				    Long id = Long.parseLong(jtable_form.getModel().getValueAt(row, 0).toString());
				    Formulario f = null;
				    try {
						f = formBean.getFormularioById(id);
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				    FrmFormAM frm = new FrmFormAM(AccionFormulario.Modificar, u, f );
				    frm.setVisible(true);
				    setVisible(false);

				}else {
				    JOptionPane.showMessageDialog(null,"Usted debe seleccionar el formulario que desea modificar", "Modificar formulario", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmListadoForm.class.getResource("/views/assets/icons/edit.png")));
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditar.setBorder(null);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(31, 111, 45, 33);
		panel_2.add(btnEditar);
		
		scrollPaneForm = new JScrollPane();
		scrollPaneForm.setBounds(31, 155, 682, 227);
		panel_2.add(scrollPaneForm);
		
		jtable_form = new JTable();
		jtable_form.setBackground(Color.WHITE);
		jtable_form.setSelectionBackground(new Color(119, 184, 105));
		jtable_form.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_form.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		jtable_form.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identificador", "Titulo", "Descripcion"
			}
		));
		scrollPaneForm.setViewportView(jtable_form);
		miLista = (ArrayList<Formulario>) formBean.obtenerTodos();	
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
		
		String titulos [] = { "Identificador", "Titulo", "Descripcion" };
		
		String informacion [][] = obtenerMatriz();
		
		jtable_form = new JTable(informacion, titulos);
		jtable_form.setBackground(Color.WHITE);
		jtable_form.setSelectionBackground(new Color(119, 184, 105));
		jtable_form.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_form.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));	
		scrollPaneForm.setViewportView(jtable_form);
		
		JTableHeader th;
		th = jtable_form.getTableHeader();
		Font fuente = new Font("Gill Sans MT", Font.PLAIN, 15);
		th.setFont(fuente);
		th.setBackground(SystemColor.control);

		scrollPaneForm.getViewport().setBackground(SystemColor.control);
	}
	
	/**
	 * Completamos la tabla con datos
	 * @return matriz [][]
	 */
	private String[][] obtenerMatriz() {
		
		String matrizInfo[][] = new String [miLista.size()][3];
		
		for( int i = 0 ; i< miLista.size() ; i++) {
			
			matrizInfo[i][0] = miLista.get(i).getId() + "";
			matrizInfo[i][1] = miLista.get(i).getTitulo() + "";
			matrizInfo[i][2] = miLista.get(i).getDescripcion() + "";
	
		}
		return matrizInfo;
	}
}
