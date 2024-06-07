//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[TimedLegDto](index.md)

# TimedLegDto

[androidJvm]\
@Xml(name = &quot;ojp:TimedLeg&quot;)

data class [TimedLegDto](index.md)(@Element(name = &quot;ojp:LegBoard&quot;)val board: [LegBoardDto](../-leg-board-dto/index.md)? = null, @Element(name = &quot;ojp:LegAlight&quot;)val alight: [LegAlightDto](../-leg-alight-dto/index.md)? = null, @Element(name = &quot;ojp:Service&quot;)val service: [ServiceDto](../-service-dto/index.md))

## Constructors

| | |
|---|---|
| [TimedLegDto](-timed-leg-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;ojp:LegBoard&quot;)board: [LegBoardDto](../-leg-board-dto/index.md)? = null, @Element(name = &quot;ojp:LegAlight&quot;)alight: [LegAlightDto](../-leg-alight-dto/index.md)? = null, @Element(name = &quot;ojp:Service&quot;)service: [ServiceDto](../-service-dto/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [alight](alight.md) | [androidJvm]<br>val [alight](alight.md): [LegAlightDto](../-leg-alight-dto/index.md)? = null |
| [board](board.md) | [androidJvm]<br>val [board](board.md): [LegBoardDto](../-leg-board-dto/index.md)? = null |
| [service](service.md) | [androidJvm]<br>val [service](service.md): [ServiceDto](../-service-dto/index.md) |
