# DEMO API#

This Spring Boot project showcases the use of Spring Boot 2.2.2.RELEASE with an H2 database and Spring Data JPA.

## **User-Interface** ##

See the front-end demonstration on https://github.com/kassiogo/demo-frontend.

## **API** ##

The app backend offers 1 endpoint:

### POST / 

**Request Body**

```
{"name":"<name of the user>"}
```

**Response Body**

```
{
    
}
```
**Notes:**
- the **contactInfo** object is not returned when the customer doesn't have contact information in our database; and
- customer **size** is: **Small**, when **# of employees** is <= 10; **Medium** when it is <= 1000; **Big** otherwise.
   
