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

import View.Listener.CategoryListener;

public class ModifyCategory extends JFrame {

    private JPanel dataPanel;

    private JLabel idLabel;
    private JTextField id;

    private JLabel nameLabel;
    private JTextField name;

    private JLabel fatherIdLabel;
    private JTextField fatherId;

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public ModifyCategory(CategoryListener cList) {
        super("Modifica una categoria");

        this.dataPanel = new JPanel();

        this.idLabel = new JLabel("<html>Id:</html>");
        this.id = new JTextField(10);
        
        this.nameLabel = new JLabel("<html>Nome:</html>");
        this.name = new JTextField(10);

        this.fatherIdLabel = new JLabel("<html>Id della supercategoria:</html>");
        this.fatherId = new JTextField(10);

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmModifyCategoryButton");
        confirm.addActionListener(cList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        dataPanel.setLayout(new GridLayout(4, 2));
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        dataPanel.add(idLabel);
        dataPanel.add(id);
        dataPanel.add(nameLabel);
        dataPanel.add(name);
        dataPanel.add(fatherIdLabel);
        dataPanel.add(fatherId);

        buttons.add(confirm);
        buttons.add(cancel);

        add(dataPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 150));
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

    public int getFatherId() {
        return Integer.parseInt(fatherId.getText());
    }

    public void clearFields() {
        id.setText("");
        name.setText("");
        fatherId.setText("");

    }
}
