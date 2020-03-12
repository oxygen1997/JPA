package com.zy.dao;

import com.zy.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest4Dynamic {

    //匿名内部类
    /**
     * 自定义查询条件
     *      1.实现Specification接口（提供泛型：查询的对象类型）
     *      2.实现toPredicate方法（构造查询条件）
     *      3.需要借助方法参数中的两个参数（
     *          root：获取需要查询的对象属性
     *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
     *       ）
     *  案例：根据客户名称查询，查询客户名为传智播客的客户
     *          查询条件
     *              1.查询方式
     *                  cb对象
     *              2.比较的属性名称
     *                  root对象
     *
     */

    @Autowired
    private CustomerDao customerDao;

    /**
     * 单条件查询
     * 多条件查询拼接条件
     */
    @Test
    public void testDynamic1(){
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> name = root.get("custName");
                Path<Object> industry = root.get("custIndustry");
                Predicate p1 = criteriaBuilder.equal(name, "渣渣飞");
                Predicate p2 = criteriaBuilder.equal(industry,"计算机软件");
                Predicate and = criteriaBuilder.and(p1, p2);
                //单条件查询
//                return p1;
                return and;
            }
        };

        Customer one = customerDao.findOne(specification);
        System.out.println(one);
    }

    /**
     * 模糊查询
     * 排序
     */
    @Test
    public void testDynamic2(){
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("custName");
                Predicate predicate = criteriaBuilder.like(path.as(String.class), "%渣%");
                return predicate;
            }
        };
//        List<Customer> all = customerDao.findAll(specification);
//        for (Customer c:all) {
//            System.out.println(c);
//        }
//        添加排序
        //创建排序对象,需要调用构造方法实例化sort对象
        //第一个参数：排序的顺序（倒序，正序）
        //   Sort.Direction.DESC:倒序
        //   Sort.Direction.ASC ： 升序
        //第二个参数：排序的属性名称
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> all = customerDao.findAll(specification, sort);
        for (Customer c:all) {
            System.out.println(c);
        }
    }

    /**
     * 分页
     *     Specification: 查询条件
     *     Pageable：分页参数
     *         分页参数：查询的页码，每页查询的条数
     *         findAll(Specification,Pageable)：带有条件的分页
     *         findAll(Pageable)：没有条件的分页
     * 返回：Page（springDataJpa为我们封装好的pageBean对象，数据列表，共条数）
     */
    @Test
    public void testPage(){
        //PageRequest对象是Pageable接口的实现类
        /**
         * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
         *      第一个参数：当前查询的页数（从0开始）
         *      第二个参数：每页查询的数量
         */
        PageRequest pageRequest = new PageRequest(1, 3);
        Page<Customer> all = customerDao.findAll(pageRequest);
        for (Customer c : all.getContent()) {
            System.out.println(c);
        }
        System.out.println("数据总数："+all.getTotalElements());
        System.out.println("总页数："+all.getTotalPages());
    }

}
