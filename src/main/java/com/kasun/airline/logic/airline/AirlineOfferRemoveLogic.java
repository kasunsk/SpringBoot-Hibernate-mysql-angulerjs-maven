package com.kasun.airline.logic.airline;

import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.airline.AirlineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kasun on 3/2/17.
 */
@Component
public class AirlineOfferRemoveLogic extends StatelessServiceLogic<Void, String> {

    @Autowired
    private AirlineDao airlineDao;

    @Transactional
    @Override
    public Void invoke(String offerId) {
        airlineDao.remove(offerId);
        return new Void();
    }
}
