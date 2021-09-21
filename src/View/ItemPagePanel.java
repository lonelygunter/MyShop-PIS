package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DAO.FeedbackDAO;
import Model.Feedback;
import Model.Image;
import Model.Item;
import View.Listener.NormalCatalogListener;

public class ItemPagePanel extends JPanel {

    private ItemPanel itemPanel;

    private JPanel eastPanel;
    private JButton addToList;
    private JButton orderNow;

    private JPanel allFeedbackPanel;

    private JButton feedbackButton;

    private JPanel allFeedbacks;
    private ArrayList<Feedback> feedbacks;
    private ArrayList<Feedback> currentFeedbacks;

    private JScrollPane scrollp;

    public ItemPagePanel(NormalCatalogListener ncList, Item item, Image image) {
        
        this.itemPanel = new ItemPanel(ncList, 1, item, image, true);

        this.eastPanel = new JPanel();

        this.addToList = new JButton("+");
        addToList.setActionCommand("addToListButton");
        addToList.addActionListener(ncList);
        addToList.setPreferredSize(new Dimension(30, 20));

        this.orderNow = new JButton("$");
        orderNow.setActionCommand("orderNowButton");
        orderNow.addActionListener(ncList);

        orderNow.setPreferredSize(new Dimension(30, 20));

        this.allFeedbackPanel = new JPanel();

        this.feedbackButton = new JButton("Lascia un commento");
        feedbackButton.setActionCommand("feedbackButton");
        feedbackButton.addActionListener(ncList);

        this.allFeedbacks = new JPanel();
        this.feedbacks = FeedbackDAO.getInstance().findAll();
        this.currentFeedbacks = new ArrayList<>();

        this.scrollp = new JScrollPane(allFeedbacks);
        
        
        setLayout(new BorderLayout());
        eastPanel.setLayout(new GridLayout(2, 1));
        allFeedbackPanel.setLayout(new BorderLayout());
        allFeedbacks.setLayout(new GridLayout(currentFeedbacks.size(), 1));
        

        for (int i = 0; i < feedbacks.size(); i++) {
            if (item.getId() == FeedbackDAO.getInstance().getItemIdById(feedbacks.get(i).getId())) {
                allFeedbacks.add(new FeedbackPanel(ncList, feedbacks.get(i)), BorderLayout.SOUTH);
                currentFeedbacks.add(FeedbackDAO.getInstance().findById(item.getId()));
            }
        }


        eastPanel.add(addToList);
        eastPanel.add(orderNow);

        itemPanel.add(eastPanel, BorderLayout.EAST);

        allFeedbackPanel.add(feedbackButton, BorderLayout.CENTER);
        allFeedbackPanel.add(scrollp, BorderLayout.SOUTH);

        add(itemPanel, BorderLayout.CENTER);
        add(allFeedbackPanel, BorderLayout.SOUTH);
    }

    public ArrayList<Feedback> getCurrentFeedbacks() {
        return currentFeedbacks;
    }
}
