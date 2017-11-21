ZIPNAME=submit.zip

submit:
	mkdir out
	mkdir out/cube
	mkdir out/zoo
	cp cube/src/Assignment3_Cube.java out/cube/
	cp cube/src/Assignment3_Side.java out/cube/
	cp zoo/src/Animal.java out/zoo/
	cp zoo/src/Carnivore.java out/zoo/
	cp zoo/src/Herbivore.java out/zoo/
	cp zoo/src/Omnivore.java out/zoo/
	cp zoo/src/Manage.java out/zoo/
	cp zoo/src/Room.java out/zoo/
	cd out/zoo && zip Assign3_2.zip ./*
	mv out/zoo/Assign3_2.zip out/cube/
	cd out/cube && zip $(ZIPNAME) ./*
	mv out/cube/$(ZIPNAME) ./
	rm -rf out

clean:
	rm -f $(ZIPNAME)
