
## Library Management System

Simple Spring Boot project to practice **JPA relationships** using an **Author – Book** example.

This project focuses on understanding:

* how JPA relationships work
* lazy loading and transactions
* basic pagination and filtering

No overengineering, just clean JPA practice.

---

## Domain

* **Author** → can have multiple books
* **Book** → belongs to one author

---

## Tech

* Spring Boot
* Spring Data JPA
* Hibernate
* Lombok

---

## Endpoints (examples)

```
POST /authors
GET  /authors/{id}

POST /authors/{id}/books

GET  /books
GET  /books/search?title=...
GET  /books/by-author?authorName=...
```

Pagination:

```
GET /books?page=0&size=5&sort=title,asc
```

---

## Notes

* DTOs are used instead of exposing entities
* Transactions are handled in the service layer
* Lazy loading behavior is handled intentionally

---

```
RexGM
```

