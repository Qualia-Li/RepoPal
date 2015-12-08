# RepoPal

An approach that can effectively detect similar repositories on GitHub, based on additional data sources including GitHub stars and readme files.

This approach is been compared to CLAN implemented on GitHub, which can be found here:https://github.com/Qualia-Li/CLANOnGitHub.

This repository is just the Star Relevance Calculator component, not the whole system.

##Heuristics
1. Projects that are starred by the same users within a short period of time are likely to be similar with one another.
2. Projects whose readme files contain similar contents are likely to be similar with one another.

##Features
1. We propose two new heuristics to identify similar repositories on GitHub which leverage two data sources not considered in prior works - i.e., GitHub star and readme files.
2. We integrate these two heuristics in a tool RepoPal. Different from previous approaches that are language specific and dependent on source code, our approach can detect applications written in different programming languages.

##Star Relevance
This component leverages the GitHub stars to rank repositories. Intuitively, two repositories that are starred at a similar period of time by many people are likely to be similar together. 

The star-based relevance score is calculated as the sum of the reciprocals of the adjusted time differences. In this way, for two repositories, when the time difference between their two stars is smaller, the reciprocal becomes larger, and therefore the relevance is higher. When there are more users starring the two repositories together, the relevance score will also be higher.
