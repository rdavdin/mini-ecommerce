# eCommerce Application

A mini e-commerce application with 4 models:

- **User**: hold user account information
- **Cart**: hold a User's items
- **Item**: define new items
- **UserOrder**: hold information about submitted orders

[The requirement in detail here](./requirement.md)

### Purposes

- Implement proper authentication and authorization controls so users can only access their data, and that data can only be accessed in a secure way
- Customize **Spring Security** with JSON Web Token (**JWT**) for securing REST APIs
- Use CI/CD tools including **Git**, **Docker**, **Jenkins**, etc to setup a CI/CD pipeline on an **AWS** instance

### Results

- Have a look to [Swagger docs here](http://159.65.133.69/ecom/swagger-ui/index.html) to test 

**Note:** To authorize APIs for testing, you can use this header:

```
Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoyNjk0ODg1NjM2fQ.qlLIwQmU0GxSnlR7Sy3hSRFDTuXMInznNtIma2AkDHIiRGLJ1RG7yQdK6jXdFMzmCkPE2LfG_Xvyk-N2fStqRg
 ```

- username: ```user```
