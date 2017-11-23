CXX = g++
CXXFLAGS = -std=c++11
OUTPUT_DIR = out
SOURCE_DIR = src
VPATH = $(SOURCE_DIR)

all: ex1

ex1: out ex1.cpp
		$(CXX) $(CXXFLAGS) $(SOURCE_DIR)/$@.cpp -o $(OUTPUT_DIR)/$@

out:
		mkdir $(OUTPUT_DIR)

clean:
	rm -rf $(OUTPUT_DIR)

.PHONY: all clean
