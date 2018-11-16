package noxcuse.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import noxcuse.logica.BD;
import noxcuse.logica.Configuracion;
import noxcuse.logica.Tabla;
import javax.swing.JTable;

public class Seleccion extends JFrame {
	private BD bd;
	private Configuracion config;
	private JComboBox comboBox;
	private JCheckBox chckbxInsert;
	private JCheckBox chckbxSelect;
	private JCheckBox chckbxUpdate;
	private JCheckBox chckbxDelete;
	private JButton btnGenerar;
	private JButton btnAnadir;
	private JButton btnExportar;
	private JScrollPane scrollPane;
	private JTextPane queryUniPane;
	private JCheckBox chckbxAuditoria;
	private JScrollPane scrollTablas;
	private JTable tableTablas;
	private JButton btnGenerar_1;
	private JButton btnCarpetado;
	private JCheckBox chckbxAuditoria_1;
	private JLabel lblPath;
	private JButton btnConfig;
	private JButton btnEjecutar;
	private JButton btnEjecutar_1;

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

	public String generar() {
		String script = "";
		for (int i = 0; i < tableTablas.getRowCount(); i++) {
			if ((boolean) tableTablas.getValueAt(i, 0)) {
				if (chckbxAuditoria_1.isSelected()) {
					if ((boolean) tableTablas.getValueAt(i, 2))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getInsertSPAuditoria();
					if ((boolean) tableTablas.getValueAt(i, 3))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getSelectSPAuditoria(this.bd.getConn());
					if ((boolean) tableTablas.getValueAt(i, 4))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getUpdateSPAuditoria();
					if ((boolean) tableTablas.getValueAt(i, 5))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getDeleteSPAuditoria();
				} else {
					if ((boolean) tableTablas.getValueAt(i, 2))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getInsertSP();
					if ((boolean) tableTablas.getValueAt(i, 3))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getSelectSP(this.bd.getConn());
					if ((boolean) tableTablas.getValueAt(i, 4))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getUpdateSP();
					if ((boolean) tableTablas.getValueAt(i, 5))
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getDeleteSP();
				}
			}
		}
		return script;
	}

