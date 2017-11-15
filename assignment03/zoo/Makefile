.SUFFIXES: .java .class
JAVAC = javac
JAVA = java
JFLAGS = -Xlint
OUTPUT_DIR = out
SOURCE_DIR = src
VPATH = $(SOURCE_DIR):$(OUTPUT_DIR)
TARGET = Main

all: $(TARGET).class

run: all
	$(JAVA) -cp $(OUTPUT_DIR) $(TARGET)

.java.class:
	$(JAVAC) $(JFLAGS) -cp $(OUTPUT_DIR) -d $(OUTPUT_DIR) $<

clean:
	rm -rf $(OUTPUT_DIR)

.PHONY: all clean run

Carnivore.class: Animal.class
Herbivore.class: Animal.class
Omnivore.class: Animal.class
ANIMALS = Carnivore.class Herbivore.class Omnivore.class

Room.class: $(ANIMALS)

ManageInfo.class: Room.class

Manage.class: ManageInfo.class Room.class Report.class

Main.class: Manage.class Report.class ManageInfo.class
