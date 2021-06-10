# Pets
## Introduction
A basic app which can store data permanently in Android. Users can store name, breed, gender and the age of the pet, and then show them in a list.
## Technology Used
- This app works on the SQlite database on Android.
- All data fetching is done using ContentProviders, this prevents the user directly accessing the database. This helps to prevent adding ghost data (like empty names or breed).
- The loading of data is done using CursorLoaders, this makes the loading task to be performed in a background thread, and prevents load on the main thread.
- The data is then finally loaded into CursorAdapter.
## Features
- Store name, breed, gender and weight of a pet
- The saved pets are shown in the MainActivity
- Edit a previously saved pet
- Delete a pet
- Delete all pets from the database
- Add dummy data