	public void carpetado() {
		String script = "";
		String path = bd.getPathBD() + "/" + bd.getBd() + "/StoredProcedures/";
		for (int i = 0; i < tableTablas.getRowCount(); i++) {
			if ((boolean) tableTablas.getValueAt(i, 0)) {
				Tabla t = ((Tabla) tableTablas.getValueAt(i, 1));
				if (chckbxAuditoria_1.isSelected()) {
					if ((boolean) tableTablas.getValueAt(i, 2)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getInsertSPAuditoria();
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "ALT", script, "sql");
					}
					if ((boolean) tableTablas.getValueAt(i, 3)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getSelectSPAuditoria(this.bd.getConn());
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "CON", script, "sql");
					}
					if ((boolean) tableTablas.getValueAt(i, 4)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getUpdateSPAuditoria();
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "ACT", script, "sql");
					}
					if ((boolean) tableTablas.getValueAt(i, 5)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getDeleteSPAuditoria();
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "DEL", script, "sql");
					}
				} else {
					if ((boolean) tableTablas.getValueAt(i, 2)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getInsertSP();
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "ALT", script, "sql");
					}
					if ((boolean) tableTablas.getValueAt(i, 3)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getSelectSP(this.bd.getConn());
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "CON", script, "sql");
					}
					if ((boolean) tableTablas.getValueAt(i, 4)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getUpdateSP();
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "ACT", script, "sql");
					}
					if ((boolean) tableTablas.getValueAt(i, 5)) {
						script = ((Tabla) tableTablas.getValueAt(i, 1)).getDeleteSP();
						((Tabla) tableTablas.getValueAt(i, 1)).generarArchivo(path,
								t.getEsquema() + ".SP_" + t.getNombre() + "DEL", script, "sql");
					}
				}
			}
		}

		JOptionPane.showMessageDialog(null, "Archivos creados en el directorio " + path);
	}

	public String generarMulti() {
		String script = "";
		for (int i = 0; i < tableTablas.getRowCount(); i++) {
			if ((boolean) tableTablas.getValueAt(i, 0)) {
				Tabla t = ((Tabla) tableTablas.getValueAt(i, 1));
				if (chckbxAuditoria_1.isSelected()) {
					if ((boolean) tableTablas.getValueAt(i, 2)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getInsertSPAuditoria();
					}
					if ((boolean) tableTablas.getValueAt(i, 3)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getSelectSPAuditoria(this.bd.getConn());
					}
					if ((boolean) tableTablas.getValueAt(i, 4)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getUpdateSPAuditoria();
					}
					if ((boolean) tableTablas.getValueAt(i, 5)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getDeleteSPAuditoria();
					}
				} else {
					if ((boolean) tableTablas.getValueAt(i, 2)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getInsertSP();
					}
					if ((boolean) tableTablas.getValueAt(i, 3)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getSelectSP(this.bd.getConn());
					}
					if ((boolean) tableTablas.getValueAt(i, 4)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getUpdateSP();
					}
					if ((boolean) tableTablas.getValueAt(i, 5)) {
						script += ((Tabla) tableTablas.getValueAt(i, 1)).getDeleteSP();
					}
				}
			}
		}
		return script;
	}

	/**
	 * Create the frame.
	 */
	public Seleccion(BD bd) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Seleccion.class.getResource("/resources/logo.png")));
		config = new Configuracion();
		setTitle("Generacion de codigo");
		this.bd = bd;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 534, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.DEFAULT_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, }));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "2, 2, 4, 1, fill, fill");
		JPanel uniTabla = new JPanel();
		uniTabla.setToolTipText("Genera los Stored Procedures por tabla");
		tabbedPane.addTab("Por tabla", null, uniTabla, null);
		uniTabla.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				RowSpec.decode("default:grow"),}));

		JLabel lblSeleccioneUnaTabla = new JLabel("Seleccione una tabla");
		uniTabla.add(lblSeleccioneUnaTabla, "2, 2");

		comboBox = new JComboBox();
		uniTabla.add(comboBox, "2, 4, 7, 1, fill, default");

		chckbxAuditoria = new JCheckBox("Auditoria");
		uniTabla.add(chckbxAuditoria, "6, 6");

		chckbxInsert = new JCheckBox("Insert");
		uniTabla.add(chckbxInsert, "2, 8");

		chckbxSelect = new JCheckBox("Select");
		uniTabla.add(chckbxSelect, "2, 10");

		chckbxUpdate = new JCheckBox("Update");
		uniTabla.add(chckbxUpdate, "2, 12");

		chckbxDelete = new JCheckBox("Delete");
		uniTabla.add(chckbxDelete, "2, 14");

		btnGenerar = new JButton("Generar");
		btnGenerar.setIcon(new ImageIcon(
				Seleccion.class.getResource("/com/sun/javafx/scene/web/skin/FontBackgroundColor_16x16_JFX.png")));
		uniTabla.add(btnGenerar, "2, 16");

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setEnabled(false);
		btnAnadir.setIcon(
				new ImageIcon(Seleccion.class.getResource("/javax/swing/plaf/metal/icons/ocean/minimize.gif")));
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		uniTabla.add(btnAnadir, "4, 16");

		btnExportar = new JButton("Exportar");
		btnExportar.setEnabled(false);
		btnExportar.setIcon(
				new ImageIcon(Seleccion.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		uniTabla.add(btnExportar, "6, 16");
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setEnabled(false);
		btnEjecutar.setIcon(new ImageIcon(Seleccion.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		uniTabla.add(btnEjecutar, "8, 16");

		JLabel lblQueryUni = new JLabel("Query");
		uniTabla.add(lblQueryUni, "2, 18");

		scrollPane = new JScrollPane();
		uniTabla.add(scrollPane, "2, 20, 7, 1, fill, fill");

		queryUniPane = new JTextPane();
		queryUniPane.setEditable(false);
		scrollPane.setViewportView(queryUniPane);

		JPanel multiTabla = new JPanel();
		tabbedPane.addTab("Multiple tabla", null, multiTabla, null);
		multiTabla.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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

		scrollTablas = new JScrollPane();
		multiTabla.add(scrollTablas, "2, 2, 7, 1, fill, fill");

		tableTablas = new JTable(new VstTableItemModel(bd.getTablas()));
		scrollTablas.setViewportView(tableTablas);

		chckbxAuditoria_1 = new JCheckBox("Auditoria");
		multiTabla.add(chckbxAuditoria_1, "2, 4");
				
						lblPath = new JLabel("");
						multiTabla.add(lblPath, "6, 4");
						lblPath.setText(bd.getPathBD());
		
				btnConfig = new JButton("Config");
				btnConfig.setSelectedIcon(
						new ImageIcon(Seleccion.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
				multiTabla.add(btnConfig, "8, 4");
						
						btnEjecutar_1 = new JButton("Ejecutar");
						btnEjecutar_1.setIcon(new ImageIcon(Seleccion.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
						multiTabla.add(btnEjecutar_1, "4, 6");
				
						btnGenerar_1 = new JButton("Generar");
						btnGenerar_1
								.setIcon(new ImageIcon(Seleccion.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
						multiTabla.add(btnGenerar_1, "6, 6");
		
				btnCarpetado = new JButton("Carpetado");
				btnCarpetado.setIcon(
						new ImageIcon(Seleccion.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
				multiTabla.add(btnCarpetado, "8, 6");

		tableTablas.getColumnModel().getColumn(0).setCellEditor(tableTablas.getDefaultEditor(Boolean.class));
		tableTablas.getColumnModel().getColumn(0).setCellRenderer(tableTablas.getDefaultRenderer(Boolean.class));
		tableTablas.getColumnModel().getColumn(0)
				.setHeaderRenderer(new CheckBoxHeader(new MyItemListener(0), "Seleccionar todos"));

		tableTablas.getColumnModel().getColumn(2).setCellEditor(tableTablas.getDefaultEditor(Boolean.class));
		tableTablas.getColumnModel().getColumn(2).setCellRenderer(tableTablas.getDefaultRenderer(Boolean.class));
		tableTablas.getColumnModel().getColumn(2)
				.setHeaderRenderer(new CheckBoxHeader(new MyItemListener(2), "Insert"));

		tableTablas.getColumnModel().getColumn(3).setCellEditor(tableTablas.getDefaultEditor(Boolean.class));
		tableTablas.getColumnModel().getColumn(3).setCellRenderer(tableTablas.getDefaultRenderer(Boolean.class));
		tableTablas.getColumnModel().getColumn(3)
				.setHeaderRenderer(new CheckBoxHeader(new MyItemListener(3), "Select"));

		tableTablas.getColumnModel().getColumn(4).setCellEditor(tableTablas.getDefaultEditor(Boolean.class));
		tableTablas.getColumnModel().getColumn(4).setCellRenderer(tableTablas.getDefaultRenderer(Boolean.class));
		tableTablas.getColumnModel().getColumn(4)
				.setHeaderRenderer(new CheckBoxHeader(new MyItemListener(4), "Update"));

		tableTablas.getColumnModel().getColumn(5).setCellEditor(tableTablas.getDefaultEditor(Boolean.class));
		tableTablas.getColumnModel().getColumn(5).setCellRenderer(tableTablas.getDefaultRenderer(Boolean.class));
		tableTablas.getColumnModel().getColumn(5)
				.setHeaderRenderer(new CheckBoxHeader(new MyItemListener(5), "Delete"));

		for (int i = 0; i < bd.getTablas().size(); i++) {
			comboBox.addItem(bd.getTablas().get(i));
		}
	}

	public void initListeners() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent w) {
				String ObjButtons[] = {"Salir", "Volver al menu", "Cancelar"};
		        int resultado = JOptionPane.showOptionDialog(null,"¿Desea salir o volver al menu?","Salir",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        switch(resultado) {
		        	case 0:
		        		System.exit(0);
		        		break;
		        	case 1:
		        		dispose();
		        		break;
		        }
		        return;
			}
			
		});
		btnGenerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAnadir.setEnabled(true);
				btnExportar.setEnabled(true);
				btnEjecutar.setEnabled(true);
				Tabla t = (Tabla) comboBox.getSelectedItem();
				String query = "";
				if(chckbxAuditoria.isSelected()) {
					if(chckbxInsert.isSelected()) {
						query += t.getInsertSPAuditoria();
					}
					if(chckbxSelect.isSelected()) {
						query += t.getSelectSPAuditoria(bd.getConn());
					}
					if(chckbxUpdate.isSelected()) {
						query += t.getUpdateSPAuditoria();
					}
					if(chckbxDelete.isSelected()) {
						query += t.getDeleteSPAuditoria();
					}
				}
				else {
					if(chckbxInsert.isSelected()) {
						query += t.getInsertSP();
					}
					if(chckbxSelect.isSelected()) {
						query += t.getSelectSP(bd.getConn());
					}
					if(chckbxUpdate.isSelected()) {
						query += t.getUpdateSP();
					}
					if(chckbxDelete.isSelected()) {
						query += t.getDeleteSP();
					}
				}
				queryUniPane.setText(query);
			}
		});
		btnAnadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tabla t = (Tabla) comboBox.getSelectedItem();
				String query = "";
				if(chckbxAuditoria.isSelected()) {
					if(chckbxInsert.isSelected()) {
						query += t.getInsertSPAuditoria();
					}
					if(chckbxSelect.isSelected()) {
						query += t.getSelectSPAuditoria(bd.getConn());
					}
					if(chckbxUpdate.isSelected()) {
						query += t.getUpdateSPAuditoria();
					}
					if(chckbxDelete.isSelected()) {
						query += t.getDeleteSPAuditoria();
					}
				}
				else {
					if(chckbxInsert.isSelected()) {
						query += t.getInsertSP();
					}
					if(chckbxSelect.isSelected()) {
						query += t.getSelectSP(bd.getConn());
					}
					if(chckbxUpdate.isSelected()) {
						query += t.getUpdateSP();
					}
					if(chckbxDelete.isSelected()) {
						query += t.getDeleteSP();
					}
				}
				queryUniPane.setText(queryUniPane.getText() + "\r\n" + query);
			}
		});
		btnExportar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter sqlFilter = new FileNameExtensionFilter("Archivo SQL (*.sql)", "sql");
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Archivo de texto (*.txt)", "txt");
				chooser.addChoosableFileFilter(sqlFilter);
				chooser.addChoosableFileFilter(txtFilter);
		        chooser.setFileFilter(sqlFilter);
			    chooser.setCurrentDirectory(new File("/home/me/Documents"));
			    int retrival = chooser.showSaveDialog(null);
			    if (retrival == JFileChooser.APPROVE_OPTION) {
			        try {
			        	String extension = "";
			        	if(!chooser.isAcceptAllFileFilterUsed()) {
			        	FileFilter fileFilter = chooser.getFileFilter();
			        	FileNameExtensionFilter fileNameExtensionFilter = (FileNameExtensionFilter) fileFilter;
			            extension = "." + fileNameExtensionFilter.getExtensions()[0];
			        	}
			        	try(FileWriter fw = new FileWriter(chooser.getSelectedFile() + extension)) {
			        	    fw.write(queryUniPane.getText());
			        	}
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String query = queryUniPane.getText();
				String [] querys = query.split("GO");
				try {
					bd.executeMultiple(querys);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al ejecutar el query, utilice la opcion generar para revisar el script generado.");
					return;
				}
				JOptionPane.showMessageDialog(null, "Exito al ejecutar el query.");
			}
		});
		btnEjecutar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = generarMulti();
				String [] querys = query.split("GO");
				try {
					bd.executeMultiple(querys);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al ejecutar el query");
				}
				JOptionPane.showMessageDialog(null, "Exito al ejecutar el query.");
			}
		});
		btnGenerar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String script = generarMulti();
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter sqlFilter = new FileNameExtensionFilter("Archivo SQL (*.sql)", "sql");
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Archivo de texto (*.txt)", "txt");
				chooser.addChoosableFileFilter(sqlFilter);
				chooser.addChoosableFileFilter(txtFilter);
				chooser.setFileFilter(sqlFilter);
				chooser.setCurrentDirectory(new File("/home/me/Documents"));
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					try {
						String extension = "";
						if (!chooser.isAcceptAllFileFilterUsed()) {
							FileFilter fileFilter = chooser.getFileFilter();
							FileNameExtensionFilter fileNameExtensionFilter = (FileNameExtensionFilter) fileFilter;
							extension = "." + fileNameExtensionFilter.getExtensions()[0];
						}
						try (FileWriter fw = new FileWriter(chooser.getSelectedFile() + extension)) {
							fw.write(script);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnCarpetado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carpetado(); 
			}
		});
		btnConfig.addActionListener(new ActionListener() {

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
			    	bd.setPathBD(nuevo);
			    	lblPath.setText(nuevo);			      
			    }
			    else {
			      System.out.println("No Selection ");
			    }
		}
				
	});
}

