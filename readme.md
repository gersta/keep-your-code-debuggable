# Keep your code debuggable
This is the source repository for my talk at the TwoDigits winter summit about working in restricted environments.

The example shows the following:
- Usage of Spring profiles to switch injection of interface implementations on the fly
- Configuration of a Spring Boot application according to the active profile
- FileReaderWriter as a workaround to get database results locally if they cannot be reached via the network

# The problem
Imagine an application that connects to multiple database. Whenever you want to develop a new feature, you need to
connect to the databases to retrieve or write data. Now imagine that the database live in different networks
and unfortunately you live in yet another one, where you cannot connect to both at the same time. On top,
you can also not install the databases (or webservices in front of them) on your machine, because it's either
not your code or the software license is simply not available to you.

In order to develop new software now, you need to adjust the code, deploy it on a server, run it and react based
on log statements - Yikes!

What you really want: Some kind of local environment that proxys the real world as good as possible and allows you
to use the debugger again!

# How it works
This little demo shows an approach based on Spring Profiles and file access. You have two profiles: Server and Local.

When in the server profile, you fetch the results from the database query and then write them to a file. Locally, you
can then read those files. The only thing you need to do is set the profile accordingly. Either:

`-Dspring.profiles.active=server`

or 

`-Dspring.profiles.active=local`

## Dependency Injection
Spring Boot can inject components into other components so you don't have to worry about dependency management all to
much. But you cannot only inject concrete classes, but also interfaces and then let Spring figure out which
implementation to take. To combine this with profiles, you can set the `@Profile` annotation on the implementing
classes. Then, whenever one profile is set, only that class will be instantiated and therefor ready for injection. All
the other implementing classes are ignored. This allows you to quickly switch between the environments without
touching the code.

## Server profile
The server profile is basically the normal profile that you use to run the application on the server. This is where
you put your database configuration as I did in the `application-server.properties` file:

```
spring.datasource.url=jdbc:h2:file:./db/twodigits
spring.datasource.username=TwoDigits
spring.datasource.password=Summit
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop

spring.h2.console.enabled=true
```

## Local profile
The local profile on the other hand is only used to run the application on your local machine. The database connection
will not be created as it is only defined in the other profile. For the sake of simplicitly, I have put all the `local`
configuration into the `application.properties`:

```
# relative to the projects root folder when running via the idea
# otherwise relative to the JAR when deployed on a server
twodigits.debuggable.local-file.write-dir=./

twodigits.debuggable.local-file.read-dir=./
```

When in the local profile, you read the results from those files, that the server profile created beforehand.

# Things to keep in mind and limitations
Obviously, this is not a perfect solution. In a less restricted environment you might have Docker and even just
the software license to install the database tool locally and then put some data into that.

Also, in order to run the application locally, you need to run it on the server at least once and then also update the
data files locally whenever the schema or the data changes. You might be able to automate this to some degree, e.g.
via a nightly cron job to run the requests on the server and then copy the files into a specified directory, which
is available to everyone