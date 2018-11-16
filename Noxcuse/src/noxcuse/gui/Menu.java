package noxcuse.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import noxcuse.logica.BD;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class Menu extends JFrame {
	
	private BD bd;
	private JPanel contentPane;
	private JButton btnStoredProcedures;
	private JButton btnPantallas;
	private JPanel panel;
	private JButton btnGenesys;
	private JButton btnAuditoria;

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
	public Menu(BD bd) {
		this.bd = bd;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/resources/logo.png")));
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 220, 212);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		btnStoredProcedures = new JButton("Stored Procedures");
		btnStoredProcedures.setIcon(new ImageIcon(getClass().getResource("/resources/sql.png")));
		contentPane.add(btnStoredProcedures, "2, 2");
		
		btnPantallas = new JButton("Pantallas");
		btnPantallas.setIcon(new ImageIcon(getClass().getResource("/resources/html.png")));
		contentPane.add(btnPantallas, "2, 4");
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Instalar funciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, "2, 6, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		btnGenesys = new JButton("GeneSys");
		btnGenesys.setEnabled(false);
		panel.add(btnGenesys, "2, 2");
		
		btnAuditoria = new JButton("Auditoria");
		btnAuditoria.setEnabled(false);
		panel.add(btnAuditoria, "2, 4");
		
		try {
			if(!bd.comprobarGeneSys())
				btnGenesys.setEnabled(true);
			if(!bd.comprobarAuditoria())
				btnAuditoria.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initListeners() {
		btnStoredProcedures.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Seleccion s = new Seleccion(bd);
				s.abrir();
				setVisible(false);
				s.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				    	setVisible(true);
				    }
					
				});
			}
			
		});
		
		btnPantallas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!bd.comprobarGeneSys()) {
						JCheckBox checkbox = new JCheckBox("Agregar auditoria local.");
						String message = "GeneSys no fue encontrado, ¿Desea instalarlo en su base de datos?";
						Object[] params = {message, checkbox};
						int geneSys = JOptionPane.showConfirmDialog(null, params, "GeneSys no encontrado", JOptionPane.YES_NO_OPTION);
					        if (geneSys == JOptionPane.YES_OPTION) {
					          bd.altaGeneSys();
					          btnGenesys.setEnabled(false);
					          if(checkbox.isSelected()) {
					        	  bd.altaAuditoria();
					        	  btnAuditoria.setEnabled(false);
					          }
					        }
					        else {
					           return;
					        }
					}
					
					GeneracionDePantalla gp = new GeneracionDePantalla(bd);
					gp.abrir();
					setVisible(false);
					gp.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					    	setVisible(true);
					    }
						
					});
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "¡Ocurrio un error!");
					e1.printStackTrace();
				}
			}
			
		});
		btnGenesys.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd.altaGeneSys();
					btnGenesys.setEnabled(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		btnAuditoria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd.altaAuditoria();
					btnAuditoria.setEnabled(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}

}
