# COMPOSITE PRIMARY KEY USING SPRING BOOT, JPA & HIBERNATE

A simple Spring Boot project demonstrating **Composite Primary Key Mapping** using **Spring Data JPA** and **Hibernate**.

This project demonstrates how multiple columns can be combined together to uniquely identify a single entity record using `@EmbeddedId` and `@Embeddable`.

This project is mainly created for learning and understanding composite primary key mapping in Spring Data JPA.

---

# HIGH LEVEL ARCHITECTURE

![Composite Primary Key](docs/images/composite-primary-key-er-diagram.drawio.svg)

---

# FEATURES

- Composite Primary Key using `@EmbeddedId`
- Custom Primary Key Class using `@Embeddable`
- Employee Management Example
- Hibernate Table Mapping
- Automatic Table Creation using Hibernate
- CRUD Operations using Spring Data JPA
- Validation using Jakarta Validation
- MariaDB Integration

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
src/main/java/com/mydomain/springweb/compositeprimarykey
│
├── entity
│   ├── Employee.java
│   └── EmployeeIdentity.java
│
├── repository
│   └── EmployeeRepository.java
│
└── SpringbootHibernateCompositePrimaryKeyApplication.java
```

---

# COMPOSITE PRIMARY KEY EXPLANATION

A composite primary key is used when a single column is not sufficient to uniquely identify a record.

In this project:

- `employeeId` alone is not unique
- because same employee IDs can exist in different departments

Therefore:

```text
employeeId + departmentId
```

are combined together to create a unique identity for each employee.

Example:

| employee_id | department_id | employee_name |
|---|---|---|
| E-101 | D-1 | Alpha |
| E-101 | D-2 | Beta  |

Here:

- `employee_id` is duplicated
- but combination of:
  - `employee_id`
  - `department_id`

is unique.

---

# EMPLOYEEIDENTITY CLASS

```java
@Embeddable
public class EmployeeIdentity implements Serializable {

    private String employeeId;
    private String departmentId;
}
```

---

## Explanation

| Annotation | Purpose |
|------------|---------|
| `@Embeddable` | Marks class as embeddable composite key |
| `Serializable` | Required for composite key class |
| `employeeId` | First part of composite key |
| `departmentId` | Second part of composite key |

---

# EMPLOYEE ENTITY

```java
@Entity
@Table(name = "employee")
public class Employee {

    @EmbeddedId
    private EmployeeIdentity id;

    private String name;
    private String email;
    private String phoneNumber;
}
```

---

## Explanation

| Annotation | Purpose |
|------------|---------|
| `@Entity` | Marks class as JPA entity |
| `@Table` | Maps entity to database table |
| `@EmbeddedId` | Embeds composite primary key object |
| `EmployeeIdentity` | Composite key class |

---

# HOW MAPPING WORKS

When an `Employee` is saved:

1. Hibernate reads `EmployeeIdentity`
2. Extracts:
   - `employeeId`
   - `departmentId`
3. Uses both columns together as primary key
4. Inserts employee record into database

Example:

| employee_id | department_id | name | email |
|---|---|---|---|
| E-101 | D-1 | Alpha | alpha@email.com |

---

# SAMPLE DATA USED

```java
Employee employee = new Employee(
    new EmployeeIdentity("E-101", "D-1"),
    "Alpha",
    "alpha@email.com",
    "1234567890"
);

employeeRepository.save(employee);
```

---

# DATABASE TABLE

## employee

| Column | Type |
|---|---|
| employee_id | VARCHAR |
| department_id | VARCHAR |
| name | VARCHAR |
| email | VARCHAR |
| phone_number | VARCHAR |

---

# PRIMARY KEY STRUCTURE

Hibernate creates composite primary key using:

```text
(employee_id, department_id)
```

Meaning:

- Both columns together form primary key
- Duplicate combinations are not allowed

Example:

✅ Allowed:

| employee_id | department_id |
|---|---|
| E-101 | D-1 |
| E-101 | D-2 |

❌ Not Allowed:

| employee_id | department_id |
|---|---|
| E-101 | D-1 |
| E-101 | D-1 |

---

# VALIDATION USED

This project uses Jakarta Validation annotations.

Example:

```java
@NotBlank
@Size(min = 2)
private String name;
```

Jakarta Validation annotations provided by:

```xml
spring-boot-starter-validation
```

because Spring Boot starter internally provides Jakarta Validation implementation (Hibernate Validator).

---

## Validation Annotations

| Annotation | Purpose |
|------------|---------|
| `@NotBlank` | Prevents empty values |
| `@Size` | Restricts text length |
| `@Pattern` | Validates phone number format |

---

# REPOSITORY

```java
public interface EmployeeRepository
    extends JpaRepository<Employee, EmployeeIdentity> {
}
```

---

## Explanation

| Generic Type | Purpose |
|------------|---------|
| `Employee` | Entity Class |
| `EmployeeIdentity` | Composite Primary Key Class |


---

# DEPENDENCIES USED

- spring-boot-starter-data-jpa
- mariadb-java-client
- spring-boot-devtools
- spring-boot-starter-validation

---

# HOW TO RUN THE PROJECT

## 1. Clone Repository

```bash
git clone https://github.com/hello-aaditya/springboot-playground.git
```

---

## 2. Navigate to Project Directory

```bash
cd springboot-hibernate-composite-primary-key
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

- Employee record gets inserted successfully
- Composite primary key gets generated
- Hibernate maps both primary key columns correctly
- Duplicate composite keys are prevented

---

# FUTURE IMPROVEMENTS

- REST APIs
- Service Layer
- DTO Layer
- Global Exception Handling
- Swagger/OpenAPI Documentation
- Pagination & Sorting
- Unit Testing
- Docker Support

---

# AUTHOR

Aaditya Kumar

---

# LICENSE

This project is developed for learning and educational purposes.
```