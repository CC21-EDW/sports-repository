package com.baloise.open.sportsrepository.domain.sports;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Activity {

  private String id;

  private String name;

  private BigDecimal distance;

  /**
   * Type of activity like running.
   */
  private java.lang.String type;
  /**
   * Timezone where the activity took place - important for times.
   */
  private java.lang.String timezone;
  /**
   * Amount of time in seconds the activity took place while being in motion.
   */
  private java.lang.Integer movingTime;
  /**
   * Amount of time in seconds the activity took place.
   */
  private java.lang.Integer elapsedTime;
  /**
   * Date when activity started.
   */
  private java.lang.Long startDate;

}
