package by.epam.courierexchange.model.entity;

public class Transport extends AbstractEntity{
    private long id;
    private String name;
    private int averageSpeed;
    private int maxProductWeight;
    private TransportType transportType;

    public Transport(){
    }

    public Transport(TransportBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.averageSpeed = builder.averageSpeed;
        this.maxProductWeight = builder.maxProductWeight;
        this.transportType = builder.transportType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public int getMaxProductWeight() {
        return maxProductWeight;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transport transport = (Transport) o;

        if (id != transport.id) {
            return false;
        }
        if (averageSpeed != transport.averageSpeed){
            return false;
        }
        if (maxProductWeight != transport.maxProductWeight){
            return false;
        }
        if (name != null ? !name.equals(transport.name) : transport.name != null){
            return false;
        }
        return transportType == transport.transportType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + averageSpeed;
        result = 31 * result + maxProductWeight;
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transport{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", averageSpeed=").append(averageSpeed);
        sb.append(", maxProductWeight=").append(maxProductWeight);
        sb.append(", transportType=").append(transportType);
        sb.append('}');
        return sb.toString();
    }

    public static class TransportBuilder{

        private long id;
        private String name;
        private int averageSpeed;
        private int maxProductWeight;
        private TransportType transportType;

        public TransportBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public TransportBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TransportBuilder setAverageSpeed(int averageSpeed) {
            this.averageSpeed = averageSpeed;
            return this;
        }

        public TransportBuilder setMaxProductWeight(int maxProductWeight) {
            this.maxProductWeight = maxProductWeight;
            return this;
        }

        public TransportBuilder setTransportType(TransportType transportType) {
            this.transportType = transportType;
            return this;
        }

        public Transport build() {
            return new Transport(this);
        }
    }
}
