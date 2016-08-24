/**
 * Transfer Objects
 * Data Transfer Objects (DTOs) are Plain Old Java Objects (POJOs)
 * 
 * used to carry data to and from different application layers. Transfer objects
 * are lightweight objects which only contain mutators and accessors (like
 * JavaBeans). Moving data through the application by using transfer objects
 * enforces consistency and makes application code much more readable than
 * passing a large number of arbitrary method parameters.
 * 
 * Required Skills
 * Software developers from the UI tier all the way down to the
 * Hibernate tier should have at least a basic understanding of these
 * objects. Those involved in the database, Hibernate and DAO layers
 * need extensive knowledge of these objects.
 * 
 * Practices and Theory
 * • DTOs along with the APIs for each layer help to define the
 *   contractual agreement between architectural layers.
 * • DTOs may contain other DTOs or collections of DTOs.
 * • DTOs should contain mutators, accessors and constructors.
 * 
 * Database Persistent DTOs
 * • Usually used when passing data from any layer into the database.
 * • They are serializable so that they can be used in a clustered
 */

package com.baseframework.domain;

