package com.crossover.techtrial.java.se.dao.airline;

import com.crossover.techtrial.java.se.dto.airline.AirlineOffer;
import com.crossover.techtrial.java.se.model.airline.AirlineOfferModel;
import com.crossover.techtrial.java.se.model.user.UserTicket;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AirlineDao {

    void saveAirlineOffer(AirlineOfferModel airlineOffer);

    List<AirlineOfferModel> loadAirlineOffers(AirlineOffer.AirlineOfferStatus airlineOfferStatus);

    void updateAirlineOffer(AirlineOfferModel airlineOffer);

    AirlineOfferModel loadOfferByRoute(String origin, String destination);

    List<UserTicket> loadApplicantAirlineOffers(Long applicantId);

    List<String> loadAllAirports();

    void remove(String airlineOfferId);
}
