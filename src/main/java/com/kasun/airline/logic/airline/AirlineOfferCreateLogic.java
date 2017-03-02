package com.kasun.airline.logic.airline;

import com.kasun.airline.adapter.airline.AirlineOfferAdapter;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Route;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kasun on 3/2/17.
 */
@Component
public class AirlineOfferCreateLogic extends StatelessServiceLogic<Void, AirlineOffer> {

    @Autowired
    private AirlineDao airlineDao;

    @Autowired
    private AirlineOfferAdapter offerAdapter;

    @Autowired
    private AirlineOfferLogicHelper helper;


    @Transactional
    @Override
    public Void invoke(AirlineOffer airlineOffer) {
        validateAirlineOffer(airlineOffer);
        AirlineOfferModel offerModel = offerAdapter.adaptFromDto(airlineOffer);
        airlineDao.saveAirlineOffer(offerModel);
        return new Void();
    }

    private void validateAirlineOffer(AirlineOffer airlineOffer) {

        ValidationUtil.validate(airlineOffer, "Airline offer can not be null");
        ValidationUtil.validate(airlineOffer.getPrice(), "Price can not be null");
        ValidationUtil.validate(airlineOffer.getRoute(), "Route can not be null");
        Route airlineOfferRoute = airlineOffer.getRoute();

        if (airlineOfferRoute.getFrom().equals(airlineOfferRoute.getTo())) {
            throw new ServiceRuntimeException(ErrorCode.INVALID_OFFER_ROUT, "Invalid route");
        }
        validateRoutAlreadyAvailable(airlineOffer);
    }

    private void validateRoutAlreadyAvailable(AirlineOffer airlineOffer) {

        Route route = airlineOffer.getRoute();
        AirlineOfferModel airlineOfferModel = helper.loadOfferByRout(route);

        if (airlineOfferModel != null) {
            throw new ServiceRuntimeException(ErrorCode.ROUTE_ALREADY_EXIST, "Offer rout already exist, Please remove current offer to add new");
        }
    }
}
