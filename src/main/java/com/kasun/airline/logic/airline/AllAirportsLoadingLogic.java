package com.kasun.airline.logic.airline;

import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.model.airline.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AllAirportsLoadingLogic extends StatelessServiceLogic<List<Airport>, Void>{

    @Autowired
    private AirlineDao airlineDao;

    @Transactional
    @Override
    public List<Airport> invoke(Void var) {
        return airlineDao.loadAllAirports();
    }
}
