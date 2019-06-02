# E-Commerce

E-Commerce is a simple Spring Boot e-commerce backend application which helps to 
   - Order items
   - Add/delete/update/search items
   - View all orders
   - View all items
   
All this can be achieved via the help of a REST API.

#### Configuration

Go to application.properties and change the MySQL configuration.
Fill the fields \<db-name\>, \<username\> and \<password\>.

```sh
spring.datasource.url = jdbc:mysql://localhost:3306/<db-name>?useSSL=false
spring.datasource.username = <username>
spring.datasource.password = <password>
```


#### Run

```sh
mvn spring-boot:run
```


### Examples

Search for an item using id
```bash
curl -X GET \
  http://localhost:8080/api/items/1 \
  -H 'Host: localhost:8080'
```

Create an item
```bash
curl -X POST \
  http://localhost:8080/api/item \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '{
	"name": "Adidas Gazelle",
	"quantity": 10,
	"cost": 4599.0,
	"itemCategory": "Shoe"
}'
```

Update an item
```bash
curl -X PUT \
  http://localhost:8080/api/items/2 \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '{
	"name": "Adidas Gazelle 2.0",
	"quantity": 4,
	"itemCategory": "Shoe",
	"cost": 3999.0
}'
```

Delete an item

```bash
curl -X DELETE \
  http://localhost:8080/api/items/2 \
  -H 'Host: localhost:8080'
```

View all Orders
```bash
curl -X GET \
  http://localhost:8080/api/orders \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '{
	"customerId": 1,
	"numberOfItemsOrdered": 10,
	"customerEmail": "onlyraj720@gmail.com"
}'
```

Create order/orders

```bash
curl -X POST \
  http://localhost:8080/api/orders \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '{
	"orders": [
		{
		    "id": 3,
		    "customerId": 1,
		    "itemId": 1,
		    "numberOfItemsOrdered": 1,
		    "customerEmail": "onlyraj720@gmail1.com"
		},
		{
		    "id": 1,
		    "customerId": 1,
		    "itemId": 3,
		    "numberOfItemsOrdered": 2,
		    "customerEmail": "onlyraj720@gmail.com"
		},
		{
		    "id": 4,
		    "customerId": 1,
		    "itemId": 3,
		    "numberOfItemsOrdered": 50,
		    "customerEmail": "onlyraj720@gmail3.com"
		}
	]
}'
```


### Development

Want to contribute? Great!

### Todos
 - Add more features
 - Add Test cases