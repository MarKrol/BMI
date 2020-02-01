package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.camp.it.logic.Service;
import pl.camp.it.model.BMI;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BMIConroller {
    @Autowired
    Service service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String runWWW(){ return "index"; }

    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public String calc(){
        return "calculator";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(){
        return "find";
    }

    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    public String calculate(@RequestParam("sex") String value,
                                @RequestParam String mass,
                                @RequestParam String height,
                                @RequestParam String age,
                                Model model){

        if (service.correctData(value,mass,height,age,1)){
            model.addAttribute("list",service.bmiList);

            model.addAttribute("discussionresult",service.discussionResult(
                                                            service.bmiList.get(service.bmiList.size()-1).getBmi()));

            return "result";
        } else{
            model.addAttribute("incorrectInfo",service.incorrectInfo());
            return "incorrect";
        }

    }

    @RequestMapping(value ="/find", method = RequestMethod.POST)
    public String find(@RequestParam String id, Model model){
        List<BMI> temp = new ArrayList<>();
        BMI user;
        if ((user=service.findElement(id))!=null){
            if(service.correctData(user.getSex(),user.getMass(),user.getHeight(),user.getAge(),0)){
                temp.add(user);
                model.addAttribute("list",temp);

                model.addAttribute("discussionresult",service.discussionResult(temp.get(0).getBmi()));


                return "result";
            } else {
                model.addAttribute("incorrectInfo",service.incorrectInfo());
                return "incorrect";
            }
        } else {
            model.addAttribute("incorrectInfo",service.incorrectInfo());
            return "incorrect";
        }
    }
}
