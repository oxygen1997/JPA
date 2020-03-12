package com.zy.dao;

import com.zy.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest3SQL {

    /**
     * 方法命名规则查询：对jpql查询，更加深入的一层封装；
     * 按照SpringDataJpa提供的方法名称规则定义方法。不需配置jpql语句进行查询
     *      findByXXX：代表查询【XXX：对象属性名[遵循驼峰命名规则]】
     *      findByCustName
     *      含义：根据属性名查询符合条件对象
     *
     * 模糊查询：
     *      单条件：findBy属性名Like
     *      多条件：findBy属性名LikeOr/And属性名Like();
     */

    @Autowired
    CustomerDao customerDao;

    /**
     * 测试按照规则命名方法查询
     */
    @Test
    public void testQueryDefineName(){
        Customer cus = customerDao.findCustomerByCustName("彦祖");
        System.out.println(cus);
    }

    /**
     * 按照命名规则模糊查询
     */
    @Test
    public void testLikeQuery(){
        List<Customer> customer = customerDao.findByCustNameLikeOrCustAddressLike("吴%","%CBD%");
        for (Customer c : customer) {
            System.out.println(c);
        }
    }
}
