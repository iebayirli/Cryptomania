# Cryptomania

Cryptomania is a cryptocurrency tracking application. [The CoinGecko crypto API](https://www.coingecko.com/en/api) has been used to get the latest prices and histories for coins.

## Screenshots

<p align="middle">
  <img width="250" src="https://raw.githubusercontent.com/iebayirli/Cryptomania/master/screenshots/ss1.jpg">
  <img width="250" src="https://raw.githubusercontent.com/iebayirli/Cryptomania/master/screenshots/ss2.jpg">
  <img width="250" src="https://raw.githubusercontent.com/iebayirli/Cryptomania/master/screenshots/ss3.jpg">
  <img width="250" src="https://raw.githubusercontent.com/iebayirli/Cryptomania/master/screenshots/ss4.jpg">
  <img width="250" src="https://raw.githubusercontent.com/iebayirli/Cryptomania/master/screenshots/ss5.jpg">
</p>

## Architecture and features
MVVM (Model-View-ViewModel) architecture pattern and data binding is used in the development of this application. The development language of the application is Kotlin. The latest technologies have been tried to be used in the development of this application.

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
