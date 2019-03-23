Read me file

*The code has been written in Java
*The code was developed in eclipse ide
*The code runs in jre environment
*operating system used is Linux
*The code requires the 2 libraries for which the .jar files have been provided in the assignment folder
*jcommon-1.2.22.jar and jfreechart-1.018.jar are the two libraries
*Other than that the assignment folder has two other files- TMAN.java and LineChart_AWT.java
*The TMAN.java contains the main program and has all the logic to computer neighbours of the overlay network
*The LineChart_AWT.java contains only the code to plot the graph
*The ouput files of the program can be found  in where the source code lies.
*Steps to run the code
1)First go to the terminal and go to the folder where the source code lies.
2)Type make in the terminal to compile the files.
3)To run for spectacle topology Type the command " java -cp ".:jfreechart-1.0.18.jar:jcommon-1.0.22.jar" TMAN 1000 30 S"
4)To run for b topology Type the command " java -cp ".:jfreechart-1.0.18.jar:jcommon-1.0.22.jar" TMAN 1000 30 B"

*The code on running will pop a graph of the final sum of distances between neighbouring nodes VS the cycles.
*The following files will be generated and saved in the folder where the source code lies.
1)File containing the sum of distances of all nodes in the format <topology>_N<number of nodes>_k<number of neighbors>
2)File containing the list of neighbours of all the nodes for the 1st cycle
3)File containing the list of neighbours of all the nodes for the 5th cycle
4)File containing the list of neighbours of all the nodes for the 10th cycle
5)File containing the list of neighbours of all the nodes for the 15th cycle
6)The node graph file for the 1st cycle in jpeg format
7)The node graph file for the 5th cycle in jpeg format
8)The node graph file for the 10th cycle in jpeg format
9)The node graph file for the 15th cycle in jpeg format
 
