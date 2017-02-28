package com.kasun.airline.dao.airline;

import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.user.UserTicket;

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
