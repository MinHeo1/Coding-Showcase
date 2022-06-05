# ui.R
library(shiny)
library(plotly)
library(ggplot2)
source("app_server.R")
data <- read.csv(url("https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv"))

states <- data %>%
  select(country) %>%
  unique()



bar_sidebar_content <- sidebarPanel(
  selectInput(
    "states",
    label = "States to pick",
    choices = states
  ),
  sliderInput("integer", "Integer:",
              min = 1, max = 12,
              value = 6))
  
  
  bar_main_content <- mainPanel(
    h1("Welcome to the Bar Chart Page"),
    plotlyOutput("bar"),
    p("This graph shows the top years with the highest co2 emissions by country. This graph was included to show the trend
      of co2 emissions of every state. As seen co2 emissions were actually highest in the older years. Around 1850 to 1920.
      The trend then seems to depreciate, meaning that overtime we see the states rely on co2 emissions less and less. This
      shows that co2 and climate change are being taken more seriously in today's age than before. There are still issues with
      the global co2 amount increasing still by a lot, the graphs show that the years with the most emission actually happened
      before there was outrage over climate change and co2 emissions.")
  )
  
  page_one <- tabPanel(
    "Bar Chart",
    titlePanel("Bar Chart"),
    sidebarLayout(
      bar_sidebar_content,
      bar_main_content
    )
  )
  
  paragraph <- paste("Climate change is a real issue within the world today. There is no doubt the impact that climate
      change has had on the environment as well as on the people itself. From health impacts to environmental
      impacts, there is so much that climate change has done and it is only getting worse. The data above shows
      the impact of co2 and the amount of co2 that is produced in the USA. It shows the trend from 1990 to 2020
      and the impact that it has had on the world. As seen, carbon emission has increased year by year and it isn't
      slowing down. Without the world stepping up to prevent more damage from climate change than there are bigger
      problems that will hinder the earth from getting better. And it's not just the US too, many other countries also
      suffer from the impact of climate change and also contributes to it.",
                     highest_co2_growth_country_2020, 
                     " is the highest co2 growth of 2020 and it was not United States. This shows that it is not just the
                     United States or any other bigger country but instead all countries contribute to it. This shows that
                     climate change is a global issue, not an issue that only bigger countries contribute to.",
                     USA_co2_growth_2020, 
                     " is how much the US has actually gone down in co2 emissions. Although the problem is still
                     prominent, some measures are being taken to lower co2 emissions. It isn't enough, but is the step in
                     the right direction.", 
                     USA_co2_growth_1990, 
                     " is how much the US has gone down in the 1990's. This shows that the US has actually done a lot more
                     for climate change than what meets the eye. And yet the total co2 emission is still high.", 
                     change_2020_1990_USA, 
                     " is the difference between 2020 and 1990 in the USA. This shows how the US has made strides to combat
                     climate change and the impact it has on the environment.", 
                     highest_co2_growth_year , 
                     " is the year with the highest co2 growth. ", 
                     highest_co2_growth_country_overall, 
                     " is the country with the highest co2 growth. This shows that it isn't the bigger countries but instead
                     under funded or more economically weaker countries that still rely on fossil fuels to do things.")
  
  page_one_mainpanel <- mainPanel(
    h1("CO2 Analysis"),
    h2("Introductory Paragraph"),
    p(paragraph)

  ) 
  page_zero <- tabPanel(
    "Intro",
    page_one_mainpanel
  )
  
  ui <- navbarPage(
    "CO2 Data",
    page_zero,
    page_one,
  )

