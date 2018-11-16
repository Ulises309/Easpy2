package noxcuse.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import noxcuse.logica.BD;
import noxcuse.logica.Datos;
import noxcuse.logica.Elemento;
import noxcuse.logica.Parametro;
import noxcuse.logica.Reporte;

public class GeneracionDePantalla extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BD bd;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<Reporte> comboBox;
	private JButton btnAlta;
	private JButton btnSeleccionar;
	private JButton btnConfigurar;
	private JButton btnGuardar;
	private JButton btnGenerar;
	private JScrollPane scrollPane;
	private VstTableItemModel model;
	private JLabel lblLayout;
	private JMenuItem eliminarItem;
	private JPopupMenu popupMenu;
	private JButton btnScript;
	private int seleccionado;
	private JLabel lblReporte;
	private JMenuItem anadirItem;
	private JMenuItem subirItem;
	private JMenuItem bajarItem;
	private JButton btnPermisos;

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
	public GeneracionDePantalla(BD bd) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GeneracionDePantalla.class.getResource("/resources/logo.png")));
		this.seleccionado = -1;
		this.bd = bd;
		try {
			bd.obtenerReportes();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se pudieron obtener los reportes");
			e.printStackTrace();
		}
		setTitle("Pantallas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    //setSize(screenSize.width, screenSize.height);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblSeleccionaUnReporte = new JLabel("Selecciona un reporte");
		contentPane.add(lblSeleccionaUnReporte, "2, 2");
		
		comboBox = new JComboBox<Reporte>();
		contentPane.add(comboBox, "2, 4, 7, 1, fill, default");
		
		btnAlta = new JButton("A\u00F1adir");
		btnAlta.setIcon(new ImageIcon(GeneracionDePantalla.class.getResource("/resources/add.png")));
		
		contentPane.add(btnAlta, "10, 4");
		
		lblReporte = new JLabel("Sin reporte seleccionado");
		contentPane.add(lblReporte, "2, 6");
		
		btnSeleccionar = new JButton("Seleccionar");
		contentPane.add(btnSeleccionar, "8, 6");
		
		btnPermisos = new JButton("Permisos");
		
		contentPane.add(btnPermisos, "10, 6");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "2, 8, 9, 1, fill, fill");
		
		List<TableCellEditor> editors = new ArrayList<TableCellEditor>(3);

		String[] variables = {
        		"STRING",
        		"INT",
        		"SESSION STRING",
        		"SESSION INT",
        		"NULL",
        		"Archivo",
        		"ARCHIVO GOOGLE DRIVE",
        		"Files",
				"XML Subir",
				"ARCHIVOS FISICOS"
        };
        
        JComboBox cbvariables = new JComboBox( variables );
        DefaultCellEditor dcevariables = new DefaultCellEditor( cbvariables );
        editors.add( dcevariables );
		
		String[] controles = { 
				"TEXTBOX",
				"COMBO",
				"FECHA",
				"ARCHIVO",
				"COMBO PADRE",
				"LISTATEXBOX",
				"GRID",
				"TXTBUSCADOR",
				"Files",
				"XML Subir",
				"ARCHIVOS FISICOS",
				"TABLA",
				"ARCHIVO GOOGLE DRIVE",
				"RADIO LISTA" };
		
        JComboBox cbControles = new JComboBox( controles );
        DefaultCellEditor dceControles = new DefaultCellEditor( cbControles );
        editors.add( dceControles );
		
		model = new VstTableItemModel();
		table = new JTable(model) {
		//  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column)
            {
                int modelColumn = convertColumnIndexToModel( column );

                if (modelColumn == 2)
                    return editors.get(0);
                if (modelColumn == 3)
                    return editors.get(1);
                else
                    return super.getCellEditor(row, column);
            }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		eliminarItem = new JMenuItem("Eliminar");
	    anadirItem = new JMenuItem("Añadir");
	    subirItem = new JMenuItem("Subir");
	    bajarItem = new JMenuItem("Bajar");
		popupMenu = new JPopupMenu() {
		      @Override
		      public void show(Component invoker, int x, int y) {
		    	int rowAtPoint = -1;
		    	eliminarItem.setVisible(true);
	    		subirItem.setVisible(true);
	    		bajarItem.setVisible(true);
		    	try{
		    		rowAtPoint = table.rowAtPoint(new Point(x, y));
		    		table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
		    	}
		    	catch(Exception e) {
		    		
		    	}
		    	if(rowAtPoint != -1) {
			    	if(rowAtPoint<0) {
			    		eliminarItem.setVisible(false);
			    	}
			        if(rowAtPoint==0) {
			        	subirItem.setVisible(false);
			        }
			        if(rowAtPoint==model.getRowCount()-1) {
			        	bajarItem.setVisible(false);
			        }
		    	}
		    	else {
		    		eliminarItem.setVisible(false);
		    		subirItem.setVisible(false);
		    		bajarItem.setVisible(false);
		    	}
		        super.show(invoker, x, y);
		      }
		};

		popupMenu.add(subirItem);
		popupMenu.add(bajarItem);
		popupMenu.add(eliminarItem);
		popupMenu.add(anadirItem);
		
		table.setComponentPopupMenu(popupMenu);
		scrollPane.setComponentPopupMenu(popupMenu);
		
		
		btnConfigurar = new JButton("Seleccionar layout");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		lblLayout = new JLabel("Seleccione un archivo layout");
		contentPane.add(lblLayout, "2, 10");
		contentPane.add(btnConfigurar, "4, 10");
		
		btnGuardar = new JButton("Guardar");
		contentPane.add(btnGuardar, "6, 10");
		
		btnScript = new JButton("Script de instalacion");
		btnScript.setIcon(new ImageIcon(GeneracionDePantalla.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		contentPane.add(btnScript, "8, 10");
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setIcon(new ImageIcon(GeneracionDePantalla.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		contentPane.add(btnGenerar, "10, 10");
		
		for (int i = 0; i < bd.getReportes().size(); i++) {
			comboBox.addItem(bd.getReportes().get(i));
		}
		
		if(bd.getPathLayout() == null) {
			btnGenerar.setEnabled(false);
		}
		else {
			lblLayout.setText(bd.getPathLayout());
		}
		
	}
	
	public void initListeners() {

		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		    	if(!model.getModificado()) {
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
		        String ObjButtons[] = {"Salir","Guardar y salir", "Volver al menu","Guardar y volver al menu", "Cancelar"};
		        int resultado = JOptionPane.showOptionDialog(null,"¿Desea salir sin guardar los cambios?","Salir",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        switch(resultado) {
		        	case 0:
		        		System.exit(0);
		        		break;
		        	case 1:
		        		btnGuardar.doClick();
		        		System.exit(0);
		        		break;
		        	case 2:
		        		dispose();
		        		break;
		        	case 3:
		        		btnGuardar.doClick();
		        		dispose();
		        		break;
		        }
		    }
		});
		btnPermisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaPermiso ap = new AltaPermiso(bd,(Reporte)comboBox.getSelectedItem());
				ap.run();
			}
		});
		btnSeleccionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(model.getModificado()) {
			        String ObjButtons[] = {"Cambiar","Guardar y cambiar", "Cancelar"};
			        int resultado = JOptionPane.showOptionDialog(null,"¿Desea seleccionar otro reporte sin guardar los cambios?","Cancelar",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
			        switch(resultado) {
			        	case 0:
			        		model.setModificado(false);
			        		break;
			        	case 1:
			        		btnGuardar.doClick();
			        		break;
			        	case 2:
			        		return;
			        }
				}
				seleccionado = comboBox.getSelectedIndex();
				lblReporte.setText(comboBox.getSelectedItem().toString());
				Reporte r = (Reporte) comboBox.getSelectedItem();
				try {
					r.obtenerParametros(bd.getConn());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"No se pudieron obtener los reportes");
					e.printStackTrace();
				}
				model.setParametros(r.getParametros());
				model.fireTableDataChanged();
				seleccionado = comboBox.getSelectedIndex();
			}
			
		});
		btnGenerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reporte r = (Reporte) comboBox.getSelectedItem();
				r.setParametros(model.getParametros());
				comboBox.setSelectedItem(r);
				String archivo;
				Elemento codigo = ((Reporte) comboBox.getSelectedItem()).generar();
				Elemento btnGenerar = new Elemento("button");
				Elemento grupo = new Elemento("div");
				grupo.addClass("form-group");
				btnGenerar.addClass("btnGenerar");
				btnGenerar.addClass("btn");
				btnGenerar.addClass("btn-primary");
				btnGenerar.setHtml("Generar");
				btnGenerar.attr("reporte", ((Reporte) comboBox.getSelectedItem()).getId_reporte());
				grupo.append(btnGenerar);
				codigo.append(grupo);
				try {
					archivo = new String(Files.readAllBytes(Paths.get(bd.getPathLayout())), StandardCharsets.UTF_8);
					archivo = archivo.replace("InsertaCodigo()", codigo.toString());
					archivo = archivo.replace("InsertaFormulario()", codigo.toString());
					archivo = archivo.replace("InsertaBoton()", btnGenerar.toString());
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File(bd.getPathHtml()));
					int retrival = chooser.showSaveDialog(null);
					if (retrival == JFileChooser.APPROVE_OPTION) {
						try {
							try (FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile())) {
								fos.write(archivo.getBytes(Charset.forName("UTF-8")));
								JOptionPane.showMessageDialog(null, "Archivo creado correctamente");
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Hubo un error al generar el archivo, compruebe que el layout existe");
				}
			}
			
		});
		btnAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AltaReporte ap = new AltaReporte(bd);
				ap.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				    	comboBox.removeAllItems();
				    	try {
							bd.obtenerReportes();
							for(int i = 0; i < bd.getReportes().size(); i++) {
					    		comboBox.addItem(bd.getReportes().get(i));
					    	}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+"No se pudieron obtener los reportes, compruebe que existen registros en Reportes.Reportes"+"</p></body></html>");
							e.printStackTrace();
						}
				    	
				    }
				});
				ap.abrir();
			}
		});
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
				for(int i = 0; i < model.getRowCount(); i++) {
						model.getParametroAt(i).actualizar(bd.getConn());
					}
				JOptionPane.showMessageDialog(null, "Campos guardados correctamente");
				model.setModificado(false);
				}
				catch(Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Hubo un error al guardar los campos");
				}
			}
			
		});
		
		btnConfigurar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new File("C:/"));
			    chooser.setDialogTitle("Seleccione un archivo");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false); 
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			    	String nuevo = chooser.getSelectedFile().toString();
			    	lblLayout.setText(nuevo);	
			    	bd.setPathLayout(nuevo);
			    	btnGenerar.setEnabled(true);
			    }
			    else {
			      System.out.println("No Selection ");
			    }
			}
		});
		
		eliminarItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				try {
					model.getParametroAt(row).eliminar(bd.getConn());
					model.parametros.remove(row);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+e1.getMessage()+"</p></body></html>");
					e1.printStackTrace();
				}
				model.fireTableDataChanged();
			}
			
		});
		
		subirItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				try {
					Parametro arriba = model.getParametroAt(row-1);
					Parametro abajo = model.getParametroAt(row);
					arriba.setOrden(arriba.getOrden()+1);
					abajo.setOrden(abajo.getOrden()-1);
					model.parametros.set(row, arriba);
					model.parametros.set(row-1, abajo);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+e1.getMessage()+"</p></body></html>");
					e1.printStackTrace();
				}
				model.fireTableDataChanged();
				model.setModificado(true);
				table.setRowSelectionInterval(row-1, row-1);
			}
			
		});
		
		bajarItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				try {
					Parametro arriba = model.getParametroAt(row);
					Parametro abajo = model.getParametroAt(row+1);
					arriba.setOrden(arriba.getOrden()+1);
					abajo.setOrden(abajo.getOrden()-1);
					model.parametros.set(row+1, arriba);
					model.parametros.set(row, abajo);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+e1.getMessage()+"</p></body></html>");
					e1.printStackTrace();
				}
				model.fireTableDataChanged();
				model.setModificado(true);
				table.setRowSelectionInterval(row+1, row+1);
			}
			
		});
		
		anadirItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Parametro ultimo = model.getParametroAt(model.getRowCount()-1);
				Parametro nuevo = new Parametro(ultimo.getReporte_Id(), ultimo.getOrden()+1, bd);
				try {
					nuevo.alta(bd.getConn());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>No se pudo añadir el campo</p></body></html>");
					e1.printStackTrace();
				}
				model.parametros.addElement(nuevo);
				model.fireTableDataChanged();
			}
			
		});
		btnScript.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(seleccionado < 0) {
					return;
				}
				int reporte = seleccionado;
				if(model.getModificado()) {
					if (JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?", "WARNING",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						btnGuardar.doClick();
					}
					else {
						JOptionPane.showMessageDialog(null, "Debe guardar los cambios para generar el script de instalación.");
						return;
					}
				}
				
			    try {
					bd.obtenerReportes();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+"No se pudieron obtener los reportes, compruebe que existen registros en Reportes.Reportes"+"</p></body></html>");
					e.printStackTrace();
				}
			    comboBox.removeAllItems();
			    for (int i = 0; i < bd.getReportes().size(); i++) {
					comboBox.addItem(bd.getReportes().get(i));
				}
			    comboBox.setSelectedIndex(reporte);
			    btnSeleccionar.doClick();
			    try {
					((Reporte) comboBox.getSelectedItem()).obtenerStore(bd.getConn());
					((Reporte) comboBox.getSelectedItem()).obtenerParametrosSP(bd.getConn());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"<html><body><p style='width:200px;'>"+"No se pudieron obtener el stored del reporte, compruebe que exista"+"</p></body></html>");
					e.printStackTrace();
				}
			    
			    String script = ((Reporte) comboBox.getSelectedItem()).getScriptProduccion();
			    
			    JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter sqlFilter = new FileNameExtensionFilter("Archivo SQL (*.sql)", "sql");
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Archivo de texto (*.txt)", "txt");
				chooser.addChoosableFileFilter(sqlFilter);
				chooser.addChoosableFileFilter(txtFilter);
		        chooser.setFileFilter(sqlFilter);
		        chooser.setSelectedFile(new File("/home/me/Documents/" + comboBox.getSelectedItem().toString() + ".sql"));
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
			        	    fw.write(script);
			        	}
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
			
		});
	}

