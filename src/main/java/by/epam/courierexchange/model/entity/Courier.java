package by.epam.courierexchange.model.entity;

public class Courier extends User{
    private double rating;
    private long transport;

    public Courier(CourierBuilder courierBuilder) {
        super(courierBuilder.builder);
        this.rating = courierBuilder.rating;
        this.transport = courierBuilder.transport;
    }

    public long getTransport() {
        return transport;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Courier)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Courier courier = (Courier) o;

        if (Double.compare(courier.rating, rating) != 0) {
            return false;
        }
        return transport == courier.transport;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (transport ^ (transport >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Courier{");
        sb.append("rating=").append(rating);
        sb.append(", transport=").append(transport);
        sb.append('}');
        return sb.toString();
    }

    public static class CourierBuilder{

        private UserBuilder builder;
        private double rating;
        private long transport;

        public CourierBuilder setBuilder(UserBuilder builder) {
            this.builder = builder;
            return this;
        }

        public CourierBuilder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public CourierBuilder setTransport(long transport){
            this.transport = transport;
            return this;
        }

        public Courier build() {
            return new Courier(this);
        }
    }
}
