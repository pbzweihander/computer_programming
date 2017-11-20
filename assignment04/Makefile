ZIPNAME=submit.zip

submit: out
	cp tree/src/Assignment4*.java out/
	cp string/src/LinkedString.java out/Assignment4_3/
	cd out && zip -r $(ZIPNAME) ./*

out:
	mkdir out
	mkdir out/Assignment4_3

clean:
	rm -rf out

