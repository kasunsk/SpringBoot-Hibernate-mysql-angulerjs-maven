package com.kasun.airline.logic.airline;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.model.user.UserTicket;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ApplicantTicketsRetrieveLogic extends StatelessServiceLogic<List<UserTicket>, String> {

    @Autowired
    private AirlineDao airlineDao;

    @Transactional
    @Override
    public List<UserTicket> invoke(String applicantId) {

        ValidationUtil.validate(applicantId, "Applicant id is null");
        return airlineDao.loadApplicantAirlineOffers(Long.parseLong(applicantId));
    }
}
