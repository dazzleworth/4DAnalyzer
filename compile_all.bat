@echo off

REM Two-stage compilation process

gcc -Wall -c -g -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" permute.c -o permute.o

REM Link with GCC standard libraries

gcc -static -static-libgcc -shared -o permute.dll permute.o

REM Now compile classes

javac -cp ".;libs\*" *.java