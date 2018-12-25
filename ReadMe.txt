UTD CS5V81-001 - Short Project 7

Project By : Achyut Bhandiwad (aab180004) and Mythri Thippareddy (mxt172530)

Submitted on: 10/21/2018

This Project was implemented as part of the course CS5V81-001 Implementation of Data Structures and Algorithms at University of Texas at Dallas.

The goal of the project was to create the functionality of RobinHood Hashing and Double Hashing.

RobinHood Hashing:

File Name: RobinHoodHashing.java
Driver Class : DriverClass.java
Package name: aab180004

The following cases are implemented: 1. Add 2. Remove 3. Contains 4: distinctElements

To compile and run, use the following commands:
javac ./aab180004/DriverClass.java ./aab180004/RobinHoodHashing.java ./aab180004/Timer.java
java ./aab180004/DriverClass

Results:
Adding random integers of 1,000,000 elements
After running 10 iterations:
Average Time using HashSet: 345.2msec
Average Time using RobinHood: 402.1
Ratio of (Time taken by HashSet/ Time Taken by RobinHood) = 0.858492912
Average memory usuage of HashSet = 64.4 MB / 240 MB.
Average memory usuage of RobinHoodHashing = 115.6 MB / 303 MB.


Double Hashing:

File Name: DoubleHashing.java
Driver Class : DriverClass.java
Package name: aab180004

The following cases are implemented: 1. Add 2. Remove 3. Contains 4: distinctElements

To compile and run, use the following commands:
javac ./aab180004/DriverClass.java ./aab180004/DoubleHashing.java ./aab180004/Timer.java
java ./aab180004/DriverClass

Results:
Number of distinct elements using HashSet:999883
Time: 753 msec.
Memory: 74 MB / 346 MB.
Number of distinct elements using DoubleHashing:999883
Time: 1981 msec.
Memory: 218 MB / 1014 MB.

