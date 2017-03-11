package com.kasun.airline.dao.email;


import com.kasun.airline.model.email.EmailModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailHibernateDaoUnitTest {

    @InjectMocks
    EmailHibernateDao emailDao = new EmailHibernateDao();

    @Mock
    SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Query query;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveEmailDataTest() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        emailDao.saveEmailData(new EmailModel());
        verify(session, times(1)).save(any());
    }
}
