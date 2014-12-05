
JAVA_HOME ?= /usr

all: build test

build:
	mkdir -p bin
	${JAVA_HOME}/bin/javac -d bin src/*java

test:
	${JAVA_HOME}/bin/java -version
	${JAVA_HOME}/bin/java -cp bin AnnotationBugTest

clean:
	rm -rf bin
