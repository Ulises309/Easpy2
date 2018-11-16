package noxcuse.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import noxcuse.logica.BD;
import noxcuse.logica.Configuracion;
import noxcuse.logica.Perfil;
import java.awt.Toolkit;

public class ConfigurarPerfiles extends JFrame {

	private Configuracion config;
	private JPanel contentPane;
	private JTextField txtHost;
	private JTextField txtBd;
	private JPasswordField pwdPwd;
	private JTextField txtNombre;
	private JTextField txtPathbd;
	private JTextField txtPathws;
	private JButton btnProbar;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnAceptar;
	private JList listPerfiles;
	private JTextField txtUser;
	private JButton btnSeleccionarWs;
	private JButton btnSeleccionarBd;
	private JLabel lblPathHtml;
	private JLabel lblPathLayout;
	private JLabel lblExt;
	private JTextField txtPathhtml;
	private JTextField txtPathlayout;
	private JTextField txtAuditoria;
	private JButton btnSeleccionarhtml;
	private JButton btnSeleccionarlayout;
	boolean guardardo = true;
	private JLabel lblGenesys;
	private JTextField txtGenesys;

	/**
	 * Launch the application.
	 */
	public void abrir() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
					initListeners();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the frame.
	 */
	public ConfigurarPerfiles() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfigurarPerfiles.class.getResource("/resources/logo.png")));
		config = new Configuracion();
		setMinimumSize(new Dimension(450,450));
		setTitle("Perfiles");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 520);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("250px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblSeleccioneUnPerfil = new JLabel("Seleccione un perfil:");
		contentPane.add(lblSeleccioneUnPerfil, "2, 2");
		
		DefaultListModel<Perfil> model = new DefaultListModel<Perfil>();
		for(int i = 0; i<config.getPerfiles().size(); i++) {
			model.addElement(config.getPerfiles().get(i));
		}
		listPerfiles = new JList(model);
		listPerfiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPerfiles.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(listPerfiles, "2, 4, fill, fill");
		
		JPanel panelEditar = new JPanel();
		panelEditar.setBorder(new TitledBorder(null, "Configurar perfil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelEditar, "4, 4, fill, fill");
		panelEditar.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblNombre = new JLabel("Nombre:");
		panelEditar.add(lblNombre, "2, 2, left, default");
		
		txtNombre = new JTextField();
		panelEditar.add(txtNombre, "4, 2, 3, 1, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblHost = new JLabel("Host:");
		panelEditar.add(lblHost, "2, 4");
		
		txtHost = new JTextField();
		panelEditar.add(txtHost, "4, 4, 3, 1, fill, default");
		txtHost.setColumns(10);
		
		JLabel lblBd = new JLabel("BD:");
		panelEditar.add(lblBd, "2, 6, left, default");
		
		txtBd = new JTextField();
		panelEditar.add(txtBd, "4, 6, 3, 1, fill, default");
		txtBd.setColumns(10);
		
		JLabel lblUser = new JLabel("User:");
		panelEditar.add(lblUser, "2, 8, left, default");
		
		txtUser = new JTextField();
		panelEditar.add(txtUser, "4, 8, 3, 1, fill, default");
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		panelEditar.add(lblPassword, "2, 10, left, default");
		
		pwdPwd = new JPasswordField();
		panelEditar.add(pwdPwd, "4, 10, 3, 1, fill, default");
		
		JLabel lblPathBd = new JLabel("Path BD:");
		panelEditar.add(lblPathBd, "2, 12, left, default");
		
		txtPathbd = new JTextField();
		panelEditar.add(txtPathbd, "4, 12, fill, default");
		txtPathbd.setColumns(10);
		
		btnSeleccionarBd = new JButton("");
		btnSeleccionarBd.setIcon(new ImageIcon(ConfigurarPerfiles.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		panelEditar.add(btnSeleccionarBd, "6, 12");
		
		JLabel lblPathWs = new JLabel("Path WS:");
		panelEditar.add(lblPathWs, "2, 14, left, default");
		
		txtPathws = new JTextField();
		panelEditar.add(txtPathws, "4, 14, fill, default");
		txtPathws.setColumns(10);
		
		btnSeleccionarWs = new JButton("");
		btnSeleccionarWs.setIcon(new ImageIcon(ConfigurarPerfiles.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		panelEditar.add(btnSeleccionarWs, "6, 14");
		
		lblPathHtml = new JLabel("Path HTML:");
		panelEditar.add(lblPathHtml, "2, 16, left, default");
		
		txtPathhtml = new JTextField();
		panelEditar.add(txtPathhtml, "4, 16, fill, default");
		txtPathhtml.setColumns(10);
		
		btnSeleccionarhtml = new JButton("");
		btnSeleccionarhtml.setIcon(new ImageIcon(ConfigurarPerfiles.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		panelEditar.add(btnSeleccionarhtml, "6, 16");
		
		lblPathLayout = new JLabel("Path Layout:");
		panelEditar.add(lblPathLayout, "2, 18, left, default");
		
		txtPathlayout = new JTextField();
		panelEditar.add(txtPathlayout, "4, 18, fill, default");
		txtPathlayout.setColumns(10);
		
		btnSeleccionarlayout = new JButton("");
		btnSeleccionarlayout.setIcon(new ImageIcon(ConfigurarPerfiles.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		panelEditar.add(btnSeleccionarlayout, "6, 18");
		
		lblExt = new JLabel("BD Auditoria:");
		panelEditar.add(lblExt, "2, 20, left, default");
		
		txtAuditoria = new JTextField();
		panelEditar.add(txtAuditoria, "4, 20, 3, 1, fill, default");
		txtAuditoria.setColumns(10);
		
		lblGenesys = new JLabel("GeneSys");
		panelEditar.add(lblGenesys, "2, 22, left, default");
		
		txtGenesys = new JTextField();
		txtGenesys.setText("GeneSys");
		panelEditar.add(txtGenesys, "4, 22, 3, 1, fill, default");
		txtGenesys.setColumns(10);
		
		btnNuevo = new JButton("Nuevo");
		contentPane.add(btnNuevo, "2, 6");
		
		btnProbar = new JButton("Probar");
		contentPane.add(btnProbar, "4, 6");
		
		btnEliminar = new JButton("Eliminar");
		contentPane.add(btnEliminar, "2, 8");
		
		btnGuardar = new JButton("Guardar");
		contentPane.add(btnGuardar, "4, 8");
		
		btnAceptar = new JButton("Aceptar");
		contentPane.add(btnAceptar, "4, 10");
	}
	
	private void refreshLista() {
		config.refreshPerfil();
		DefaultListModel model = (DefaultListModel) listPerfiles.getModel();
		model.removeAllElements();
		for(int i = 0; i < config.getPerfiles().size(); i++) {
			model.addElement(config.getPerfiles().get(i));
		}
		listPerfiles.setModel(model);
	}
	
	private void initListeners() {
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				config.removePerfil((Perfil) listPerfiles.getSelectedValue());
				config.refreshPerfil();
				DefaultListModel model = (DefaultListModel) listPerfiles.getModel();
				model.removeElementAt(listPerfiles.getSelectedIndex());
				listPerfiles.setModel(model);
			}
			
		});
		btnProbar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String host = txtHost.getText();
				String bd = txtBd.getText();
				String user = txtUser.getText();
				String pwd = new String(pwdPwd.getPassword());
				BD test = new BD(host, bd, user, pwd);
				if(test.test())
					JOptionPane.showMessageDialog(null,"Base de datos correcta");
				else
					JOptionPane.showMessageDialog(null,"Base de datos incorrecta");
				
			}
			
		});
		btnNuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Perfil p = new Perfil("NuevoPerfil", "", "", "", "", "C:/BDs", "C:/WSs", "C:/HTMLs", "", "GarSaAdmin", "GeneSys");
				config.addPerfil(p);
				config.refreshPerfil();
				refreshLista();
			}
			
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Perfil viejo = (Perfil) listPerfiles.getSelectedValue();
				String nombre = txtNombre.getText();
				String host = txtHost.getText();
				String bd = txtBd.getText();
				String user = txtUser.getText();
				String pwd = new String(pwdPwd.getPassword());
				String pathBd = txtPathbd.getText();
				String pathWs = txtPathws.getText();
				String pathHTML = txtPathhtml.getText();
				String pathLayout = txtPathlayout.getText();
				String auditoria = txtAuditoria.getText();
				String geneSys = txtGenesys.getText();
				Perfil nuevo = new Perfil(nombre, host, bd, user, pwd, pathBd, pathWs, pathHTML, pathLayout, auditoria, geneSys);
				if(viejo!=null) 
					config.editPerfil(viejo, nuevo);
				else
					config.addPerfil(nuevo);
				
				refreshLista();
			}
		});
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		listPerfiles.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(listPerfiles.getSelectedIndex()==-1) {
					txtNombre.setText("");
					txtHost.setText("");
					txtBd.setText("");
					txtUser.setText("");
					pwdPwd.setText("");
					txtPathbd.setText("");
					txtPathws.setText("");
					txtPathhtml.setText("");
					txtPathlayout.setText("");
					txtAuditoria.setText("");
					txtGenesys.setText("");
					return;
				}
				if (!arg0.getValueIsAdjusting()){
					Perfil p = (Perfil) listPerfiles.getSelectedValue();
					txtNombre.setText(p.getNombre());
					txtHost.setText(p.getHost());
					txtBd.setText(p.getBd());
					txtUser.setText(p.getUser());
					pwdPwd.setText(p.getPwd());
					txtPathbd.setText(p.getPathBD());
					txtPathws.setText(p.getPathWS());
					txtPathhtml.setText(p.getPathHTML());
					txtPathlayout.setText(p.getPathLayout());
					txtAuditoria.setText(p.getAuditoria());
					txtGenesys.setText(p.getGeneSys());
		        }
				
			}
			
		});
		btnSeleccionarBd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new File("C:/"));
			    chooser.setDialogTitle("Seleccione una carpeta");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			    	String nuevo = chooser.getSelectedFile().toString();
			    	txtPathbd.setText(nuevo);			      
			    }
			    else {
			      System.out.println("No Selection ");
			    }
			}
			
		});
		btnSeleccionarWs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new File("C:/"));
			    chooser.setDialogTitle("Seleccione una carpeta");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			    	String nuevo = chooser.getSelectedFile().toString();
			    	txtPathws.setText(nuevo);			      
			    }
			    else {
			      System.out.println("No Selection ");
			    }
			}
			
		});
		
		btnSeleccionarhtml.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new File("C:/"));
			    chooser.setDialogTitle("Seleccione una carpeta");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			    	String nuevo = chooser.getSelectedFile().toString();
			    	txtPathhtml.setText(nuevo);			      
			    }
			    else {
			      System.out.println("No Selection ");
			    }
			}
			
		});
		
		btnSeleccionarlayout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new File("C:/"));
			    chooser.setDialogTitle("Seleccione un archivo");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false); 
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			    	String nuevo = chooser.getSelectedFile().toString();
			    	txtPathlayout.setText(nuevo);			      
			    }
			    else {
			      System.out.println("No Selection ");
			    }
			}
			
		});
	}

}
