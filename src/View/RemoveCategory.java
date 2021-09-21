package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import View.Listener.CategoryListener;

public class RemoveCategory extends JFrame {
    
    private JPanel singleCategory;
    private JCheckBox checkBox;
    private JLabel category;
    

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public RemoveCategory(CategoryListener cList) {
        super("Elenco delle categorie");

        this.singleCategory = new JPanel();
        
        this.category = new JLabel("<html>categoria</html>");
        this.checkBox = new JCheckBox(category.getText());

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmDeleteCategoryButton");
        confirm.addActionListener(cList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        singleCategory.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        singleCategory.add(checkBox);

        buttons.add(confirm);
        buttons.add(cancel);

        add(singleCategory, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
