package org.ivan.sabiana2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ivan.sabiana2.service.SabianaServiceMev;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Sabiana controller.
 */
@RestController
@RequestMapping("mev")
public class SabianaControllerMev {

  private final SabianaServiceMev sabianaServicemev;

  /**
   * Instantiates a new Sabiana controller.
   *
   * @param sabianaServicemev the sabiana service
   */
  public SabianaControllerMev(SabianaServiceMev sabianaServicemev) {
    this.sabianaServicemev = sabianaServicemev;

  }


  /**
   * Sabiana model and view.
   *
   * @return the model and view
   */
  @GetMapping("/allFancoil")
  public ModelAndView sabiana() {

    return sabianaServicemev.allFancoil();
  }

  /**
   * Single fancoil model and view.
   *
   * @param ambiente the ambiente
   * @return the model and view
   */
  @GetMapping("/fancoil")
  public ModelAndView singleFancoil(@RequestParam String ambiente) throws JsonProcessingException {
    return sabianaServicemev.singleFancoil(ambiente);
  }

  /**
   * Index model and view.
   *
   * @return the model and view
   */
  @GetMapping("/index")
  public ModelAndView index() {
    return new ModelAndView("index");
  }

  /**
   * Sets fancoil.
   *
   * @param onOff    the on off
   * @param fan      the fan
   * @param setPoint the set point
   * @param ambiente the ambiente
   * @return the fancoil
   */
  @GetMapping("/setFancoil")
  public ModelAndView setFancoil(@RequestParam boolean onOff,String mode, String fan, int setPoint, String ambiente)
      throws JsonProcessingException {

    return sabianaServicemev.setFancoil(onOff,mode, fan, setPoint, ambiente);
  }


}
