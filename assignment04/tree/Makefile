.SUFFIXES: .java .class
JAVAC = javac
JAVA = java
JFLAGS = -Xlint
OUTPUT_DIR = out
SOURCE_DIR = src
VPATH = $(SOURCE_DIR):$(OUTPUT_DIR)
TARGET = Main

all: Tester4_1.class Tester4_2.class

run: all
	$(JAVA) -cp $(OUTPUT_DIR) Tester4_1
	$(JAVA) -cp $(OUTPUT_DIR) Tester4_2

1: Tester4_1.class

2: Tester4_2.class

run1: 1
	$(JAVA) -cp $(OUTPUT_DIR) Tester4_1

run2: 2
	$(JAVA) -cp $(OUTPUT_DIR) Tester4_2

test: Main.class
	$(JAVA) -Xss4M -Xmx128M -cp $(OUTPUT_DIR) Main check

.java.class:
	$(JAVAC) $(JFLAGS) -cp $(OUTPUT_DIR) -d $(OUTPUT_DIR) $<

clean:
	rm -rf $(OUTPUT_DIR)

.PHONY: all clean run 1 2 run1 run2

Node4_1.class: Node4_1.java
Assignment4_1.class: Node4_1.class Assignment4_1.java
Assignment4_2.class: Node4_1.class Assignment4_2.java

Tester4_1.class: Assignment4_1.class Tester4_1.java
Tester4_2.class: Assignment4_2.class Tester4_2.java

Main.class: Assignment4_1.class Assignment4_2.class Main.java
