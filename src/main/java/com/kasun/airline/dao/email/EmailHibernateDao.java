package com.kasun.airline.dao.email;

import com.kasun.airline.dao.AbstractDao;
import com.kasun.airline.model.email.EmailModel;

/**
 * Created by kasun on 3/3/17.
 */
public class EmailHibernateDao extends AbstractDao<Long, EmailModel> implements EmailDao {

    @Override
    public void saveEmailData(EmailModel emailModel) {

    }
}
