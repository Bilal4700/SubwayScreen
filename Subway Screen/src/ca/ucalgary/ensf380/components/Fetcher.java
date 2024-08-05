package ca.ucalgary.ensf380.components;

//Make it compulsory for every inheritant class to fetch the data from api

abstract class Fetcher {

	abstract void fetch() throws Exception;
	
}
