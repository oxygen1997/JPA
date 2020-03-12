package com.zy.jpa;

import com.zy.jpa.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JPQLTest {

    /**
     * select *
     */
    @Test
    public void findAll(){
        EntityManager em = JPAUtil.getEntityManager();
        String sql = "from com.zy.jpa.domain.Customer";
        Query query = em.createQuery(sql);
        List result = query.getResultList();
        for (Object obj : result) {
            System.out.println(obj);
        }
    }

    /**
     * select * order by desc  倒序排列
     */
    @Test
    public void findAllOrderByDesc(){
        EntityManager em = JPAUtil.getEntityManager();
        String sql = "from com.zy.jpa.domain.Customer order by custId desc";
        Query query = em.createQuery(sql);
        List result = query.getResultList();
        for (Object obj : result) {
            System.out.println(obj);
        }
    }

    /**
     * select count（column）  count函数
     */
    @Test
    public void Count(){
        EntityManager em = JPAUtil.getEntityManager();
        String sql = "select count(custId) from Customer";
        Query query = em.createQuery(sql);
        Object result = query.getSingleResult();
        System.out.println(result);
    }

    /**
     * select where condition
     */
    @Test
    public void getByCondition(){
        EntityManager em = JPAUtil.getEntityManager();
//        String sql = "from Customer where custId=5";
        String sql = "from Customer where custName like '吴%' ";
        Query query = em.createQuery(sql);
        List list = query.getResultList();
        for (Object o:list) {
            System.out.println(o);
        }

    }


}
