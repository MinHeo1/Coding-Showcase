library(tidytext)
library(dplyr)
rm(list = ls())
df <- read.csv("~/Desktop/info201/final-project-MinHeo1/survey.csv")
df <- df %>% filter(grepl('2014', df$Timestamp))

df$Gender <- toupper(df$Gender)
df$Gender[grep("\\bM\\b",df$Gender)] <- 'MALE'
df$Gender[grep("\\bF\\b",df$Gender)] <- 'FEMALE'
df$Gender[grep("\\bWOMAN\\b",df$Gender)] <- 'FEMALE'

keepGender <- c("MALE", "FEMALE")
df <- df[df$Gender %in% keepGender, ]

df <- 
  df %>%
  group_by(Country) %>%
  filter(family_history == "No") %>%
  filter(Country == "United States") %>% 
  subset(select = c(Timestamp, Age, Gender, Country, state, family_history, no_employees, mental_vs_physical, coworkers))

mostCommon <-
  df %>%
  count(state, sort = TRUE)

keepState <- as.vector(unlist(mostCommon$state))[1:10]

df <- df[df$state %in% keepState, ]