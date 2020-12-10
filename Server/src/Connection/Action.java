package Connection;

import DBConnection.ConnectionClass;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Action {
    public void execute(Handler handler, String action) throws IOException, ClassNotFoundException, SQLException {
        switch (action) {
            case "enterStudent": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT * FROM scholarship.Student WHERE login = ? AND password = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, log);
                preparedStmt.setString(2, pass);
                ResultSet data = preparedStmt.executeQuery();
                if(data.next()) {
                    handler.write(String.valueOf(data.getInt("idStudent")));
                }
                else {
                    handler.write("Nothing");
                }
                connection.close();
                break;
            }
            case "enterDean": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT * FROM scholarship.Deanery WHERE login = ? AND password = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, log);
                preparedStmt.setString(2, pass);
                ResultSet data = preparedStmt.executeQuery();
                if(data.next()) {
                    handler.write(String.valueOf(data.getInt("idDeanery")));
                }
                else {
                    handler.write("Nothing");
                }
                connection.close();
                break;
            }
            case "enterAccountant": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT * FROM scholarship.Accountant WHERE login = ? AND password = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, log);
                preparedStmt.setString(2, pass);
                ResultSet data = preparedStmt.executeQuery();
                if(data.next()) {
                    handler.write(String.valueOf(data.getInt("idAccountant")));
                }
                else {
                    handler.write("Nothing");
                }
                connection.close();
                break;
            }

            case "regAccountant": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String count = "SELECT COUNT(*) FROM scholarship.Accountant";
                Statement statement = connection.createStatement();
                ResultSet count_acc_set = statement.executeQuery(count);
                count_acc_set.next();
                int next_id = count_acc_set.getInt("COUNT(*)");
                String query = "INSERT INTO scholarship.Accountant (idAccountant, name, lastName, patronymic, phone, login, password) VALUES (?, 'Anon', 'Anon', 'Anon', 'None', ?, ?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, next_id + 1);
                preparedStmt.setString(2, log);
                preparedStmt.setString(3, pass);
                preparedStmt.execute();
                connection.close();
                break;
            }

            case "returnId": {
                Integer id = (Integer) handler.read();
                handler.write(id);
                break;
            }

            case "returnIdStr": {
                Integer id = (Integer) handler.read();
                handler.write(id);
                String str = (String) handler.read();
                handler.write(str);
                break;
            }

            case "loadExamInfo": {
                Integer student_id = (Integer) handler.read();

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT session FROM scholarship.student WHERE idStudent = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, student_id);
                ResultSet res = preparedStmt.executeQuery();
                res.next();

                int session_num = res.getInt("session");

                String query1 = "SELECT * FROM scholarship.exam WHERE Session = ?";
                PreparedStatement preparedStmt1 = connection.prepareStatement(query1);
                preparedStmt1.setInt(1, session_num);
                ResultSet res1 = preparedStmt1.executeQuery();
                while (res1.next()) {
                    ArrayList r = new ArrayList();
                    r.add(res1.getString("subject"));
                    r.add(res1.getString("date"));
                    r.add(res1.getString("mark"));
                    System.out.println(r.get(0));
                    System.out.println(r.get(1));
                    System.out.println(r.get(2));
                    handler.write(r);
                    r.clear();
                }
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }

            case "loadBankInfo": {
                Integer student_id = (Integer) handler.read();

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT name, lastName, patronymic, Scholarship_idScholarship, bankCard FROM scholarship.student WHERE idStudent = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, student_id);
                ResultSet res = preparedStmt.executeQuery();
                res.next();

                int scholarship_id = res.getInt("Scholarship_idScholarship");

                String query1 = "SELECT * FROM scholarship.scholarship WHERE idScholarship = ?;";
                PreparedStatement preparedStmt1 = connection.prepareStatement(query1);
                preparedStmt1.setInt(1, scholarship_id);
                ResultSet res1 = preparedStmt1.executeQuery();
                while (res1.next()) {
                    ArrayList r = new ArrayList();
                    r.add(res.getString("lastName") + " " + res.getString("name") + " " + res.getString("patronymic"));
                    r.add(res.getString("bankCard"));
                    r.add(res1.getString("date"));
                    System.out.println(r.get(0));
                    System.out.println(r.get(1));
                    System.out.println(r.get(2));
                    handler.write(r);
                    r.clear();
                }
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }
            case "loadScholarshipInfo": {
                Integer student_id = (Integer) handler.read();

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT Scholarship_idScholarship FROM scholarship.student WHERE idStudent = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, student_id);
                ResultSet res = preparedStmt.executeQuery();
                res.next();

                int scholarship_id = res.getInt("Scholarship_idScholarship");

                String query1 = "SELECT * FROM scholarship.scholarship WHERE idScholarship = ?;";
                PreparedStatement preparedStmt1 = connection.prepareStatement(query1);
                preparedStmt1.setInt(1, scholarship_id);
                ResultSet res1 = preparedStmt1.executeQuery();
                while (res1.next()) {
                    ArrayList r = new ArrayList();
                    r.add(res1.getString("type"));
                    r.add(res1.getString("amount"));
                    System.out.println(r.get(0));
                    System.out.println(r.get(1));
                    handler.write(r);
                    r.clear();
                }
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }
            case "loadShowStudentInfo": {
                // Принимаем логин декана для недопущения ошибки
                Integer dean_id = (Integer) handler.read();
                System.out.println(dean_id);

                // Выбираем всех студентов
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                // Добавляем inner join session и faculty чтобы мы сразу получали avgMark и faculty.name (не нужно писать отдельные запрос)
                String query = "SELECT student.*, session.avgMark AS avg, faculty.name AS facname FROM scholarship.student INNER JOIN scholarship.session on Idsession=student.session INNER JOIN scholarship.faculty on idFaculty=student.faculty;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                System.out.println(students);

                // Прогоняем всех студентов
                while(students.next()) {
                    ArrayList r = new ArrayList();
                    r.add(students.getString("name"));
                    r.add(students.getString("lastName"));
                    r.add(students.getString("patronymic"));
                    r.add(students.getString("group"));
                    r.add(students.getString("recordBook"));
                    r.add(students.getString("facname"));
                    r.add(students.getString("avg"));
                    handler.write(r);
                    r.clear();
                }

                // Посылаем сообщение о конце считывания
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }
            case "loadRecordBookInfo": {
                // Принимаем логин декана для недопущения ошибки
                Integer dean_id = (Integer) handler.read();
                System.out.println(dean_id);

                // Выбираем всех студентов
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                // Добавляем inner join session и faculty чтобы мы сразу получали avgMark и faculty.name (не нужно писать отдельные запрос)
                String query = "SELECT student.* FROM scholarship.student;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                System.out.println(students);

                // Прогоняем всех студентов
                while(students.next()) {
                    ArrayList r = new ArrayList();
                    r.add(students.getString("recordBook"));
                    handler.write(r);
                    r.clear();
                }

                // Посылаем сообщение о конце считывания
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }

            case "loadDeanScholarshipInfo": {
                String value = (String) handler.read();
                System.out.println(value);

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String query = "SELECT student.*, scholarship.* FROM scholarship.student INNER JOIN scholarship.scholarship on IdScholarship=student.Scholarship_idScholarship WHERE recordBook = ?;";
                PreparedStatement preparedStmt1 = connection.prepareStatement(query);
                preparedStmt1.setString(1, value);
                ResultSet res1 = preparedStmt1.executeQuery();
                System.out.println(res1);

                while(res1.next()) {
                    ArrayList r = new ArrayList();
                    r.add(res1.getString("amount"));
                    r.add(res1.getString("type"));
                    handler.write(r);
                    r.clear();
                }

                // Посылаем сообщение о конце считывания
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);

                System.out.println("Works on server!");

                break;
            }

            case "loadTransactionsHistory": {
                Integer accountant_id = (Integer) handler.read();
                System.out.println(accountant_id);

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String query = "SELECT student.*, scholarship.* FROM scholarship.student INNER JOIN scholarship.scholarship on IdScholarship=student.Scholarship_idScholarship;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                System.out.println(students);

                // Прогоняем всех студентов
                while(students.next()) {
                    ArrayList r = new ArrayList();
                    r.add(students.getString("lastName") + "   " + students.getString("name") + "   " + students.getString("patronymic") + "   " + students.getString("bankCard") + "   " + students.getString("date") + "   " + students.getString("amount"));
                    handler.write(r);
                    r.clear();
                }

                // Посылаем сообщение о конце считывания
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }

            case "chosenStudentRating": {
                Integer id = (Integer) handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String query = null;
                String inc = (String) handler.read();
                System.out.println(inc);
                if (inc.equals("По возрастанию рейтинга")) {
                    query = "SELECT * FROM scholarship.student INNER JOIN scholarship.session ON student.session = session.idSession ORDER BY avgMark ASC;";
                }
                else if (inc.equals("По убыванию рейтинга")) {
                    query = "SELECT * FROM scholarship.student INNER JOIN scholarship.session ON student.session = session.idSession ORDER BY avgMark DESC;";
                }
                else if (inc.equals("По возрастанию зачетки")) {
                    query = "SELECT * FROM scholarship.student INNER JOIN scholarship.session ON student.session = session.idSession ORDER BY recordBook ASC;";
                }
                else if (inc.equals("По убыванию зачетки")) {
                    query = "SELECT * FROM scholarship.student INNER JOIN scholarship.session ON student.session = session.idSession ORDER BY recordBook DESC;";
                }
                System.out.println(query);
                Statement statement = connection.createStatement();
                ResultSet res = statement.executeQuery(query);
                System.out.println(res);


                while(res.next()) {
                    ArrayList r = new ArrayList();
                    r.add(res.getString("lastName"));
                    r.add(res.getString("name"));
                    r.add(res.getString("group"));
                    r.add(res.getString("recordBook"));
                    r.add(res.getString("avgMark"));
                    handler.write(r);
                    r.clear();
                }

                // Посылаем сообщение о конце считывания
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }

            case "loadDeanEditStudent": {
                Integer dean_id = (Integer) handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT student.*, session.avgMark AS avg, faculty.name AS facname FROM scholarship.student INNER JOIN scholarship.session on Idsession=student.session INNER JOIN scholarship.faculty on idFaculty=student.faculty;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                System.out.println(students);
                while (students.next()) {
                    ArrayList r = new ArrayList();
                    r.add(students.getString("name"));
                    r.add(students.getString("lastName"));
                    r.add(students.getString("patronymic"));
                    r.add(students.getString("group"));
                    r.add(students.getString("recordBook"));
                    r.add(students.getString("facname"));
                    handler.write(r);
                    r.clear();
                }
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }
            case "AccScholarshipInfo": {
                Integer dean_id = (Integer) handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT student.*, scholarship.* FROM scholarship.student INNER JOIN scholarship.scholarship on IdScholarship=student.Scholarship_idScholarship;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                while (students.next()) {
                    ArrayList r = new ArrayList();
                    r.add(students.getString("name"));
                    r.add(students.getString("lastName"));
                    r.add(students.getString("patronymic"));
                    r.add(students.getString("recordBook"));
                    r.add(students.getString("date"));
                    r.add(students.getString("recordBook"));
                    handler.write(r);
                    r.clear();
                }
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }

            case "deanChangeStudent": {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String recordBookId = (String) handler.read();
                String fieldName = (String) handler.read();
                String newValue = (String) handler.read();
                System.out.println(recordBookId);
                String query0 = "SELECT * FROM scholarship.student WHERE student.recordBook = ?;";
                PreparedStatement preparedStmt = connection.prepareStatement(query0);
                preparedStmt.setString(1, recordBookId);
                System.out.println("2");
                ResultSet res = preparedStmt.executeQuery();
                System.out.println("3");
                res.next();
                System.out.println("4");
                Integer stud_id = res.getInt("idStudent");
                System.out.println("5");
                String query = null;

                if (fieldName.equals("Фамилия")) {
                    query = "UPDATE scholarship.student SET student.lastName = ? WHERE idStudent = ?;";
                }
                else if (fieldName.equals("Имя")) {
                    query = "UPDATE scholarship.student SET student.name=? WHERE idStudent=?;";
                }
                else if (fieldName.equals("Отчество")) {
                    query = "UPDATE scholarship.student SET student.patronymic=? WHERE idStudent=?;";
                }
                else if (fieldName.equals("Группа")) {
                    query = "UPDATE scholarship.student SET student.group=? WHERE idStudent=?;";
                    System.out.println("ddd");
                }
                else if (fieldName.equals("Зачетка")) {
                    query = "UPDATE scholarship.student SET student.recordBook=? WHERE idStudent=?;";
                }

                PreparedStatement preparedStmt1 = connection.prepareStatement(query);
                preparedStmt1.setString(1, newValue);
                preparedStmt1.setInt(2, stud_id);
                preparedStmt1.execute();
                System.out.println("DONE");
                break;
            }

            case "deanChangeStudentScholarship": {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String recordBookId = (String) handler.read();
                String fieldName = (String) handler.read();
                //String newValue = (String) handler.read();
                System.out.println(recordBookId);
                String query0 = "SELECT * FROM scholarship.student INNER JOIN scholarship.scholarship on IdScholarship=student.Scholarship_idScholarship WHERE student.recordBook = ?;";
                PreparedStatement preparedStmt = connection.prepareStatement(query0);
                preparedStmt.setString(1, recordBookId);
                System.out.println("2");
                ResultSet res = preparedStmt.executeQuery();
                System.out.println("3");
                res.next();
                System.out.println("4");
                Integer stud_id = res.getInt("idScholarship");
                System.out.println("5");
                String query = null;

                if (fieldName.equals("Обычная")) {
                    query = "UPDATE scholarship.scholarship SET scholarship.type= ? WHERE idScholarship= ? ;";
                }
                else if (fieldName.equals("Повышенная")) {
                    query = "UPDATE scholarship.scholarship SET scholarship.type= ? WHERE idScholarship= ? ;";
                }

                PreparedStatement preparedStmt1 = connection.prepareStatement(query);
                preparedStmt1.setString(1, fieldName);
                preparedStmt1.setInt(2, stud_id);
                preparedStmt1.execute();
                System.out.println("DONE");
                break;
            }

            case "accountantChangeScolarship": {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String recordBookId = (String) handler.read();
                String newValue = (String) handler.read();
                String query0 = "SELECT * FROM scholarship.student INNER JOIN scholarship.scholarship on IdScholarship=student.Scholarship_idScholarship WHERE student.recordBook = ?;";
                PreparedStatement preparedStmt = connection.prepareStatement(query0);
                preparedStmt.setString(1, recordBookId);
                ResultSet res = preparedStmt.executeQuery();
                res.next();
                Integer stud_id = res.getInt("idScholarship");
                String query = "UPDATE scholarship.scholarship SET scholarship.date= ? WHERE idScholarship= ? ;";
                PreparedStatement preparedStmt1 = connection.prepareStatement(query);

                preparedStmt1.setString(1, newValue);
                preparedStmt1.setInt(2, stud_id);
                preparedStmt1.execute();
                System.out.println("DONE");
                break;
            }

            case "DeaneryAddScholarship": {
                Integer dean_id = (Integer) handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT student.*, scholarship.* FROM scholarship.student INNER JOIN scholarship.scholarship on IdScholarship=student.Scholarship_idScholarship;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                System.out.println(students);
                while (students.next()) {
                    ArrayList r = new ArrayList();
                    r.add(students.getString("name"));
                    r.add(students.getString("lastName"));
                    r.add(students.getString("group"));
                    r.add(students.getString("recordBook"));
                    r.add(students.getString("type"));
                    handler.write(r);
                    r.clear();
                }
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }

            case "loadGraph": {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT avgMark, COUNT(*) as count from scholarship.session group by avgMark;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                while (students.next()) {
                    ArrayList r = new ArrayList();
                    System.out.println(students.getString("avgMark") + " " + students.getInt("count"));
                    r.add(students.getString("avgMark"));
                    r.add(students.getInt("count"));
                    handler.write(r);
                    r.clear();
                }
                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }
            case "loadReport": {
                Integer accountant_id = (Integer) handler.read();
                System.out.println(accountant_id);

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String query = "SELECT student.*, scholarship.*, session.* FROM scholarship.student INNER JOIN scholarship.scholarship on IdScholarship=student.Scholarship_idScholarship INNER JOIN scholarship.session on IdSession=student.session;";
                Statement statement = connection.createStatement();
                ResultSet students = statement.executeQuery(query);
                System.out.println(students);

                while(students.next()) {
                    ArrayList r = new ArrayList();
                    r.add(students.getString("lastName") + "    |ИМЯ|" + students.getString("name") + "    |ОТЧЕСТВО|" + students.getString("patronymic") + "    |КАРТА|" + students.getString("bankCard") + "    |ДАТА|" + students.getString("date") + "    |РАЗМЕР|" + students.getString("amount")+ "    |БАЛЛ|" + students.getString("avgMark"));
                    handler.write(r);
                    r.clear();
                }

                ArrayList r = new ArrayList();
                r.clear();
                r.add("stop");
                handler.write(r);
                break;
            }
        }
    }
}
