@ECHO OFF
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;./libs/*

java -Xms128m -Xmx384m -Xnoclassgc ResultRequest %1 %2 > %1-%2-results.txt

echo "Done. Please see %1-%2-results.txt for data"