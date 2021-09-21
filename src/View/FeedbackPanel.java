package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.FeedbackDAO;
import DAO.PurchaseDAO;
import DAO.UserDAO;
import Model.Feedback;
import View.Listener.NormalCatalogListener;

public class FeedbackPanel extends JPanel {

    private JPanel feedbackPanel;
    private JLabel name;
    private String ratingString;
    private JLabel rating;

    private String answering;
    private JLabel feedback;

    private JButton feedbackButton;

    public FeedbackPanel(NormalCatalogListener ncList, Feedback currentFeedback) {
        
        this.feedbackPanel = new JPanel();

        int purchaseHasItem = PurchaseDAO.getInstance().getPurchaseIdById(FeedbackDAO.getInstance().getPurchaseHasItemById(currentFeedback.getId()));
        if (purchaseHasItem == 0) {
            this.name = new JLabel("Team di MyShop");
        } else {
            this.name = new JLabel(UserDAO.getInstance().findById(PurchaseDAO.getInstance().getBuyerIdByPurchaseId(purchaseHasItem)).getUsername());
        }
        this.ratingString = "";

        for (int i = 0; i < 5; i++) {
            if (i < currentFeedback.getRating()) {
                ratingString += "★";
            } else {
                ratingString += "☆";
            }
        }

        this.rating = new JLabel("<html><font color=\"#eade00\">" + ratingString + "</font></html>");
        this.answering = "";
        if (currentFeedback.getAnswering() != 0) {
            answering = "→ ";
        }
        this.feedback = new JLabel("<html>" + answering + currentFeedback.getComment() + "</html>");
        
        this.feedbackButton = new JButton("Rispondi al commento");
        feedbackButton.setActionCommand("replyToFeedbackButton");
        feedbackButton.addActionListener(ncList);


        setLayout(new BorderLayout());
        feedbackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        
        feedbackPanel.add(name);
        feedbackPanel.add(rating);
        feedbackPanel.add(feedback);

        add(feedbackPanel, BorderLayout.CENTER);
        add(feedbackButton, BorderLayout.SOUTH);

        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(1000, 50));
    }
}
