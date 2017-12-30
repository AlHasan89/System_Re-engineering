library(ggplot2)
data <- read.csv("ImportantClassesMetrics.csv")

#Just show a spread of methods with respect to instruction count and Cyclomatic complexity
ggplot(data,aes(x=LOC,y=Wheighted.Method.Count)) + geom_point()

#Add some labels for nodes with higher values of cyclomatic complexity.
ggplot(data,aes(x=LOC,y=Wheighted.Method.Count)) + geom_point() + 
  geom_text(data=data[data$Wheighted.Method.Count>5,],aes(label=Name),nudge_x = 60)
