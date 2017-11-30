@ECHO OFF
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;libs\*

java -Xms128m -Xmx384m -Xnoclassgc SearchRequest