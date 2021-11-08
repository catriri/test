package com.learn.test.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class FakeDaoMybatisImpl implements FakeDao{
    @Override
    public String fakeRecord() {
        return "a fake Mybatis record";
    }
}
