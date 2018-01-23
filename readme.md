## What's this ?

Welcome! I made this little program to display and analyse Singapore's 4D numbers. It automatically retrives current and historical official draw results to display. I'm also building tools that allow analysis and prediction of future results of number sequence(s) of higher chances of being drawn (it makes use of a bit of probability theory too!)

### NOTE: This app does not facilitate gambling or participating in the lottery

## How to use?

The program is written in Java with different invokable components. The heavy lifting algorithms are implemented in C. Some of the programs are script based, some are GUI based.

**__For Windows__**

![tolaunch screenshot](https://i.imgur.com/rEiojQi.png)


For those not conversant with the command-line, there is a `run.bat` helper to allow you to launch commands within the program by setting-up the environment. In plain English, double click on the icon that has a title run. Once launched, there are commands you can use to do specific things. Refer below to the list of commands and actions they are supposed to perform.

![commandready screenshot](https://i.imgur.com/2uMPbjY.png)

## Examples

One of the important tasks to include or exclude numbers for analysis is to quickly identify 12-permutation numbers in past 4D draws. 12-permutation numbers are a combination of a 4d number of which 2 of the digits are the same. For example any of this combinations are considered 12-permutation numbes: `<span style="color:blue">XX</span><span style="color:red">YY</span>`, `<span style="color:blue">X</span><span style="color:red">Y</span><span style="color:blue">X</span><span style="color:red">Y</span>`, `<span style="color:blue">X</span><span style="color:red">YY</span><span style="color:blue">X</span>`, `<span style="color:red">Y</span><span style="color:blue">X</span><span style="color:red">Y</span><span style="color:blue">X</span>` or `<span style="color:red">Y</span><span style="color:blue">XX</span><span style="color:red">Y</span>`
The following operations are supported - 

Request all the draws for the month

```RequestForMonth Febuary 2017```

or

```RequestForMonth Feb 2017```

for short form. Months are case-insensitive. 

If all goes well, 