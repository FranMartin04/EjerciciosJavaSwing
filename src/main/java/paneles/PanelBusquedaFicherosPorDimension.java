package paneles;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;



import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PanelBusquedaFicherosPorDimension extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFolder;
	private JScrollPane scrollPane = new JScrollPane();
	private JSlider slider;
	private JLabel lblDescSeleccion = new JLabel("> 0 KB");

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel de Búsqueda de Ficheros");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PanelBusquedaFicherosPorDimension());
            frame.setSize(800, 800); // Establecer dimensiones de la ventana
            frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
            frame.setVisible(true);
        });
    }

	/**
	 * Create the panel.
	 */
	public PanelBusquedaFicherosPorDimension() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Búsqueda de ficheros por tamaño");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(10, 0, 10, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Carpeta:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfFolder = new JTextField();
		GridBagConstraints gbc_jtfFolder = new GridBagConstraints();
		gbc_jtfFolder.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFolder.gridx = 1;
		gbc_jtfFolder.gridy = 1;
		add(jtfFolder, gbc_jtfFolder);
		jtfFolder.setColumns(10);
		
		JButton btnSelectFolder = new JButton("Selecciona carpeta");
		btnSelectFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfFolder.setText(FileUtils.selectFolder("C:\\Users\\rafae\\Downloads\\", JFileChooser.DIRECTORIES_ONLY));
				selectFilesBySizeKB();
			}
		});
		GridBagConstraints gbc_btnSelectFolder = new GridBagConstraints();
		gbc_btnSelectFolder.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelectFolder.gridx = 2;
		gbc_btnSelectFolder.gridy = 1;
		add(btnSelectFolder, gbc_btnSelectFolder);
		
		JLabel lblNewLabel_2 = new JLabel("Tamaño del fichero:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		slider = new JSlider();
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				selectFilesBySizeKB();
			}
		});
		slider.setMaximum(5);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 2;
		add(slider, gbc_slider);
		
		lblDescSeleccion = new JLabel("> 0 KB");
		GridBagConstraints gbc_lblDescSeleccion = new GridBagConstraints();
		gbc_lblDescSeleccion.insets = new Insets(0, 0, 5, 0);
		gbc_lblDescSeleccion.gridx = 2;
		gbc_lblDescSeleccion.gridy = 2;
		add(lblDescSeleccion, gbc_lblDescSeleccion);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

	}
	
	
	/**
	 * 
	 * @param length
	 */
	private void selectFilesBySizeKB () {
		
		int fileLengSelection = 0;
		switch (slider.getValue()) {
		case 0:
			lblDescSeleccion.setText("> 0 KB");
			fileLengSelection = 0; break; // 0 KB
		case 1:
			lblDescSeleccion.setText("> 100 KB");
			fileLengSelection = 100; break; // 100 KB
		case 2:
			lblDescSeleccion.setText("> 1 MB");
			fileLengSelection = 1024; break; // 1 MB
		case 3:
			lblDescSeleccion.setText("> 10 MB");
			fileLengSelection = 10240; break; // 10 MB
		case 4:
			lblDescSeleccion.setText("> 100 MB");
			fileLengSelection = 102400; break; // 100 MB
		case 5:
			lblDescSeleccion.setText("> 1 GB");
			fileLengSelection = 1048576; break;	// 1 GB				
		}

		
		List<File> files = FileUtils.getFilesIntoFolder(this.jtfFolder.getText());
		
		List<File> filteredFiles = new ArrayList<File>();
		
		for (File f : files) {
			if (f.length() >= (fileLengSelection * 1024)) {
				filteredFiles.add(f);
			}
		}
		
		Object tableData[][] = new Object[filteredFiles.size()][3];
		
		for (int i = 0; i < filteredFiles.size(); i++) {
			File f = filteredFiles.get(i);
			tableData[i][0] = f.getName();
			tableData[i][1] = (f.length() / 1024f ) + " KB";
			Date d = new Date(f.lastModified());
			tableData[i][2] = d.toString();
		}
		
		JTable table = new JTable(tableData, 
				new String[] {"Nombre", "Tamaño", "Última modificación"});
		
		lblDescSeleccion.setText(lblDescSeleccion.getText() + " (" + filteredFiles.size() + " ficheros)");

		this.scrollPane.setViewportView(table);
	}
	
}
