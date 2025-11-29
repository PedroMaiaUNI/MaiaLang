@echo off
call cleanup.bat

set "flex=lib\jflex-full-1.9.1.jar"
set "cup=lib\java-cup-11b.jar"
set "libs=.;lib\java-cup-11b.jar;lib\java-cup-11b-runtime.jar;lib\jflex-1.8.2.jar"

java -jar %flex% scanner.flex
java -jar %cup% -parser parser -symbols sym parser.cup
javac -cp "%libs%" *.java
java -cp "%libs%" Main