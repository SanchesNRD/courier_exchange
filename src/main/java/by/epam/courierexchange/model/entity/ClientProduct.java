package by.epam.courierexchange.model.entity;

public class ClientProduct extends AbstractEntity{
    private long id;
    private Client client;
    private Product product;
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientProduct)) {
            return false;
        }

        ClientProduct that = (ClientProduct) o;

        if (id != that.id) {
            return false;
        }
        if (client != null ? !client.equals(that.client) : that.client != null) {
            return false;
        }
        if (product != null ? !product.equals(that.product) : that.product != null) {
            return false;
        }
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientProduct{");
        sb.append("client=").append(client);
        sb.append(", product=").append(product);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
