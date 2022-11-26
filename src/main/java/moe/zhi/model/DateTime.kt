package moe.zhi.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/7/3
 */
open class DateTime {
    @JsonProperty("\$id")
    var id: String? = null
    var currentDateTime: String? = null
    var currentFileTime: String? = null
    var ordinalDate: String? = null
    var timeZoneName: String? = null
}