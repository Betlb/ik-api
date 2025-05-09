# Personnel Management System

This project is a personnel management system designed to be used by administrators (such as HR staff or managers) with a secure user login mechanism. The system provides various features to simplify personnel and leave management.

---

## Features

### User Authentication
- Only authorized users can log in to the system.

### Personnel Management
- **Add / Delete / Update**: Personnel records can be easily added, updated, or removed from the system.
- **View All**: A list of all personnel can be displayed.
- **Filter by Department**: Personnel can be listed based on their assigned department.
- **PDF Reporting**: Reports in PDF format can be generated for all personnel or by department, based on selected attributes.

### Leave Management
- **Leave Entry**: Leaves can be added for personnel.
- **Leave PDF Export**: Leave data can be exported as PDF reports.
- **Leave Limit Control**: Each employee has a defined leave quota. The system checks remaining leave before approval. If the limit is exceeded, new leave cannot be granted.
- **Leave History**: Previously granted leaves can be viewed when needed.

### Department Management
- New departments can be added to the system.

---

## Demo

You can view a live demonstration of the application via the link below:  
[🔗 View Demo](https://drive.google.com/file/d/1QisdDXZczQOPOjd4m56JYgyDoSJaOUCZ/view?usp=sharing)

---

## Developer Notes

- **Backend**: Spring Boot (Java)  
- **Frontend**: Angular  
- **Database**: PostgreSQL  
- **PDF Reporting**: iReport (JasperReports)  
- **Architecture**: Layered structure (Controller – Service – Repository)

---
