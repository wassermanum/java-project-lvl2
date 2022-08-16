# Makefile
run-dist:
	./build/install/app/bin/app

coverage:
	gradle JacocoReport
    
