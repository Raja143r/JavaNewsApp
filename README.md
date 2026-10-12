# Newsapp

A Spring Boot REST API for publishing and searching news articles. It uses an in-memory H2 database and loads a few sample articles on startup.

## Requirements

- Java 17 or newer
- Maven 3.6 or newer

## Run

```powershell
mvn spring-boot:run
```

The API is available at `http://localhost:8080/api/articles`. The H2 console is at `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:mem:newsdb`, username `sa`, and a blank password.

## Run with Docker

Start Docker Desktop and wait until its engine is running. From the project directory, build and run the container:

```powershell
docker build -t newsapp .
docker run --rm --name newsapp -p 8080:8080 newsapp
```

The API is available at `http://localhost:8080/api/articles`. Press `Ctrl+C` to stop the container. Docker builds the app inside the image, so Java and Maven are not required on the host.

## API

- `GET /api/articles?page=0&size=10` lists articles, newest first.
- `GET /api/articles?search=technology` searches titles, summaries, and categories.
- `GET /api/articles/{id}` gets one article.
- `POST /api/articles` creates an article.
- `PUT /api/articles/{id}` replaces an article's editable fields.
- `DELETE /api/articles/{id}` deletes an article.

Example request body:

```json
{
  "title": "A new story",
  "summary": "A short introduction to the story.",
  "content": "The full article text goes here.",
  "author": "Alex Morgan",
  "category": "Local",
  "imageUrl": null
}
```

Run the tests with `mvn test`.
