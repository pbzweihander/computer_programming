ZIPNAME=submit.zip


submit: out
	cp cube/src/Assignment3_Cube.java out/cube/
	cp cube/src/Assignment3_Side.java out/cube/
	cp zoo/src/Animal.java out/zoo/
	cp zoo/src/Carnivore.java out/zoo/
	cp zoo/src/Herbivore.java out/zoo/
	cp zoo/src/Omnivore.java out/zoo/
	cp zoo/src/Manage.java out/zoo/
	cp zoo/src/Room.java out/zoo/
	cd out && zip -r $(ZIPNAME) cube zoo

out:
	mkdir out
	mkdir out/cube
	mkdir out/zoo

clean:
	rm -rf out

