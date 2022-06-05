# A2: U.S. COVID Trends

## Overview
In many ways, we have come to understand the gravity and trends in the COVID-19 pandemic through quantitative means. Regardless of media source, people are consuming more epidemiological information than ever, primarily through reported figures, charts, and maps.

This assignment is your opportunity to work directly with the same data used by the New York Times. While the analysis is guided through a series of questions, it is your opportunity to use programming skills to ask more detailed questions about the pandemic.

You'll load the data directly from the [New York Times GitHub page](https://github.com/nytimes/covid-19-data/), and you should make sure to read through their documentation to understand the meaning of the datasets.

Note, this is a long assignment, meant to be completed over the two weeks when we'll be learning data wrangling skills. I strongly suggest that you **start early**, and approach it with patience. We're asking real questions of real data, and there is inherent trickiness involved.

## Analysis
You should start this assignment by opening up your `analysis.R` script. The script will guide you through an initial analysis of the data. Throughout the script, there are prompts labeled **Reflection**. Please write 1 - 2 sentences for each of these reflections below:

- What does each row in the data represent (hint: read the [documentation](https://github.com/nytimes/covid-19-data/)!)?
Each row in the data represents the date of the instance of COVID-19 for each dataset (i.e., for states it's the date and cases/deaths for each state and for counties it's the date and case/deaths for each county).
- What did you learn about the dataset when you calculated the state with the lowest cases (and what does that tell you about testing your assumptions in a dataset)?
American Samoa aws the state with the lowest cases which shows that the lower the population the less likely that it will have more cases. It tests the assumption that more liberal states would have lower cases when it really depends on population.
- Is the location with the highest number of cases the location with the most deaths? If not, why do you believe that may be the case?
The county with the highest deaths was Los Angeles while the county with the most deaths was New York City. The locations don't match up because of different variables such as amount of hospitals, amount of medical personnel, and other things. These factors could have contributed to why New York City had more deaths even though Los Angeles had more cases.
- Why are there so many observations (counties) in the variable `lowest_in_each_state` (i.e., wouldn't you expect the number to be ~50)?
There are so many observations in the variable because of the variable amount of deaths and the addition of US territories and other usually not considered states. And some counties had the same amount of deaths too.
- What surprised you the most throughout your analysis?
What surprised me the most throughout the analysis was the sheer amount of cases and deaths there were in the US. Only hearing daily cases had desensitized me to cases in the "thousands" but seeing the total number shocked me. This truly is one of the most devastating pandemics and the numbers exemplified them.
