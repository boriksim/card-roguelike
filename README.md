# Card Roguelike — Java Course Practice

Practice repository for a hands-on Java course. One console game grows here
through the whole course: from a hardcoded battle skeleton (v0.0) to a
roguelike with saves, an SQL leaderboard and a REST backend (v1.0).

## Structure

| Module  | What it is |
|---------|------------|
| `game/` | The game itself. Current version: **v0.0** — a hardcoded 1-vs-1 card battle. |
| `m01/`  | Exercise sandboxes for course module 1 (packages `ex01` … `ex08`). |

## Requirements

- JDK 21
- IntelliJ IDEA (Community Edition is fine) — Maven support is built in

## Open in IntelliJ

1. **File → Open…** and select this `practice` folder.
2. Wait until the Maven import finishes (progress bar at the bottom).

## Run the game

Open `game/src/main/java/game/Main.java` and click the green Run arrow
next to the `main` method.

## Run the tests

Right-click the project root → **Run 'All Tests'**, or from the command line:

```
mvn test
```

Notes on exercises:

- `m01/ex01` … `ex04` are checked by comparing the console output with the
  `expected-output.txt` file next to each exercise.
- `m01/ex05` … `ex08` are checked by JUnit tests. Each test class starts
  with a `@Disabled` line — remove it when you start the exercise, then
  make the test green.
