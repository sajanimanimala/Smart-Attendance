Smart Attendance System
The Smart Attendance System is a digital solution designed to automate and streamline the student attendance process using QR codes. Faculty members generate dynamic QR codes for each lecture, and students can scan them through the application to instantly record attendance. The system uses Java Swing for the user interface and MySQL with JDBC for secure backend data management. It provides real-time attendance tracking, enabling students to view their attendance status anytime without relying on faculty. Admins can view consolidated attendance reports and export them for documentation. This project enhances accuracy, minimizes manual effort, and reduces attendance fraud while ensuring transparency for both students and administrators.
Objectives
1.	Automate Attendance Recording
To replace manual attendance methods with a QR-based digital system that ensures fast, accurate, and hassle-free attendance marking.
2.	Reduce Proxy & Human Errors
To eliminate proxy attendance and reduce manual errors by validating each student through unique QR codes.
3.	Enable Real-Time Attendance Tracking
To allow students to instantly view their subject-wise attendance status, including total classes, attended classes, and percentage.
4.	Improve Faculty Efficiency
To provide faculty with automated attendance reports, class-wise summaries, and CSV/Excel export functionality for documentation.
5.	Enhance Data Security & Reliability
To securely store and manage attendance records using a database with proper validation and duplicate prevention.
6.	Provide a Simple & User-Friendly Interface
To build an easy-to-navigate desktop application using Java Swing so both students and faculty can use it comfortably.
7.	Increase Transparency
To ensure students do not need to follow faculty for attendance updates, promoting transparency and academic discipline.
Tech Stack
Frontend (UI):
•	Java Swing – Used for designing all application windows (Login, Admin Dashboard, Student Dashboard, QR Scanner, Attendance View).
•	JTable & DefaultTableModel – For presenting attendance data in tabular form.
Backend / Core Logic:
•	Java (Core + OOP) – Main programming language for implementing attendance logic, QR generation, and database operations.
•	ZXing Library – For generating and decoding QR codes.
•	JDBC (Java Database Connectivity) – For connecting Java application with MySQL database.
Database:
•	MySQL – Stores student data, attendance records, QR tokens, and admin details.
File Handling:
•	CSV/Excel Export (FileWriter / Apache POI ) – Export attendance reports for faculty use.
