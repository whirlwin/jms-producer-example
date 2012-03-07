# JMS producer example

## Important note
Start by setting up [my other project first]
(https://github.com/whirlwin/jms-consumer-example). It's easier that way.

## Requirements
- Eclipse IDE for Java EE Developers (or similar)
- GlassFish
- A destination and preferably a consumer.


## Instuctions

1. Clone this repository to your computer.

2. Import as a regular Java project into your workspace.

3. Right click the project and select `Properties`.

4. Select `Java Build Path` and `Libraries`. Click `Add external JARs...`.
Locate `javaee.jar` (usually inside `path/to/glassfish/glassfish3/lib`). Click
`OK`. Click `. Do the same for `gf-client.jar` and `jndi-properties.jar`. Click
`OK`.

## Testing the application
Run it. After a few seconds check the GlassFish log, usually located at
`path/to/glassfish/installation/glassfish3/domains/domain1/logs/server.log`.

The last entry will show what the MDB (consumer) has logged if everything is set
up correctly.
