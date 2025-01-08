# Schedule App

Unofficial Android schedule app for UEK students and staff made in Kotlin using Android Studio.

### Functionalities
- Shows current schedule for any group, teacher or classroom, accessing it from https://planzajec.uek.krakow.pl/ using the Retrofit library
- Allows storing schedules locally by adding them to the app's Room database
- The "favorited" schedules can then be displayed together as the user's personal schedule
- Allows to hide unwanted classes
- UI made with Material 3 

### To do
- Better displaying of hidden classes and more ways to hide them (by day of the week, by hour)
- Ability to mark classes as important
- Ability to change color scheme
- Improvements to general code quality in certain places

### Libraries used
- Android Jetpack libraries (Compose, Hilt, Room, etc.)
- Retrofit
- TikXml
