package com.learn.test;

import com.learn.test.dao.FakeDao;
import com.learn.test.service.FakeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = TestApplication.class)
class TestApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    void testApplicationContext(){
        System.out.println("spring container " + applicationContext);

        FakeDao fakeDao = applicationContext.getBean(FakeDao.class);
        System.out.println(fakeDao.fakeRecord());

        fakeDao = applicationContext.getBean("fakeHibernate", FakeDao.class);
        System.out.println(fakeDao.fakeRecord());
    }

    @Test
    void testBeanManagement() {
        FakeService fakeService = applicationContext.getBean(FakeService.class);
        System.out.println(fakeService);
    }

    @Test
    void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Autowired
    @Qualifier("fakeHibernate")
    private FakeDao fakeDao;
    @Autowired
    private FakeService fakeService;
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Test
    public void testDI(){
        System.out.println(fakeDao);
        System.out.println(fakeService);
        System.out.println(simpleDateFormat);
    }

}
