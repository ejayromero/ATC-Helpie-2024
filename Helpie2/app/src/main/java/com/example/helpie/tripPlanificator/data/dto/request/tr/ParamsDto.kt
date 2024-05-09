package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
@Xml(name = "Params")
data class ParamsDto (
    @PropertyElement(name = "IncludeTrackSections")
    val track: String? = null,

    @PropertyElement(name = "IncludeTurnDescription")
    val turn: String? = null,

    @PropertyElement(name = "IncludeIntermediateStops")
    val intermediate: String? = null

)