public class VstTableItemModel extends AbstractTableModel {
	private Object[][] tablas;
	private String[] columnNames = { "Generar", "Tabla", "Insert", "Select", "Update", "Delete" };

	public VstTableItemModel(Vector<Tabla> tablas) {
		this.tablas = new Object[tablas.size()][6];
		for (int i = 0; i < tablas.size(); i++) {
			this.tablas[i][0] = Boolean.FALSE;
			this.tablas[i][1] = tablas.get(i);
			this.tablas[i][2] = Boolean.FALSE;
			this.tablas[i][3] = Boolean.FALSE;
			this.tablas[i][4] = Boolean.FALSE;
			this.tablas[i][5] = Boolean.FALSE;
		}
	}

	@Override
	public int getRowCount() {
		return tablas.length;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Tabla tabla = (Tabla) tablas[rowIndex][1];
		switch (columnIndex) {
		case 0:
			value = tablas[rowIndex][0];
			break;
		case 1:
			value = tabla;
			break;
		case 2:
			value = tablas[rowIndex][2];
			break;
		case 3:
			value = tablas[rowIndex][3];
			break;
		case 4:
			value = tablas[rowIndex][4];
			break;
		case 5:
			value = tablas[rowIndex][5];
			break;

		}

		return value;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Boolean.class;
		case 1:
			return Tabla.class;
		case 2:
			return Boolean.class;
		case 3:
			return Boolean.class;
		case 4:
			return Boolean.class;
		case 5:
			return Boolean.class;
		default:
			return Boolean.class;

		}
	}

	/*
	 * Override this if you want the values to be editable...
	 * 
	 * @Override public void setValueAt(Object aValue, int rowIndex, int
	 * columnIndex) { //.... }
	 */
	public boolean isCellEditable(int row, int column) {
		if (column == 0 || column == 2 || column == 3 || column == 4 || column == 5)
			return true;

		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// super.setValueAt(aValue, rowIndex, columnIndex); by default empty
		// implementation is not necesary if direct parent is AbstractTableModel
		tablas[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);// notify listeners
	}

	/**
	 * This will return the tabla at the specified row...
	 * 
	 * @param row
	 * @return
	 */
	public Tabla getTablaAt(int row) {
		return (Tabla) tablas[row][1];
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}

