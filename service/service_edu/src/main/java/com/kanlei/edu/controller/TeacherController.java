package com.kanlei.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanlei.commonUtils.GenericResult;
import com.kanlei.edu.entity.Teacher;
import com.kanlei.edu.entity.vo.TeacherQuery;
import com.kanlei.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author kanlei
 * @since 2020-07-20
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    /**
     * 注入teacherService
     */
    @Autowired
    TeacherService teacherService;

    /**
     * 通过讲师id修改讲师
     * @param id
     * @param teacher
     * @return
     */
    @ApiOperation(value = "通过讲师id修改讲师")
    @PutMapping("/{id}")
    public GenericResult updateTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                       @PathVariable("id")String id,
                                       @ApiParam(name = "teacher", value = "讲师对象", required = true)
                                       @RequestBody Teacher teacher){
        // 此步得加上
        teacher.setId(id);
        boolean update = teacherService.updateById(teacher);
        if (update) {
            return GenericResult.success();
        } else {
            return GenericResult.failure();
        }
    }

    /**
     * 通过id获取讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "通过id获取讲师")
    @GetMapping("/getTeacher/{id}")
    public GenericResult getTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                    @PathVariable("id")String id){

        Teacher teacher = teacherService.getById(id);
        return GenericResult.success().data("teacher", teacher);
    }
    /**
     * 添加讲师
     *
     * @param teacher 讲师信息
     * @return
     */
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public GenericResult addTeacher(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                                    @RequestBody Teacher teacher) {

        boolean save = teacherService.save(teacher);
        if (save) {
            return GenericResult.success();
        } else {
            return GenericResult.failure();
        }
    }

    /**
     * 按照条件分页查询讲师
     *
     * @param current      当前页
     * @param limit        当前页显示数
     * @param teacherQuery 查询条件
     * @return
     */
    @ApiOperation("按照条件分页查询讲师")
    @PostMapping("/pageTeacher/{current}/{limit}")
    public GenericResult pageListTeacherCondition(@ApiParam(name = "current", value = "当前页", required = true)
                                                  @PathVariable(value = "current") long current,
                                                  @ApiParam(name = "limit", value = "当前页显示数", required = true)
                                                  @PathVariable(value = "limit") long limit,
                                                  @ApiParam(name = "teacherQuery", value = "查询条件",required = false)
                                                  @RequestBody TeacherQuery teacherQuery) {

        //创建分页模型
        Page<Teacher> teacherPage = new Page<>(current, limit);
        // 封装查询条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        //多条件查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }

        IPage<Teacher> page = teacherService.page(teacherPage, wrapper);
        long total = page.getTotal();
        List<Teacher> records = page.getRecords();
        return GenericResult.success().data("total", total).data("records", records);
    }

    /**
     * 分页查询讲师
     *
     * @param current 当前页
     * @param limit   当前页显示数
     * @return 页面数据
     */
    @ApiOperation("分页查询讲师")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public GenericResult pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true)
                                         @PathVariable(value = "current") long current,
                                         @ApiParam(name = "limit", value = "当前页显示数", required = true)
                                         @PathVariable(value = "limit") long limit) {
        // 创建分页模型
        Page<Teacher> page = new Page<>(current, limit);
        // 获取查询后的分页模型
        IPage<Teacher> teacherIPage = teacherService.page(page, null);
        long total = teacherIPage.getTotal();
        List<Teacher> records = teacherIPage.getRecords();
        return GenericResult.success().data("total", total).data("records", records);
    }

    /**
     * 实现通过id进行讲师的逻辑删除
     *
     * @param id 讲师id
     * @return 是否删除成功
     */
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/{id}")
    public GenericResult deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                           @PathVariable(value = "id") String id) {

        boolean flag = teacherService.removeById(id);
        if (flag) {
            return GenericResult.success();
        } else {
            return GenericResult.failure();
        }
    }

    /**
     * 获取所有讲师
     *
     * @return 讲师集合
     */
    @ApiOperation("获取所有讲师")
    @GetMapping("/findAll")
    public GenericResult getAllTeacher() {

        List<Teacher> teachers = teacherService.list(null);
        return GenericResult.success().data("items", teachers);
    }

}

