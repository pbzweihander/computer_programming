.SUFFIXES: .java .class
JAVAC = javac
JAVA = java
JFLAGS = -Xlint
OUTPUT_DIR = out
SOURCE_DIR = src
VPATH = $(SOURCE_DIR):$(OUTPUT_DIR)
TARGET = Lab09Tester

all: $(TARGET).class

run: all
	$(JAVA) -cp $(OUTPUT_DIR) $(TARGET)

.java.class:
	$(JAVAC) $(JFLAGS) -cp $(OUTPUT_DIR) -d $(OUTPUT_DIR) $<

clean:
	rm -rf $(OUTPUT_DIR)

.PHONY: all clean run

Lab09Tree.class: Lab09Node.class
Lab09Tester.class: Lab09Node.class Lab09Tree.class
