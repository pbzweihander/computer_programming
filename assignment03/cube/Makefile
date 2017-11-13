JAVAC = javac
JAVA = java
JFLAGS = -source 1.8
OUTPUT_DIR = out
SOURCE_DIR = src
VPATH = $(SOURCE_DIR):$(OUTPUT_DIR)

all: Test_Cube.class

Test_Cube.class: out Assignment3_Cube.java Assignment3_Side.java Test_Cube.java
	$(JAVAC) $(JFLAG) -d $(OUTPUT_DIR) $(SOURCE_DIR)/*

out:
	mkdir $(OUTPUT_DIR)

run: all
	$(JAVA) -cp $(OUTPUT_DIR) Test_Cube

clean:
	rm -rf $(OUTPUT_DIR)

.PHONY: all clean run
