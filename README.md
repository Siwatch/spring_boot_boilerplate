# Spring Boot 3.x Clean Architecture Boilerplate

โปรเจกต์นี้เป็นโครงสร้างพื้นฐาน (Boilerplate) สำหรับการพัฒนา Backend ด้วย Spring Boot 3.x โดยเน้นที่ **Clean Architecture**, **SOLID Principles** และความปลอดภัยระดับ Production-ready

---

## 🏗️ Project Structure (โครงสร้างโฟลเดอร์)

โปรเจกต์นี้ใช้การจัดโฟลเดอร์แบบ **Descriptive Naming** เพื่อให้สื่อความหมายชัดเจนว่าแต่ละส่วนมีหน้าที่อะไร:

### 1. `src/main/java/com/example/template/`
*   **`common/`**: โค้ดที่ใช้ร่วมกันทั้งโปรเจกต์ (Shared Kernel)
    *   `dto/`: มาตรฐานการตอบกลับ API (`ApiResponse`)
    *   `entity/`: คลาสพื้นฐานสำหรับฐานข้อมูล (`BaseEntity` พร้อม Soft Delete)
    *   `exception/`: ระบบจัดการ Error ส่วนกลาง (`GlobalExceptionHandler`)
    *   `logging/`: Middleware สำหรับบันทึก Request/Response Log
*   **`config/`**: การตั้งค่า Framework ต่างๆ (Security, JPA Auditing, OpenAPI/Swagger)
*   **`security/`**: ระบบรักษาความปลอดภัย (แยกขาดจาก Domain)
    *   `jwt/`: Logic การจัดการ Token (สร้าง, แกะ, ตรวจสอบ)
    *   `filter/`: ตัวดักจับ Request เพื่อเช็คสิทธิ์ (Security Filter)
    *   `service/`: สะพานเชื่อมระหว่าง Spring Security กับฐานข้อมูลผู้ใช้
*   **`domain/`**: หัวใจของธุรกิจ (Business Logic) แบ่งตาม Feature
    *   **`auth/`**: ระบบสมาชิกและการระบุตัวตน
        *   `controller/`, `service/`, `repository/`, `entity/`, `dto/`, `mapper/`
*   **`controller/`**: สำหรับ API ทั่วไปที่ไม่ขึ้นกับ Domain เฉพาะทาง (เช่น `HealthController`)

---

## 🛠️ Tech Stack
*   **Java 21** & **Spring Boot 3.2.4**
*   **Spring Security 6** (Stateless JWT)
*   **Spring Data JPA** (PostgreSQL)
*   **MapStruct** (สำหรับ Entity-DTO Mapping)
*   **Jakarta Validation** (ตรวจสอบข้อมูล Request)
*   **Springdoc-openapi** (Swagger UI)

---

## 🚀 How to Run (วิธีการใช้งาน)

### 1. ตั้งค่าฐานข้อมูล (PostgreSQL)
สร้างฐานข้อมูลชื่อ `template_db` หรือแก้ไขไฟล์ `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/template_db
    username: your_username
    password: your_password
```

### 2. Build & Run
หากคุณมี Maven ในเครื่อง:
```bash
mvn clean install
mvn spring-boot:run
```

### 3. เข้าดู API Documentation
เมื่อโปรเจกต์รันแล้ว สามารถดู Swagger UI ได้ที่:
`http://localhost:8080/swagger-ui.html`

---

## 🔐 Authentication Flow
1.  **Register**: `POST /api/v1/auth/register` เพื่อสร้าง User ใหม่
2.  **Login**: `POST /api/v1/auth/login` เพื่อรับ **Access Token**
3.  **Authenticated Request**: แนบ Token ใน Header สำหรับ API ที่ต้องล็อกอิน:
    `Authorization: Bearer <your_token>`

---

## 📝 บันทึกเพิ่มเติม
*   **Soft Delete**: เมื่อสั่งลบข้อมูล ระบบจะไม่ลบจริงแต่จะเซ็ต `is_deleted = true`
*   **Auditing**: ระบบจะบันทึก `createdAt` และ `updatedAt` ให้โดยอัตโนมัติ
*   **Logging**: ทุก Request จะถูก Log ลง Console พร้อมเวลาที่ใช้ในการประมวลผล
