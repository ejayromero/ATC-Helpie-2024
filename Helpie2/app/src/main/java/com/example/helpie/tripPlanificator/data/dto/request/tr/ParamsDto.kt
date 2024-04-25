package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml
@Xml(name = "Params")
data class ParamsDto (
    @Element(name = "IncludeTrackSections")
    val track: String? = null,

    @Element(name = "IncludeTurnDescription")
    val turn: String? = null,

    @Element(name = "IncludeIntermediateStops")
    val intermediate: String? = null

)