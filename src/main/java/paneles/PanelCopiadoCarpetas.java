package paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PanelCopiadoCarpetas extends JPanel {
    private static final long serialVersionUID = 1L;
	private JTextField jtfCarpetaOrigen;
    private JTextField jtfCarpetaDestino;
    private JButton btnCarpetaOrigen;
    private JButton btnCarpetaDestino;
    private JButton btnCopiar;
    private JProgressBar jprogress;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel de Copiado de Carpetas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.getContentPane().add(new PanelCopiadoCarpetas());
        frame.setVisible(true);
    }
    public PanelCopiadoCarpetas() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());

        JLabel lblCopiadoDeContenido = new JLabel("Copiado de contenido de carpetas");
        GridBagConstraints gbc_lblCopiadoDeContenido = new GridBagConstraints();
        gbc_lblCopiadoDeContenido.gridwidth = 3;
        gbc_lblCopiadoDeContenido.insets = new Insets(10, 10, 10, 10);
        gbc_lblCopiadoDeContenido.gridx = 1;
        gbc_lblCopiadoDeContenido.gridy = 0;
        add(lblCopiadoDeContenido, gbc_lblCopiadoDeContenido);

        JLabel lblCarpetaOrigen = new JLabel("Carpeta de origen:");
        GridBagConstraints gbc_lblCarpetaOrigen = new GridBagConstraints();
        gbc_lblCarpetaOrigen.anchor = GridBagConstraints.EAST;
        gbc_lblCarpetaOrigen.insets = new Insets(10, 10, 10, 10);
        gbc_lblCarpetaOrigen.gridx = 0;
        gbc_lblCarpetaOrigen.gridy = 1;
        add(lblCarpetaOrigen, gbc_lblCarpetaOrigen);

        jtfCarpetaOrigen = new JTextField();
        GridBagConstraints gbc_jtfCarpetaOrigen = new GridBagConstraints();
        gbc_jtfCarpetaOrigen.gridwidth = 3;
        gbc_jtfCarpetaOrigen.insets = new Insets(10, 10, 10, 10);
        gbc_jtfCarpetaOrigen.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtfCarpetaOrigen.gridx = 1;
        gbc_jtfCarpetaOrigen.gridy = 1;
        add(jtfCarpetaOrigen, gbc_jtfCarpetaOrigen);
        jtfCarpetaOrigen.setColumns(10);

        btnCarpetaOrigen = new JButton("Seleccionar Carpeta Origen");
        GridBagConstraints gbc_btnCarpetaOrigen = new GridBagConstraints();
        gbc_btnCarpetaOrigen.insets = new Insets(10, 10, 10, 10);
        gbc_btnCarpetaOrigen.gridx = 4;
        gbc_btnCarpetaOrigen.gridy = 1;
        add(btnCarpetaOrigen, gbc_btnCarpetaOrigen);

        JLabel lblCarpetaDestino = new JLabel("Carpeta de destino:");
        GridBagConstraints gbc_lblCarpetaDestino = new GridBagConstraints();
        gbc_lblCarpetaDestino.anchor = GridBagConstraints.EAST;
        gbc_lblCarpetaDestino.insets = new Insets(10, 10, 10, 10);
        gbc_lblCarpetaDestino.gridx = 0;
        gbc_lblCarpetaDestino.gridy = 2;
        add(lblCarpetaDestino, gbc_lblCarpetaDestino);

        jtfCarpetaDestino = new JTextField();
        GridBagConstraints gbc_jtfCarpetaDestino = new GridBagConstraints();
        gbc_jtfCarpetaDestino.gridwidth = 3;
        gbc_jtfCarpetaDestino.insets = new Insets(10, 10, 10, 10);
        gbc_jtfCarpetaDestino.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtfCarpetaDestino.gridx = 1;
        gbc_jtfCarpetaDestino.gridy = 2;
        add(jtfCarpetaDestino, gbc_jtfCarpetaDestino);
        jtfCarpetaDestino.setColumns(10);

        btnCarpetaDestino = new JButton("Seleccionar Carpeta Destino");
        GridBagConstraints gbc_btnCarpetaDestino = new GridBagConstraints();
        gbc_btnCarpetaDestino.insets = new Insets(10, 10, 10, 10);
        gbc_btnCarpetaDestino.gridx = 4;
        gbc_btnCarpetaDestino.gridy = 2;
        add(btnCarpetaDestino, gbc_btnCarpetaDestino);

        btnCopiar = new JButton("Copiar Archivos");
        GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
        gbc_btnCopiar.insets = new Insets(10, 10, 10, 10);
        gbc_btnCopiar.gridx = 2;
        gbc_btnCopiar.gridy = 3;
        add(btnCopiar, gbc_btnCopiar);

        jprogress = new JProgressBar();
        GridBagConstraints gbc_jprogress = new GridBagConstraints();
        gbc_jprogress.insets = new Insets(10, 10, 10, 10);
        gbc_jprogress.gridx = 2;
        gbc_jprogress.gridy = 4;
        gbc_jprogress.fill = GridBagConstraints.HORIZONTAL;
        gbc_jprogress.gridwidth = 3;
        add(jprogress, gbc_jprogress);

        btnCarpetaOrigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarCarpetaOrigen();
            }
        });

        btnCarpetaDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarCarpetaDestino();
            }
        });

        btnCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarArchivos();
            }
        });
    }

    private void seleccionarCarpetaOrigen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            jtfCarpetaOrigen.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void seleccionarCarpetaDestino() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            jtfCarpetaDestino.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void copiarArchivos() {
        String origen = jtfCarpetaOrigen.getText();
        String destino = jtfCarpetaDestino.getText();
        File carpetaOrigen = new File(origen);
        File carpetaDestino = new File(destino);
        if (!carpetaOrigen.exists() || !carpetaOrigen.isDirectory()) {
            JOptionPane.showMessageDialog(this, "La carpeta de origen no es válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!carpetaDestino.exists() || !carpetaDestino.isDirectory()) {
            JOptionPane.showMessageDialog(this, "La carpeta de destino no es válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File[] archivos = carpetaOrigen.listFiles();
        int totalArchivos = archivos.length;
        int archivosCopiados = 0;
        for (File archivo : archivos) {
            if (archivo.isFile()) {
                File nuevoArchivo = new File(carpetaDestino, archivo.getName());
                try (InputStream in = new FileInputStream(archivo);
                     OutputStream out = new FileOutputStream(nuevoArchivo)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                    archivosCopiados++;
                    jprogress.setValue((archivosCopiados * 100) / totalArchivos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Archivos copiados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }


}

