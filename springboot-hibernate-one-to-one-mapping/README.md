# ONE-TO-ONE MAPPING USING SPRING BOOT, JPA & HIBERNATE

A simple Spring Boot project demonstrating **Bi-Directional One-To-One Mapping** using **Spring Data JPA** and **Hibernate**.

This project demonstrates how a single `User` is associated with exactly one `UserProfile` using JPA entity relationship mapping.

This project is mainly created for learning and understanding entity relationship mapping in Spring Data JPA.

---

# HIGH LEVEL ARCHITECTURE

![One-To-One Mapping](docs/images/one-to-one-er-diagram.drawio.svg)

---

# FEATURES

- Create a `User`
- Create a `UserProfile`
- Bi-Directional One-To-One Mapping
- JPA Relationship Mapping
- Lazy Loading using `FetchType.LAZY`
- Foreign Key Handling using `@JoinColumn`
- Relationship Ownership using `mappedBy`
- Cascade Operations using `CascadeType.ALL`
- Enum Mapping using `@Enumerated`
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
src/main/java/com/mydomain/springweb/onetoonemapping
│
├── entity
│   ├── User.java
│   ├── UserProfile.java
│   └── Gender.java
│
├── repository
│   ├── UserRepository.java
│   └── UserProfileRepository.java
│
└── SpringbootHibernateOneToOneMappingApplication.java
```

---

# ENTITY RELATIONSHIP EXPLANATION

## User Entity

```java
@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "up_id")
private UserProfile userProfile;
```

### Explanation

| Annotation | Purpose |
|------------|---------|
| `@OneToOne` | Defines one-to-one relationship |
| `FetchType.LAZY` | Loads related entity only when needed |
| `CascadeType.ALL` | Propagates all operations to child entity |
| `@JoinColumn` | Creates foreign key column |
| `up_id` | Foreign key column name |

---

## UserProfile Entity

```java
@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userProfile")
private User user;
```

### Explanation

| Annotation | Purpose |
|------------|---------|
| `@OneToOne` | Defines reverse side of relationship |
| `FetchType.LAZY` | Loads parent entity only when required |
| `CascadeType.ALL` | Propagates operations to related entity |
| `mappedBy` | Indicates relationship is managed by `userProfile` field in `User` entity |

---

# HOW MAPPING WORKS

When a `User` is saved:

1. User gets inserted into `user` table
2. UserProfile gets inserted into `user_profile` table
3. Hibernate automatically sets foreign key values
4. Relationship is maintained bi-directionally

Example:

| user.id | name | email | up_id |
|---|---|---|---|
| 1 | Alpha | misteralpha@email.com | 1 |

---

| user_profile.id | phone_number | address | gender | dob |
|---|---|---|---|---|
| 1 | 1234567890 | AlphaCity | MALE | 1999-05-25 |

---

# SAMPLE DATA USED

```java
// USER OBJECT
User user = new User();
user.setName("Alpha");
user.setEmail("misteralpha@email.com");

// USER PROFILE OBJECT
UserProfile userProfile = new UserProfile();
userProfile.setAddress("AlphaCity");
userProfile.setDob(LocalDate.of(1999, 5, 25));
userProfile.setGender(Gender.MALE);
userProfile.setPhoneNumber("1234567890");

// SETTING RELATIONSHIP
user.setUserProfile(userProfile);
userProfile.setUser(user);

// SAVE OPERATION
userRepository.save(user);
```

---

# DATABASE TABLES

## user

| Column | Type |
|---|---|
| id | BIGINT |
| name | VARCHAR |
| email | VARCHAR |
| up_id | BIGINT (FK) |

---

## user_profile

| Column | Type |
|---|---|
| id | BIGINT |
| phone_number | VARCHAR |
| address | VARCHAR |
| gender | VARCHAR |
| dob | DATE |

---

# HOW FOREIGN KEY WORKS

The following annotation:

```java
@JoinColumn(name = "up_id")
```

creates foreign key column:

```text
up_id
```

inside `user` table.

This foreign key references:

```text
user_profile.id
```

---

# LAZY LOADING EXPLANATION

This project uses:

```java
fetch = FetchType.LAZY
```

Meaning:

- Related entity is not loaded immediately
- Hibernate fetches related data only when accessed
- Improves performance in large applications

Example:

- Loading `User` does not immediately load `UserProfile`
- `UserProfile` loads only when `getUserProfile()` is called

---

# CASCADE OPERATION EXPLANATION

This project uses:

```java
cascade = CascadeType.ALL
```

Meaning:

- Save parent → child automatically saved
- Delete parent → child automatically deleted
- Update parent → child updated automatically

Because of cascade:

```java
userRepository.save(user);
```

automatically saves:
- User
- UserProfile

---

# MAPPEDBY EXPLANATION

```java
mappedBy = "userProfile"
```

indicates that:

- `User` entity owns the relationship
- `UserProfile` is inverse side
- Prevents creation of unnecessary additional join table

---

# ENUM MAPPING

The project uses:

```java
@Enumerated(EnumType.STRING)
```

for storing enum values as readable text in database.

Example:

```text
MALE
FEMALE
```

instead of ordinal numbers.

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
cd springboot-hibernate-one-to-one-mapping
```

---

## 3. Build Project

### Linux / macOS

```bash
./mvnw clean install
```

### Windows

```cmd
mvnw.cmd clean install
```

---

## 4. Run Application

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

- One `User` record gets inserted
- One `UserProfile` record gets inserted
- Foreign key relationship is automatically maintained by Hibernate
- Relationship works in both directions

---

# FUTURE IMPROVEMENTS

- Unidirectional Mapping
- REST APIs
- DTO Layer
- Service Layer
- Validation using `@Valid`
- Swagger/OpenAPI Documentation
- Spring Security
- Docker Support
- Unit Testing

---

# AUTHOR

Aaditya Kumar

---

# LICENSE

This project is developed for learning and educational purposes.