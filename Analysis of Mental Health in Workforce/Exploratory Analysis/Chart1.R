
library(tidytext)
library(dplyr)
library(ggplot2)
rm(list = ls())
df <- read.csv("https://raw.githubusercontent.com/info201b-2021-aut/final-project-MinHeo1/main/Data/survey.csv?token=AV5GCMIX2WD4DAFDLEJMEMLBXF55I")
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
  subset(select = c(Timestamp, Age, Gender, Country, state, family_history, no_employees, mental_vs_physical))

mostCommon <-
  df %>%
  count(state, sort = TRUE)

keepState <- as.vector(unlist(mostCommon$state))[1:10]

df <- df[df$state %in% keepState, ]
``
ggplot(df, aes(x = no_employees)) +
  geom_bar(aes(fill = state)) + facet_grid(. ~ state) + 
  theme(axis.text.x=element_text(angle=90, hjust=1)) + labs(title="Mental Health issues Count by Number of Employees", y="Number of Employees", x="Mental Health issues count")

``