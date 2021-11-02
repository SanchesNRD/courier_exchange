package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.ClientProduct;

import java.util.List;
import java.util.Optional;

public interface ClientDao extends BaseDao<Long, Client>{
    int createById(long userId) throws DaoException;
    int updateAddressToClient(long idClient, long idAddress) throws DaoException;

    List<ClientProduct> selectAllClientProduct() throws DaoException;
    Optional<ClientProduct> selectClientProductById(long id) throws DaoException;
    List<ClientProduct> selectActiveClientProductById(long id) throws DaoException;
    int deleteClientProductById(Long id) throws DaoException;
    boolean deleteClientProductByClient(Long id) throws DaoException;
    int createClientProduct(Long clientId, Long productId, Long addressId) throws DaoException;
    List<ClientProduct> selectClientProductForCourier(long id, int weight) throws DaoException;
}
