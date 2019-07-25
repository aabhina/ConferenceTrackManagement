For running the app, run the class named ConferenceScheduleMain.java
Please provide a correct path for the input text file, inside this class, for the variable named "pathToInputTalksListFile".


Basic logic as follows :
First calculate the number of tracks required by extracting/processing the  input from the given information in the text file.
Calculate the total no. of minutes available for talk on each track. Then calculate the total minutes of talks needed to be scheduled on these tracks. Then calculate that how many morning and afternoon sessions would be required. Based on the above data, we  calculate the number of tracks needed.
Then we sort all the individual talks in descending order of their time duration.
Then we schedule a talk in a track by picking the talks one by one and putting it into the morning/afternoon session.
Then we pick all the talks which are now with the associated track number & process that information and output the conference object.

Basic building blocks for the solution :
Track class —> Day 1/2/3 etc.
Slot class —> Morning/Afternoon
Event class —> Any talk/lunch/networking is an event, which will be slotted into a particular time duration, in any of the available slots, in any of the tracks. 
Lunch & Networking class —> extend the Event class, a particular fixed type of event.
Talk class —> Each individual talk/lecture.
Conference class —> Overall structure of the conference, which will be finally printed out as the output.