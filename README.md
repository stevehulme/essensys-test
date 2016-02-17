# essensys-test

Task to parse and update call records from a spreadsheet.

There are currently two branches, one master with the core requirement and one with the summary by day functionality which is taken from the 
last master commit and includes some refactoring (I've not merged the branch back on to master to allow a comparison)

To build run 'mvn clean install' from the root directory

To execute run 'java -jar target/essensys-test-1.0-SNAPSHOT.jar call_data.csv' from the root directory (the file is part of the project)