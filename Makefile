.PHONY: build clean run tema3

build: tema3

run:
	java -Xmx1G -jar Tema3.jar ${ARGS} 
	#java -Xmx1G Tema2

	
tema3:
	javac tema3/*.java tema3/exceptions/*.java tema3/hero/*.java tema3/labyrinth/*.java
	cp tema3/*.class . 
	cp tema3/exceptions/*.class .
	cp tema3/hero/*.class .
	cp tema3/labyrinth/*.class .
	jar cfe Tema3.jar tema3.Tema3 tema3/*.class tema3/exceptions/*.class tema3/hero/*.class tema3/labyrinth/*.class
	#jar cfe Tema2.jar Tem/a2 Tema2.class

clean:
	rm Tema3.jar
	rm -f *.class
	rm tema3/*.class 
	rm tema3/exceptions/*.class 
	rm tema3/hero/*.class 
	rm tema3/labyrinth/*.class 