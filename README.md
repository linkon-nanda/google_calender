This repository contains an automated test suite for Google Calendar using Java. The suite is designed to verify core functionalities like navigating to the calendar, adding, updating, and deleting tasks.
The goal of this automation is to ensure that key features of the Google Calendar web application function correctly. The tests are implemented in TestCases.java and triggered via App.java, the entry point of the automation code.

Technologies Used:
Java,
Selenium WebDriver,
TestNG,
Gradle

Test Cases:-

TestCase01: 
Verify Calendar Home Page.
Description:
Verify that the Google Calendar homepage loads correctly.
Test Steps:
Navigate to Google Calendar.
Verify that the current URL contains the word "calendar".
Expected Result:
The URL of the Calendar homepage contains "calendar".

TestCase02: 
Verify Calendar Navigation and Add Task
Description:
Switch to month view and add a task for the current date.
Test Steps:
Switch to the month view.
Click on the calendar to add a task.
Navigate to the "Tasks" tab.
Select today's date and enter task details:
Title: Crio INTV Task Automation
Description: Crio INTV Calendar Task Automation
Expected Result:
Calendar switches to month view and a new task is created successfully.

TestCase03: 
Verify the Task Updation.
Description:
Update an existing task and verify the new description.
Test Steps:
Click on an existing task.
Open task details.
Update the description to:
"Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application"
Verify that the new description is displayed.
Expected Result:
The task is updated successfully with the new description.

TestCase04:
Verify the Task Deletion.
Description:
Delete an existing task and confirm its deletion.
Test Steps:
Click on an existing task.
Open task details.
Verify the title of the task.
Delete the task.
Confirm that "Task deleted" message is displayed.
Expected Result:
The task is deleted and confirmation message "Task deleted" is shown.
