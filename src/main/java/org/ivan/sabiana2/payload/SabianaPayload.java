package org.ivan.sabiana2.payload;

import lombok.Data;

@Data
public class SabianaPayload {
  private String name;
  private boolean on;
  private boolean requestThermo;
  private double setPointHeating;
  private double setPointCooling;
  private double t1;
  private double t3;
}
