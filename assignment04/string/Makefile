.SUFFIXES: .java .class
.java.class:
		$(JAVAC) $(JFLAGS) -cp $(OUTPUT_DIR) -d $(OUTPUT_DIR) $<
JAVAC = javac
JAVA = java
JFLAGS = -Xlint
OUTPUT_DIR = out
VPATH = $(OUTPUT_DIR)
TARGET = Main

all: $(TARGET).class

run: all
		$(JAVA) -cp $(OUTPUT_DIR) $(TARGET)

clean:
		rm -rf $(OUTPUT_DIR)

.PHONY: all clean run

LinkedStringInterface.class: LinkedStringInterface.java

LinkedList.class: LinkedListNode.java LinkedListIterator.java LinkedList.java
		$(JAVAC) $(JFLAGS) -cp $(OUTPUT_DIR) -d $(OUTPUT_DIR) LinkedListNode.java LinkedListIterator.java LinkedList.java

LinkedString.class: LinkedStringInterface.class LinkedList.class LinkedString.java
Main.class: LinkedString.class Main.java
