DPSS
====

Distributed Player Status System (DPSS) using Java RMI

This distributed System that manages players status across multiple game servers. 
In this system, we have three game servers for North-America(NA), Europe(EU) and
Asia(AS). Each server manages a number of player accounts stored in a hash table.
There are two types of users. Players and administrators which both have accounts.
The Game Servers communicate with each other through UDP while the users each communicate 
with their associated server based on IP-Address through RMI.
Below, you can see a class diagram of the program.

![Alt text](/ClassDiagram.jpg "UML Class Diagram")

