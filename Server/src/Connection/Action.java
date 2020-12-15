package Connection;

import DBConnection.ConnectionClass;

import javax.xml.transform.Result;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Action extends AbstractAction {
    @Override
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
                String query = "SELECT session FROM scholarship.Student WHERE idStudent = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, student_id);
                ResultSet res = preparedStmt.executeQuery();
                res.next();

                int session_num = res.getInt("session");

                String query1 = "SELECT * FROM scholarship.Exam WHERE Session = ?";
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
                String query = "SELECT name, lastName, patronymic, Scholarship_idScholarship, bankCard FROM scholarship.Student WHERE idStudent = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, student_id);
                ResultSet res = preparedStmt.executeQuery();
                res.next();

                int scholarship_id = res.getInt("Scholarship_idScholarship");

                String query1 = "SELECT * FROM scholarship.Scholarship WHERE idScholarship = ?;";
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
                String query = "SELECT Scholarship_idScholarship FROM scholarship.Student WHERE idStudent = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, student_id);
                ResultSet res = preparedStmt.executeQuery();
                res.next();

                int scholarship_id = res.getInt("Scholarship_idScholarship");

                String query1 = "SELECT * FROM scholarship.Scholarship WHERE idScholarship = ?;";
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
                String query = "SELECT Student.*, Session.avgMark AS avg, Faculty.name AS facname FROM scholarship.Student INNER JOIN scholarship.Session on Idsession=Student.session INNER JOIN scholarship.Faculty on idFaculty=Student.faculty;";
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
                String query = "SELECT Student.* FROM scholarship.Student;";
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

                String query = "SELECT Student.*, Scholarship.* FROM scholarship.Student INNER JOIN scholarship.Scholarship on IdScholarship=Student.Scholarship_idScholarship WHERE recordBook = ?;";
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

                break;
            }

            case "loadTransactionsHistory": {
                Integer accountant_id = (Integer) handler.read();
                System.out.println(accountant_id);

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String query = "SELECT Student.*, Scholarship.* FROM scholarship.Student INNER JOIN scholarship.Scholarship on IdScholarship=Student.Scholarship_idScholarship;";
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
                    query = "SELECT * FROM scholarship.Student INNER JOIN scholarship.Session ON Student.session = Session.idSession ORDER BY avgMark ASC;";
                }
                else if (inc.equals("По убыванию рейтинга")) {
                    query = "SELECT * FROM scholarship.Student INNER JOIN scholarship.Session ON Student.session = Session.idSession ORDER BY avgMark DESC;";
                }
                else if (inc.equals("По возрастанию зачетки")) {
                    query = "SELECT * FROM scholarship.Student INNER JOIN scholarship.Session ON Student.session = Session.idSession ORDER BY recordBook ASC;";
                }
                else if (inc.equals("По убыванию зачетки")) {
                    query = "SELECT * FROM scholarship.Student INNER JOIN scholarship.Session ON Student.session = Session.idSession ORDER BY recordBook DESC;";
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
                String query = "SELECT Student.*, Session.avgMark AS avg, Faculty.name AS facname FROM scholarship.Student INNER JOIN scholarship.Session on Idsession=Student.session INNER JOIN scholarship.Faculty on idFaculty=Student.faculty;";
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

            case "DeaneryDeleteInfo": {
                Integer dean_id = (Integer) handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT Student.*, Session.avgMark AS avg, Faculty.name AS facname FROM scholarship.Student INNER JOIN scholarship.Session on Idsession=Student.session INNER JOIN scholarship.Faculty on idFaculty=Student.faculty;";
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
                String query = "SELECT Student.*, Scholarship.* FROM scholarship.Student INNER JOIN scholarship.Scholarship on IdScholarship=Student.Scholarship_idScholarship;";
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
                String query0 = "SELECT * FROM scholarship.Student WHERE Student.recordBook = ?;";
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
                    query = "UPDATE scholarship.Student SET Student.lastName = ? WHERE idStudent = ?;";
                }
                else if (fieldName.equals("Имя")) {
                    query = "UPDATE scholarship.Student SET Student.name=? WHERE idStudent=?;";
                }
                else if (fieldName.equals("Отчество")) {
                    query = "UPDATE scholarship.Student SET Student.patronymic=? WHERE idStudent=?;";
                }
                else if (fieldName.equals("Группа")) {
                    query = "UPDATE scholarship.Student SET Student.group=? WHERE idStudent=?;";
                    System.out.println("ddd");
                }
                else if (fieldName.equals("Зачетка")) {
                    query = "UPDATE scholarship.Student SET Student.recordBook=? WHERE idStudent=?;";
                }

                PreparedStatement preparedStmt1 = connection.prepareStatement(query);
                preparedStmt1.setString(1, newValue);
                preparedStmt1.setInt(2, stud_id);
                preparedStmt1.execute();
                System.out.println("DONE");
                break;
            }

            case "deanDeleteStudent": {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String recordBookId = (String) handler.read();
                System.out.println(recordBookId);
                String query0 = "SELECT * FROM scholarship.Student WHERE Student.recordBook = ?;";
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

                query = "DELETE FROM scholarship.Student WHERE idStudent=?;";
                PreparedStatement preparedStmt1 = connection.prepareStatement(query);
                preparedStmt1.setInt(1, stud_id);
                preparedStmt1.execute();
                System.out.println("DONE");
                break;
            }

            case "deanChangeStudentScholarship": {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String recordBookId = (String) handler.read();
                String fieldName = (String) handler.read();
                String newValue = (String) handler.read();
                System.out.println(recordBookId);
                String query0 = "SELECT * FROM scholarship.Student INNER JOIN scholarship.Scholarship on IdScholarship=Student.Scholarship_idScholarship WHERE Student.recordBook = ?;";
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
                    query = "UPDATE scholarship.Scholarship SET Scholarship.type= ?, Scholarship.amount= ? WHERE idScholarship= ? ;";
                }
                else if (fieldName.equals("Повышенная")) {
                    query = "UPDATE scholarship.Scholarship SET Scholarship.type= ?, Scholarship.amount= ?  WHERE idScholarship= ? ;";
                }

                PreparedStatement preparedStmt1 = connection.prepareStatement(query);
                preparedStmt1.setString(1, fieldName);
                preparedStmt1.setString(2, newValue);
                preparedStmt1.setInt(3, stud_id);
                preparedStmt1.execute();
                System.out.println("DONE");
                break;
            }

            case "accountantChangeScolarship": {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String recordBookId = (String) handler.read();
                String newValue = (String) handler.read();
                String query0 = "SELECT * FROM scholarship.Student INNER JOIN scholarship.Scholarship on IdScholarship=Student.Scholarship_idScholarship WHERE Student.recordBook = ?;";
                PreparedStatement preparedStmt = connection.prepareStatement(query0);
                preparedStmt.setString(1, recordBookId);
                ResultSet res = preparedStmt.executeQuery();
                res.next();
                Integer stud_id = res.getInt("idScholarship");
                String query = "UPDATE scholarship.Scholarship SET Scholarship.date= ? WHERE idScholarship= ? ;";
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
                String query = "SELECT Student.*, Scholarship.* FROM scholarship.Student INNER JOIN scholarship.Scholarship on IdScholarship=Student.Scholarship_idScholarship;";
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
                String query = "SELECT avgMark, COUNT(*) as count from scholarship.Session group by avgMark;";
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

                String query = "SELECT Student.*, Scholarship.*, Session.* FROM scholarship.Student INNER JOIN scholarship.Scholarship on IdScholarship=Student.Scholarship_idScholarship INNER JOIN scholarship.Session on IdSession=Student.session;";
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

            case "deanAddStudent": {
                System.out.println("Add student");

                ArrayList student = (ArrayList) handler.read();
                ArrayList exam1 = (ArrayList) handler.read();
                ArrayList exam2 = (ArrayList) handler.read();
                ArrayList exam3 = (ArrayList) handler.read();
                ArrayList exam4 = (ArrayList) handler.read();

                float avgm = (Integer.valueOf((String) exam1.get(1)) + Integer.valueOf((String) exam2.get(1)) +
                        Integer.valueOf((String) exam3.get(1)) + Integer.valueOf((String) exam4.get(1))) / 4;

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                System.out.println("Scholarship");

                String count999 = "SELECT COUNT(*) FROM scholarship.Scholarship";
                Statement statement999 = connection.createStatement();
                ResultSet count_acc_set999 = statement999.executeQuery(count999);
                count_acc_set999.next();
                int next_id999 = count_acc_set999.getInt("COUNT(*)");
                String query999 = "INSERT INTO scholarship.Scholarship (idScholarship, type, date, amount) VALUES (?, 'Обычная', '01.01.2020', 115)";
                PreparedStatement preparedStmt999 = connection.prepareStatement(query999);
                preparedStmt999.setInt(1, next_id999 + 1);
                preparedStmt999.execute();

                System.out.println("Session");

                String count = "SELECT COUNT(*) FROM scholarship.Session";
                Statement statement = connection.createStatement();
                ResultSet count_acc_set = statement.executeQuery(count);
                count_acc_set.next();
                int next_id = count_acc_set.getInt("COUNT(*)");
                String query = "INSERT INTO scholarship.Session (idSession, avgMark) VALUES (?, ?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, next_id + 1);
                preparedStmt.setFloat(2, avgm);
                preparedStmt.execute();

                System.out.println(exam1);

                String count1 = "SELECT COUNT(*) FROM scholarship.Exam";
                Statement statement1 = connection.createStatement();
                ResultSet count_acc_set1 = statement1.executeQuery(count1);
                count_acc_set1.next();
                int next_id1 = count_acc_set1.getInt("COUNT(*)");
                String query1 = "INSERT INTO scholarship.Exam (idExam, subject, date, mark, session) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt1 = connection.prepareStatement(query1);
                preparedStmt1.setInt(1, next_id1 + 1);
                preparedStmt1.setString(2, (String) exam1.get(2));
                preparedStmt1.setString(3, (String) exam1.get(0));
                preparedStmt1.setInt(4, Integer.valueOf((String) exam1.get(1)));
                preparedStmt1.setInt(5, next_id + 1);
                preparedStmt1.execute();

                System.out.println(exam2);

                String count2 = "SELECT COUNT(*) FROM scholarship.Exam";
                Statement statement2 = connection.createStatement();
                ResultSet count_acc_set2 = statement1.executeQuery(count2);
                count_acc_set2.next();
                int next_id2 = count_acc_set2.getInt("COUNT(*)");
                String query2 = "INSERT INTO scholarship.Exam (idExam, subject, date, mark, session) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt2 = connection.prepareStatement(query2);
                preparedStmt2.setInt(1, next_id2 + 1);
                preparedStmt2.setString(2, (String) exam2.get(2));
                preparedStmt2.setString(3, (String) exam2.get(0));
                preparedStmt2.setInt(4, Integer.valueOf((String) exam2.get(1)));
                preparedStmt2.setInt(5, next_id + 1);
                preparedStmt2.execute();

                System.out.println(exam3);

                String count3 = "SELECT COUNT(*) FROM scholarship.Exam";
                Statement statement3 = connection.createStatement();
                ResultSet count_acc_set3 = statement1.executeQuery(count3);
                count_acc_set3.next();
                int next_id3 = count_acc_set3.getInt("COUNT(*)");
                String query3 = "INSERT INTO scholarship.Exam (idExam, subject, date, mark, session) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt3 = connection.prepareStatement(query3);
                preparedStmt3.setInt(1, next_id3 + 1);
                preparedStmt3.setString(2, (String) exam3.get(2));
                preparedStmt3.setString(3, (String) exam3.get(0));
                preparedStmt3.setInt(4, Integer.valueOf((String) exam3.get(1)));
                preparedStmt3.setInt(5, next_id + 1);
                preparedStmt3.execute();

                System.out.println(exam4);

                String count4 = "SELECT COUNT(*) FROM scholarship.Exam";
                Statement statement4 = connection.createStatement();
                ResultSet count_acc_set4 = statement1.executeQuery(count4);
                count_acc_set4.next();
                int next_id4 = count_acc_set4.getInt("COUNT(*)");
                String query4 = "INSERT INTO scholarship.Exam (idExam, subject, date, mark, session) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt4 = connection.prepareStatement(query3);
                preparedStmt4.setInt(1, next_id4 + 1);
                preparedStmt4.setString(2, (String) exam4.get(2));
                preparedStmt4.setString(3, (String) exam4.get(0));
                preparedStmt4.setInt(4, Integer.valueOf((String) exam4.get(1)));
                preparedStmt4.setInt(5, next_id + 1);
                preparedStmt4.execute();


                String count5 = "SELECT COUNT(*) FROM scholarship.Student";
                Statement statement5 = connection.createStatement();
                ResultSet count_acc_set5 = statement5.executeQuery(count5);
                count_acc_set5.next();
                int next_id5 = count_acc_set5.getInt("COUNT(*)");
                String query5 = "INSERT INTO scholarship.Student(`idStudent`,`name`,`lastName`,`patronymic`,`group`,`recordBook`,`bankCard`,`login`,`password`,`Scholarship_idScholarship`,`faculty`,`session`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt5 = connection.prepareStatement(query5);
                preparedStmt5.setInt(1, next_id5 + 1);
                preparedStmt5.setString(2, (String) student.get(1));
                preparedStmt5.setString(3, (String) student.get(0));
                preparedStmt5.setString(4, (String) student.get(2));
                preparedStmt5.setString(5, (String) student.get(3));
                preparedStmt5.setString(6, (String) student.get(4));
                preparedStmt5.setString(7, (String) student.get(5));
                preparedStmt5.setString(8, ((String)student.get(0)).toLowerCase());
                preparedStmt5.setString(9, ((String)student.get(1)).toLowerCase());
                preparedStmt5.setInt(10, next_id999 + 1);
                preparedStmt5.setInt(11, (Integer) student.get(6));
                preparedStmt5.setInt(12, next_id + 1);
                preparedStmt5.execute();

                System.out.println(student);
            }
        }
    }

    public static float get_avg_mark(ArrayList marks) {
        float avg = 0;
        int num = 0;
        for (Object mark : marks) {
            avg += (Integer) mark;
            num += 1;
        }
        return (avg / num);
    }
}
