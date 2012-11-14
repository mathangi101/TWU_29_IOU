package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Bill;
import com.thoughtworks.twu.service.BillService;
import com.thoughtworks.twu.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BillController {

    private BillService billService;

    ConnectionService connectionService;

    @Autowired
    public BillController(BillService billService, ConnectionService connectionService) {
        this.billService = billService;
        this.connectionService = connectionService;
    }

    public BillController() {
    }


    @RequestMapping(value = "/add-bill", method = RequestMethod.POST)
    public ModelAndView billPage(@RequestParam(value = "descriptionItem",required = false) String description,
                                 @RequestParam(value = "amountItem",required = false) String amount) {
        String notificationMessage;
        String descriptionMessage;
        String amountMessage;
        ModelAndView modelAndView = new ModelAndView("/add-bill");
        if (!(description.isEmpty() || amount.isEmpty())) {

          double billAmount =  Double.parseDouble(amount);
            Bill bill = new Bill(description, billAmount);
            saveBill(bill);
             descriptionMessage = "Description: "+description;
            amountMessage = "Amount: "+amount;
            notificationMessage = " Saved Successfully.";
            modelAndView.addObject("descriptionMessage",descriptionMessage).addObject("amountMessage",amountMessage).addObject("notificationMessage",notificationMessage);
        }
        else
        {
            notificationMessage = "Incorrect Information";
            modelAndView.addObject("notificationMessage",notificationMessage);
        }
        return modelAndView;
    }


    private boolean saveBill(Bill bill) {
        try{
            billService.insertBill(bill);
            return true;
        } catch (Exception ex){
            return false;
        }
    }


    @RequestMapping(value = "/add-bill", method = RequestMethod.GET)
    public ModelAndView listOfAllConnections(String userEmail) {
        ModelAndView modelAndView = new ModelAndView("/add-bill");
        List<String> allConnections= connectionService.getAllConnections(userEmail);
        modelAndView.addObject("allConnections", allConnections);
        return modelAndView;
    }

//    @RequestMapping(value = "/add-bill")
//    public ModelAndView listOfAllConnections(@RequestParam(value = "user") String userEmail) {
//        ModelAndView modelAndView = new ModelAndView("/add-bill");
//        ArrayList<String> allConnections= connectionService.getAllConnections(userEmail);
//        modelAndView.addObject("allConnections", allConnections);
//        return modelAndView;
//    }

}

