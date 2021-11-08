package com.learn.test.controller;

import com.learn.test.service.FakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/fake")
public class FakeController {

    private FakeService fakeService;

    @Autowired
    public void setFakeService(FakeService fakeService) {
        this.fakeService = fakeService;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello spring boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return fakeService.findRecord();
    }

    // http request and response
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        // receive request data
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        // return response data
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>Web Page</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get request
    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current + "\n" + limit);
        return "students record";
    }

    // /student/123
    @GetMapping("/student/{id}")
    @ResponseBody
    public String getStudent(@PathVariable("id") String id){
        System.out.println(id);
        return "a student record";
    }

    // Post request
    @PostMapping("/student")
    @ResponseBody
    public String addStudent(String name, int age){
        System.out.println(name + "\n" + age);
        return "student added";
    }

    // Response html data
    @GetMapping("/teacher")
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "Alex");
        modelAndView.addObject("age", 30);
        modelAndView.setViewName("/demo/teacher");
        return modelAndView;
    }

    @GetMapping("/school")
    public String getSchool(Model model){
        model.addAttribute("name", "No.1 School");
        model.addAttribute("address", "China");
        return "/demo/school";
    }

    // response Json data, always used int asynchronous request
    // Java object -> Json String -> JS object
    @GetMapping("/employee")
    @ResponseBody
    public Map<String, Object> getEmployee(){
        Map<String, Object> employee = new HashMap<>();
        employee.put("name", "Alex");
        employee.put("age", 30);
        employee.put("salary", 8000.00);
        return employee;
    }

    @GetMapping("/employees")
    @ResponseBody
    public List<Map<String, Object>> getEmployees(){
        List<Map<String, Object>> employees = new ArrayList<>();

        Map<String, Object> employee = new HashMap<>();
        employee.put("name", "Alex");
        employee.put("age", 30);
        employee.put("salary", 8000.00);
        employees.add(employee);

        employee = new HashMap<>();
        employee.put("name", "Beatrix");
        employee.put("age", 28);
        employee.put("salary", 7500.00);
        employees.add(employee);

        employee = new HashMap<>();
        employee.put("name", "Carol");
        employee.put("age", 35);
        employee.put("salary", 9000.00);
        employees.add(employee);

        return employees;
    }
}
