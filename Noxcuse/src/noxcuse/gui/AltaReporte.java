package noxcuse.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import noxcuse.logica.BD;
import noxcuse.logica.Reporte;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AltaReporte extends JFrame {
	private BD bd;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtSp;
	private JTextField txtEsquema;
	private JTextField txtBasededatos;
	private JTextField txtDescripcion;
	private JTextField txtNombrearchivo;
	private JTextField txtCampoNombre;
	private JButton btnAlta;
	private JButton btnCancelar;
	private JComboBox cbPagina;

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
	public AltaReporte(BD bd) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaReporte.class.getResource("/resources/logo.png")));
		this.bd = bd;
		setTitle("Alta de pantallas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 320);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblNombre = new JLabel("Nombre:");
		contentPane.add(lblNombre, "2, 2, left, default");
		
		txtNombre = new JTextField();
		contentPane.add(txtNombre, "4, 2, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblSp = new JLabel("Sp:");
		contentPane.add(lblSp, "2, 4, left, default");
		
		txtSp = new JTextField();
		contentPane.add(txtSp, "4, 4, fill, default");
		txtSp.setColumns(10);
		
		JLabel lblEsquema = new JLabel("Esquema:");
		contentPane.add(lblEsquema, "2, 6, left, default");
		
		txtEsquema = new JTextField();
		contentPane.add(txtEsquema, "4, 6, fill, default");
		txtEsquema.setColumns(10);
		
		JLabel lblBaseDeDatos = new JLabel("Base de datos:");
		contentPane.add(lblBaseDeDatos, "2, 8, left, default");
		
		txtBasededatos = new JTextField();
		contentPane.add(txtBasededatos, "4, 8, fill, default");
		txtBasededatos.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		contentPane.add(lblDescripcion, "2, 10, left, default");
		
		txtDescripcion = new JTextField();
		contentPane.add(txtDescripcion, "4, 10, fill, default");
		txtDescripcion.setColumns(10);
		
		JLabel lblNombreArchivo = new JLabel("Nombre Archivo:");
		contentPane.add(lblNombreArchivo, "2, 12, left, default");
		
		txtNombrearchivo = new JTextField();
		contentPane.add(txtNombrearchivo, "4, 12, fill, default");
		txtNombrearchivo.setColumns(10);
		
		JLabel lblCampoNombre = new JLabel("Campo Nombre:");
		contentPane.add(lblCampoNombre, "2, 14, left, default");
		
		txtCampoNombre = new JTextField();
		contentPane.add(txtCampoNombre, "4, 14, fill, default");
		txtCampoNombre.setColumns(10);
		
		JLabel lblPagina = new JLabel("Pagina:");
		contentPane.add(lblPagina, "2, 16, left, default");
		
		String[] paginas =  { 
				"1 - Reporte en Grid",
				"2 - Reporte a Excel",
				"3 - Reporte en XML",
				"4 - Grafica Linea ,Barras & Spider",
				"5 - Grafica Pay & Dona",
				"6 - Proceso Nocturno",
				"7 - Pagina de Alta",
				"8 - Pagina de Modificacion",
				"9 - Pagina de Mostrar",
				"10 - Reporte Txt",
				"11 - Reporte Grid Por Default",
				"12 - Stream TXT",
				"13 - Reporte en PDF"
		};
		
		cbPagina = new JComboBox(paginas);
		contentPane.add(cbPagina, "4, 16, fill, default");
		
		btnAlta = new JButton("Alta");
		contentPane.add(btnAlta, "4, 18");
		
		btnCancelar = new JButton("Cancelar");
		contentPane.add(btnCancelar, "4, 20");
	}
	
	private void initListeners() {
		btnAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte r = new Reporte(bd);
				r.setNombre(txtNombre.getText());
				r.setSp(txtSp.getText());
				r.setBasdedatos(txtBasededatos.getText());
				r.setEsquema(txtEsquema.getText());
				r.setDescripcion(txtDescripcion.getText());
				r.setCampo_nombre(txtCampoNombre.getText());
				r.setNombre_archivo(txtNombrearchivo.getText());
				r.setPagina_id(cbPagina.getSelectedIndex()+1);
				try {
					r.alta(bd.getConn());
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+"Reporte creado con exito"+"</p></body></html>");
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+e.getMessage()+"</p></body></html>");
					e.printStackTrace();
				}
				
			}
			
		});
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		
		
		
		class Enter implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				btnAlta.doClick();
			}
			
		}
		
		Enter enter = new Enter();
		
		txtNombre.addActionListener(enter);
		txtSp.addActionListener(enter);
		txtEsquema.addActionListener(enter);
		txtBasededatos.addActionListener(enter);
		txtDescripcion.addActionListener(enter);
		txtNombrearchivo.addActionListener(enter);
		txtCampoNombre.addActionListener(enter);
	}

}
