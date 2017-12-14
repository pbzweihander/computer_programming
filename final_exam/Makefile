ZIPNAME=submit.zip
SUBZIPNAME=final01.zip

HEADER_FILE=header.txt
ROOT1_DIR=sorting_pairs/src
ROOT1_FILES=final_02.cpp
ROOT2_DIR=tree/src/main/java
ROOT2_FILES=Final3.java
SUB_DIR=circular_arrays/src/main/java
SUB_FILES=final_01.java CircularArray.java

submit: $(ROOT1_FILES) $(ROOT2_FILES) $(SUBZIPNAME)
	@echo " Zipping into $(ZIPNAME)"
	@cd out/root && zip $(ZIPNAME) ./*
	@echo " Moving $(ZIPNAME) to out folder..."
	@mv out/root/$(ZIPNAME) ./out
	@echo " Done!"

$(ROOT1_FILES): out
	@echo " Copy $@ into root folder..."
	@{ cat $(HEADER_FILE); cat $(ROOT1_DIR)/$@; } > out/root/$@

$(ROOT2_FILES): out
	@echo " Copy $@ into root folder..."
	@{ cat $(HEADER_FILE); cat $(ROOT2_DIR)/$@; } > out/root/$@

$(SUBZIPNAME): $(SUB_FILES)
	@echo " Zipping into $(SUBZIPNAME)..."
	@cd out/sub && zip $(SUBZIPNAME) ./*
	@echo " Moving $(SUBZIPNAME) into root folder..."
	@mv out/sub/$(SUBZIPNAME) out/root/

$(SUB_FILES): out
	@echo " Copy $@ into sub folder..."
	@{ cat $(HEADER_FILE); cat $(SUB_DIR)/$@; } > out/sub/$@

out:
	@echo " Generating folders..."
	@mkdir out
	@mkdir out/root
	@mkdir out/sub

clean:
	@echo " Cleaning..."
	@rm -rf out

