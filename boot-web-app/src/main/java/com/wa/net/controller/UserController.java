package com.wa.net.controller;

import com.alibaba.druid.support.json.JSONUtils;

import com.alibaba.fastjson.JSON;
import com.wa.net.domain.User;
import com.wa.net.service.UserService;
import com.wa.net.utils.ActivemqConstant;
import com.wa.net.utils.AjaxStatus;
import com.wa.net.utils.JsonHelp;
import com.wa.net.utils.JsonResult;
import com.wa.net.vo.PageResultVo;
import com.wa.net.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private JmsTemplate jmsTemplate;

    @Value("${spring.upload.filepath}")
    private String fileUploadPath;

    @Value("${spring.upload.httpfilepath}")
    private String fileHttpAddress;

    /**
     * 测试mq发送消息 与监听的
     * @param modelMap
     * @return
     */
    @RequestMapping("/say-hello")
    public String test(ModelMap modelMap) {
        modelMap.addAttribute("name", "spring-boot");
        return "say-hello";
    }

    @RequestMapping("/{userId}")
    public @ResponseBody String get(@PathVariable("userId") String userId) {
        UserVo userVo=userService.findUserById(userId);
        return JSON.toJSONString(userVo);
    }

    @RequestMapping(value ="/addOrUpdateUser",method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public  @ResponseBody String addUser(@RequestBody UserVo userVo) {
        JsonResult result=new JsonResult(AjaxStatus.OK);
        Integer no;
        if(null!=userVo.getId()){
           no=userService.updateUser(userVo);
        }else{
           no=userService.addUser(userVo);
        }
        if(null!=no){
            result.setResultstr("sussess");
            result.setBean(no);
            return  JSON.toJSONString(result);
        }
        return "save user fail";
    }


    @RequestMapping(value ="/findUsersByPage",method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public  @ResponseBody String findUsersByPage(HttpServletRequest request) {
        Map<String, Object> currentParam = JsonHelp.parserPageParms(request);
        Integer current_size=(Integer)currentParam.get(JsonHelp.CURRENT_SIZE);
        Integer current_page=(Integer)currentParam.get(JsonHelp.CURRENT_PAGE);
        PageResultVo p = userService.findUserByPage(current_size, current_page, currentParam);
        return JsonHelp.getPageJson(p, current_page, current_size);
    }

    @RequestMapping(value ="/findUserByPage",method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public  @ResponseBody String findUserByPage(@RequestBody UserVo userVo) {
        JsonResult result=new JsonResult(AjaxStatus.OK);
        PageResultVo vo=userService.findUserValue(userVo);
        if(null!=vo){
            result.setResultstr("sussess");
            result.setBean(vo);
            return  JSON.toJSONString(result);
        }
        return "save user fail";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public @ResponseBody String deleteUser(@PathVariable("id") Integer id) {
        JsonResult result=new JsonResult(AjaxStatus.OK);
        Integer no=userService.deleteUser(id,null);
        if(null!=no){
            result.setResultstr("sussess");
            result.setBean(no);
            return  JSON.toJSONString(result);
        }
        return JSON.toJSONString(no);
    }

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public @ResponseBody String uploadFile(@RequestParam(value="file", required=true) MultipartFile resfile,
                                           HttpServletRequest request,
                                           HttpSession session, HttpServletResponse response){
        JsonResult result=new JsonResult(AjaxStatus.OK);
        System.out.println("文件名称:"+(request.getParameter("filename")));
        // ((StandardMultipartHttpServletRequest) request).getMultipartFiles()
        System.out.println("upload post:" + session.getId());
        session.setAttribute("file", resfile);
        response.setContentType("text/html;charset=UTF-8");
        if(!resfile.isEmpty()){
            File uploadDir = new File(fileUploadPath);
            if (!uploadDir.exists()) {// 不存在则创建
                uploadDir.mkdirs();
            }
            System.out.println(resfile.getOriginalFilename());
            System.out.println(resfile.getSize());
            String name = System.currentTimeMillis() + ".jpg";
            String localPath =  fileUploadPath + name;
            String url = fileHttpAddress  + name;
            try {
                resfile.transferTo(new File(localPath));
                result.setBean(url);
                result.setResultstr("success");
                return JSON.toJSONString(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(new JsonResult(AjaxStatus.ERROR));
    }

}
