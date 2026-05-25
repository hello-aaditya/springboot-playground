# MANY-TO-MANY MAPPING USING SPRING BOOT, JPA & HIBERNATE

A simple Spring Boot project demonstrating **Bidirectional Many-To-Many Mapping** using **Spring Data JPA** and **Hibernate**.

This project demonstrates how multiple `Posts` can contain multiple `Tags`, and multiple `Tags` can belong to multiple `Posts` using JPA relationship mapping.

This project is mainly created for learning and understanding entity relationship mapping in Spring Data JPA.

---

# HIGH LEVEL ARCHITECTURE

![Many-To-Many Mapping](docs/images/many-to-many-er-diagram.drawio.svg)

---

# FEATURES

- Create a `Post`
- Create Multiple `Tags`
- Bidirectional Many-To-Many Mapping
- JPA Relationship Mapping
- Join Table Handling using `@JoinTable`
- Lazy Loading using `FetchType.LAZY`
- Cascade Operations using `CascadeType.PERSIST` and `CascadeType.MERGE`
- Environment Variable Configuration using `.env`
- Automatic Table Creation using Hibernate

---

# TECH STACK

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming Language |
| Spring Boot 4.x | Backend Framework |
| Spring Data JPA | Database Operations |
| Hibernate | ORM Framework |
| MariaDB | Relational Database |
| Maven | Dependency Management |

---

# PROJECT ARCHITECTURE

```txt
src/main/java/com/mydomain/springweb/manytomanymapping
│
├── entity
│   ├── Post.java
│   └── Tag.java
│
├── repository
│   ├── PostRepository.java
│   └── TagRepository.java
│
└── SpringbootHibernateManyToManyMappingApplication.java
```

---

# ENTITY RELATIONSHIP EXPLANATION

## Post Entity

```java
@ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    }
)

@JoinTable(
    name = "post_tags",

    joinColumns = {
        @JoinColumn(name = "post_id")
    },

    inverseJoinColumns = {
        @JoinColumn(name = "tag_id")
    }
)

private Set<Tag> tags = new HashSet<>();
```

---

## Explanation

| Annotation | Purpose |
|------------|---------|
| `@ManyToMany` | Defines many-to-many relationship |
| `FetchType.LAZY` | Loads tags only when accessed |
| `CascadeType.PERSIST` | Saves child entities automatically |
| `CascadeType.MERGE` | Updates child entities automatically |
| `@JoinTable` | Creates intermediate join table |
| `joinColumns` | Foreign key of current entity (`Post`) |
| `inverseJoinColumns` | Foreign key of opposite entity (`Tag`) |

---

# TAG ENTITY

```java
@ManyToMany(
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "tags"
)

private Set<Post> posts = new HashSet<>();
```

---

## Explanation

| Annotation | Purpose |
|------------|---------|
| `mappedBy = "tags"` | Indicates relationship is managed by `Post` entity |
| `Set<Post>` | Stores associated posts |
| `FetchType.LAZY` | Prevents unnecessary data loading |

---

# HOW MAPPING WORKS

When a `Post` is saved:

1. Post gets inserted into `posts` table
2. Tags get inserted into `tags` table
3. Hibernate automatically creates entries in `post_tags` join table

---

# SAMPLE DATA USED

```java
Post post = new Post(
    "Hibernate Many to Many Example",
    "Description",
    "content"
);

Tag springBoot = new Tag("Spring Boot");
Tag hibernate = new Tag("Hibernate");

post.getTags().add(springBoot);
post.getTags().add(hibernate);

springBoot.getPosts().add(post);
hibernate.getPosts().add(post);

postRepository.save(post);
```

---

# DATABASE TABLES

## posts

| Column | Type |
|---|---|
| id | BIGINT |
| title | VARCHAR |
| description | VARCHAR |
| content | VARCHAR |
| posted_at | DATETIME |
| last_updated_at | DATETIME |

---

## tags

| Column | Type |
|---|---|
| id | BIGINT |
| name | VARCHAR |

---

## post_tags

| Column | Type |
|---|---|
| post_id | BIGINT (FK) |
| tag_id | BIGINT (FK) |

---

# JOIN TABLE EXPLANATION

The `post_tags` table acts as an intermediate table connecting:

```text
Post  <----->  Tag
```

Example:

| post_id | tag_id |
|---|---|
| 1 | 1 |
| 1 | 2 |

This means:

- Post `1` contains Tag `1`
- Post `1` contains Tag `2`

---

# DATABASE CONFIGURATION

This project uses environment variables with `.env`.

## `.env`

```env
DB_URL=jdbc:mariadb://localhost:3306/your_database_name
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

---


# DEPENDENCIES USED

- spring-boot-starter-data-jpa
- mariadb-java-client
- spring-boot-devtools

---

# HOW TO RUN THE PROJECT

## 1. Clone Repository

```bash
git clone https://github.com/hello-aaditya/springboot-playground.git
```

---

## 2. Navigate to Project Directory

```bash
cd springboot-hibernate-many-to-many-mapping
```

---

## 3. Configure Environment Variables

Create `.env`

```env
DB_URL=jdbc:mariadb://localhost:3306/your_database_name
DB_USERNAME=root
DB_PASSWORD=your_password
```

---

## 4. Build Project

### Linux / macOS

```bash
./mvnw clean install
```

### Windows

```cmd
mvnw.cmd clean install
```

---

## 5. Run Application

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows

```cmd
mvnw.cmd spring-boot:run
```

---

# OUTPUT

After running the application:

- One `Post` record gets inserted
- Multiple `Tag` records get inserted
- Join table entries are automatically maintained by Hibernate

---

# IMPORTANT NOTES

## Why `FetchType.LAZY`?

It prevents unnecessary loading of related entities.

Example:

- Load `Post`
- Tags are fetched only when `getTags()` is called

This improves performance.

---

## Why `CascadeType.PERSIST` and `MERGE`?

It ensures:

- Saving parent saves child automatically
- Updating parent updates child automatically

Avoiding `REMOVE` is safer because tags may be shared across multiple posts.

---

## Why `mappedBy`?

Without `mappedBy`, Hibernate creates an additional join table.

Using:

```java
mappedBy = "tags"
```

tells Hibernate:

> Relationship is already managed by `Post` entity.

---

# FUTURE IMPROVEMENTS

- REST APIs
- DTO Layer
- Service Layer
- Validation using `@Valid`
- Swagger/OpenAPI Documentation
- Spring Security
- Docker Support
- Unit Testing
- Pagination & Sorting

---

# AUTHOR

Aaditya Kumar

---

# LICENSE

This project is developed for learning and educational purposes.