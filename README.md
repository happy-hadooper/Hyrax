# Hyrax
Hadoop Logs Analyzer

# Example
Tun run app use:
./bin/spark-submit --class ua.hyrax.parse.SparkHyrax --master local[4] parse-1.2-SNAPSHOT.jar file:///opt/mapr/spark/spark-1.6.1/yarn-mapr-resourcemanager-123.log > hyrax.log

