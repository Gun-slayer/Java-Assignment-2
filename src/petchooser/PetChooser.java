
package petchooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PetChooser extends JFrame implements ActionListener {
    private JLabel petImageLabel;
    private ButtonGroup petGroup;
    private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;

    // === EDIT THESE PATHS IF NEEDED ===
    // Put your images in the same folder as this file, or change the paths below
    private final String IMAGE_FOLDER = "."; // Current folder (same as .java)

    public PetChooser() {
        setTitle("PetChooser");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Radio buttons panel
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(5, 1));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig");

        petGroup = new ButtonGroup();
        petGroup.add(birdButton);
        petGroup.add(catButton);
        petGroup.add(dogButton);
        petGroup.add(rabbitButton);
        petGroup.add(pigButton);

        radioPanel.add(birdButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(rabbitButton);
        radioPanel.add(pigButton);

        pigButton.setSelected(true);

        // Pet image display
        petImageLabel = new JLabel();
        petImageLabel.setHorizontalAlignment(JLabel.CENTER);
        petImageLabel.setPreferredSize(new Dimension(250, 250));
        petImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        updatePetImage("Pig");

        // Listeners
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

        // Layout
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(radioPanel, BorderLayout.WEST);
        mainPanel.add(petImageLabel, BorderLayout.CENTER);

        add(mainPanel);

        showSelectionMessage("Pig");
    }

    private void updatePetImage(String pet) {
        // Build full path
        String imagePath = IMAGE_FOLDER + "/" + pet.toLowerCase() + ".png";

        try {
            ImageIcon icon = new ImageIcon(imagePath);
            if (icon.getIconWidth() == -1) {
                throw new Exception("Image not found");
            }
            Image scaled = icon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
            petImageLabel.setIcon(new ImageIcon(scaled));
            petImageLabel.setText(null);
        } catch (Exception e) {
            petImageLabel.setIcon(null);
            petImageLabel.setText(pet + " (image not found)\nExpected: " + imagePath);
        }
    }

    private void showSelectionMessage(String pet) {
        JOptionPane.showMessageDialog(this,
                "You selected: " + pet,
                "Pet Selection",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedPet = "";
        if (e.getSource() == birdButton) selectedPet = "Bird";
        else if (e.getSource() == catButton) selectedPet = "Cat";
        else if (e.getSource() == dogButton) selectedPet = "Dog";
        else if (e.getSource() == rabbitButton) selectedPet = "Rabbit";
        else if (e.getSource() == pigButton) selectedPet = "Pig";

        if (!selectedPet.isEmpty()) {
            updatePetImage(selectedPet);
            showSelectionMessage(selectedPet);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PetChooser().setVisible(true));
    }
}