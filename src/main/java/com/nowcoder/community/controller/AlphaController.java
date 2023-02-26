package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {


    @Autowired
    private AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String sayhello(){
        return "hello world";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getDate(){
        return alphaService.find();
    }

//    mvc框架下获得请求响应对象
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        // 获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        // 得到请求行的key
        Enumeration<String> enumeration = request.getHeaderNames();
        // 是否还有更多的元素
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try(
                PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // get请求
    // /student?current=1&limit=20
    // 限制get类型
    @RequestMapping(path = "/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            // 请求参数为current的值, 不传也行，默认值为1
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        // 结果没有写路径也控制台也输出了数据
        System.out.println(current);
        System.out.println(limit);
        return "some strudent";
    }

    // 还有一个没写

    // post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String savestudent(String name, String age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应html数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    // 不加body默认html
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "zhangsan");
        mav.addObject("age", "30");
        mav.setViewName("/demo/view");
        return mav;
    }

    // 另一种，这种方法更方便
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    // 不加body默认html
    public String getSchool(Model model){
        model.addAttribute("name", "shitschool");
        model.addAttribute("age", "80");
        return "demo/view";
    }

    // 响应json数据，异步请求中
    // java对象 通过 jason 转成 js
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "yesehll");
        emp.put("age", "22");
        emp.put("salary", "17000.00");
        return emp;
    }

    // 所有员工
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "yesehll");
        emp.put("age", "22");
        emp.put("salary", "17000.00");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "yesehll2");
        emp.put("age", "22");
        emp.put("salary", "177000.00");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "yesehll3");
        emp.put("age", "24");
        emp.put("salary", "127000.00");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "yesehll4");
        emp.put("age", "22");
        emp.put("salary", "197000.00");
        list.add(emp);

        return list;
    }

}
