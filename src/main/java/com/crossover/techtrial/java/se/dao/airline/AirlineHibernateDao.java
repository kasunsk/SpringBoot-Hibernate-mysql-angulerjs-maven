package com.crossover.techtrial.java.se.dao.airline;

import com.crossover.techtrial.java.se.dao.AbstractDao;
import com.crossover.techtrial.java.se.dto.AirlineOffer;
import com.crossover.techtrial.java.se.model.airline.AirlineOfferModel;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
@Repository
public class AirlineHibernateDao extends AbstractDao<Long,AirlineOfferModel> implements AirlineDao {

    public void saveAirlineOffer(AirlineOfferModel airlineOffer) {
        //persist(airlineOffer);
        getSession().save(airlineOffer);
    }

    public List<AirlineOfferModel> loadAirlineOffers(AirlineOffer.AirlineOfferStatus airlineOfferStatus) {

        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("from AirlineOfferModel AM ");

        if (airlineOfferStatus != null) {
            queryBuilder.append(" WHERE ");

            if (airlineOfferStatus != null) {
                queryBuilder.append("airlineOfferStatus=:airlineOfferStatus");
            }
        }

        Query query = getSession().createQuery(queryBuilder.toString());
        return query.list();
    }

    public void updateAirlineOffer(AirlineOfferModel airlineOffer) {

    }
}
