To test:

mvn test

To run:

mvn package:
java -jar target/http_stats-1.0-SNAPSHOT.jar [file_with_events] url_to_check

if file_with_events is omitted events will be read from standard input.