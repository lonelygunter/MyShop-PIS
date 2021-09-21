package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Listener.AdministratorListener;

public class ModifyItem extends JFrame {

    private JPanel dataPanel;

    private JLabel idLabel;
    private JTextField id;

    private JLabel nameLabel;
    private JTextField name;

    private JLabel descriptionLabel;
    private JTextField description;

    private JLabel priceLabel;
    private JTextField price;

    private JLabel typeLabel;
    private JTextField type;

    private JLabel availabilityLabel;
    private JTextField availability;

    private JLabel wholesalerLabel;
    private JTextField wholesaler;

    private JLabel categoryLabel;
    private JTextField category;

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public ModifyItem(AdministratorListener adminList) {
        super("Modifica un articolo");

        this.dataPanel = new JPanel();
        
        this.idLabel = new JLabel("<html>Id:</html>");
        this.id = new JTextField(10);

        this.nameLabel = new JLabel("<html>Nome:</html>");
        this.name = new JTextField(10);

        this.descriptionLabel = new JLabel("<html>Descrizione:</html>");
        this.description = new JTextField(10);

        this.priceLabel = new JLabel("<html>Prezzo:</html>");
        this.price = new JTextField(10);

        this.typeLabel = new JLabel("<html>Tipo:</html>");
        this.type = new JTextField(10);

        this.availabilityLabel = new JLabel("<html>Disponibilità:</html>");
        this.availability = new JTextField(10);

        this.wholesalerLabel = new JLabel("<html>Venditore:</html>");
        this.wholesaler = new JTextField(10);

        this.categoryLabel = new JLabel("<html>Categoria:</html>");
        this.category = new JTextField(10);


        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmModifyItemButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        dataPanel.setLayout(new GridLayout(9, 2));
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        dataPanel.add(idLabel);
        dataPanel.add(id);
        dataPanel.add(nameLabel);
        dataPanel.add(name);
        dataPanel.add(descriptionLabel);
        dataPanel.add(description);
        dataPanel.add(priceLabel);
        dataPanel.add(price);
        dataPanel.add(typeLabel);
        dataPanel.add(type);
        dataPanel.add(availabilityLabel);
        dataPanel.add(availability);
        dataPanel.add(wholesalerLabel);
        dataPanel.add(wholesaler);
        dataPanel.add(categoryLabel);
        dataPanel.add(category);
        
        buttons.add(confirm);
        buttons.add(cancel);

        add(dataPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public int getId() {
        return Integer.parseInt(id.getText());
    }

    public String getName() {
        return name.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public float getPrice() {
        return Float.parseFloat(price.getText());
    }

    public String getUserType() {
        return type.getText();
    }

    public int getAvailability() {
        return Integer.parseInt(availability.getText());
    }

    public int getWholesalerId() {
        return Integer.parseInt(wholesaler.getText());
    }

    public int getCategoryId() {
        return Integer.parseInt(category.getText());
    }

    public void clearFields() {
        name.setText("");
        description.setText("");
        price.setText("");
        type.setText("");
        availability.setText("");
        wholesaler.setText("");
        category.setText("");
    }
}
