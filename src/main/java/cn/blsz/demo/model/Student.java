package cn.blsz.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author yingtao
 * @ClassName Student
 * @description: TODO
 * @datetime 2022年 08月 12日 14:00
 * @version: 1.0
 */
@Data
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    private Integer cid;
}