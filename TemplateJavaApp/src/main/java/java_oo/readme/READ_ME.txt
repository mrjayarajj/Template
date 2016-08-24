
-----
GRASP 
-----

1. Creator
	What object should create object B?
	Object A should create object B if:
	Object A contain object B
	Object A save object B to a file or database.
	Object A use object B
	Object A has all the data needed to instantiate object B
	
	if classes are very similar extend them from a class or interface
	implement getter and setter for all the classes
	implement the saving and loading of class information from files and database inside of the classes. 	

2. Expert
	if object A is going to instantiate object B, it must have the info needed to create it.
	Object A must be the expert on how to create object B
	

3. Low Coupling

	Dependence: Class depends on another class, but it isn't a member of the class "Uses A"
	Association: Class contain a reference to another "Has A"
	Composition/Aggregation: class holds an instance of another "Owns A/Part Of"
	Inheritance: Implement or extend another class "Is A" 
	
	Design class that are independent so that changes in other class have no effect
	Avoid creating sub classes and if you do sub class interface or abstract class
	Add flexibility and encapsulation to classes to avoid major problem from high coupling

4. Controller

	what object stand between the UI and coordinates system operations ?
	The controller represents the device that the other software runs in
	The controller will be the object that connects to most other objects in the sequence diagram.
	
	The UI forwards client request to the controller
	The controller forwards request to the proper system objects
	This lowers coupling of the UI to system objects
	This allows the UI to change without effecting the system
	The UI should never handle logic, if so it breake's the re-usability
	The UI shoudn't handle system events
	If your controller is bloated create many of them representing different parts of the UI
	
5. High Cohesion

	A measure of how focused the responsibility of an object are
	class should be easy to comprehend,maintain and reusable.
	create class that handle a few responsibility for one part of the system
	class should have very few methods and should work with other classes to fulfill a complicated need
	if in doubt delegate
	
6. Polymorphism

	Polymorphism is used to dynamically handle similar object types
	<eliminate conditional statements>
	
	
7. Pure Fabrication

	create classes to improve code reuse while limitting coupling
	pure Fabrication = no real world counterpart
	Information experts cause high coupling
	GOF patterns: adapter, command, Strategy , etc
	
8. Indirection

	Avoid coupling by separating objects with an intermediate object
	Adapters allow system object to interact with external interfaces.
	GOF: Adapter,Bridge,Facade,Observer,Mediator
	
9. Protected variations (encapsulation)

	How do you design objects so that that variation doesn't have bad effects?
	Solution: Look for elements with coupling that may chane and surrounds then with a stable interface.

 