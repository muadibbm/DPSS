DPSS
====

Distributed Player Status System (DPSS)
---------------------------------------

This distributed System that manages players status across multiple game servers. 
In this system, we have three game servers for North-America(NA), Europe(EU) and
Asia(AS). Each server manages a number of player accounts stored in a hash table.
There are two types of users. Players and administrators which both have accounts.

DPSS using Java RMI
-------------------

The Game Servers communicate with each other through UDP while the users each communicate 
with their associated server based on IP-Address through RMI.
Below, you can see a class diagram of the program.

![Alt text](/ClassDiagram.jpg "UML Class Diagram")

DPSS using Java IDL
-------------------

The same implementation is kept but instead of RMI, the system implements CORBA using Java IDL for
communication between clients and servers. There is also changes that have been added to improve concurrency and
efficiency as a distributed system.

