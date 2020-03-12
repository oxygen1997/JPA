package com.zy.dao;

import com.zy.domain.Customer;
import com.zy.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LinkManDaoTest5Many {

    @Autowired
    LinkManDao linkManDao;

    @Autowired
    CustomerDao customerDao;

    /**
     * 保存操作
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testMany1(){
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小昭");
        Customer customer = new Customer();
        customer.setCustName("张无忌");
//        customer.getSet().add(linkMan);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
}

