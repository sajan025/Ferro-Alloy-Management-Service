package com.jsp.ferro.alloy.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;





/**
 * @author Sajan
 *
 */
@Controller
public class HomeController {

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        modelAndView.setViewName("login");
        return modelAndView;
    }
    
    @GetMapping(value="/sms")
    public ModelAndView smsDashboard(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        modelAndView.setViewName("sms/sms-dashboard");
        return modelAndView;
    }
    
    @GetMapping(value="/smsHistory/{id}")
    public ModelAndView smsHistoryDashboard(Model model,@PathVariable Integer id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("qualitypkid",id);
        modelAndView.setViewName("sms/sms-history-dashboard");
        return modelAndView;
    }
    @GetMapping(value="/quality")
    public ModelAndView qualityDashboard(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        modelAndView.setViewName("quality/quality-dashboard");
        return modelAndView;
    }
    @GetMapping(value="/finance")
    public ModelAndView financeDashboard(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        modelAndView.setViewName("finance/finance-dashboard");
        return modelAndView;
    }
    @GetMapping(value="/financeHistory/{id}")
    public ModelAndView financeHistoryDashboard(Model model,@PathVariable Integer id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("qualitypkid",id);
        model.addAttribute("contextPath", request.getContextPath());
        modelAndView.setViewName("finance/finance-history-dashboard");
        return modelAndView;
    }
    
    @GetMapping(value="/costingHistory/{id}")
    public ModelAndView costingHistoryDashboard(Model model,@PathVariable Integer id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("qualitypkid",id);
        model.addAttribute("contextPath", request.getContextPath());
        modelAndView.setViewName("finance/costing-history-dashboard");
        return modelAndView;
    }
    @GetMapping(value="/qualityHistory/{id}")
    public ModelAndView qualityHistoryDashboard(Model model, HttpServletRequest request,@PathVariable String id, HttpSession session ){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("qualitypkid",id);
       // session.setAttribute("qualitypkid", id);
        modelAndView.setViewName("quality/quality-history-dashboard");
        
        return modelAndView;
    }
    
    @GetMapping(value="/admin")
    public ModelAndView adminDashboard(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        modelAndView.setViewName("admin/admin-dashboard");
       
        return modelAndView;
    }

}
