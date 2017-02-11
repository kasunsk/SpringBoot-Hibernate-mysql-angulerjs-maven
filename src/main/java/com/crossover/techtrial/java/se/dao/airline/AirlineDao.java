package com.crossover.techtrial.java.se.dao.airline;

import com.crossover.techtrial.java.se.dto.AirlineOffer;
import com.crossover.techtrial.java.se.model.airline.AirlineOfferModel;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AirlineDao {

    void saveAirlineOffer(AirlineOfferModel airlineOffer);

    List<AirlineOfferModel> loadAirlineOffers(AirlineOffer.AirlineOfferStatus airlineOfferStatus);

    void updateAirlineOffer(AirlineOfferModel airlineOffer);
}
