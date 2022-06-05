data <- read.csv(url("https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv"))

#Overview
#Climate change continues to be a pressing issue around the globe that has differential impacts (Links to an external site.) on individuals based on where they live and their financial status. In this assignment, you will use your data analysis and visualization skills to explore trends in CO2 emissions using data compiled by Our World In Data (Links to an external site.) (Links to an external site.). In doing so, you will:
#Work with a real world, messy dataset
#Choose your own direction for analysis, including which variables to analyze and compare
#Design and build a compelling interactive visualization to expose particular patterns in the data
#Create an interactive Shiny application website to share your results
#This assignment is more open-ended than previous ones -- you will be tasked with understanding the data itself, choosing the variables you want to analyze, and deciding the optimal way to write your code. 

#Instructions 
#As with previous assignments, follow this link (Links to an external site.) to create your own private repository for this assignment. This will automatically create a private repository which you will submit to Canvas as your assignment. You do not need to fork the repository (just clone it to your machine and begin working). Then, complete the steps outlined below by creating the necessary files.
#Once your application is built, make sure that you host it on https://www.shinyapps.io/ (Links to an external site.). 

#Data
#The data, which you will load from this GitHub repository(specifically the owid-co2-data.csv file). A few hints on working with it:
#Read the documentation: See the GitHub Repository to view the "Codebook" that explains the meaning of each variable. You'll need to understand what each variable represents, so read carefully!
#Beware of missing values: You don't need to worry about "imputing" these, but when choosing what to visualize, you will want to focus on a particular location and/or year that has sufficient data (note, this varies across the variables)
#Cross-reference their visual tool (Links to an external site.) (Links to an external site.): You may want to view their tool to check your work, or get inspired!

#Assignment structure
#For this assignment, you will create a multipage interactive shiny application that allows users to explore data about CO2 emissions, which must include:
#An introductory page (tab) that introduces the topic, and provides some summary values from the dataset
#An interactive visualization page where users can control a visualization using at least two Shiny widgets
#See below for additional information for each component. 

#Introduction Page
#As you introduce your small project, you should describe the variables that you've chosen to analyze. In doing so, make clear which measure(s) of CO2 emission you are focusing on. 
#Then, you will share at least 5 relevant values of interest. These will likely be calculated using your DPLYR skills, answering questions such as: 
#What is the average value of my variable across all the counties (in the current year)?
#Where is my variable the highest / lowest?
#How much has my variable change over the last N years?
#Feel free to calculate and report values that you find relevant. Importantly, you should calculate these values in your app_server.R file, and display them in your user interface using the appropriate method. 
#growth percentage
#share global co2
#Interactive Visualization Page
#On your second page (tab), you'll create an interactive visualization that is controlled by two or more Shiny widgets. A few requirements for this page:
#The chart must be interactive (e.g., display information when you hover over visual marks), so you'll need to use a library such as Plotly or Bokeh (or another interactive package of your choice)
#The chart must have clear axis labels, title, and legend (depeding on the chart type)
#At least one of the widgets must change what data is displayed in the chart (e.g., selecting a variable to display in the chart). The other widget can control something like the colors used in the chart, or allow you to select something like the year being displayed
#Below the chart, include a description of why you included the chart, and what patterns emerged as you explored it
#When I say "clear" or "human readable" titles and labels, that means that you should not just display the variable name.

#Submission
#Once you've finished editing files, you'll need to host your project on shinyapps.io (Links to an external site.). As always, you need to also use git to add and commit the changes you've made, and push those changes to your repository on GitHub. Please submit the URL of your GitHub Repository as you assignment submission on Canvas, and make a comment along with your submission with the URL of your project on shinyapps.io.