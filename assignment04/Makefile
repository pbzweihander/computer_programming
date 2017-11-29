ZIPNAME=submit.zip
SUBZIPNAME=assignment4_3.zip

submit: out
	cp tree/src/Assignment4*.java out/tree
	cp string/src/main/java/LinkedString.java out/string/
	cd out/string && zip $(SUBZIPNAME) ./*
	mv out/string/$(SUBZIPNAME) out/tree/
	cd out/tree && zip $(ZIPNAME) ./*
	mv out/tree/$(ZIPNAME) ./
	rm -rf out

out:
	mkdir out
	mkdir out/tree
	mkdir out/string

clean:
	rm -rf out
	rm -rf $(ZIPNAME)

