<a name="readme-top"></a>

<div align="center">

# **Library Management System (JavaFX • MySQL • macOS Installer)**

![contributors](https://img.shields.io/github/contributors/Nityananda-Krishnamoorthy/LMS.svg?style=for-the-badge)
![forks](https://img.shields.io/github/forks/Nityananda-Krishnamoorthy/LMS.svg?style=for-the-badge&logo=github)
![stars](https://img.shields.io/github/stars/Nityananda-Krishnamoorthy/LMS.svg?style=for-the-badge&logo=github)
![issues](https://img.shields.io/github/issues/Nityananda-Krishnamoorthy/LMS.svg?style=for-the-badge)
![license](https://img.shields.io/github/license/Nityananda-Krishnamoorthy/LMS.svg?style=for-the-badge)

<img src="images/app_icon.icns" width="200">

### **A complete desktop-based Library Management System built using JavaFX, MySQL, and Maven.**
Includes a **macOS (.dmg) installer** built with jpackage for Apple Silicon (M1–M4).

[View Repository](https://github.com/Nityananda-Krishnamoorthy/LMS)
·  
[Report Bug](https://github.com/Nityananda-Krishnamoorthy/LMS/issues)
·  
[Request Feature](https://github.com/Nityananda-Krishnamoorthy/LMS/issues)

</div>

---

## **Table of Contents**

1. [About the Project](#about-the-project)  
2. [Features](#features)  
3. [Tech Stack](#tech-stack)  
4. [Project Structure](#project-structure)  
5. [Installation & Setup](#installation--setup)  
6. [Database Setup](#database-setup)  
7. [Build macOS Installer](#build-macos-installer)  
8. [Run the Application](#run-the-application)  
9. [Roadmap](#roadmap)  
10. [Author](#author)

---

## **About the Project**

This Library Management System is a full desktop application built with **JavaFX** and designed for managing books, users, and inventory in a library environment.

The project includes:

- Complete CRUD operations  
- MySQL-based persistent storage  
- A clean UI created using JavaFX FXML  
- Proper modular Java configuration  
- A custom **macOS .dmg installer generated using jpackage**  
- Custom application icon  

This project is ideal for learning:

- JavaFX development  
- Modular Java (Java 25 modular runtime)  
- Database connectivity using JDBC  
- Packaging standalone desktop applications  

---

## **Features**

- Add, update, delete books  
- Search & filter functionality  
- Book availability tracking  
- Book price & condition tracking  
- Gender tagging for authors  
- JavaFX UI with FXML  
- macOS installer (`.dmg`)  
- Custom icons and runtime image  
- Clean modular Maven architecture  

---

## **Tech Stack**


| Layer       | Technology                     |
|:-----------:|:------------------------------:|
| UI          | JavaFX, FXML                    |
| Backend     | Java 25 (Temurin)               |
| Database    | MySQL + JDBC                    |
| Build Tool  | Maven                           |
| Packaging   | jlink + jpackage                |
| OS Support  | macOS ARM (Apple Silicon)       |


---

## **Project Structure**

```swift
LMS/
│── pom.xml
│── build-runtime/ # jlink runtime image
│── out/ # macOS .dmg output
│── src/
│ └── main/
│ ├── java/
│ │ └── com/librarymanagementsystem/
│ │ ├── App.java
│ │ ├── Books.java
│ │ └── Controllers...
│ └── resources/
│ └── com/librarymanagementsystem/
│ ├── main_screen.fxml
│ └── images/
│ └── app_icon.icns
```

---

## **Installation & Setup**

### 1. Clone the repository

```sh
git clone https://github.com/Nityananda-Krishnamoorthy/LMS.git
cd LMS
```

### 2. Install Java 25 (Temurin)

```sh
brew install --cask temurin
```

### 3. Install Maven

```sh
brew install maven
```

### 4. Verify installation

```sh
java -version
mvn -v
```
---

## **Database Setup**

### 1. Create MySQL database

```sql
CREATE DATABASE lms;
USE lms;
```

### 2. Create the table

```sql
CREATE TABLE books (
    sno INT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    authorGender VARCHAR(20),
    publicationYear INT,
    genre VARCHAR(100),
    bookPrice DOUBLE,
    bookAvailability BOOLEAN,
    bookCover VARCHAR(255),
    `condition` VARCHAR(100)
);
```

### 3. Configure connection in your Java code

Your JDBC connection should look like:

```java
String url = "jdbc:mysql://localhost:3306/lms";
String user = "root";
String password = "your-password";
```
---

## **Build macOS Installer**

### 1. Create runtime image (jlink)

```sh
jlink \
  --module-path "$JAVA_HOME/jmods:/Users/yourpath/javafx-jmods-25.0.1" \
  --add-modules javafx.controls,javafx.fxml,javafx.graphics,java.sql \
  --output build-runtime \
  --strip-debug --compress=2 --no-header-files --no-man-pages
  ```

### 2. Create .dmg installer (jpackage)

```sh
jpackage \
  --type dmg \
  --name "LMS" \
  --app-version 1.0 \
  --input target \
  --main-jar libraryapp-1.0.0-jar-with-dependencies.jar \
  --main-class com.librarymanagementsystem.App \
  --icon images/app_icon.icns \
  --runtime-image build-runtime \
  --dest out
  ```

Installer appears in:

```bash
/LMS/out/LMS-1.0.dmg
```
---

## **Run the Application**

Using Maven (development mode):

```sh
mvn clean javafx:run
```

Using packaged JAR:

```sh
java -jar target/libraryapp-1.0.0-jar-with-dependencies.jar
```

Using macOS app:

```yml
Open the .dmg and drag LMS.app to Applications.
```
---
## **Roadmap**

 - [x] Core LMS features

 - [x] Database integration

 - [x] macOS installer

 - [ ] Windows .exe installer

 - [ ] Linux .deb package

 - [ ] User login system

 - [ ] Admin/Staff roles

 - [ ] Dashboard charts

 - [ ] Export/Import book data

 ---

 ## **Author**

**Nityananda Krishnamoorthy**

- GitHub: [https://github.com/Nityananda-Krishnamoorthy](https://github.com/Nityananda-Krishnamoorthy)

- Email: [contactmenitish06@gmail.com](mailto:contactmenitish06@gmail.com)

<p align="right">(<a href="#readme-top">back to top</a>)</p> 