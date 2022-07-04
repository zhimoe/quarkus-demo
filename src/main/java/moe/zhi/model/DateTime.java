package moe.zhi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/7/3
 */
@Data
public class DateTime {
    @JsonProperty("$id")
    String id;
    String currentDateTime;
    String currentFileTime;
    String ordinalDate;
    String timeZoneName;
}
