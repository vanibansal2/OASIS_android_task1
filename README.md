# OASIS Android Task 1 - Unit Converter App

## Objective
Build an Android app that converts values between common units of measurement (length, weight, temperature) based on user input.

## Tools Used
- Android Studio
- Java
- XML
- Spinner (Dropdown)
- RadioGroup

## Features
- 3 measurement categories: Length, Weight, Temperature
- Category selector (RadioButtons) that resets unit dropdowns accordingly
- Input field for numeric value with validation
- Source and target unit Spinners
- Convert button with instant result display
- Toast message for empty or invalid input
- Clean dark-themed UI

## Steps Performed
1. Created new Android Studio project with Empty Views Activity (Java)
2. Designed UI using ScrollView + LinearLayout with RadioGroup, EditText, Spinners
3. Created 3 custom drawable files for input, spinner, and result backgrounds
4. Implemented full conversion logic in MainActivity.java for Length (7 units), Weight (5 units), Temperature (3 units)
5. Added input validation with Toast messages
6. Tested on Android emulator (Pixel 6, API 34)

## Outcome
A fully working unit converter supporting Length (km, m, cm, mm, miles, feet, inches), Weight (kg, g, mg, lb, oz), and Temperature (Celsius, Fahrenheit, Kelvin) with accurate conversions and proper input handling.

## Live Demo
[unit-converter-by-evolving-vani.netlify.app](https://unit-converter-by-evolving-vani.netlify.app)

## Internship
Oasis Infobyte - Android Application Development Internship
