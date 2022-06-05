library(tidytext)
library(dplyr)
library(ggplot2)
rm(list = ls())
df <- survey
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

my_df <- df[-c(94), ]
View(my_df)






state_group <- my_df %>%
  group_by(state)
View(state_group)
ggplot(my_df, aes(x = state, y = Age, color = state)) +
  geom_violin(size = 1) + labs(title = "The age distribution of different states", x="States", y = "Age distribution") + theme(plot.title = element_text(hjust = 0.5))
