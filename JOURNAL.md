# Development Journal — Workout Tracker

> A daily log of progress, decisions, and lessons learned while building the Workout Tracker API.

**Author:** Zach Christensen
**Stack:** Spring Boot 4.1 · Java 21 · Spring Data JPA · Flyway · Spring Security · PostgreSQL

**Tags:** `feat` · `fix` · `refactor` · `infra` · `docs` · `spike` · `chore`

---

## Entries

### 2026-08-11 · — WorkoutMapper/WorkoutService

## **What I did**

- WorkoutMapper
  - job = turn workout from database into json shape that api returns

- WorkoutService 
  - job = handles workout business logic for user. Mirrors ExerciseService

## **Decisions**

## **Blockers / Notes**
```
Data flow review and update:
```
- Client sends JSON {"exerciseName": "Bench Press"} ... whatever it is
- Controller turns JSON into ExerciseRequest and call ExerciseService.create
- ExerciseService asks UserRepository (DB functions) for user. Either loads User entity or throws 
  exception.
  - builds exercise entity (Java mirror for table row) from request
  - asks ExerciseRepository.save to write it
- ExerciseRepostory talks to Postgres
- ExerciseService builds and ExerciseResponse and returns
- Controller sends JSON back
- Works similarly for workout

## **Next up**
- Controllers

### 2026-08-6 · — Refresh

## **What I did**

## **Decisions**

## **Blockers / Notes**

I haven't touched this in a couple weeks so I am just refreshing on what I have 
built and the flow of the application.

- Docker
  - Runs Postgres in a container. Volume keeps the data (folder on your machine that survives when the container is deleted).
- Postgres
  - Database where my tables live. App connects through application.yml.
  - Flyway creates the tables from SQL. Hibernate just checks they match my Java.
- Entities
  - Java versions of the tables (User, Exercise, Workout, WorkoutSet).
  - Repositories = save/find/delete against the DB.
- DTO
  - Small data bag for talking to the outside world (JSON in/out). Entity is for the DB; DTO is for the API.
  - Request = what the client sends in. Response = what I send back. Keeps entities inside the app.
  - Exercise service done. Workout mapper + service still next.
## **Next up**

### 2026-07-21 · — App layers + DTOs

## **What I did**

```

```

## **Decisions**
- Some of this could have gone in the previous entry but this is how the app layers
work. controller -> service -> repository.
  - Controller - Talks http. recieve json, return json.
  - service - who can do what. ownership.
  - repository - talks to the database (crud)

## **Blockers / Notes**
- DTO (data transfer object)
  - class to carry data across boundary. In my case between outside world and my app

- Java Record 
  - class that only holds data. Can't change data after created

## **Next up**

### 2026-07-14 · — Entities + repositories

## **What I did**

```
Built out entities, repositories and tested with CommandlineRunner
```

## **Decisions**
Flyway owns the schema. Hibernate only validates

## **Blockers / Notes**
- JPA (jakarta persistance api) - maps java objects to database tables

- Entities
  - Translation of the table (mirroring).
  - Constructor is a Hibernate requirement. Can leave empty

## **Next up**


### 2026-07-08 · — Database Config

## **What I did**

```
Configured app database connection. Made schema design
```

## **Decisions**
Flyway owns the schema. Hibernate only validates

## **Blockers / Notes**
My create table statements live in numbered sdl files in git instead of being typed into psql and lost. Flyway runs any files that haven't run yet at start up so any machine is caught up. 
Hibernate is verifying that my java is matching my matches my real tables.
## **Next up**


### 2026-07-02 · — Initial Setup

## **What I did**

```
I setup the skeleton of the application with Spring Boot and then configured my docker-compose file.
```

## **Decisions**

## **Blockers / Notes**

- Docker notes:
  - 3 main concepts (image, container, volume)
    ```
    An image is the blueprint. I contains everything the software needs to run. The container is a running instance of the image. These are easily created and destroyed without effecting the application. Since containers are disposable by design the needs to be a way to keep data between container lifes. Volumes are a way to keep a something safe on a machine so it doesn't disappear with the container.
    ```
## **Next up**

