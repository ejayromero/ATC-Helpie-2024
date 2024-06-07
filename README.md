# ATC-Helpie-2024
Git repository of the App Helpie for ATC project 2024


## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Structure](#structure)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Contribution](#contribution)
6. [License](#license)

## Introduction
Travelling and mobility are essential in our society. Without them, we would not be able to dis-
cover new places, peoples and ideas. We would not be able to develop and construct society and
life projects. As easy mobility is taken for granted by most of us - thanks to cars, bikes and public
transportation, etc. - we do not always realize its importance. However for some people, mobility
is difficult, which heavily impairs their independence.

In Switzerland, the SBB (Swiss Federal Railway) Mobile App is the most widely used applica-
tion to help travelling through public transportation. It features a lot of useful functionalities such
as travel planning, real time information about schedule and location as well as ticket buying. How-
ever, this makes the application complex and inaccessible for certain people.

As the SBB are concerned by inclusiveness they have developed a second application: ”SBB Inclu-
sive” providing additional and adapted information for people with visual or auditory impairment.
In this project, we will concentrate on a different kind of condition: cognitive impairment. For
individuals experiencing cognitive challenges,the complexity and high density of stimuli found in
the public transportation system and SBB Mobile App can be overwhelming.

The Helpie App is an idea that was initially brought by P lusSport: an association that tries
to gather people with all kinds of disabilities in sport events. They realized that one of the biggest
challenges for people with cognitive impairment was the transportation of the different people to-
wards the site of the event and that the SBB Mobile App was not adequately adapted. They
therefore brought the concept of a simplified and adapted app to the Hackathon, a project that was
made in collaboration with several railway companies like the SBB, but also the SNCF, OBB and
the DB (respectively the french, austrian and german railway biggest representatives). Within a
week-end, they developed some ideas and the mock-up of an app.

Helpie then eventually landed in our hands: the Assistive Technology Challenge, an EPFL-project
whose primary goal is to develop a personalized solution for a person with disabilities. We were
assigned a challenger that lies on the autistic spectrum, let’s name him Charlie, and regularly met
with him to adapt the progress of Helpie.

## Features
OurChallenger wanted an application with as few stimuli as possible, but with enough information to do a full journey. The following design choices were therefore taken in order to develop a simple app that would guide him through a journey:

1. **Global Theme**: We decided to keep the blue chosen by the first HackaHealth for the global theme. We aimed to create a clean and straightforward interface. Every element is designed to be easily understandable and accessible thanks to the color contrasts and the button shapes.

2. **Start Screen**: A start screen leads the user to the choice of destination. For starting the trip, a set of four pre-registered relevant addresses are displayed, like "Home", "Work", or "Sport", so that he does not have to bother with entering the address. An additional feature would, however, permit to do so if needed.

3. **Journey Summary**: The journey summary is displayed with the estimated time of arrival and a description of each step. To follow on the journey, the user needs to click on the START button ("Commencer"). There is also a grey Back button ("retour") if one wants to change the destination, e.g., because of a previous misclick.

4. **Take a Ticket**: We have two interfaces to remind the user to take their ticket. The choice of this interface can be made using the settings. The first screen that we decided to implement tells the user to take an "easy-ride" ticket using the official SBB application. By clicking on "Prendre le billet," the user will be transferred to the SBB app directly in the easy-ride section and only needs to drag the button to the right (explained on the screen). The ticket will automatically be taken and calculate its price by looking at the ongoing trip. However, if Charlie already has a ticket, in those situations, we activate the other version of this interface, which reminds him to stamp his ticket.

5. **Step-by-Step Instruction**: A step-by-step journey, where we try to detail the trip as much as possible while giving only the necessary information each time to not saturate the screen with too many stimuli. Therefore, all steps are given in chronological order, one-by-one where the only possible interactions are to go forward through the trip or to interrupt the journey.

   * For walking travels, for example, to go to the next bus stop, Google Maps will be launched with the adequate destination. Charlie is quite used to dealing with satellite maps, especially with Google Maps when he walks and requested it.
   * For public transport, essential information is clearly displayed such as the waiting time, the ID, estimated time of arrival (ETA), direction of the transportation as well as the type of transportation.
6. **Appropriate Notifications and Warnings**: Two types of notifications have been implemented in order to remind Charlie to go back to Helpie if he uses another app as well as giving him the appropriate reminders to go in/out of transportation, for instance:

    * When leaving the app, a blue movable square will still be on the screen to remind the user of the ongoing travel and allow them to go back easily to Helpie (see fig. []). 
    * Two minutes before the arrival, a yellow notification will pop up to warn the user to be ready for the change of transport (see fig. []). The same type of notification will pop up when arriving at the destination of a checkpoint (e.g., the bus stop or train station).
7. **Help Button**: A help button is always displayed (see fig. []), so that the user can easily call someone if they happen to be in a dire or stressful situation.

8. **Settings**: On the menu page, a settings button is available (see fig. []) that will allow a close relative or the caregiver to personalize the user's experience. This button is for now accessible only on the start screen.

9. **Progress Bar**: To help the user engage and use the app, a gamified progress bar was added to the display (see fig. []).

## Structure
```
ATC-Helpie-2024/Helpie2/app/src/main/java/com/example/helpie
│
└───foregroundServices (Notification and window)
│   │
│   └───ForegroundService (Notification and window)
│
└───tripPlanificator (Trip Planification API)
│   │
│   └───OjpSdk
│   │
│   └───tripHandling.kt (Trip Planification API)
│   │
│   └───...
│
└───ui
│   │
│   └───DestinationScreen.kt 
│   │
│   └───FinalScreen.kt
│   │
│   └───HelpieViewModel 
│   │
│   └───HelpScreen.kt
│   │
│   └───InBusScreen.kt 
│   │
│   └───JourneyInTransportScreen.kt
│   │
│   └───OutBusScreen.kt
│   │
│   └───PopUpStop.kt
│   │
│   └───ReachStopScreen.kt
│   │
│   └───SettingsScreen.kt
│   │
│   └───StartScreen.kt
│   │
│   └───StepScreen.kt
│   │
│   └───SummaryScreen.kt
│   │
│   └───TakeTicketScreen.kt
│   │
│   └───TicketScreen.kt
│   │
│   └───WaitingTransport.kt
│   │
│   └───WalkScreen.kt
│   │
│   └───...
│
└───HelpieScreen.kt
│
└───MainActivity.kt
│
└───RunninApp
│
└───UiState.kt
│
└───...
``` 


## Installation
You can download this app by cloning this repository and opening it in Android Studio. Then build the gradle and run the app on an emulator or a physical device. 

## Usage
For usage instructions, please refer to the user guide provided in this repository.

## Documentation
The code is documented in Kdoc and cann be generated with Dokka. The .html is available in the .zip and the documentation is also available in markdown on documentation/index.md

## Contact Information
Your contact information or the contact information of your team.

## Members:
* Claire Payoux
* Eliser Josan Romero
* Jade Therras
* Lena Florence De Sepibus
