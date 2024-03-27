package org.ivan.sabiana2.controller;
import org.ivan.sabiana2.service.SabianaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SabianaController {

    private final SabianaService sabianaService;

    public SabianaController(SabianaService sabianaService) {
        this.sabianaService = sabianaService;

    }


    @GetMapping("/allFancoil")
    public ModelAndView sabiana() {
        return sabianaService.allFancoil();
    }

    @GetMapping("/fancoil")
    public ModelAndView singleFancoil(@RequestParam String ambiente){
        return sabianaService.singleFancoil(ambiente);
    }
    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

@GetMapping("/setFancoil")
public ModelAndView setFancoil(@RequestParam boolean onOff,/*String fan,int setPoint,String flap,*/ String ambiente) {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("onOff", onOff);
        mav.addObject("ambiente", ambiente);
        String fan = "V2";
        int setPoint = 22;
        String flap = "V2";

    return sabianaService.setFancoil(onOff,fan,setPoint,flap,ambiente);
}

}
