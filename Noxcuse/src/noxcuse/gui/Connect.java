package noxcuse.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import noxcuse.logica.BD;
import noxcuse.logica.Configuracion;
import noxcuse.logica.Perfil;
import java.awt.Toolkit;

public class Connect extends JFrame {

	private JPanel contentPane;
	private JTextField txtHost;
	private JTextField txtBd;
	private JTextField txtUser;
	private JPasswordField pwdPwd;
	private JLabel lblStatus;
	private JButton btnProbar;
	private JButton btnConectar;
	private Handler handler;
	private JProgressBar progressBar;
	private Configuracion config;
	private JPanel panel;
	private JButton btnConfigurar;
	private JButton btnConectarPerfil;
	private JComboBox cbPerfil;
	
	/**
	 * Create the frame.
	 */
	public Connect() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Connect.class.getResource("/resources/logo.png")));
		config = new Configuracion();
		init();		
		initEvents();
	}
	
	public void init() {
		setTitle("Easpy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 330);
		setMinimumSize(new Dimension(220,250));
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Conectate a la base de datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
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
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Perfiles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, "2, 2, 2, 3, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		cbPerfil = new JComboBox();
		panel.add(cbPerfil, "2, 2, 3, 1, fill, default");
		for(int i = 0; i<config.getPerfiles().size(); i++) {
			cbPerfil.addItem(config.getPerfiles().get(i));
		}
		
		btnConfigurar = new JButton("Configurar");
		panel.add(btnConfigurar, "2, 4");
		
		btnConectarPerfil = new JButton("Conectar");
		panel.add(btnConectarPerfil, "4, 4");
		
		JLabel lblHost = new JLabel("Host:");
		contentPane.add(lblHost, "2, 6, fill, fill");
		
		txtHost = new JTextField();
		contentPane.add(txtHost, "3, 6, fill, fill");
		txtHost.setColumns(10);
		txtHost.setText(config.getHost());
		
		JLabel lblBd = new JLabel("BD:");
		contentPane.add(lblBd, "2, 8, left, fill");
		
		txtBd = new JTextField();
		contentPane.add(txtBd, "3, 8, fill, default");
		txtBd.setColumns(10);
		txtBd.setText(config.getBd());
		
		JLabel lblUser = new JLabel("User:");
		contentPane.add(lblUser, "2, 10, left, default");
		
		txtUser = new JTextField();
		contentPane.add(txtUser, "3, 10, fill, default");
		txtUser.setColumns(10);
		txtUser.setText(config.getUser());
		
		JLabel lblPwd = new JLabel("Pwd:");
		contentPane.add(lblPwd, "2, 12, left, default");
		
		pwdPwd = new JPasswordField();
		contentPane.add(pwdPwd, "3, 12, fill, default");
		pwdPwd.setText(config.getPwd());
		
		lblStatus = new JLabel("Ingrese los datos de la BD");
		contentPane.add(lblStatus, "2, 14, 2, 1");
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(10);
		contentPane.add(progressBar, "2, 16, 2, 1");
		
		btnProbar = new JButton("Probar");
		contentPane.add(btnProbar, "2, 18");
		
		btnConectar = new JButton("Conectar");
		contentPane.add(btnConectar, "3, 18");
		
		handler = new Handler();
	}
	public void menu(BD bd) {
		try {
			bd.connect();
			Menu m = new Menu(bd);
			setVisible(false);
			m.abrir();
			m.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
			    	setVisible(true);
			    }
				
			});
		} catch(Exception ex) {
			lblStatus.setText("Conexion fallida! Intente de nuevo");
			JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+ex.getMessage()+"</p></body></html>");
			ex.printStackTrace();
		}
		
	}
	public void initEvents() {
		btnProbar.addActionListener(handler);
		btnConectar.addActionListener(handler);
		btnConfigurar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConfigurarPerfiles cp = new ConfigurarPerfiles();
				cp.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				    	cbPerfil.removeAllItems();
				    	config.refreshPerfil();
				    	for(int i = 0; i < config.getPerfiles().size(); i++) {
				    		cbPerfil.addItem(config.getPerfiles().get(i));
				    	}
				    }
				});
				cp.abrir();
			}
		});
		btnConectarPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Perfil p = (Perfil) cbPerfil.getSelectedItem();
				try {
					BD bd = new BD(p);
					menu(bd);
				}
				catch(Exception ex) {
					lblStatus.setText("Conexion fallida! Intente de nuevo");
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+ex.getMessage()+"</p></body></html>");
					ex.printStackTrace();
				}
			}
		});
	}
	
	private class Handler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			progressBar.setValue(0);
			if(e.getSource() == btnProbar) {
				BD bd = new BD(txtHost.getText(),txtBd.getText(),txtUser.getText(),new String(pwdPwd.getPassword()));
				try {
					progressBar.setValue(2);
					boolean conectado = bd.test();
					progressBar.setValue(10);
					if(conectado) {
						lblStatus.setText("Prueba exitosa!");
						JOptionPane.showMessageDialog(null,"Base de datos correcta");
					}
					else {
						lblStatus.setText("Prueba fallida! Intente de nuevo");
						JOptionPane.showMessageDialog(null,"Base de datos incorrecta");
					}
					
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(e.getSource() == btnConectar) {
				BD bd = new BD(txtHost.getText(),txtBd.getText(),txtUser.getText(),new String(pwdPwd.getPassword()));
				bd.setHost(txtHost.getText());
				bd.setBd(txtBd.getText());
				bd.setUser(txtUser.getText());
				bd.setPwd(new String(pwdPwd.getPassword()));
				menu(bd);
				
			}
		}
		
	}
}
