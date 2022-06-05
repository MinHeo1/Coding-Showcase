
survey <- read.csv("~/Documents/INFO201/final-project-MinHeo1/Data:/survey.csv")
   View(survey)
  
summary(survey)

mean_age <- mean(survey$Age)
mean(survey$Age, na.rm = TRUE)
print(mean_age)

median_age <- median(survey$Age, na.rm = TRUE)

max_age <- max(survey$Age, na.rn = TRUE)

min_age -> min(survey$Age, na.rn = TRUE)

family_history<- survey$family_history

no_family_history_total <- sum(stringr::str_detect(family_history, "No"), na.rm = TRUE)

summary_info -> list(mean_age, median_age, max_age, min_age, no_family_history_total)