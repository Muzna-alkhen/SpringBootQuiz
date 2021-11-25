# SpringBootQuiz
This is a Test quiz made up for maids.cc company

# Implementation Notes:
- Spring boot version 2.6.0
- using mysql connector 
# API's :
1) Products: 
   - fetch all products : GET:http://localhost:8080/product/all
   - create new product : POST:http://localhost:8080/product/create/
   - edit a product :     POST:http://localhost:8080/product/update/{id}
2) Client:
   - fetch all clients : GET:http://localhost:8080/client/all
   - create new client : POST:http://localhost:8080/client/create/
   - edit a client :     POST:http://localhost:8080/client/update/{id}
3) Sales:
   -  fetch all sales : GET:http://localhost:8080/sales/all
   - create new sale operation : POST:http://localhost:8080/sales/create/
   - edit a sale operation : POST:http://localhost:8080/sales/update/{id}
# Logging : 
- slf4j.Logger
- logging level -> info

