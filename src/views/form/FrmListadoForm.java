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

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import enums.AccionFormulario;
import models.Formulario;
import models.Usuario;
import services.FormularioBeanRemote;
import services.UsuarioBeanRemote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmListadoForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable jtable_form;
	private JScrollPane scrollPaneForm;
	private FormularioBeanRemote formBean;
	private JLabel lblTitulo;
	
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
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTitulo.setBounds(39, 11, 257, 14);
		panel.add(lblTitulo);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmListadoForm.class.getResource("/views/assets/icons/icon-app-ventana.png")));
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
		/*
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = jtable_form.getSelectedRow(); 

				if(row >= 0) {
				    // Obtiene el valor de la celda 0 de la fila seleccionada
				    String usuarioNombre = jtable_form.getModel().getValueAt(row, 0).toString(); 

				    // Preguntamos si quiere eliminar el rol
				    int pregunta = JOptionPane.showConfirmDialog(null,"¿Seguro que quieres eliminar este usuario?", "Eliminar usuario", JOptionPane.YES_NO_OPTION);

				    // Si la opcion es "YES" entonces llamamos al metodo del controlador de eliminar
				    if(pregunta == JOptionPane.YES_OPTION){
				    	
				        Usuario usu;
				        var eliminar = false;
						try {
							usu = uBean.getUsuario(usuarioNombre);
							uBean.borrar(usu.getId());
							eliminar = true;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        
				        if(eliminar) {
				            JOptionPane.showMessageDialog(null,"Usuario eliminado", "Eliminar usuario", JOptionPane.INFORMATION_MESSAGE);
				            
				            construirTabla();
				            
				        }else {
				            JOptionPane.showMessageDialog(null,"Error al eliminar un usuario", "Eliminar usuario", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				    } else {
				    JOptionPane.showMessageDialog(null,"Usted debe seleccionar el usuario que desea eliminar", "Eliminar usuario", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		*/
		btnEliminar.setIcon(new ImageIcon(FrmListadoForm.class.getResource("/views/assets/icons/eliminar.png")));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEliminar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnEliminar.setBackground(new Color(204, 204, 204));
		btnEliminar.setBounds(31, 110, 45, 33);
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
		btnEditar.setBorder(new LineBorder(new Color(240, 248, 255)));
		btnEditar.setBackground(new Color(204, 204, 204));
		btnEditar.setBounds(31, 111, 45, 33);
		panel_2.add(btnEditar);
		
		scrollPaneForm = new JScrollPane();
		scrollPaneForm.setBounds(31, 155, 682, 227);
		panel_2.add(scrollPaneForm);
		
		jtable_form = new JTable();
		jtable_form.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identificador", "Titulo", "Descripcion"
			}
		));
		scrollPaneForm.setViewportView(jtable_form);
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
		jtable_form.setBackground(SystemColor.activeCaptionBorder);
		scrollPaneForm.setViewportView(jtable_form);
	}
	
	/**
	 * Completamos la tabla con datos
	 * @return matriz [][]
	 */
	private String[][] obtenerMatriz() {
		ArrayList<Formulario> miLista = null;	
		miLista = (ArrayList<Formulario>) formBean.obtenerTodos();
		
		String matrizInfo[][] = new String [miLista.size()][3];
		
		for( int i = 0 ; i< miLista.size() ; i++) {
			
			matrizInfo[i][0] = miLista.get(i).getId() + "";
			matrizInfo[i][1] = miLista.get(i).getTitulo() + "";
			matrizInfo[i][2] = miLista.get(i).getDescripcion() + "";
	
		}
		return matrizInfo;
	}
}
