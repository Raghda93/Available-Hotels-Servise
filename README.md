# Available-Hotels-Servise
This service used to get all available hotels from other provider services.


Discribtion :

I used RESTful web service to catch the request, using "HotelsService" class.
The URI : /main/AvailableHotels/search.
The service will read the request parameters as a QueryParam.

My sqrvice will send a request for each provider url to get the result.
In this project I assumed that each provider service will recive the request parameters as a query parameters and return the response as json object.
ex. : The Json object for BestHotels provider :
[
{"hotel":"hotelName1", "hotelRate":5 , "roomAmenities" : { "menities1" : "value1" , "menities2" : "values2"}},
{"hotel":"hotelName2", "hotelRate":4 , "roomAmenities" : { "menities1" : "value3" , "menities2" : "values4"}}
]


The code written to make the process in the future scallable for adding a new providers, how can you add a new provider ?
1. By add a new properties file in (WebContent/WEB-INF/properties) folder (you can use "providerHotelsConfig.properties.copy" cope and remove the extension ".copy") ==> the properties file used to map between my service parameters and other providers services.
2. Fill it with the provider parameters name and types.
3. Add a new instance in HotelsConfig class.



