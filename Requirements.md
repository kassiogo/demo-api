# DEMO API#

The **Demo API** bla bla. 

## **User-Interface** ##

It's no front-end at this first moment.

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
   