public class VstTableItemModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Parametro> parametros;
	private String[] columnNames = { "Id_Campo", "Reporte_Id", "Variable_Id", "Control_Id", "Titulo_Pagina",
			"Nombre", "Orden", "Visible", "Valor_Variable", "DataSet", "DS", "Parametro", "ComboPadre",
			"FileJScript", "FuncionJS", "Evento", "CssFile", "Css", "Ancho", "Longitud", "Renglon",
			"Class", "Parsley", "Omitir" };
	private String[] controles = { 
			"TEXTBOX",
			"COMBO",
			"FECHA",
			"ARCHIVO",
			"COMBO PADRE",
			"LISTATEXBOX",
			"GRID",
			"TXTBUSCADOR",
			"Files",
			"XML Subir",
			"ARCHIVOS FISICOS",
			"TABLA",
			"ARCHIVO GOOGLE DRIVE",
			"RADIO LISTA" };
	String[] variables = {
    		"STRING",
    		"INT",
    		"SESSION STRING",
    		"SESSION INT",
    		"NULL",
    		"Archivo",
    		"ARCHIVO GOOGLE DRIVE",
			"Files",
			"XML Subir",
			"ARCHIVOS FISICOS"
    		
    };
	private boolean modificado = false;
	
	public VstTableItemModel() {
		this.parametros = new Vector<Parametro>();
	}
	
	public VstTableItemModel(Vector<Parametro> parametros) {
		setParametros(parametros);
	}
	
	public void setParametros(Vector<Parametro> parametros) {
		this.parametros = parametros;
	}

	@Override
	public int getRowCount() {
		return parametros.size();
	}

	@Override
	public int getColumnCount() {
		return 24;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = "??";
		value = parametros.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return ((Parametro) value).getId_Campo();
		case 1:
			return ((Parametro) value).getReporte_Id();
		case 2:
			return variables[((Parametro) value).getVariable_Id()-1];
		case 3:
			return controles[((Parametro) value).getControl_Id()-1];
		case 4:
			return Datos.isNull(((Parametro) value).getTitulo_pagina());
		case 5:
			return Datos.isNull(((Parametro) value).getNombre());
		case 6:
			return ((Parametro) value).getOrden();
		case 7:
			return ((Parametro) value).getVisible();
		case 8:
			return Datos.isNull(((Parametro) value).getValor_variable());
		case 9:
			return Datos.isNull(((Parametro) value).getDataset());
		case 10:
			return Datos.isNull(((Parametro) value).getDs());
		case 11:
			return Datos.isNull(((Parametro) value).getParametro());
		case 12:
			return Datos.isNull(((Parametro) value).getCombopadre());
		case 13:
			return Datos.isNull(((Parametro) value).getFilejscript());
		case 14:
			return Datos.isNull(((Parametro) value).getFuncionjs());
		case 15:
			return Datos.isNull(((Parametro) value).getEvento());
		case 16:
			return Datos.isNull(((Parametro) value).getCssfile());
		case 17:
			return Datos.isNull(((Parametro) value).getCss());
		case 18:
			return ((Parametro) value).getAncho();
		case 19:
			return ((Parametro) value).getLongitud();
		case 20:
			return ((Parametro) value).getRenglon();
		case 21:
			return Datos.isNull(((Parametro) value).getClase());
		case 22:
			return Datos.isNull(((Parametro) value).getParsley());
		case 23:
			return ((Parametro) value).isOmitir();
		}
		
		return Datos.isNull((String) value);

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return Integer.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return Integer.class;
		case 7:
			return Integer.class;
		case 8:
			return String.class;
		case 9:
			return String.class;
		case 10:
			return String.class;
		case 11:
			return String.class;
		case 12:
			return String.class;
		case 13:
			return String.class;
		case 14:
			return String.class;
		case 15:
			return String.class;
		case 16:
			return String.class;
		case 17:
			return String.class;
		case 18:
			return String.class;
		case 19:
			return Integer.class;
		case 20:
			return Integer.class;
		case 21:
			return Integer.class;
		case 22:
			return String.class;
		case 23:
			return Boolean.class;
		default:
			return Integer.class;
		}
	}

	public boolean isCellEditable(int row, int column) {
		if(column>1)
			return true;
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Parametro p;
		p = parametros.get(rowIndex);
		switch (columnIndex) {
		case 0:
			p.setId_Campo((int) aValue);
			break;
		case 1:
			p.setReporte_Id((int) aValue);
			break;
		case 2:
			p.setVariable_Id( findRow( this.variables, (String) aValue));
			break;
		case 3:
			p.setControl_Id(findRow( this.controles, (String) aValue));
			break;
		case 4:
			p.setTitulo_pagina((String) aValue);
			break;
		case 5:
			p.setNombre((String) aValue);
			break;
		case 6:
			p.setOrden((int) aValue);
			break;
		case 7:
			p.setVisible((int) aValue);
			break;
		case 8:
			p.setValor_variable((String) aValue);
			break;
		case 9:
			p.setDataset((String) aValue);
			break;
		case 10:
			p.setDs((String) aValue);
			break;
		case 11:
			p.setParametro((String) aValue);
			break;
		case 12:
			p.setCombropadre((String) aValue);
			break;
		case 13:
			p.setFilejscript((String) aValue);
			break;
		case 14:
			p.setFuncionjs((String) aValue);
			break;
		case 15:
			p.setEvento((String) aValue);
			break;
		case 16:
			p.setCssfile((String) aValue);
			break;
		case 17:
			p.setCss((String) aValue);
			break;
		case 18:
			p.setAncho((int) aValue);
			break;
		case 19:
			p.setLongitud((int) aValue);
			break;
		case 20:
			p.setRenglon((int) aValue);
			break;
		case 21:
			p.setClase((String) aValue);
			break;
		case 22:
			p.setParsley((String) aValue);
			break;
		case 23:
			p.setOmitir((Boolean) aValue);
		default:
			break;
		}
		parametros.set(rowIndex, p);
		fireTableCellUpdated(rowIndex, columnIndex);
		modificado = true;
	}

	public Parametro getParametroAt(int row) {
		return this.parametros.get(row);
	}
	
	public Vector<Parametro> getParametros(){
		return this.parametros;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public boolean getModificado() {
		return modificado;
	}
	
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
	public int findRow(String[] array, String text) {
		for(int i = 0; i<array.length; i++) {
			if(array[i].equals(text))
				return i+1;
		}
		return (Integer) null;
	}
	
}

}