# EComm-Main
Run Application:

To start the application right click on Application.java --> Run as --> Java Application.
spring-boot-starter-tomcat will enable an embedded Apache Tomcat 7 instance, by default.

The base URL would be http://localhost:8182/

Use any Rest Client (I'm using Postman) to interact with HTTP APIs. You can access Category and Product as Admin using POST method
with URL as http://localhost:8182/admin/category and http://localhost:8182/admin/product. Provide JSON object in the body for category 
as {
	"mainCategoryName" : "main1",
	"subCategoryName" : "sub1"
}

and send the request.
FOR PRODUCT (JSON Object)
{
	"productName" : "pname",
	"productSummary" : "psumm",
	"productDescription" : "pdesc",
	"productDate" : "2009-02-15",
	"productPrice" : 20,
	"productCondition" : "pcon",
	"unitInStock" : 10,
	"productCategory" : {
  "categoryId": 1,
  "mainCategoryName": "main",
  "subCategoryName": "sub"
}
}

To create a customer for the cart you can access URL http://localhost:8182/customer using POST request and JSON Object as 
{
  "customerId": 1,
  "email": "bell.media@gmail.com",
  "password": "hello",
  "customerName": "Bell",
  "registerDate": 1234656000000,
  "enabled": true,
  "cart": {
    "cartId": 1,
    "cartItems": null,
    "grandTotal": 0
  },
  "roles": null,
  "productComments": null,
  "shippingAddresses": null
}
You can use Customer ID in header and get the customer list using GET method.
Using GET methodand URL http://localhost:8182/cart one can provide cartId_ in Header and get the response.
Using URL http://localhost:8182/customer/list with GET method we can access the customer list
