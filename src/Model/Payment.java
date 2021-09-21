package Model;

import java.util.Date;

public class Payment extends Order{

    private Long nCard;
    private Date vDate;
    private int cvv;
    private Order order;
    private int orderId;


    public Payment() {
        this.nCard = 0L;
        this.vDate = new Date();
        this.cvv = 0;
        this.order = new Order();
        this.orderId = 0;
    }

    public Payment(Long nCard, Date vDate, int cvv, Order order) {
        this.nCard = nCard;
        this.vDate = vDate;
        this.cvv = cvv;
        this.order = order;
        setOrderId(order.getId());
    }


    public Long getNCard() {
        return nCard;
    }

    public void setNCard(Long nCard) {
        this.nCard = nCard;
    }

    public Date getVDate() {
        return vDate;
    }

    public void setVDate(Date vDate) {
        this.vDate = vDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Order getOrder(){
        return order;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
