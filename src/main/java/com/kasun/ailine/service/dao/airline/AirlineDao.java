package com.kasun.ailine.service.dao.airline;

import com.kasun.ailine.service.dto.airline.AirlineOffer;
import com.kasun.ailine.service.model.airline.AirlineOfferModel;
import com.kasun.ailine.service.model.user.UserTicket;

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
