<h1>How to run</h1>
<h2>Database</h2>
I tried to get H2 setup but IntelliJ wouldn't connect to it which made debugging a nightmare.

To get around this I have been running a local postgresql instance. You can set one up and then adjust application.properties to point to it.

I have also provided schema.sql and data.sql (main/resources) which you can use to populate the database. You will need to create the users and accounts (note the password is hashed using BCrypt with 12 rounds).

Passwords are "test"
<h2>Minting server</h2>
I have set  up a local server to deal with oauth related things. You can run the docker container and adjust application.properties (shouldn't be necessary).

```agsl
docker run --rm --name sso -p 9000:9000 ghcr.io/vmware-tanzu-learning/course-secure-rest-api-oauth2-code/sso:latest

```
