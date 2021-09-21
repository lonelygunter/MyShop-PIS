package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Listener.NormalCatalogListener;

public class LeaveFeedback extends JFrame{

    private JPanel feedbackPanel;
    private JLabel feedbackLabel;
    private JTextField feedback;

    private JPanel ratingPanel;
    private JLabel ratingLabel;
    private JTextField rating;
    private JButton done;

    public LeaveFeedback(NormalCatalogListener ncList) {
        super("Inserire valutazione, commento e premere invio");

        this.feedbackPanel = new JPanel();
        this.feedbackLabel = new JLabel("Commento:");
        this.feedback = new JTextField(10);

        this.ratingPanel = new JPanel();
        this.ratingLabel = new JLabel("Valutazione da 1 a 5:");
        this.rating = new JTextField(10);

        this.done = new JButton("Fatto");
        done.setActionCommand("feedbackTextField");
        done.addActionListener(ncList);


        feedbackPanel.setLayout(new FlowLayout());
        rating.setLayout(new FlowLayout());
        setLayout(new GridLayout(3, 1));


        feedbackPanel.add(feedbackLabel);
        feedbackPanel.add(feedback);

        ratingPanel.add(ratingLabel);
        ratingPanel.add(rating);

        add(ratingPanel);
        add(feedbackPanel);
       
        add(done);

        setSize(new Dimension(400, 150));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public String getFeedbackField() {
        return feedback.getText();
    }

    public int getRatingField() {
        return Integer.parseInt(rating.getText());
    }

    public void clearFields() {
        feedback.setText("");
        rating.setText("");
    }
}
