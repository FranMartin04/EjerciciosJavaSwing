package paneles;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class PanelBusquedaFicheros extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldCarpeta;
    private JTextField textFieldTexto;
    private JTable table;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel de Búsqueda de Ficheros");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PanelBusquedaFicheros());
            frame.setSize(800, 800); // Establecer dimensiones de la ventana
            frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
            frame.setVisible(true);
        });
    }
    public PanelBusquedaFicheros() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("Busqueda de ficheros");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 0;
        add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Carpeta:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.gridwidth = 2;
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        add(lblNewLabel_1, gbc_lblNewLabel_1);

        textFieldCarpeta = new JTextField();
        GridBagConstraints gbc_textFieldCarpeta = new GridBagConstraints();
        gbc_textFieldCarpeta.gridwidth = 4;
        gbc_textFieldCarpeta.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldCarpeta.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCarpeta.gridx = 2;
        gbc_textFieldCarpeta.gridy = 1;
        add(textFieldCarpeta, gbc_textFieldCarpeta);
        textFieldCarpeta.setColumns(10);

        JButton btnSeleccionarCarpeta = new JButton("Selecciona Carpeta");
        GridBagConstraints gbc_btnSeleccionarCarpeta = new GridBagConstraints();
        gbc_btnSeleccionarCarpeta.insets = new Insets(0, 0, 5, 0);
        gbc_btnSeleccionarCarpeta.gridx = 6;
        gbc_btnSeleccionarCarpeta.gridy = 1;
        add(btnSeleccionarCarpeta, gbc_btnSeleccionarCarpeta);

        JLabel lblNewLabel_2 = new JLabel("Texto en nombre:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.gridwidth = 2;
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        add(lblNewLabel_2, gbc_lblNewLabel_2);

        textFieldTexto = new JTextField();
        GridBagConstraints gbc_textFieldTexto = new GridBagConstraints();
        gbc_textFieldTexto.gridwidth = 4;
        gbc_textFieldTexto.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldTexto.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldTexto.gridx = 2;
        gbc_textFieldTexto.gridy = 2;
        add(textFieldTexto, gbc_textFieldTexto);
        textFieldTexto.setColumns(10);

        JButton btnBuscarFicheros = new JButton("Buscar Ficheros");
        GridBagConstraints gbc_btnBuscarFicheros = new GridBagConstraints();
        gbc_btnBuscarFicheros.insets = new Insets(0, 0, 5, 0);
        gbc_btnBuscarFicheros.gridx = 6;
        gbc_btnBuscarFicheros.gridy = 2;
        add(btnBuscarFicheros, gbc_btnBuscarFicheros);

        table = new JTable();
        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.gridwidth = 7;
        gbc_table.insets = new Insets(0, 0, 0, 5);
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.gridx = 0;
        gbc_table.gridy = 3;
        add(table, gbc_table);

        // Acción para el botón de seleccionar carpeta
        btnSeleccionarCarpeta.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Seleccione una carpeta");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                textFieldCarpeta.setText(selectedFile.getAbsolutePath());
            }
        });

        // Acción para el botón de buscar ficheros
        btnBuscarFicheros.addActionListener(e -> {
            String textoBusqueda = textFieldTexto.getText();
            String carpeta = textFieldCarpeta.getText();
            buscarFicheros(textoBusqueda, carpeta);
        });
    }

    // Método para buscar ficheros en una carpeta según un texto de búsqueda en el nombre
    private void buscarFicheros(String textoBusqueda, String carpeta) {
        File dir = new File(carpeta);
        File[] files = dir.listFiles();

        // Nombres de columna corregidos
        String[] columnNames = {"Nombre", "Tamaño (KB)", "Última Modificación"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for (File file : files) {
            if (file.isFile() && file.getName().contains(textoBusqueda)) {
                long fileSizeKB = file.length() / 1024;
                String lastModified = dateFormat.format(new Date(file.lastModified()));
                model.addRow(new Object[]{file.getName(), fileSizeKB, lastModified});
            }
        }

        table.setModel(model);
    }
}
