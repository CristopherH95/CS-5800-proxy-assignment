# CS-5800: Proxy Assignment

This repository contains a simulation of a song data lookup backend for an assignment in CS-5800 (Advanced Software Engineering).
The proxy design pattern is used to wrap the simulated server lookup and cache results.
The `driver` package implements a basic program demonstrating the instantiation of the different classes implemented here.
An example of its output can be found in the `output.png` file.

## Build & Run

To build with Maven, simply navigate to the project root in the command line and run:

```shell
mvn package
```

Alternatively, IDEs such as IntelliJ should be able to build the source files using their standard build utilities.

Once built, the project can be run using the `driver` package:

```shell
java -cp ./target/proxy-assignment-1.0-SNAPSHOT.jar driver.Main
```
