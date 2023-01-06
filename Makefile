CC = g++

test: PortalMain.o DemoPortal.o
	$(CC) PortalMain.o DemoPortal.o -o test
#executable of name test is created. use ./test to run

PortalMain.o: PortalMain.cpp
	$(CC) -c PortalMain.cpp

DemoPortal.o: demo/DemoPortal.h demo/DemoPortal.cpp
	$(CC) -c demo/DemoPortal.cpp

clean:
	rm *.o test
#deletes all .o files from the directory