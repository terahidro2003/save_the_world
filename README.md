# Save The World
This project is a Java implementation of the DECIDE assignment detecting and intercepting ballistic threats.

## What the Project Does
The DECIDE program evaluates tracking date and parameters to determine whether or not an interceptor launch should be authorized

Given radar tracking points, parameters, and logical constraints, the program evaluates a set of Launch Interceptor Conditions as defined in the DECIDE manual. The conditions arelater used according to the Logical Connector Martix and Preliminary Unlocking Vector in order to know the relevant conditions. After this process, the program comes to a conclusion of whether or not to launch the interceptor.

## Prerequisites
- JDK 22 or higher installed
- Maven

## Getting Started

1. Clone the repository:
   ```bash
   git clone git@github.com:terahidro2003/save_the_world.git
   ```
2. Compile project using Maven:
   ```bash
   ./mvnw clean install compile
   ```   
3.  Run the tests to ensure everything is working:
   ```bash
   ./mvnw clean test
   ```
4. Run the application (Main class):
   ```bash
   ./mvnw exec:java -Dexec.mainClass="com.skarbalius.Main"

   ## License
This peoject is licensed under the BSD 2-Clause License. See the License file for more information.


## Statement of Contributions
 
 Alberto Rayon: Helped in pair programming for the implementation of Launch Interceptor Conditions 0-2. Implemented LIC's 3 through 8 and wrote unit tests for each. After the conditions were met, worked on refactoring the LIC's. Also responsible for README file contents.

 Juozas Skarbalius: Set up the initial project structure, including Maven configuration, input parsing, chain of responsibility design pattern, and the README. Implemented task handling logic, and contributed to refactoring as well as the implementation and testing of PUM and LCM functionality.

 Adrian Grund: Implemented Launch Interceptor Conditions 0, 1, 2, 9, 10, 11, 12, 13, and 14 and wrote unit tests for these conditions. Contributed to refactoring and cleanup efforts, reorganized condition-related files, improved test structure, and managed project merges. Also completed the way of working assessment and integration tests 2 & 3.

 Jintong Jang: Implemented the PUMHandler and its associated unit tests, contributing to the logic responsible for combining condition evaluations.

 Bahar Kimanos: Added the FUV and final decision handler tests and its respective tests. Refactrored the FUV handler to make some fixes. 
