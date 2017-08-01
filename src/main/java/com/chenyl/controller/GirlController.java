package com.chenyl.controller;

import com.chenyl.domain.Girl;
import com.chenyl.domain.Result;
import com.chenyl.repository.GirlRepository;
import com.chenyl.service.GirlService;
import com.chenyl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cookily on 2017/7/28.
 */
@RestController
public class GirlController {
    private static final Logger logger= LoggerFactory.getLogger(GirlController.class);
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     *
     * @return
     */
    @GetMapping(value = "/findGirlList")
    public List<Girl> findGirlList() {
        logger.info("findGirlList");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     *
     * @return
     */
    @PostMapping(value = "/addGirl")
    public Result<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
        //加@Valid Girl 表示验证的对象是Girl 而验证的结果返回给BindingResult
        if (bindingResult.hasErrors()) {
            //System.err.println(bindingResult.getFieldError().getDefaultMessage());
           /* Result result=new Result();
            result.setCode(1);
            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
            result.setData(null);
            return result;*/
           return null;
//            return   ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

       /* Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);*/
      /*  Result result=new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(girlRepository.save(girl));*/

        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/findGirlById/{id}")
    public Girl findGirlOne(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);
    }

    /**
     * 更新
     *
     * @param id
     * @param cupSize
     * @param age
     */
    @PutMapping(value = "/updateGirl/{id}")
    public Girl updateGirl(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping(value = "deletGirlById/{id}")
    public void deleteGirlById(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询
     *
     * @param age
     * @return
     */
    @RequestMapping(value = "/findGirlByAge/{age}")
    public List<Girl> findGirlListByAge(@PathVariable("age") Integer age) {

        return girlRepository.findByAge(age);
    }

    @GetMapping(value = "/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        girlService.getAge(id);
    }
}
