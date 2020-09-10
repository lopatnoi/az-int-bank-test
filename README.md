
Project servers:
---
<ul> 
<li> Keycloak server: <a href="http://localhost:9999/auth/">http://localhost:9999/auth/</a>
<li> Login server: <a href="http://localhost:8080">http://localhost:8080</a>
<li> Resource server: <a href="http://localhost:9090">http://localhost:9090</a>
</ul> 

#### Keycloak server:
<a href="https://www.keycloak.org">https://www.keycloak.org <br>
Server used OAuth2 authentication. 
It's provides sign-in sign-up services.

#### Login server:
Login with OAuth2 by client credentials.
It's uses authorized client to connect with remote resource server.  

#### Resource server:
A resource server which provides product list.

## How to start
1 Run Keycloak server on `/keycloak` folder by Docker Compose.
```
 docker-compose up -d
```

2 Wait while it successfully started. Check it logs.
```
docker-compose logs -f
```
3 Start `LoginApplication` and `ResourceApplication` in free order.

4 Connect first time to 
<a href="http://localhost:8080">http://localhost:8080
or
<a href="http://localhost:8080">http://localhost:8080/products <br>
You will be redirected to login page

5 Register user and login with it. For example `user` as username and `password` as password.

6  Use <a href="http://localhost:8080">http://localhost:8080 or <a href="http://localhost:8080">http://localhost:8080/products
to see the result after authentication.

### Notes:
Before run tests you should also run Keycloak server.