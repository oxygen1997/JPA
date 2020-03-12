package com.zy.dao;

import com.zy.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JpaRepository<实体类,实体类对应表中主键的实体类类型>
 *      封装基本的CRUD操作
 * JpaSpecificationExecutor<实体类>
 *      封装了复杂的查询(分页)
 */

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "from Customer where custName=?1")
    public Customer findJpql(String custName);

    @Query(value = "select * from customer where cust_name=?2 and cust_id=?1",nativeQuery = true)
    public Customer findJpql2(Long id,String name);

    @Query(value = "update Customer set custName=?1 where custId=?2")
    @Modifying
    public Integer updateJpql(String name,Long id);

    Customer findCustomerByCustName(String name);

    List<Customer> findByCustNameLikeOrCustAddressLike(String custName,String custAddress);

}
