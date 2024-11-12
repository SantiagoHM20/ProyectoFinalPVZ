package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class GameGUI extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JFileChooser fileChooser;
    private JLabel imageLabel;

    public GameGUI() {
        prepareElements();
        prepareElementsMenu();
        prepareActions();
        prepareActionsMenu();
    }

    private void prepareElements() {
        setTitle("Plants vs Zombies");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width, height);
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

         loadImage("F:\\descargas\\ProyectoFinal\\PlantVsZombies\\images\\imagenInicial.jpg");

            add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton nuevaPartidaButton = new JButton("Nueva Partida");
        nuevaPartidaButton.setBackground(Color.GREEN);
        nuevaPartidaButton.setOpaque(true);
        nuevaPartidaButton.setBorderPainted(false);
        nuevaPartidaButton.setFocusPainted(false);

        JButton reanudarPartidaButton = new JButton("Reanudar Partida");
        reanudarPartidaButton.setBackground(Color.GREEN);
        reanudarPartidaButton.setOpaque(true);
        reanudarPartidaButton.setBorderPainted(false);
        reanudarPartidaButton.setFocusPainted(false);

        addHoverEffect(nuevaPartidaButton);
        addHoverEffect(reanudarPartidaButton);

        buttonPanel.add(nuevaPartidaButton);
        buttonPanel.add(reanudarPartidaButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.GREEN);
            }
        });
    }

    private void loadImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        imageLabel.setIcon(imageIcon);
    }

    private void prepareElementsMenu() {
        menuBar = new JMenuBar();
        menuArchivo = new JMenu("Archivo");

        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem salvar = new JMenuItem("Salvar");
        JMenuItem salir = new JMenuItem("Salir");

        menuArchivo.add(nuevo);
        menuArchivo.add(abrir);
        menuArchivo.add(salvar);
        menuArchivo.addSeparator();
        menuArchivo.add(salir);

        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
    }

    private void prepareActions() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                exit();
            }
        });
    }

    private void prepareActionsMenu() {
        for (int i = 0; i < menuArchivo.getItemCount(); i++) {
            JMenuItem item = menuArchivo.getItem(i);
            if (item != null) {
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (item.getText()) {
                            case "Nuevo":
                                JOptionPane.showMessageDialog(null, "Nuevo seleccionado");
                                break;
                            case "Abrir":
                                abrirArchivo();
                                break;
                            case "Salvar":
                                salvarArchivo();
                                break;
                            case "Salir":
                                exit();
                                break;
                        }
                    }
                });
            }
        }
    }

    private void abrirArchivo() {
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Abrir en construcción: " + selectedFile.getName());
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo.");
        }
    }

    private void salvarArchivo() {
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Salvar en construcción: " + selectedFile.getName());
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo para salvar.");
        }
    }

    private void exit() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        GameGUI ventana = new GameGUI();
        ventana.setVisible(true);
    }
}
