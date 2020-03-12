package com.zy.dao;

import com.zy.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器配置信息
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void find(){
        List<Customer> all = customerDao.findAll();
        for (Customer c:all) {
            System.out.println(c);
        }
    }

    @Test
    public void  findOne(){
        System.out.println(customerDao.findOne(8L));
    }

    /**
     *     @Column(name = "cust_id")
     *     @Column(name = "cust_address")
     *     @Column(name = "cust_industry")
     *     @Column(name = "cust_level")
     *     @Column(name = "cust_name")
     *     @Column(name = "cust_phone")
     *     @Column(name = "cust_source")
     */
    @Test
    public void add(){
        Customer customer = customerDao.save(new Customer("深圳市福田区", "计算机软件", "P6", "渣渣辉", "18899996666", "娱乐圈"));
        System.out.println(customer);
    }

    @Test
    public void addBatch(){
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("深圳市福田区", "计算机软件", "P9", "渣渣辉", "18899996666", "娱乐圈"));
        list.add(new Customer("深圳市福田区", "游戏", "P6", "彭于晏", "18899996666", "娱乐圈"));
        list.add(new Customer("深圳市福田区", "直播教育", "P5", "金城武", "18899996666", "娱乐圈"));
        List<Customer> save = customerDao.save(list);
        for (Customer e:save) {
            System.out.println(e);
        }
    }

    @Test
    public void update(){
        Customer customer = new Customer();
        customer.setCustId(12L);
        customer.setCustAddress("棒子");
        customer.setCustName("金城武");
        customerDao.save(customer);
    }
    @Test
    public void delete(){
        customerDao.delete(12L);
    }

}
