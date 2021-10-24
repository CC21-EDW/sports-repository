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
}
