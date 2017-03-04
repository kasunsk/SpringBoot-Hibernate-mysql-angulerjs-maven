package com.kasun.airline.logic.airline;

import com.kasun.airline.adapter.airline.AirlineOfferAdapter;
import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.dto.airline.OfferRequest;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AvailableAirlineOfferRetrieveLogic extends StatelessServiceLogic<List<AirlineOffer>, OfferRequest> {

    @Autowired
    private AirlineDao airlineDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AirlineOfferAdapter offerAdapter;

    @Transactional
    @Override
    public List<AirlineOffer> invoke(OfferRequest offerRequest) {

        authenticateApplicant(offerRequest.getApplicantId());
        List<AirlineOfferModel> offerList = airlineDao.loadAirlineOffers(offerRequest.getStatus());
        return offerAdapter.adaptFromModelList(offerList);
    }

    private void authenticateApplicant(String applicantId) {
        userService.authenticateUser(new ServiceRequest<>(applicantId));
    }
}
