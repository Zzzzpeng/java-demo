package com.chen.tkmybatis.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
@Data
@ToString
//@Table(name = "user")
public class User {
    String username;
    Date time;

}
