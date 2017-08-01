package com.chenyl.repository;

import com.chenyl.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by cookily on 2017/7/28.
 */
public interface GirlRepository extends JpaRepository<Girl,Integer> {
    //通过年龄来查询
    public List<Girl> findByAge(Integer age);
}
