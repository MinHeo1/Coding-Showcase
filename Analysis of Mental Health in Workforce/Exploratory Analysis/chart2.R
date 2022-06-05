library(tidyverse)
library(dplyr)
library(ggplot2)
survey_df <- read.csv("https://raw.githubusercontent.com/info201b-2021-aut/final-project-MinHeo1/main/Data/survey.csv?token=AV5GCMIX2WD4DAFDLEJMEMLBXF55I") 
state_df <- survey_df %>%
  filter(Country == "United States") %>%
  group_by(state) %>%
filter(state=="CA" || state=="NY" || state=="WA" || state=="TX" || state=="OR" || state=="IN" || state=="MA" || state=="OH" || state=="TN")
  ordered_no_employees <- factor(state_df$no_employees, levels=c("1-5", "6-25", "26-100", "100-500", "500-1000", "More than 1000"))
ggplot(state_df, aes(x=state, y=ordered_no_employees)) + geom_point(aes(col=coworkers)) + labs(title="Employee amount by Willingness to Discuss Mental Health per State", y="Number of Employees", x="State", color="Willingness")

