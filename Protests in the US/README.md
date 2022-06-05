# Assignment 1: Protests
The past few years in the United States, there has been a surge in protests in support of Black Lives Matter, gender equity, and other social issues. In this assignment, you'll work with data from [CountLove](https://countlove.org/) -- the same data often [cited](https://www.nytimes.com/2020/08/28/us/black-lives-matter-protest.html) by the New York Times -- to learn more about demonstrations over the past few years.

By completing the assignment, you will demonstrate the following skills:

- Use of **version control** for managing your code
- Declaring document rendering using **markdown** syntax
- Foundational programming skills in R.


## Background Research
Before diving into this (or any) dataset, it's important to have _domain familiarity_ (i.e., to know something about the topic). As preparation, I'm asking that you read **three articles** about protests in the U.S., and provide a brief 1 - 2 sentence summary or takeaway from each one.

In the section below, create an **unordered list** of the three articles you found. Make sure to provide an appropriate markdown link (_not_ just the URL) to the article in addition to your 1 - 2 sentence summary.
- [New York Times](https://www.nytimes.com/interactive/2020/07/03/us/george-floyd-protests-crowd-size.html). This article describes how unprecendented the protests for the BLM movement was. This shift happened because of the changing opinion of black lives in the US, the trump presidency, as well as the COVID-19 pandemic.
- [Brookings](https://www.brookings.edu/blog/how-we-rise/2021/06/09/pandemics-and-protests-america-has-experienced-racism-like-this-before/). This article details how racism has been here before and what we are experiencing during the BLM movement (racist police, brutal killings, etc.) and in African American communities (less health coverage, less safe, etc.) is the same as it was before. We have protested before, but this time, if we want change, we need to make the United States an anti-racist society that covers all issues instead of narrowing it down to singular issues.
- [reuters](https://www.reuters.com/world/us/abortion-rights-advocates-will-march-across-us-protest-restrictive-laws-2021-10-02/). Mass protests against a law passed in Texas that banned abortions once a fetal heartbeat is made (usually after a few weeks) even though most women don't know they are pregnant until after the heartbeat is heard. This law is also controversial because it allows people to profit (upwards of 10,000) off of reporting abortions to authorities causing outrage among many, especially the women living in Texas.
## Accompanying Image
In this section, please **display one image** to accompany your text, and describe _why_ you included it (~2 - 3 sentences). This will require that you download an image into your project folder. In your description, use **bold** and _italics_ (at least once, for practice) to emphasize some of your points.
![BLM Protest in D.C.!](https://static.dw.com/image/53709521_403.jpg)
I chose this image because it shows the **outreach** of the protests in the United States in response to the murder of Geroge Floyd. The BLM protests were some of the _most widespread_  protests and it had a global reach with countries such as England and France taking part in it. These protests were some of the most important in recent history as it **finally** shed some light (and only _some_) of the disparity that is seen among African American communities and **many (in the millions)** took part to end racism. There is still a long way to go, but this shows that there is still hope among many.
## Analysis
At this point, you should open up your `analysis.R` script to begin working with the data. The script will guide you through an initial analysis of the data. Throughout the script, there are prompts labeled **Reflection**. Please write 1 - 2 sentences for each of these reflections below:

- What does the difference between the mean and the median tell you about the *distribution* of the data?
The difference between the mean the median (seeing as how the mean is greater than the median) shows us that the data is positively skewed.
- Does the number of protests in Washington surprise you? Why or why not?
The protests being at 1375 was surprising because I expected the number to be much higher, at the top or near the top of the list. The western part of Washington is more liberal and I assumed there would have been more protests, but the relatively low number (compared to the higher states) did surprise me.
- Looking at the `state_table` variable, what data quality issues do you notice, and how would you use that to change your analysis (no need to actually change your analysis)?
The data table doesn't show what parts of eahc state that the protests take in or what the proportion (i.e. how many people participated). This would have helped create a better understanding of where and why the protests were occurring.
- Does the change in the number of protests from 2019 to 2020 surprise you? Why or why not?
No it does not because so many factors came into play during 2020 that I would have been surprised if there weren't any protests. The murder of George Floyd, the pandemic, even the attempted inssurection all came into play during 2020 (mainly due to the pandemic) but also because many had reached a breaking point.
- Do a bit of research. Find at least *two specific policies* that have been changed as a result of protests in 2020. These may be at the city, state, or University level. Please provide a basic summary, as well as a link to each article.
- [Juneteenth](https://hbr.org/2021/06/how-your-organization-can-recognize-juneteenth#:~:text=In%20June%20of%202021%2C%20Congress,a%20state%20or%20ceremonial%20holiday.&text=For%20Black%20Americans%2C%20the%20shift,from%2067%25%20to%2093%25.). Juneteenth was officially recognized as a federal holiday (due in part of the BLM protests). It celebrates the emancipation of African-American slaves.
- [Removal of Confderate Statues](https://www.npr.org/2021/09/02/1033595859/virginia-supreme-court-remove-statue-robert-e-lee-confederate-richmond). The Virginian Supreme Court allowed the statue of Robert E. Lee to be taken down in Virgina. The removal of confederate statues was staunchly guarded but the protests seemed to have altered some people's perception of it.
- Take a look (`View()`) your `high_level_table` variable. What picture does this paint of the U.S.?
The US has trouble with education, civil, rights, and racial injustice. It shows how far off the US is in terms of equality still because a lot of the issues go hand-in-hand. Education issues are caused by racial injustice which causes issues with civil rights. The US still has a long way to go to ensure equality for all as declared in the Declaration of Independece.
## Critical Reflection
Now that you have had time to work with the data and visualize it, I want you to practice thinking critically about the dataset we provided. An important part of data analysis is reflecting on the assumptions, limitations, and gaps in your datasets. Remember that all datasets have to make assumptions and not all assumptions are bad or unreasonable! Practicing these skill will help you in your career to be self critical and ethically alert.

For this section, please write 2-3 sentences for each of the reflections below:

- How was the dataset collected and who collected the data?
The dataset was collected by Count Love. They used local newspapers and TV stations to find out where and why the protests took place.
- What assumptions does the dataset make? List atleast two assumptions you identified. For inspiration, check out this [blog post](https://towardsdatascience.com/check-your-assumptions-about-your-data-20be250c143) that describes one method for identifying data assumptions.   
One assumption that this dataset makes is that all the protests that occurred in the state (or area) was covered by local news or TV stations. It may have been possible that they were neglected for other news too. Another assumption that is made is listing the number of participants. Because there were no clear cut numbers from their sources, they had to estimate and assume for each of the protests (i.e., dozens meant 20, and hundreds only meant 100).
- What data is missing from the dataset? Think about if there are any data points you would have liked to have seen in the dataset.
The data missing from this dataset is how long each protest was and what actually happened in it. Meaning if there were violence or if the police showed up or if the crowd disparsed easily or even if the protest was planned or not. These were all missing from the dataset.
## Final Thoughts
When you are finished, with your analysis, please answer the following questions in 1-2 sentences each.

- What about the analysis surprised you?
The low amount of protests that were in the year of 2019 really surprised me (as well as the difference from 2020). This surprised me because many forms of racial inequality and injustice has been seen throughout the years so I expected more protests than there actually were years previous of 2020. I knew 2020 had a lot, but I didn't know that it had that many.
- What parts of this analysis did you find challenging?
The actual coding took a lot (and I mean a lot) of effort. Trying to parse through the dataset to get the data that I wanted to look for took much googling as well as consulting the textbook (and even then I had to take some shots in the dark in the hopes of getting it right).
- What types of analysis do you wish you were able to do with the dataset, but currently don't have the technical skills to do?
I would love to be able to parse through the data to see what type of protests occurred most frequently in what state. So, for example, we take civil rights issue and figure out which states had the highest proportions of that one.
