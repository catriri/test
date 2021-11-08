package com.learn.test.dao;

import org.springframework.stereotype.Repository;

@Repository("fakeHibernate")
public class FakeDaoHibernateImpl implements FakeDao{
    @Override
    public String fakeRecord() {
        return "a fake Hibernate record";
    }
}
