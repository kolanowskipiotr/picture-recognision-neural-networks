# to run in ide
`mvn clean compiler:compile exec:java`

# to build single jar with all dependencies
`mvn clean compile assembly:single`
jar will be stored in /target

# to run from command line
`java -jar [single_jar_file_name].jar`