package com.kasun.airline.dao.email;

import com.kasun.airline.model.email.EmailModel;

/**
 * Created by kasun on 3/3/17.
 */
public interface EmailDao {

    void saveEmailData(EmailModel emailModel);
}
