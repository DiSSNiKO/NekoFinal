-- NEKO FITNESS --

The NekoFitness app aims to aid the user in their fitness journey, every little bit counts.
The app allows one to fetch dozens of exercises via an API call
from an online database at a click of a button. Said exercises come with comprehensive intructions for ease of use.

Create routines - The user can choose their favourite/contextually appropriate exercises and bundle
them together in a routine for easy access later. Routines will be stored locally on their device.

Our app also allows the user to track their caloric intake, also with the use of an extensive API.
The calorie calculator sums up major macronutrients and caloric content from meals provided
- displaying them in a concise manner.
If the app is minimized, upon date change, a broadcast receiver will automatically delete all daily nutrition logs
and add them to the archive table, the user can opt to do this manually.

NekoFitness Technical:

--Storage solution SQLiteOpenHelper
NekoFitness uses an SQLite Database (SQLiteOpenHelper DB) to manage app storage on the device locally.

There are 3 tables:
Routine table -> functionality deletion and insertion
Daily nutrition log table (Temporary) -> functionality same as above
Archive nutrition log table (Logs by date) -> functionality same as above

for further details, DB class can be found in com.example.nekofitness > database

--BroadCast Receiver
As stated in the description, NekoFitness utilizes a dynamic broadcast receiver to perform database DML
operations on both nutrition log tables, mainly it cleans the temporary and updates the archive table
when a date change is detected

for further details check com.example.nekofitness > broadCastRecievers

--RETROFIT (API)
NekoFitness uses the Retrofit library to gain access to APIs. Two APIs are used in this app
both from api-ninjas.com: Exercises, Nutrition.
Retrofit functionality details are found in com.example.nekofitness > RetrofitAPI

--Navigation
NekoFitness boasts a single activity infrastructure, meaning all navigation and page functionality
is delegated to fragments. We have implemented a viewPager and bottom navigation hybrid for ease of use.
There are a total of five fragments in nekofitness

--Miscellaneous
Our app also user recyclerviews for dynamic layouts (sometimes)
ViewModels are used for reactive layouts, for when the user updates a database table
DataClasses are used for both API data fetching and saving to Database.

