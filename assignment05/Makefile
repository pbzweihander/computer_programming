ZIPNAME=submit.zip
SUBZIPNAME=Assign5_2.zip

HEADER_FILE=header.txt
ROOT_DIR=tree/src/main/java
ROOT_FILES=Assignment5_1.java Queue.java StringQueue.java
SUB_DIR=vector/src
SUB_FILES=MyVector.cpp MyVector.h

submit: $(ROOT_FILES) $(SUBZIPNAME)
	@echo " Zipping into $(ZIPNAME)"
	@cd out/root && zip $(ZIPNAME) ./*
	@echo " Moving $(ZIPNAME) to out folder..."
	@mv out/root/$(ZIPNAME) ./out
	@echo " Done!"

$(ROOT_FILES): out
	@echo " Copy $@ into root folder..."
	@{ cat $(HEADER_FILE); cat $(ROOT_DIR)/$@; } > out/root/$@

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