class CheckBoxHeader extends JCheckBox implements TableCellRenderer, MouseListener {
	protected CheckBoxHeader rendererComponent;
	protected String nombre;
	protected int column;
	protected boolean mousePressed = false;

	public CheckBoxHeader(ItemListener itemListener, String nombre) {
		this.nombre = nombre;
		rendererComponent = this;
		rendererComponent.addItemListener(itemListener);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (table != null) {
			JTableHeader header = table.getTableHeader();
			if (header != null) {
				rendererComponent.setForeground(header.getForeground());
				rendererComponent.setBackground(header.getBackground());
				rendererComponent.setFont(header.getFont());
				header.addMouseListener(rendererComponent);
			}
		}
		setColumn(column);
		rendererComponent.setText(this.nombre);
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		return rendererComponent;
	}

	protected void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	protected void handleClickEvent(MouseEvent e) {
		if (mousePressed) {
			mousePressed = false;
			JTableHeader header = (JTableHeader) (e.getSource());
			JTable tableView = header.getTable();
			TableColumnModel columnModel = tableView.getColumnModel();
			int viewColumn = columnModel.getColumnIndexAtX(e.getX());
			int column = tableView.convertColumnIndexToModel(viewColumn);

			if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
				doClick();
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		handleClickEvent(e);
		((JTableHeader) e.getSource()).repaint();
	}

	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}

class MyItemListener implements ItemListener {
	private int column;

	public MyItemListener(int col) {
		this.column = col;
	}

	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		if (source instanceof AbstractButton == false)
			return;
		boolean checked = e.getStateChange() == ItemEvent.SELECTED;
		for (int x = 0, y = tableTablas.getRowCount(); x < y; x++) {
			tableTablas.setValueAt(new Boolean(checked), x, column);
		}
	}
}

}
