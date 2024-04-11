package org.ivan.sabiana2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.ivan.sabiana2.service.SabianaServiceApi;
import org.ivan.sabiana2.util.Ambienti;
import org.ivan.sabiana2.util.Fan;
import org.ivan.sabiana2.util.Mode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Sabiana controller.
 */
@RestController
@Validated
@RequestMapping("api")
public class SabianaControllerApi {

  private final SabianaServiceApi sabianaServiceApi;

  /**
   * Instantiates a new Sabiana controller.
   *
   * @param sabianaServiceApi the sabiana service
   */
  public SabianaControllerApi(SabianaServiceApi sabianaServiceApi) {
    this.sabianaServiceApi = sabianaServiceApi;

  }


  /**
   * Sabiana model and view.
   *
   * @return the model and view
   */
  @GetMapping("/allFancoil")
  public ResponseEntity<?> sabiana() {
    return sabianaServiceApi.allFancoil();
  }



  /**
   * Single fancoil model and view.
   *
   * @param ambiente the ambiente
   * @return the model and view
   */
  @GetMapping("/fancoil")
  public ResponseEntity<?> singleFancoil(@RequestParam Ambienti ambiente) throws JsonProcessingException {
    return sabianaServiceApi.singleFancoil(ambiente);
  }
  @GetMapping("/sabianaPayload")
  public ResponseEntity<?> sabianaPayload(@RequestParam Ambienti ambiente) throws JsonProcessingException {
    return sabianaServiceApi.sabianaPayload(ambiente);
  }
  @GetMapping("/allSabianaPayload")
  public ResponseEntity<?> sabianaPayload2() throws JsonProcessingException {
    return sabianaServiceApi.allSabianaPayload();
  }

  /**
   * Index model and view.
   *
   * @return the model and view
   */
  @GetMapping("/")
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
  public ResponseEntity<?> setFancoil(@RequestParam boolean onOff,Mode mode, Fan fan, @Max(30) @Min(10) int setPoint, Ambienti ambiente)
      throws JsonProcessingException {

    return sabianaServiceApi.setFancoil(onOff,mode, fan, setPoint, ambiente);
  }


}
