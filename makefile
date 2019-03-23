exec: \
compile

compile: \
TMAN.java
	javac -cp ".:jcommon-1.0.22.jar:jfreechart-1.0.18.jar" TMAN.java
