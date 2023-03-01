package com.example.Workshop24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Workshop24.model.OrderDetails;
import com.example.Workshop24.model.Orders;
import com.example.Workshop24.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    OrderService orderService;
    
    @GetMapping(path = "/")
    public String index() {
        // session.invalidate();
        return "index";
    }

    @GetMapping("/shop")
    public String shop(@ModelAttribute OrderDetails orderDetails, Model model, HttpSession session) {
        
        Orders totalOrder = (Orders) session.getAttribute("totalOrder");
        
        // if (null == totalOrder) {
        //     session.setAttribute("totalOrder", new Orders());
        // } else {
        //     totalOrder.getOrders().add(orderDetails);
        // }
        // System.out.println(orderDetails);
        model.addAttribute("totalOrder", totalOrder);
        model.addAttribute("orderDetails", new OrderDetails());
        return "shop";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute OrderDetails orderDetails, Model model, HttpSession session) {
        
        Orders totalOrder = (Orders) session.getAttribute("totalOrder");
        
        if (null == totalOrder) {
            session.setAttribute("totalOrder", new Orders());
            totalOrder = (Orders) session.getAttribute("totalOrder");
        }
        
        totalOrder.getOrders().add(orderDetails);
        // session.setAttribute("totalOrder", totalOrder);
        
        // model.addAttribute("totalOrder", totalOrder);
        // model.addAttribute("order", new Orders());

        return "redirect:/shop";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Orders totalOrder = (Orders) session.getAttribute("totalOrder");
        model.addAttribute("totalOrder", totalOrder);
        model.addAttribute("orders", new Orders());
        return "checkout";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Orders orders, HttpSession session) {
        Orders totalOrder = (Orders) session.getAttribute("totalOrder");
        totalOrder.setCustomerName(orders.getCustomerName());
        totalOrder.setShipAddress(orders.getShipAddress());
        totalOrder.setNotes(orders.getNotes());
        
        session.setAttribute("totalOrder", totalOrder);

        // commit orders to db
        orderService.commitOrder(totalOrder);

        // update session
        session.setAttribute("orderStatus", "submitted");

        return "redirect:/confirm";
    }

    @GetMapping("/confirm")
    public String confirm(HttpSession session, Model model) {

        String orderStatus = (String) session.getAttribute("orderStatus");

        if(!orderStatus.equalsIgnoreCase("submitted")) {
            return "redirect:/shop";
        }
        
        session.invalidate();
        model.addAttribute("orderStatus", orderStatus);
        return "confirm";
    }
}
