package noxcuse.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import noxcuse.logica.BD;
import noxcuse.logica.Item;
import noxcuse.logica.Reporte;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import javax.swing.ListSelectionModel;

public class AltaPermiso extends JFrame {

	private JPanel contentPane;
	private JButton btnAgregarusuario;
	private JButton btnAgregartipousuario;
	private JTextField txtBuscarusuario;
	private JTextField txtBuscartipousuario;
	private BD bd;
	private Reporte reporte;
	private JList<Item> listUsuarios;
	private JList<Item> listTipoUsuarios;
	private JPopupMenu popupMenuUsuario;
	private JButton btnEliminarusuario;
	private JButton btnEliminartipousuario;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			initListeners();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public AltaPermiso(BD bd, Reporte reporte) {
		this.bd = bd;
		this.reporte = reporte;
		setTitle("Alta de permiso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblReporte = new JLabel(reporte.getNombre());
		contentPane.add(lblReporte, "2, 2, 5, 1, center, default");
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		contentPane.add(lblUsuarios, "2, 4");
		
		JLabel lblTiposDeUsuarios = new JLabel("Tipos de usuarios");
		contentPane.add(lblTiposDeUsuarios, "5, 4");
		
		txtBuscarusuario = new JTextField();
		contentPane.add(txtBuscarusuario, "2, 6, fill, default");
		txtBuscarusuario.setColumns(10);
		
		btnAgregarusuario = new JButton("Agregar");
		contentPane.add(btnAgregarusuario, "3, 6");
		
		txtBuscartipousuario = new JTextField();
		contentPane.add(txtBuscartipousuario, "5, 6, fill, default");
		txtBuscartipousuario.setColumns(10);
		
		btnAgregartipousuario = new JButton("Agregar");
		btnAgregartipousuario.setIcon(null);
		contentPane.add(btnAgregartipousuario, "6, 6");
		
		try {
			listUsuarios = new JList(reporte.obtenerUsuarios(bd));
			listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listUsuarios.setBorder(new LineBorder(new Color(0, 0, 0)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error al obtener los permisos por usuario");
			e.printStackTrace();
		}
		contentPane.add(listUsuarios, "2, 8, 2, 1, fill, fill");
		
		try {
			listTipoUsuarios = new JList(reporte.obtenerTipoUsuario(bd));
			listTipoUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listTipoUsuarios.setBorder(new LineBorder(new Color(0, 0, 0)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error al obtener los permisos por tipo de usuario");
			e.printStackTrace();
		}
		contentPane.add(listTipoUsuarios, "5, 8, 2, 1, fill, fill");
		
		btnEliminarusuario = new JButton("Eliminar");
		contentPane.add(btnEliminarusuario, "3, 10");
		
		btnEliminartipousuario = new JButton("Eliminar");
		contentPane.add(btnEliminartipousuario, "6, 10");
	}
	
	private void actualizarUsuarios() {
		Vector<Item> usuarios;
		try {
			usuarios = reporte.obtenerUsuarios(bd);
			DefaultListModel<Item> model = new DefaultListModel<Item>();
			for(int i = 0; i<usuarios.size(); i++) {
				model.addElement(usuarios.get(i));
			}
			listUsuarios.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void actualizarTipoUsuario() {
		try {
			Vector<Item> usuarios = reporte.obtenerTipoUsuario(bd);
			DefaultListModel<Item> model = new DefaultListModel<Item>();
			for(int i = 0; i<usuarios.size(); i++) {
				model.addElement(usuarios.get(i));
			}
			listTipoUsuarios.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void eliminarPermiso(int id) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String queryString = "DELETE "+bd.getGeneSys()+".Accesos WHERE Id_Acceso = " +id;
			Statement statement = bd.getConn().createStatement();
			statement.execute(queryString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void initListeners() {
		btnEliminarusuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listUsuarios.isSelectionEmpty())
					return;
				eliminarPermiso(listUsuarios.getSelectedValue().id);
				actualizarUsuarios();
			}
			
		});
		btnEliminartipousuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listTipoUsuarios.isSelectionEmpty())
					return;
				eliminarPermiso(listTipoUsuarios.getSelectedValue().id);
				actualizarTipoUsuario();
			}
			
		});
		btnAgregarusuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Vector<Item> resultado = bd.obtenerUsuario(txtBuscarusuario.getText());
					if(resultado.size()==0) {
						JOptionPane.showMessageDialog(null, "No se encontraron usuarios");
						return;
					}
					if(resultado.size()==1) {
						reporte.agregarPermiso(bd, resultado.get(0).id, 2, 0);
						actualizarUsuarios();
						return;
					}
					Buscador b = new Buscador(resultado);
					b.run();
					b.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					    	try {
								reporte.agregarPermiso(bd, b.seleccionado, 2, 0);
								actualizarUsuarios();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		btnAgregartipousuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Vector<Item> resultado = bd.obtenerTipoUsuario(txtBuscartipousuario.getText());
					if(resultado.size()==0) {
						JOptionPane.showMessageDialog(null, "No se encontraron tipos de usuarios");
						return;
					}
					if(resultado.size()==1) {
						reporte.agregarPermiso(bd, resultado.get(0).id, 1, 0);
						actualizarTipoUsuario();
						return;
					}
					Buscador b = new Buscador(resultado);
					b.run();
					b.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					    	try {
								reporte.agregarPermiso(bd, b.seleccionado, 1, 0);
								actualizarTipoUsuario();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
}
