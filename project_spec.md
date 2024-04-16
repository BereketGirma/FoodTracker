# **LifeGoalTracker**

## Table of Contents

1. [App Overview](#App-Overview)
3. [Product Spec](#Product-Spec)
4. [Wireframes](#Wireframes)
5. [Build Notes](#Build-Notes)

## App Overview

### Description 

LifeGoalTracker is an intuitive and user-friendly Android app designed to help individuals stay on track with their fitness and nutrition goals.

The app allows users to input their desired fitness objectives, record the types and amounts of food they consume, and log their physical activities. It automatically calculates the calories consumed and burned, updating the user on the remaining calorie allowance for the day. The app's clean interface, featuring a top toolbar for quick calorie insights and a bottom toolbar for navigation, promotes a seamless user experience. With a profile section for personalized settings and a recycler view taht neatly categorizes meal entries into breakfast, lunch, and dinner, LifeGoalTracker is the perfect digital companion for anyone looking to manage their diet, monitor their exercise routines, and achieve their lifestyle ambitions with precision and ease.

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:** Health and Fitness
- **Mobile:** The app is indispensable for tracking fitness goals on the go. Utilizing the smartphone's capabilities, users can log food and exercise immediately, ensuring accuracy and convenience. 
- **Story:** LifeGoalTracker empowers users to set and achieve fitness goals through meticulous tracking and calculations of caloric intake and expenditure. It supports a balanced approach to diet and exercise, promoting healthy living.
- **Market:** Designed for fitness enthusiasts, individuals aiming for weight management, and anyone who seeks a structured and quantifiable approach to their health goals.
- **Habit:** The app is intended for daily use, with users updating their food intake and exercise output regularly to get real-time insights into their progress and remaining caloric allowance.
- **Scope:** V1 would offer basic functionality to set goals, log food and exercise, and track remaining calories. V2 could introduce a database of foods and exercises with preset caloric values for easier logging. V3 might include integration with wearable devices for automatic exercise tracking and an AI-based suggestion system for meal and workout plans.

## Product Spec

### 1. User Features (Required and Optional)

Required Features:

<!-- - An Input(EditText) where users can enter the name of the food and the app will display the number of calories of that food.
- Calculate the suggested amount of calories per day based on age and weight. -->
- **Calorie Tracking Toolbar** A section at the top that allows users to quickly view their daily calorie goals, food intake, exercise, and remaining calories. This should update in real-time as users add or subtract from their intake and expenditure.
- **Meal Logging** An interface to input meals, including breakfast, lunch, and dinner. Each meal entry should allow for detailed information such as food name, portion size, and calorie count.
- **Exercise Logging** A feature to log exercises with details such as type of activity, duration, and calories burned.
- **User Profile** A dedicated area for users to set up their fitness goal, dietary preferences, and other personal information that could affect calorie needs.

Stretch Features:

<!-- - When entering a food or an exercise, the app will automatically calculate the number of calories consumed or burned. -->
- **Integrated Food Database** An expansive database of foods with pre-calculated calorie and nutritional information that users can search and add to their daily intake.
- **AI Meal and Exercise Planner** Using AI to suggest Food and Exercise.
- **Daily Streak Challenge** Including challenges, badges and rewards to increase users' engagement and motivation.

### 2. Chosen API(s)

- https://api-ninjas.com/api/nutrition
  - Automatically detects the name of the food from the query.
  - Returns the number of calories of a food and other nutritional facts.
- https://api-ninjas.com/api/caloriesburned
  - Automatically detects the exercise from the query.
  - Returns the number of calories burned from doing the exercise for an hour.

### 3. User Interaction

Required Feature

- Enter age and weight
  - The app will calculate the suggested daily calories intake.
- Enter name of food eaten
  - The app will return the number of calories of the food, and add it to today's calories.
- Enter name of exercise
  - The app will return the number of calories burned from doing the exercise, and subtract it from today's calories.

## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->

### [BONUS] Digital Wireframes & Mockups
<img src="https://github.com/BereketGirma/FoodTracker/assets/145808743/61b83a1b-4594-44ea-9bd4-c061e557cdbd" width=250>

<img src="https://github.com/BereketGirma/FoodTracker/assets/145808743/c9fc6dc5-cbdb-4812-b7ce-b17bbc1d659d" width=250>

### [BONUS] Interactive Prototype
<img src="https://github.com/BereketGirma/FoodTracker/assets/145808743/57a58a01-c009-47f0-905c-f842ee83cc27" width=250>

## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!

## License

Copyright **2024** **CodePath-AND101-Group 23**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
