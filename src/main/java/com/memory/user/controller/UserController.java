package com.memory.user.controller;

import com.memory.base.constant.ResponseCode;
import com.memory.base.web.BaseController;
import com.memory.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user", produces = "application/json;charset=utf-8")
public class UserController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
//
//    @ResponseBody
//    @RequestMapping(value = {"/list", "/"}, method = RequestMethod.GET)
//    public String list(User user) {
//
//        // TODO
//
//        List<User> list = userService.getAll(user);
//        return responseJson(SUCCESS, list);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public String get(@RequestParam(value = "id, required = false") Long id) {
//        return id == null ? responseJson(FAILURE) : responseJson(SUCCESS, userService.getById(id));
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(User user) {
//
//        int result;
//        try {
//            if (user.getId() != null) {
//
//                // TODO
//
//                result = userService.update(user);
//            } else {
//
//                // TODO
//
//                result = userService.add(user);
//            }
//        } catch (Exception e) {
//            log.error("save user fail!", e);
//            return responseJson(ERROR);
//        }
//
//        return result > 0 ? responseJson(SUCCESS) : responseJson(FAILURE);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/del", method = RequestMethod.POST)
//    public String del(@RequestParam(value = "id, required = false") Long id) {
//        return id == null ? responseJson(FAILURE) :
//                (userService.delById(id) != 1 ? responseJson(FAILURE) : responseJson(SUCCESS));
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public String getRest(@PathVariable(value = "id") Long id) {
//        return id == null ? responseJson(FAILURE) : responseJson(SUCCESS, userService.getById(id));
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public String delRest(@PathVariable(value = "id") Long id) {
//        return id == null ? responseJson(FAILURE) :
//                (userService.delById(id) != 1 ? responseJson(FAILURE) : responseJson(SUCCESS));
//    }
//
    @ResponseBody
    @RequestMapping(value = "/tx")
    public String testTx(HttpServletRequest request, HttpServletResponse response) {
        try {
            userService.getAdd();
            return responseJson(ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.error("tx exception", e);
            return responseJson(ResponseCode.ERROR);
        } finally {
            log.info("finally");
        }
    }
}
