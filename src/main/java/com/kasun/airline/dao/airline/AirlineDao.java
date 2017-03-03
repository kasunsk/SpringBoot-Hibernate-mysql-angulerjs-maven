package com.kasun.airline.dao.airline;

import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Airport;
import com.kasun.airline.model.user.UserTicket;

import java.util.List;

/**
 * This interface provide the database access methods
 */
public interface AirlineDao {

    void saveAirlineOffer(AirlineOfferModel airlineOffer);

    List<AirlineOfferModel> loadAirlineOffers(AirlineOffer.AirlineOfferStatus airlineOfferStatus);

    void updateAirlineOffer(AirlineOfferModel airlineOffer);

    AirlineOfferModel loadOfferByRoute(String origin, String destination);

    List<UserTicket> loadApplicantAirlineOffers(Long applicantId);

    List<Airport> loadAllAirports();

    void remove(String airlineOfferId);
}
