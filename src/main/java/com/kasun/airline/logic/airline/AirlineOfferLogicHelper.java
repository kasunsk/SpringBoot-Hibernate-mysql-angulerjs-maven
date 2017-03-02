package com.kasun.airline.logic.airline;

import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Route;
import com.kasun.airline.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kasun on 3/2/17.
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
        userService.authenticateUser(applicantId);
    }
}
