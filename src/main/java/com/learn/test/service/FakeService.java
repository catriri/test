package com.learn.test.service;

import com.learn.test.dao.FakeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class FakeService {

    private FakeDao fakeDao;

    @Autowired
    @Qualifier("fakeHibernate")
    public void setFakeDao(FakeDao fakeDao) {
        this.fakeDao = fakeDao;
    }

    public String findRecord(){
        return fakeDao.fakeRecord();
    }

    public FakeService() {
        System.out.println("instantiate FakeService");
    }

    @PostConstruct
    public void init(){
        System.out.println("initialize FakeService");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy FakeService");
    }
}
