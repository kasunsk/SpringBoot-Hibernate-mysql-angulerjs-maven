package com.kasun.airline.dao.airline;

import com.kasun.airline.dao.AbstractDao;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Airport;
import com.kasun.airline.model.user.UserTicket;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
@Repository
public class AirlineHibernateDao extends AbstractDao<Long, AirlineOfferModel> implements AirlineDao {

    public void saveAirlineOffer(AirlineOfferModel airlineOffer) {
        getSession().save(airlineOffer);
    }

    @SuppressWarnings("unchecked")
    public List<AirlineOfferModel> loadAirlineOffers(AirlineOffer.AirlineOfferStatus airlineOfferStatus) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("from AirlineOfferModel AM ");

        if (airlineOfferStatus != null) {
            queryBuilder.append(" WHERE ");
            queryBuilder.append("airlineOfferStatus=:airlineOfferStatus");
        }
        Query query = getSession().createQuery(queryBuilder.toString());
        return query.list();
    }

    public void updateAirlineOffer(AirlineOfferModel airlineOffer) {
        getSession().update(airlineOffer);
    }

    @Override
    public AirlineOfferModel loadOfferByRoute(String origin, String destination) {

        Query query = getSession().createQuery("from AirlineOfferModel AM where AM.origin=:origin and AM.destination=:destination");
        query.setParameter("origin", origin);
        query.setParameter("destination", destination);

        return (AirlineOfferModel) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserTicket> loadApplicantAirlineOffers(Long applicantId) {

        Query query = getSession().createQuery("from UserTicket UT where UT.userId=:userId");
        query.setLong("userId", applicantId);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Airport> loadAllAirports() {

        Query sqlQuery = getSession().createQuery("from Airport");
        return sqlQuery.list();
    }

    @Override
    public void remove(String airlineOfferId) {
        AirlineOfferModel airlineOffer = loadAirlineOfferById(airlineOfferId);
        getSession().delete(airlineOffer);
    }

    public AirlineOfferModel loadAirlineOfferById(String airlineOfferId) {
        Query query = getSession().createQuery("from AirlineOfferModel where offerId=:offerId");
        query.setParameter("offerId", Long.parseLong(airlineOfferId));
        return (AirlineOfferModel) query.uniqueResult();
    }
}
