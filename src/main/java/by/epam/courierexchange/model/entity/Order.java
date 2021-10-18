package by.epam.courierexchange.model.entity;

import java.sql.Date;

public class Order extends AbstractEntity{
    private long id;
    private Courier courier;
    private ClientProduct clientProduct;
    private Date date;
    private OrderStatus orderStatus;

    public Order(OrderBuilder builder) {
        this.id = builder.id;
        this.clientProduct = builder.clientProduct;
        this.courier = builder.courier;
        this.date = builder.date;
        this.orderStatus = builder.orderStatus;
    }

    public long getId() {
        return id;
    }

    public ClientProduct getClientProduct() {
        return clientProduct;
    }

    public Courier getCourier() {
        return courier;
    }

    public Date getDate() {
        return date;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)){
            return false;
        }

        Order order = (Order) o;

        if (courier != null ? !courier.equals(order.courier) : order.courier != null) {
            return false;
        }
        if (clientProduct != null ? !clientProduct.equals(order.clientProduct) : order.clientProduct != null){
            return false;
        }
        if (date != null ? !date.equals(order.date) : order.date != null) {
            return false;
        }
        return orderStatus == order.orderStatus && id!=order.id;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (courier != null ? courier.hashCode() : 0);
        result = 31 * result + (clientProduct != null ? clientProduct.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", courier=").append(courier);
        sb.append(", clientProduct=").append(clientProduct);
        sb.append(", date=").append(date);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append('}');
        return sb.toString();
    }

    public static class OrderBuilder{

        private long id;
        private ClientProduct clientProduct;
        private Courier courier;
        private Date date;
        private OrderStatus orderStatus;

        public OrderBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setClientProduct(ClientProduct clientProduct) {
            this.clientProduct = clientProduct;
            return this;
        }



        public OrderBuilder setCourier(Courier courier) {
            this.courier = courier;
            return this;
        }

        public OrderBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public OrderBuilder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
