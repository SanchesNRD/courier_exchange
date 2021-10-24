package by.epam.courierexchange.model.dao;

public class ColumnName {
    //default for everyone
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String STATUS = "status";
    public static final String STATUS_ID = "status_id";
    public static final String TYPE_ID = "type_id";

    //users
    public static final String USERS_STATUS_ID="users.status_id";
    public static final String USER_ID="users.id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_MAIL = "mail";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_PHONE = "phone";
    public static final String USER_IMAGE = "image";

    //clients
    public static final String ADDRESS_ID = "address_id";
    public static final String CLIENT_POINT_ID = "clients.id";
    //products
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_WEIGHT = "weight";
    public static final String PRODUCT_LENGTH = "length";
    public static final String PRODUCT_WIDTH = "width";
    public static final String PRODUCT_HEIGHT = "height";
    //client_product
    public static final String CLIENT_ID ="client_id";
    public static final String PRODUCT_ID = "product_id";
    public static final String USER_POINT_NAME = "users.name";
    public static final String CLIENTS_ADDRESS_ID = "clients.address_id";
    public static final String CLIENT_PRODUCT_ADDRESS_ID = "client_product.address_id";
    public static final String PRODUCT_POINT_NAME = "products.name";
    public static final String CLIENT_PRODUCT_ID = "client_product.id";

    public static final String ORDER_CLIENT_PRODUCT_ID = "client_product_id";
    //couriers
    public static final String COURIER_RATING = "rating";
    public static final String COURIERS_ID = "couriers.id";
    //transport
    public static final String TRANSPORT_NAME = "name";
    public static final String TRANSPORT_AVERAGE_SPEED = "average_speed";
    public static final String TRANSPORT_MAX_PRODUCT_WEIGHT = "max_product_weight";
    //courier_transport
    public static final String COURIER_ID = "courier_id";
    public static final String TRANSPORT_ID = "transport_id";

    //addresses
    public static final String ADDRESS_COUNTRY = "country";
    public static final String ADDRESS_CITY = "city";
    public static final String ADDRESS_STREET = "street";
    public static final String ADDRESS_STREET_NUMBER = "street_number";
    public static final String ADDRESS_APARTMENT ="apartment";

    //orders
    public static final String ORDER_DATE = "date";
    public static final String ORDERS_DATE = "orders.date";
    public static final String ORDERS_STATUS = "orders.status_id";
    public static final String ORDERS_ID = "orders.id";

    public static final String CL_ADDRESS_ID = "cl.address_id";
    public static final String U_NAME = "u.name";
    public static final String U_SURNAME = "u.surname";
    public static final String U_LOGIN = "u.login";
    public static final String U_PASSWORD = "u.password";
    public static final String U_MAIL = "u.mail";
    public static final String U_IMAGE = "u.image";
    public static final String U_PHONE = "u.phone";
    public static final String U_STATUS = "u.status_id";

    public static final String U2_NAME = "u2.name";
    public static final String U2_SURNAME = "u2.surname";
    public static final String U2_LOGIN = "u2.login";
    public static final String U2_PASSWORD = "u2.password";
    public static final String U2_MAIL = "u2.mail";
    public static final String U2_IMAGE = "u2.image";
    public static final String U2_PHONE = "u2.phone";
    public static final String U2_STATUS = "u2.status_id";

    public static final String CO_TRANSPORT = "co.transport_id";
    public static final String CO_RATING = "co.rating";

    public static final String P_NAME = "p.name";
    public static final String P_WEIGHT = "p.weight";
    public static final String P_LENGTH = "p.length";
    public static final String P_WIDTH = "p.width";
    public static final String P_HEIGHT = "p.height";
    public static final String P_TYPE = "p.type_id";

    public static final String CP_ADDRESS = "cp.address_id";

    public static final String A_COUNTRY = "a.country";
    public static final String A_CITY = "a.city";
    public static final String A_STREET = "a.street";
    public static final String A_STREET_NUMBER = "a.street_number";
    public static final String A_APARTMENT ="a.apartment";


    private ColumnName(){}
}
