package com.chenyl.service;

import com.chenyl.domain.Girl;
import com.chenyl.enums.ResultEnum;
import com.chenyl.exception.GirlException;
import com.chenyl.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cookily on 2017/7/29.
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girlA=new Girl();
        girlA.setAge(15);
        girlA.setCupSize("A");
        girlA.setMoney(2.1f);
        girlRepository.save(girlA);

        Girl girlB=new Girl();
        girlB.setAge(18);
        girlB.setCupSize("B");
        girlB.setMoney(2.1f);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception{
        Girl girl=girlRepository.findOne(id);
        Integer age=girl.getAge();
        if (age<10){
            //返回 你还在上小学吧
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age>10 && age<16){
            //返回 可能在上初中
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }

        //如果 大于16岁，加钱
        //。。。
    }
}
