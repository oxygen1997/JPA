package com.zy.jpa.domain;

import javax.persistence.*;

/**
 * @author zy
 * 配置映射关系
 *          1.实体类和表的映射关系
 *          2.实体类中属性和表中字段的映射关系
 * @Entity   声明实体类
 * @Tabel    配置实体类和表的映射关系
 *              name：数据库表的名称
 *
 */
@Entity
@Table(name="customer")
public class Customer {
    /**
     * Id：主键
     * GeneratedValue：主键的增长方式
     *      GenerationType.IDENTITY：自增  【底层数据库支持自增】
     *      GenerationType.SEQUENCE：序列  【底层数据库支持序列】
     *      GenerationType.TABLE：JPA提供机制，通过一张数据表形式帮助用户完成自增
     *             配置文件中create和TABLE搭配，主表主键不是自增，会生成hibernate_sequence表来保存下一次主表中的id
     *      GenerationType.AUTO：由程序帮助选择主键生成策略
     *             配置文件中create和TABLE搭配，和TABLE相似
     * Column：列名
     */

    //客户主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;

    //客户地址
    @Column(name = "cust_address")
    private String custAddress;

    //客户所属行业
    @Column(name = "cust_industry")
    private String custIndustry;

    //客户级别
    @Column(name = "cust_level")
    private String custLevel;

    //客户名称
    @Column(name = "cust_name")
    private String custName;

    //客户电话
    @Column(name = "cust_phone")
    private String custPhone;

    //客户来源
    @Column(name = "cust_source")
    private String custSource;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public Customer(){}

    public Customer(String custAddress, String custIndustry, String custLevel, String custName, String custPhone, String custSource) {
        this.custAddress = custAddress;
        this.custIndustry = custIndustry;
        this.custLevel = custLevel;
        this.custName = custName;
        this.custPhone = custPhone;
        this.custSource = custSource;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custAddress='" + custAddress + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custName='" + custName + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custSource='" + custSource + '\'' +
                '}';
    }
}
