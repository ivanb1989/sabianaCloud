package org.ivan.sabiana2.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.ivan.sabiana2.model.Sabiana;
import org.ivan.sabiana2.util.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Service
public class SabianaService {


  public ModelAndView allFancoil() {

    String json = callHttp(Constant.API_KEY_SABIANA, MediaType.APPLICATION_JSON,
        Constant.API_URL_SABIANA_VENT, HttpMethod.GET, null);

    // Ora puoi deserializzare il JSON in una mappa utilizzando Jackson
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      // Deserializza il JSON in una mappa chiave-valore

      List<Map<String, Object>> responseList = objectMapper.readValue(json,
          new TypeReference<List<Map<String, Object>>>() {
          });

      Map<String, Object> ventUnit = (Map<String, Object>) responseList.get(0);

      ArrayList<?> uni3 = (ArrayList<?>) ventUnit.get("units");

      Map<String, Object> fan0 = (Map<String, Object>) uni3.get(0);
      Map<String, Object> fan1 = (Map<String, Object>) uni3.get(1);
      Map<String, Object> fan2 = (Map<String, Object>) uni3.get(2);

      ModelAndView mav = new ModelAndView("allFancoil");
      mav.addObject("fan0", fan0);
      mav.addObject("name0", fan0.get("name"));
      mav.addObject("fan1", fan1);
      mav.addObject("name1", fan1.get("name"));
      mav.addObject("fan2", fan2);
      mav.addObject("name2", fan2.get("name"));

      return mav;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }


  public ModelAndView singleFancoil(String ambiente) {
    String url = Constant.API_URL_SABIANA;

    url = getAmbiente(ambiente, url);

    String json = callHttp(Constant.API_KEY_SABIANA, MediaType.APPLICATION_JSON,
        url, HttpMethod.GET, null);

    ObjectMapper objectMapper = new ObjectMapper();
    try {

      Map<String, Object> responseMap = objectMapper.readValue(json,
          new TypeReference<Map<String, Object>>() {
          });
      String name = (String) responseMap.get("name");

      Map<String, Object> ventUnit = (Map<String, Object>) responseMap.get("ventUnit");

      ModelAndView mav = new ModelAndView("fancoil");
      mav.addObject("name", name.toUpperCase());

      mav.addObject("nameUnite", ambiente.toUpperCase());
      mav.addObject("ventUnit", ventUnit);

      return mav;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }


  public ModelAndView setFacoilEndPoint(boolean mode, /*String fan, int setPoint,*/
      String ambiente) {
    ModelAndView mav = new ModelAndView("setFancoil");

    return mav;
  }

  public ModelAndView setFancoil(boolean onOff, String fan, int setPoint, String flap,
      String ambiente) {
    String on_Off = (onOff) ? "true" : "false";

    String requestBody = "{\n" +
        "  \"on\": true,\n" +
        "  \"mode\": \"heating\",\n" +
        "  \"fan\": \"V2\",\n" +
        "  \"setPoint\": 26,\n" +
        "  \"flap\": \"full\"\n" +
        "}";

    String url = getAmbiente(ambiente, Constant.API_URL_SET_FANCOIL);

    String string = callHttp(Constant.API_KEY_SABIANA, MediaType.APPLICATION_JSON, url,
        HttpMethod.POST, requestBody);
    return singleFancoil(ambiente);
  }


  private String callHttp(String apiKey, MediaType mediaType, String url, HttpMethod method,
      String body) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("auth", apiKey);
    headers.setContentType(mediaType);
    HttpEntity<String> entity =
        (body != null) ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> exchange = restTemplate.exchange(url, method, entity, String.class);
    return exchange.getBody();
  }

  private static String getAmbiente(String ambiente, String url) {
    switch (ambiente.toLowerCase()) {

      case "camera":
        url += Constant.API_URL_SABIANA_CAMERA;
        break;
      case "cameretta":
        url += Constant.API_URL_SABIANA_CAMERETTA;
        break;
      case "sala":
        url += Constant.API_URL_SABIANA_SALA;
        break;
    }
    return url;
  }


  private Sabiana saveSabiana(Map<String, Object> map, Sabiana sabiana) {

    if (map.containsKey("address")) {
      sabiana.setAddress((String) map.get("address"));
      sabiana.setName((String) map.get("name"));
      sabiana.setUnitType((String) map.get("unitType"));
      sabiana.setLastUpdate((long) map.get("lastUpdate"));
    } else {
      sabiana.setOn((Boolean) map.get("on"));
      sabiana.setMode((String) map.get("mode"));
      sabiana.setFan((String) map.get("fan"));
      sabiana.setAutoModeAvailable((Boolean) map.get("autoModeAvalible"));
      sabiana.setRequestThermo((Boolean) map.get("requestThermo"));
      sabiana.setWithActiveAlarms((Boolean) map.get("withActiveAlarms"));
      sabiana.setActiveAlarms((List<String>) map.get("activeAlarms"));
      sabiana.setSetPointHeating((Double) map.get("setPointHeating"));
      sabiana.setSetPointCooling((Double) map.get("setPointCooling"));
      sabiana.setSetPointAutoMode((Double) map.get("setPointAutoMode"));
      sabiana.setSetPointHeatingMin((Double) map.get("setPointHeatingMin"));
      sabiana.setSetPointHeatingMax((Double) map.get("setPointHeatingMax"));
      sabiana.setSetPointCoolingMin((Double) map.get("setPointCoolingMin"));
      sabiana.setSetPointCoolingMax((Double) map.get("setPointCoolingMax"));
      sabiana.setLockAllFeatures((Boolean) map.get("lockAllFeatures"));
      sabiana.setLockOnOff((Boolean) map.get("lockOnOff"));
      sabiana.setLockMode((Boolean) map.get("lockMode"));
      sabiana.setLockSet((Boolean) map.get("lockSet"));
      sabiana.setLockFan((Boolean) map.get("lockFan"));
      sabiana.setSlave((Boolean) map.get("slave"));
      sabiana.setControllerType((String) map.get("controllerType"));
      sabiana.setFlap((String) map.get("flap"));
      sabiana.setT1((Double) map.get("t1"));
      sabiana.setT2((Double) map.get("t2"));
      sabiana.setT3((Double) map.get("t3"));
    }
    return sabiana;
  }

  private List<String> getFieldEntity(Class<?> entity) {

    Field[] fields = entity.getDeclaredFields();

    // Converti i campi in una lista di nomi
    List<String> fieldNames = new ArrayList<>();
    for (Field field : fields) {
      fieldNames.add(field.getName());
    }
    return fieldNames;

  }


}
