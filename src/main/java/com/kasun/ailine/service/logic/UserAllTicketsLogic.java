package com.kasun.ailine.service.logic;

import com.kasun.ailine.service.common.service.ServiceLogic;
import com.kasun.ailine.service.model.user.UserTicket;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kasun on 2/28/17.
 */
@Component
public class UserAllTicketsLogic implements ServiceLogic<List<UserTicket>, String> {

    @Override
    @Transactional
    public String invoke(List<UserTicket> request) {
        return null;
    }
}
