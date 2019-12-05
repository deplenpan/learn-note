package com.icbc.patrol.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icbc.patrol.validator.IdValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/15 15:16
 */
@ApiModel(value = "用户信息")
@Accessors(chain = true)
@TableName(value = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 4377475395386176616L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(required = true, name = "id", value = "用户id", dataType = "query")
    @NotNull(groups = IdValidator.class, message = "id不能为空")
    private Integer id;

    /**
     * 用户姓名
     */
    @ApiModelProperty(name = "name", value = "用户姓名", dataType = "query")
    private String name;

    /**
     * 用户年龄
     */
    @ApiModelProperty(name = "age", value = "用户年龄", dataType = "query")
    private Integer age;

    /**
     * 用户登录名
     */
    @ApiModelProperty(name = "username", value = "用户姓名", dataType = "query")
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名必须是4~32位数字或字母")
    private String username;

    /**
     * 用户登录密码
     */
    @ApiModelProperty(name = "password", value = "用户姓名", dataType = "query")
    @JsonIgnore
    @Length(min = 4, max = 32, message = "密码必须是4~32位数字或字母")
    private String password;
//
//    /**
//     * 手机号
//     */
//    @ApiModelProperty(name = "phone", value = "手机号", dataType = "query")
//    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
//    private String phone;

    /**
     * 密码的盐值
     */
    @JsonIgnore
    private String salt;

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer id, String name, Integer age, String username, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
