# Cryptomania

Cryptomania is a cryptocurrency tracking application that uses the latest technologies on Android. [The CoinGecko crypto API](https://www.coingecko.com/en/api) has been used to get the latest prices and histories for coins.

## Screenshots

## Architecture and features
MVVM (Model-View-ViewModel) architecture pattern and data binding is used in the development of this application. The development language of the application is Kotlin. 

Features;
- The user can view all coins information; such as name, symbol, current price, price change percentage etc.
- The user can view the selected coins details; hashing algorithm information, description, time interval change with chart table.
- The user can search for a specific coin by the coin ID in the search field.
- The user can group coins by adding them to the favourites.

## Libraries

* Architecture;
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding/)
    * [Live Data](https://developer.android.com/topic/libraries/architecture/livedata)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) 
    * [Navigation](https://developer.android.com/guide/navigation)
    * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)

* Third parties;
    * [Kotlin Coroutines (Kotlin flows)](https://developer.android.com/kotlin/flow)
    * [Moshi](https://github.com/square/moshi)
    * [Retrofit](https://github.com/square/retrofit)
    * [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
    * [Glide](https://bumptech.github.io/glide/)

Thank you.
