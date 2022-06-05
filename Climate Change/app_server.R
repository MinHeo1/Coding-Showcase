#Server File
library(shiny)
library(ggplot2)
library(plotly)

source("scripts/chart1.R")
data <- read.csv(url("https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv"))

highest_co2_growth_country_2020 <- data %>%
  filter(year == 2020) %>%
  filter(co2_growth_prct == max(co2_growth_prct, na.rm=TRUE))%>%
  pull(country)


USA_co2_growth_2020 <- data %>%
  filter(country == "United States") %>%
  filter(year == 2020) %>%
  pull(co2_growth_prct)

USA_co2_growth_1990 <- data %>%
  filter(country == "United States") %>%
  filter(year == 1990) %>%
  pull(co2_growth_prct)


change_2020_1990_USA <- abs(USA_co2_growth_2020 - USA_co2_growth_1990)

highest_co2_growth_year <- data %>%
  filter(co2_growth_prct == max(co2_growth_prct, na.rm=TRUE)) %>%
  pull(year)


highest_co2_growth_country_overall <- data %>%
  filter(co2_growth_prct == max(co2_growth_prct, na.rm=TRUE)) %>%
  pull(country)



server <- function(input, output) {
  output$bar <- renderPlotly({
    return(bar_chart(input$states, input$integer))
  })

}

