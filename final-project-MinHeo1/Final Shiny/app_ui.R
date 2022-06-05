library(shiny)
library(ggplot2)
library(dplyr)

data <- read.csv('https://raw.githubusercontent.com/info201b-2021-aut/final-project-MinHeo1/main/Data/survey.csv?token=AV5GCMIX2WD4DAFDLEJMEMLBXF55I')
data <- data %>% filter(grepl('2014', data$Timestamp))
data$Gender <- toupper(data$Gender)
data$Gender[grep("\\bM\\b",data$Gender)] <- 'MALE'
data$Gender[grep("\\bF\\b",data$Gender)] <- 'FEMALE'
data$Gender[grep("\\bWOMAN\\b",data$Gender)] <- 'FEMALE'
keepGender <- c("MALE", "FEMALE")
data <- data[data$Gender %in% keepGender, ]

data_2 <- data %>%
  filter(Age <= 100)%>%
  group_by(state)%>%
  group_by(no_employees)

page_one <- tabPanel(
  "Introduction",
  mainPanel(
    ui <- fluidPage(
      h1("Introduction"),
      h5("Our group chose to focus on mental health in businesses because of how prevalent mental health issues.
        Only recently has mental health taken priority with many and many people still disregard it as 
        faking it or lying. Mental health can affect anybody from children to adults, making it a wide 
        scope issue that should be addressed. Therefore our group chose our dataset so that it narrows the scope
        and focuses primarily on mental health in business. The dataset consists of a self-reported survey that takes into account
        people and mental health. It takes in the state, the age, treatment, business size, what type of business (i.e., tech, 
        self-employed, etc.), and willingess to discuss mental health at their companies. We chose this dataset because working 
        in business takes a toll on anybody both physically and mentally yet talking about mental health is ignored for many 
        professionals. It is also an underlying issue for a plethora of other issues (such as suicide) so researching the 
        impact and rates of mental health could lead to other discoveries and more awareness."),
        
      h5("The questions that we decided to tackle include what is the number of employees that suffer from mental illness
        and whether gender played a part in it. What is the impact of mental health based off of company size as well as
        age distribution (i.e., what age groups suffered particularly more than other age groups) and did they recieve treatment.
        And what are the consequences of mental health based off of the state that they are in and company size."),
      
      img(src = "mentalhealth.png", height = 300, width = 300),
      h3("Our Dataset"),
      tags$h3("Our Dataset"),
      "Here is our dataset that we chose to do our analysis on, ",
      tags$a(href="https://www.kaggle.com/osmi/mental-health-in-tech-survey",
             "our dataset")
    )
  )
)

page_two <- tabPanel(
  "Number of Employees by Country and Gender",
  
  sidebarLayout(
    sidebarPanel(
      selectInput(
        inputId = "country",
        label = "Select a country: ",
        choices = unique(data$Country),
        selected = "United States"),
      
      radioButtons(
        inputId = "radio",
        label = "Pick a gender",
        choices = list("MALE" = "MALE", "FEMALE" = "FEMALE"),
        selected = "MALE"),
    ),
    mainPanel(
      ui <- fluidPage(
        h1("number of employees"),
        plotOutput("box"), 
        h3("Description"),
        h5("The graph shows the number of people who filled out the mental health survey
           based off of country as well as gender. This graph is important to not only set a baseline
           for the number of sufferers of mental health, but it also shows the distribution of
           mental health sufferers in all parts of the world. The graph also takes into account company size
           in order to see if company size has an impact on mental health.")
      )
    )
  )
)

page_three <- tabPanel(
  "Age Distribution",
  
  sidebarLayout(
    sidebarPanel(
      
      radioButtons(
        inputId = "treatment",
        label = "Did the surveyor receive treatment or not: ",
        choices = list("Yes" = "Yes", "No" = "No"),
        selected = "Yes"
      ),
      
      
      selectInput(
        inputId = "size",
        label = "Select the size of the company: ",
        choices = unique(data_2$no_employees),
        selected = "1-5"),
    ),
    
    mainPanel(  
      ui <- fluidPage(
        
        h1("Age distribution of mental health in work place"),
        plotOutput("line"),
        h3("Description"),
        h5("This graph shows the age distribution of mental health sufferers in differing company
           sizes and whether they received treatment or not. This graph shows the United States and the
           differing age distributions in multiple different states based off of whether the surveyed got
           treatment and company size. This is important because it can show what age range suffers the 
           most from mental health issues and whether company size also plays a role in it.")
      )   
    )
  )
)

page_four <- tabPanel(
  "The Impact of Company Size on Mental Health",
  
  sidebarLayout(
    sidebarPanel(
      
      
      checkboxGroupInput(
        inputId = "stateChoice", 
        label = h3("States"), 
        choices = list("CA" = "CA", "NY" = "NY", "TX" = "TX"),
        selected = "CA"
      ),
      selectInput(
        inputId = "employees",
        label = "Select the size of the company: ",
        choices = unique(data_2$no_employees),
        selected = "1-5"),
    ),
    
    mainPanel(  
      ui <- fluidPage(
        
        h1("Mental Health and Company Size"),
        plotOutput("bar"),
        h3("Description"),
        h5("This graph shows the proportion of mental health sufferers in three major states, California,
           New York, and Texas. These states were chose because they are the hubs of the United States and sets
           a good baseline to show the impact of mental health. This is also divided by company size to see the impact
           that it has on mental health as well.")
      )   
    )
  )
)

page_five <- tabPanel(
  "Final Thoughts",
  mainPanel(
    ui <- fluidPage(
      h1("Final Thoughts"),
      h5("After conducting this exploration on mental health in companies, we found that for the number of people
        who suffer from mental health, the highest proportion are with company sizes 25-100 and more than 1000 for
        both male and female. This may be due to the fact that bigger company sizes has higher chance of mental health but
        We see that company sizes of 500-1000 is lower than 25-100. As for gender, Overall females have higher rates of 
        mental health issues when compared to men. This may be due to overall workload compared to men, 
        or it may be social stigma that has caused this difference. Although it isn't a big difference it is still relevant. 
        The social stigma is that men are supposed to be tough or not suffer from mental health which may cause them to report 
        less for mental health. Nonetheless, mental health is still an ongoing issue that should be addressed by not just the 
        person but the companies as well. The major takeaway from this analysis is that mental health is a major issue, even if
        the data shows relatively low amounts of mental health (compared to company size at least), for both males and females
        and that it should have a higher priority for employees and employers to dissaude burnout or other harmful side-effects."),
        
        h5("Another conclusion that we can draw from this exploration is about the age distribution of mental health sufferers in
        companies as well as whether they received treatment for it or not. From the dataset (and the charts our group made for it)
        we found that the majority of people who suffer from mental health issues but received treatment are in the age range of 30-40.
        This could primarily be do to the fact that this is their prime of their careers, they are looking for promotions, 
        higher paying jobs, looking for stability (and if they haven't done so already families or partners). The range is also between 
        around 15-55 which shows that mental health could impact anybody no matter their age. As for those that did not receive treatment
        but said they suffered from mental health the majority of sufferers are in the age range of 25-45. This shows that more people suffer
        from mental health but don't pursue treatment. This could be because of a plethora of issues but the major ones might be the company
        does not want them too, they are scared to (potentially because of stigma), or they do not know where to start. Which goes to show that
        mental health issues are prevalent, and only recently has things been done to try and help those that suffer from it. The age range is
        also 22-70. The range is much wider as well which shows mental health isn't a young persons issue. Many people can suffer from it no
        matter the age. The major takeaway from this analysis is that mental health can affect a wide range of ages, and although mental health
        has become easier to talk about, there are still many that do not receive treatment for it."),
        
        h5("The final conclusion that we can draw from this exploration is that the larger the company size the more likely it is
        for their employees to suffer from mental health issues. Especially in the state of California (compared to New York or Texas). We
        chose to focuse primarily on those three states because they represent the bigger states in the United States and they are cultural hubs.
        California is similar to a microcosm for the west, Texas the middle, and New York the east. Therefore, getting a glimpse
        into what they look like could indicate what mental health is like all over the United States. As assumed, the larger companies had higher
        mental health rates especially in the state of California. There were some outliers as companies with 25-100 employees had similar 
        proportions, but the idea still hold when comparing smaller with bigger companies(i.e., 1-5 vs 1000+, etc.). The conclusion that can be
        drawn from this is that the bigger the company the more likely there is to be competition for spots as well as promotions while 
        smaller companies may allow for more leniency. Bigger companies also have more employees which increases the likelihood for there
        to be more mental health cases than smaller companies but the graph focuses on proportion (i.e., 1 out of 5 employees is 20%, while 
        200 employees out of 1000 is also 20%). This goes to show that mental health, again, is prevalent in business. Especially because
        this is self-reported, there may be many more undocumented cases of mental health in each business (where they may be unwilling
        to share because of stigma or other factors, such as not wanting to show weakness).This analysis only strengthened the argument that
        mental health should have some priority when it comes to professional career. Mental health can affect anyone from any company, but the
        effects of it are devastating. Therefore, employees should be aware of their limits and companies should try their best to lessen the
        burden on their employees (such as allowing mental health to be expressed, offering treatment, etc.)."
      )
    )
  )
)

ui <- navbarPage(
  "Mental health Survey Data Exploration",
  page_one,
  page_two,
  page_three,
  page_four,
  page_five
)
