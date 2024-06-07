//[app](../../../index.md)/[com.example.helpie](../index.md)/[Localisation](index.md)

# Localisation

[androidJvm]\
data class [Localisation](index.md)(val destinationName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val destinationAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null, val latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null, val isValid: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)

Data class representing a location.

## Constructors

| | |
|---|---|
| [Localisation](-localisation.md) | [androidJvm]<br>constructor(destinationName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, destinationAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null, latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null, isValid: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) |

## Properties

| Name | Summary |
|---|---|
| [destinationAddress](destination-address.md) | [androidJvm]<br>val [destinationAddress](destination-address.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The address of the destination. |
| [destinationName](destination-name.md) | [androidJvm]<br>val [destinationName](destination-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The name of the destination. |
| [isValid](is-valid.md) | [androidJvm]<br>val [isValid](is-valid.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if the location is valid. (according to google maps API) |
| [latitude](latitude.md) | [androidJvm]<br>val [latitude](latitude.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null<br>The latitude of the location. |
| [longitude](longitude.md) | [androidJvm]<br>val [longitude](longitude.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null<br>The longitude of the location. |
