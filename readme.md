## What's this ?

Welcome! I made this little program to display and analyse Singapore's 4D numbers. It automatically retrives current and historical official draw results to display. I'm also building tools that allow analysis and prediction of future results of number sequence(s) of higher chances of being drawn (it makes use of a bit of probability theory too!)

### NOTE: This app does not facilitate gambling or participating in the lottery

```### DISCLAIMER: This software was created to demonstrate programming skills, computer software and mathematical algorithms research. We cannot gurantee any winnings or be responsible for any monentary losses incurred as a result of its usage. Play at your own discreation.```

Click [here](http://www.singaporepools.com.sg/landing/en/Pages/index.html) for more information on Singapore's 4D lottery.

## How to use?

The program is written in Java with different invokable components. The heavy lifting algorithms are implemented in C. Some of the programs are command-line based, some are GUI based.

**__For Windows__**

![tolaunch screenshot](https://i.imgur.com/rEiojQi.png)


For those not conversant with the command-line, there is a `run.bat` helper to allow you to launch commands within the program by setting-up the environment. In plain English, double click on the icon that has a title run. Once launched, there are commands you can use to do specific things. Refer below to the list of commands and actions they are supposed to perform.

![commandready screenshot](https://i.imgur.com/2uMPbjY.png)

## Examples

One of the important tasks to include or exclude numbers for analysis is to quickly identify 12-permutation numbers in past 4D draws. 12-permutation numbers are a combination of a 4d number of which at least 2 of the digits are the same. For example any of this combinations are considered 12-permutation numbes: <span style="color:blue">XX</span><span style="color:red">YZ</span>, <span style="color:blue">X</span><span style="color:red">Y</span><span style="color:blue">X</span><span style="color:red">Z</span>, <span style="color:blue">X</span><span style="color:red">YZ</span><span style="color:blue">X</span>, <span style="color:red">Y</span><span style="color:blue">X</span><span style="color:red">Y</span><span style="color:blue">X</span> or <span style="color:red">Y</span><span style="color:blue">XX</span><span style="color:red">Y</span>
The following operations are supported - 

### 1. Request all the draws for the month

Now that the console has launched, it is ready to accept commands from you. To request all 12-permutation numbers for a particular month, at the cursor, type

```RequestForMonth Febuary 2017```

or

```RequestForMonth Feb 2017```

for short form. Months are case-insensitive. 

If all goes well, you should see a report generated titled `<Mon>-<Year>-results.txt` or `<Month>-<Year>-results.txt` depending on the way you input-ed the month.

![12-permutation screenshot](https://i.imgur.com/w9HPmJJ.png)

In this case, we gave the month as October and year as 2017. We should get the following

```

Draw date: Sunday, 1 October, 2017

Found 12-permutation: 4878  [ 9884, ]
Found 12-permutation: 1600  [ ]
Found 12-permutation: 4191  [ ]
Found 12-permutation: 4199  [ ]
Found 12-permutation: 6282  [ 2622, ]

Draw date: Wednesday, 4 October, 2017

Found 12-permutation: 7917  [ 9172, ]
Found 12-permutation: 4534  [ ]
Found 12-permutation: 8208  [ 8285, ]
Found 12-permutation: 8233  [ 0338, ]
Found 12-permutation: 0767  [ ]
Found 12-permutation: 1080  [ ]
Found 12-permutation: 6474  [ ]

Draw date: Saturday, 7 October, 2017

Found 12-permutation: 7127  [ ]
Found 12-permutation: 2989  [ ]
Found 12-permutation: 8955  [ 9584, ]
Found 12-permutation: 6939  [ ]

Draw date: Sunday, 8 October, 2017

Found 12-permutation: 0340  [ ]
Found 12-permutation: 0646  [ ]
Found 12-permutation: 1861  [ 1864, 5816, ]
Found 12-permutation: 8202  [ 0289, ]
Found 12-permutation: 7388  [ ]

Draw date: Wednesday, 11 October, 2017

Found 12-permutation: 5105  [ ]
Found 12-permutation: 6576  [ ]

Draw date: Saturday, 14 October, 2017

Found 12-permutation: 8565  [ 5368, 5536, ]
Found 12-permutation: 5315  [ 0135, 5553, 5536, 5573, ]
Found 12-permutation: 8198  [ ]
Found 12-permutation: 5065  [ 5586, 5536, ]
Found 12-permutation: 7646  [ ]

Draw date: Sunday, 15 October, 2017

Found 12-permutation: 2955  [ 5652, 5926, ]
Found 12-permutation: 2712  [ ]
Found 12-permutation: 3253  [ 3232, ]
Found 12-permutation: 9739  [ 3297, 6997, ]

Draw date: Wednesday, 18 October, 2017

Found 12-permutation: 0545  [ 5034, 4540, ]
Found 12-permutation: 8678  [ 7683, 8286, ]
Found 12-permutation: 1733  [ ]
Found 12-permutation: 5935  [ ]
Found 12-permutation: 9808  [ 8009, 8895, ]

Draw date: Saturday, 21 October, 2017

Found 12-permutation: 8515  [ ]
Found 12-permutation: 0959  [ ]
Found 12-permutation: 2040  [ 4502, ]
Found 12-permutation: 4584  [ 4345, 5486, 5483, ]
Found 12-permutation: 4787  [ 8740, ]
Found 12-permutation: 5211  [ ]
Found 12-permutation: 6356  [ ]
Found 12-permutation: 7010  [ 1072, ]
Found 12-permutation: 9829  [ ]

Draw date: Sunday, 22 October, 2017

Found 12-permutation: 8768  [ ]
Found 12-permutation: 1433  [ ]
Found 12-permutation: 2808  [ ]
Found 12-permutation: 8748  [ ]

Draw date: Wednesday, 25 October, 2017

Found 12-permutation: 4797  [ 7374, 9748, ]
Found 12-permutation: 2988  [ ]
Found 12-permutation: 7212  [ ]
Found 12-permutation: 1464  [ 4610, 4816, ]
Found 12-permutation: 4959  [ ]
Found 12-permutation: 0757  [ 5607, ]
Found 12-permutation: 6088  [ 3608, ]
Found 12-permutation: 6088  [ 3608, ]

Draw date: Saturday, 28 October, 2017

Found 12-permutation: 3404  [ ]
Found 12-permutation: 8020  [ 1002, ]
Found 12-permutation: 0363  [ ]
Found 12-permutation: 5915  [ ]
Found 12-permutation: 7606  [ ]
Found 12-permutation: 1699  [ 1997, 6193, ]
Found 12-permutation: 2072  [ 7209, ]
Found 12-permutation: 6070  [ ]

Draw date: Sunday, 29 October, 2017

Found 12-permutation: 3853  [ 3305, ]
Found 12-permutation: 0696  [ 9602, ]
Found 12-permutation: 6826  [ ]
Found 12-permutation: 6141  [ 2641, ]
```

### 2. Search for base number

Base number(s) of a 4-digit number is a pattern of (usually) 3 consecutive digits you are searching for in a 4-digit number. Take for example `4127` and `3421`. We see that the common numbers are 4, 2, and 1. The next code will search a series of draw dates to determine which drawn numbers contain the base numbers you are searching for. This tool comes in a Graphic User Interface (GUI) and gives you more flexibility in specifying your start and end dates.

So with the console open, type the command `search`

![search command](https://i.imgur.com/maLPiWx.png)




