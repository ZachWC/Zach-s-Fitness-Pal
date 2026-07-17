# Development Journal — Workout Tracker

> A daily log of progress, decisions, and lessons learned while building the Workout Tracker API.

**Author:** Zach Christensen
**Stack:** Spring Boot 4.1 · Java 21 · Spring Data JPA · Flyway · Spring Security · PostgreSQL

**Tags:** `feat` · `fix` · `refactor` · `infra` · `docs` · `spike` · `chore`

---

## Entries

### 2026-07-14 · — User.java

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

