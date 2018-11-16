package noxcuse.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import noxcuse.logica.Item;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Buscador extends JFrame {

	private JPanel contentPane;
	private JButton btnSeleccionar;
	private JList list;
	public int seleccionado = 0;

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
	public Buscador(Vector<Item> lista) {
		setTitle("Buscador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblSeleccioneUno = new JLabel("Seleccione uno");
		contentPane.add(lblSeleccioneUno, "2, 2");
		
		list = new JList(lista);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(list, "2, 4, 3, 1, fill, fill");
		
		btnSeleccionar = new JButton("Seleccionar");
		contentPane.add(btnSeleccionar, "4, 6");
	}


	private void seleccionar() {
		if(list.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null,"Debe seleccionar una opcion");
			return;
		}
		this.seleccionado = ((Item) list.getSelectedValue()).id;
		this.dispose();
	}
	
	
	public void initListeners() {
		btnSeleccionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				seleccionar();
			}
		});
	}

}
