package com.zy.dao;

import com.zy.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest2Jpql {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void countAndExist(){
        boolean exists = customerDao.exists(3L);
        System.out.println("id=3："+exists);
        long count = customerDao.count();
        System.out.println("  总数据条数："+count);
    }

    /**
     * findOne: em.find();立即加载
     * getOne:em.getReference();延迟加载【使用时加载，执行sql】
     *  不加事务报错：could not initialize proxy - no Session;
     *  基于JPA的实现来分析，就是在进行数据库访问之时，当前针对数据库的访问与操作session已经关闭且释放了，故提示no Session可用。
     */
    @Test
    @Transactional
    public void findGet(){
        Customer one = customerDao.getOne(5L);
        System.out.println(one);
    }

    /**
     * 使用jpql时，jpql中字段名需要和对应实体类属性名一致 custName
     *     @Query(value = "from Customer where custName=?1")
     *     public Customer findJpql(String custName);
     */
    @Test
    @Transactional
    public void jpql(){
        Customer cus = customerDao.findJpql("吴彦祖");
        System.out.println(cus);
        Customer customer = customerDao.findJpql2(5L,"宋江" );
        System.out.println(customer);
    }

    /**
     * 更新操作需要添加事务注解，回滚注解value=false
     * sb-datajpa事务默认回滚
     */

    @Test
    @Transactional
    @Rollback(value = false)
    public void jpq2Update(){
        Integer code = customerDao.updateJpql("彦祖", 5L);
        System.out.println(code);

    }
}
