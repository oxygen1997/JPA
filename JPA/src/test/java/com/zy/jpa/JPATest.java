package com.zy.jpa;

import com.zy.jpa.domain.Customer;
import com.zy.jpa.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPATest {

    /**
     * JPA操作步骤：
     *          1.加载配置文件创建工厂（实体类管理器工厂）对象
     *          2.通过实体类管理器工厂获取实体类管理器
     *          3.获取事务，开启事务
     *          4.完成crud
     *          5.提交事务（回滚事务）
     *          6.释放资源
     */

    /**
     * save
     */
    @Test
    public void testSave(){
        //实体类管理器工厂
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyJpa");
        //实体类管理器
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Customer customer = new Customer("上海市CBD","IT/互联网","P10","吴德华","66666666666","alibaba");
            entityManager.persist(customer);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    /**
     * select  find() 立即加载
     */
    @Test
    public void testFind(){
        EntityManager em = null;
        try{
            em = JPAUtil.getEntityManager();
            Customer customer = em.find(Customer.class, 1L);
            System.out.println(customer);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
    /**
     * select    getReference() 延迟加载
     * 当查询对象被使用时再发送sql语句加载对象
     */
    @Test
    public void testReference(){
        EntityManager em = null;
        try{
            em = JPAUtil.getEntityManager();
            Customer customer = em.find(Customer.class, 1L);
            System.out.println(customer);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }

    /**
     * update
     * 使用EntityManager的merge()方法更新
     * 先查询，在设置值更新
     */
    @Test
    public void testUpdate(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Customer customer = em.find(Customer.class, 1L);
            customer.setCustName("刘德华");
            em.merge(customer);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
    }

    /**
     * delete
     */
    @Test
    public void testDelete(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Customer customer =em.find(Customer.class,4L);
            em.remove(customer);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
    }

}
