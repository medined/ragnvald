# RAGNVALD

This project helps Pokemon collectors.

# Get It

```
git clone https://github.com/medined/ragnvald.git
```

# Compile It

```
mvn package
```

# Run It

```
java -jar target/ragnvald-0.0.1-SNAPSHOT.jar
```

# See It

```
firefox http://localhost:8080
```

# See the Database Console

```
firefox http://localhost:8080/console
```

Use the following information to connect to the in-memory database:

URL: jdbc:h2:mem:testdb
USER: sa
PASSWORD: {blank}

# Database

This project uses JPA to access a database. While in development it is using
the H2 in-memory server. 

## Adding a Record

```
PokomonSetRepository repository = context.getBean(PokomonSetRepository.class);
repository.save(new PokemonSet(1, "Base Set", "baseSet", 102));
repository.save(new PokemonSet(2, "Jungle", "jungle", 64));
```
