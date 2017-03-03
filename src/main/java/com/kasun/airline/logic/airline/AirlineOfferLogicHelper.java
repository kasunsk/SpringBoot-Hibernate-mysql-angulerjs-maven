package com.kasun.airline.logic.airline;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Route;
import com.kasun.airline.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This helper class will provide common methods for Airline Logic classes.
 */
@Component
public class AirlineOfferLogicHelper {

    @Autowired
    private AirlineDao airlineDao;

    @Autowired
    private UserService userService;

    protected AirlineOfferModel loadOfferByRout(Route airlineRout) {
        return airlineDao.loadOfferByRoute(airlineRout.getFrom(), airlineRout.getTo());
    }

    protected void authenticateApplicant(String applicantId) {
        userService.authenticateUser(new ServiceRequest<>(applicantId));
    }
}
