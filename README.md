# CZ2002_OOProject
================
## 3.  Design considerations & use of OO concepts
As the focus of this course is object oriented programming, we have decided to use an object database(db4o) for persistence of our objects as this would preserve the uniqueness of our objects without needing a unique key attribute for each object. Using an object database has also allowed us to focus on the logic of the program as opposed to wasting time on data management. Also this would allow us to not be accused of plagiarism as any code using a file reader would likely trigger turnitin’s plagiarism algorithms.  

Due to this course also focusing on OOP and not on data entry, we have decided to minimized the amount of data needed to create an object and instead just have a name an ID. Requiring less attributes in an object will also allow us to demonstrate the program in a timely fashion and still showing the capabilities. Furthermore, adding additional attributes would be easy to implement in the future if required as there is full separation between entity and business logic.  

Although the recommended data structure for an aggregation is a set due to having only unique elements, we have used a list as the set interface does not provide a method for getting an element by position due to the nature of a set. Thus, we have included constraints to prevent duplicate objects.  

For our course classes we chose to use a single class to represent any class with a type attribute defining the class type. This allows us to easily add or remove class types as required instead of using Java reflection or creating 3 sub classes that have the only difference in the value of a type attribute and class name.  

For validation and invalid input, we have tried hard to disallow invalid input as opposed to handling invalid input. For example, when choosing a single unique object, the user can select the object from an easy-to-use list or search for the object by predefined attributes.   

In the first case, a single method lets the user select a single object from any array of objects that implement a <choosable> interface. If the user decides not to select any object, the method returns null and the user is taken back to the main menu.  

For validity of input like limiting names to [aA-zZ ], we have decided that as NTU is a globalized university, we will not restrict anything to an Anglicized preconceived notion of naming but instead allow any input supported by Unicode other than a carriage return.  The only exception to this is NTU's internal identification like course code and student ID.  

For exam and coursework components, due to them having mostly similar attributes and functions, we have extended them from an abstract <ExamComponent> class. The only difference in the 2 classes is that a single exam is owned by a course while many coursework components are owned by the coursework.  

Furthermore, for exam and coursework, we have chosen to use a weighted percentage system to compute the final percentage as opposed to using a hard percentage of 100. We feel this be more intuitive for users as in test cases suggested, if a percentage of 50% was entered for the coursework, the user had to make sure the coursework components added up to 50%. Whereas in a weighted percentage, the user just needs to make sure they are in the correct ratios. Our program will take care of making sure they add up correctly in terms of the weights given.  

For our result class, it has a one-to-many relationship with both the the student and ExamComponent. Therefore, the uniqueness of a result object is defined its student and ExamComponent objects (or foreign keys if in a relational database). Our system validates and maintains this uniqueness at data creation time.  

For the use of OO concepts, we have tried to the SOLID principles where possible. All our objects have a Single responsibility – that of storing data and connections to other objects. This  leads to us to the Open/closed principle where the entities can be extended but modifying their attributes would lead to the program crashing. For the Liskov substitution principle, our child objects can easily replace their parent objects. In following the Interface segregation principle, we created an interface that specific objects use instead of using general interfaces already implemented.  

