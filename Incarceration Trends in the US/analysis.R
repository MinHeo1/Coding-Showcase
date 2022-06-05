incarnation_df <- read.csv(url("https://raw.githubusercontent.com/vera-institute/incarceration-trends/master/incarceration_trends.csv"))

library("dplyr")
library("ggplot2")
library("leaflet")


#Introduction + Summary Information

# What is the average proportion of African Americans in prisons in urban areas?
mean_blacks_jailed_urban <- incarnation_df%>%
  filter(urbanicity == 'urban') %>%
  summarize(mean = mean( (black_prison_pop/black_pop_15to64), na.rm=TRUE))
mean_blacks_jailed_urban

# What is the average amount of lationx jailed in 2018 from Washington? 
mean_lationx_jailed <- incarnation_df%>%
  filter(year == 2018, state == "WA") %>%
  summarize(mean = mean(latinx_jail_pop), na.rm=TRUE)
mean_lationx_jailed

# Which State has the highest amount of blacks in prison
highest_blacks_prison <- incarnation_df%>%
  filter(black_prison_pop == max(black_prison_pop, na.rm=TRUE)) %>%
  pull(state)
highest_blacks_prison

# Which urbanicity has the highest amount of asians in jail
highest_asians_prison <- incarnation_df%>%
  group_by(urbanicity) %>%
  summarize(sum = sum(aapi_prison_pop, na.rm=TRUE)) %>%
  filter(sum == max(sum)) %>%
  pull(urbanicity)
highest_asians_prison

# How much has the total num of blacks jailed changed from 2010 compared to 2018?
blacks_jailed_2018 <- incarnation_df%>%
  filter(year == 2018) %>%
  summarize(amount = sum( black_jail_pop, na.rm=TRUE))
blacks_jailed_2018

blacks_jailed_2010 <- incarnation_df%>%
  filter(year == 2010) %>%
  summarize(amount = sum( black_jail_pop, na.rm=TRUE))
blacks_jailed_2010

change_1970_2018 <- abs(blacks_jailed_2010$amount - blacks_jailed_2018$amount)
change_1970_2018

#Trends over time chart

amount_jailed_by_race <- incarnation_df %>%
  group_by(year) %>%
  summarise(black_pop = sum(black_jail_pop, na.rm=TRUE), 
            asian_pop = sum(aapi_jail_pop, na.rm=TRUE),
            white_pop = sum(white_jail_pop, na.rm=TRUE),
            native_pop = sum(native_jail_pop, na.rm=TRUE),
            latinx_pop = sum(latinx_jail_pop, na.rm=TRUE)
  )
amount_jailed_by_race

year_plot <- ggplot(amount_jailed_by_race , aes(year, group = 1)) +
  geom_line(aes(y = black_pop , colour = "Number of Jailed African Americans")) +
  geom_line(aes(y = latinx_pop, colour = "Number of Jailed Latinx Americans")) +
  geom_line(aes(y = asian_pop, colour = "Number of Jailed Asian Americans")) +
  geom_line(aes(y = native_pop, colour = "Number of Jailed Native Americans")) +
  geom_line(aes(y = white_pop, colour = "Number of Jailed White Americans")) +
  xlab("Years") + ylab("Population") +
  ggtitle("Total Number of Jailed Individuals by Race Over the Years")
year_plot

#Variable comparison chart

library(ggplot2)

# create a dataset

comparing_urbanicity <- incarnation_df %>%
  group_by(urbanicity) %>%
  summarise(sum_jailed_black = sum(black_jail_pop, na.rm=TRUE))
comparing_urbanicity
# Grouped
bar_chart_jail <- ggplot(comparing_urbanicity, aes(x=urbanicity, y=sum_jailed_black, fill=urbanicity)) + 
  geom_bar(stat = "identity")+
  ggtitle("Total Amount of Jailed African Americans")
  
## Interactive Map

usa <- map_data("county")
usa$region <- str_to_title(usa$region)
usa$subregion <- str_to_title(usa$subregion)
usa$region <- state.abb[match(usa$region, state.name)]
usa$new_name <- paste(usa$region, usa$subregion, 'County')
usa <- distinct(usa, new_name , .keep_all= TRUE)
incarnation_df$new_name <- paste(incarnation_df$state, incarnation_df$county_name)
usa_combined <- left_join(incarnation_df,  usa, by = "new_name") 
usa_combined

black_prisoned_map <- leaflet( data = usa_combined) %>%
  addProviderTiles("CartoDB.Positron") %>%
  addCircles(
    lat = ~lat,
    lng = ~long,
    radius = ~black_prison_pop * .01,
    stroke = FALSE,
    popup = paste("County Name:", usa_combined$county_name, "<br>",
                  "Number Imprisoned:", usa_combined$black_prison_pop, "<br>",
                  "Total Population:", usa_combined$total_pop, "<br>")
    
    
  ) 





 
