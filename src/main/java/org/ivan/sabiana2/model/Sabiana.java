package org.ivan.sabiana2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

/**
 * The type Sabiana.
 */
@Entity
@Setter
@Getter
public class Sabiana {

  @Id
  private String address;
  private String name;
  private String unitType;
  private long lastUpdate;
  private boolean on;
  private String mode;
  private String fan;
  private boolean autoModeAvailable;
  private boolean requestThermo;
  private boolean withActiveAlarms;
  private List<String> activeAlarms;
  private double setPointHeating;
  private double setPointCooling;
  private double setPointAutoMode;
  private double setPointAutoModeRange;
  private double setPointHeatingMin;
  private double setPointHeatingMax;
  private double setPointCoolingMin;
  private double setPointCoolingMax;
  private boolean lockAllFeatures;
  private boolean lockOnOff;
  private boolean lockMode;
  private boolean lockSet;
  private boolean lockFan;
  private boolean slave;
  private String controllerType;
  private String flap;
  private double t1;
  private double t2;
  private double t3;



}
