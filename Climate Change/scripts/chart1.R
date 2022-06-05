library(ggplot2)
library(dplyr)
library(stringr)
library(plotly)
data <- read.csv(url("https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv"))

# Creates bar chart from state and amount
bar_chart <- function(s, amount) {
#filters out bar chart
  mid <- data %>%
    filter(country== s) %>%
    arrange(desc(co2_growth_prct)) %>%
    slice(1:amount)
  
  
  bar <- ggplot(mid, aes(x = year, y = co2_growth_prct,
                         fill = str_to_title(year),
                         text = paste("Year: ", year,
                                "\n CO2 Growth Percentage ", co2_growth_prct))) +
    geom_bar(stat = "identity") +
    scale_fill_brewer(palette = "Set3") +
    labs(title = paste("Top",
                       amount,
                       "Years with the highest CO2 Growth Percentage",
                       s),
         x = "Year",
         y = "CO2 Growth percentage",
         fill = "Year")
    bar_chart <- ggplotly(bar, tooltip = "text")

  return(bar_chart)
}
 
