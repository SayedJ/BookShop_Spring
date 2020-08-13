package net.guides.springboot2.crud.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long orderId;

    @NotNull
    @Column(name = "date")
    private Date orderDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private boolean orderStatus;

    @NotNull
    @Column(name = "price", precision=10, scale=2)
    private BigDecimal orderPrice;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<OrderBook> books = new ArrayList<>();


    public void addBook(Books book) {
        OrderBook orderBook = new OrderBook(this, book);
        books.add(orderBook);
        book.getOrders().add(orderBook);
    }

    public void removeBook(Books book) {
        OrderBook orderBook = new OrderBook(this, book);
        book.getOrders().remove(orderBook);
        books.remove(orderBook);
        orderBook.setOrder(null);
        orderBook.setBook(null);
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<OrderBook> getBooks() {
        return books;
    }

    public void setBooks(List<OrderBook> books) {
        this.books = books;
    }


    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order order = (Order) obj;
        return Objects.equals(getOrderId(), order.getOrderId());
    }
}