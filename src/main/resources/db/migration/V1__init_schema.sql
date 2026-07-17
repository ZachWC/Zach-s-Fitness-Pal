CREATE TABLE users (
    id            BIGSERIAL PRIMARY KEY,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE exercises (
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT NOT NULL REFERENCES users(id),
    exercise_name         VARCHAR(100) NOT NULL,
    muscle_group VARCHAR(50),
    UNIQUE (user_id, exercise_name)
);

CREATE TABLE workouts (
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT NOT NULL REFERENCES users(id),
    workout_date DATE NOT NULL,
    notes        TEXT
);

CREATE TABLE workout_sets (
    id          BIGSERIAL PRIMARY KEY,
    workout_id  BIGINT NOT NULL REFERENCES workouts(id) ON DELETE CASCADE,
    exercise_id BIGINT NOT NULL REFERENCES exercises(id),
    set_number  INT NOT NULL,
    reps        INT NOT NULL,
    weight_kg   NUMERIC(5,2)
);